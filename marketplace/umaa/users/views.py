__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from models import User
from django.contrib.auth.models import Group, Permission
from django.http import Http404

from rest_framework.permissions import BasePermission
from serializers import UserSerializer, UserWithPasswordSerializer
from serializers import UserProfileSerializer, UserWithPasswordProfileSerializer
from serializers import GroupSerializer, PermissionCodenameSerializer

from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from rest_framework_jwt.utils import jwt_payload_handler
from django_countries import countries
import requests


UNAUTHORIZED_MSG = {"detail": "You do not have permission to perform this action."}


class IsAuthenticated(BasePermission):
    def has_permission(self, request, view):
        return request.user and request.user.is_authenticated()


class UserList(APIView):
    permission_classes = (IsAuthenticated,)

    def get(self, request):
        """
        Return the User's list
        """

        if request.user.has_perm('umaa.list_all_users'):
            users = User.objects.all()
            serializer = UserSerializer(users, many=True)
            return Response(serializer.data)
        else:
            return Response(UNAUTHORIZED_MSG, status=status.HTTP_401_UNAUTHORIZED)

    def post(self, request):
        """
        Create a new User
        """

        if request.user.has_perm('umaa.create_user'):
            serializer = UserWithPasswordSerializer(data=request.DATA)
            if serializer.is_valid():
                user = serializer.save()
                if 'password' in request.DATA:
                    password = request.DATA['password']
                    user.set_password(password)
                    user.save()

                    # create SLA provider (SLA module)
                    if user.groups.filter(name__in=['Service Provider', 'Function Provider']).exists():
                        sla_response = requests.post('http://sla.docker:9040/providers', auth=('user', 'password'), json={"uuid": user.id, "name": user.username})
                        if sla_response.status_code == 201:
                            print 'SLA provider created %s-%s' % (user.id, user.username)
                        else:
                            print 'SLA provider creation failed %s-%s' % (user.id, user.username)

                return Response(serializer.data, status=status.HTTP_201_CREATED)
            else:
                return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(UNAUTHORIZED_MSG, status=status.HTTP_401_UNAUTHORIZED)


class UserRegister(APIView):

    def post(self, request):
        """
        Create a new User
        """

        serializer = UserWithPasswordSerializer(data=request.DATA)
        if serializer.is_valid():
            user = serializer.save()
            if 'password' in request.DATA:
                password = request.DATA['password']
                user.set_password(password)
                user.save()

                try:
                    # create SLA provider (SLA module)
                    if user.groups.filter(name__in=['Service Provider', 'Function Provider']).exists():
                        sla_response = requests.post('http://sla.docker:9040/providers', auth=('user', 'password'), json={"uuid": user.id, "name": user.username})
                        if sla_response.status_code == 201:
                            print 'SLA provider created %s-%s' % (user.id, user.username)
                        else:
                            print 'SLA provider creation failed %s-%s' % (user.id, user.username)
                except:
                    print 'SLA provider creation failed %s-%s' % (user.id, user.username)

            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class UserDetails(APIView):
    permission_classes = (IsAuthenticated,)

    def get_object(self, pk):
        try:
            return User.objects.get(pk=pk)
        except User.DoesNotExist:
            raise Http404

    def get(self, request, pk):
        user = self.get_object(pk)
        if request.user.has_perm('umaa.list_all_users') or request.user == user:
            serializer = UserSerializer(user, many=False)
            return Response(serializer.data)
        else:
            return Response(UNAUTHORIZED_MSG, status=status.HTTP_401_UNAUTHORIZED)

    def put(self, request, pk):
        user = self.get_object(pk)
        if request.user.has_perm('umaa.edit_user') or request.user == user:
            serializer = UserSerializer(user, data=request.DATA, partial=True)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data, status=status.HTTP_200_OK)
            else:
                return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(UNAUTHORIZED_MSG, status=status.HTTP_401_UNAUTHORIZED)

    def patch(self, request, pk):
        user = self.get_object(pk)
        if request.user.has_perm('umaa.edit_user') or request.user == user:
            serializer = UserWithPasswordSerializer(user, data=request.DATA, partial=True)
            if serializer.is_valid():
                serializer.save()
                if 'password' in request.DATA:
                    password = request.DATA['password']
                    user.set_password(password)
                    user.save()
                return Response(serializer.data, status=status.HTTP_200_OK)
            else:
                return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(UNAUTHORIZED_MSG, status=status.HTTP_401_UNAUTHORIZED)

    def delete(self, request, pk):
        if request.user.has_perm('umaa.delete_user'):
            user = self.get_object(pk)
            user.delete()
            return Response(status=status.HTTP_204_NO_CONTENT)
        else:
            return Response(UNAUTHORIZED_MSG, status=status.HTTP_401_UNAUTHORIZED)


class UserDetailsGroups(APIView):
    permission_classes = (IsAuthenticated,)

    def get_object(self, pk):
        try:
            return User.objects.get(pk=pk)
        except User.DoesNotExist:
            raise Http404

    def get(self, request, pk):
        user = self.get_object(pk)
        if request.user.has_perm('umaa.list_all_users') or request.user == user:
            serializer = GroupSerializer(user.groups, many=True)
            return Response(serializer.data)
        else:
            return Response(UNAUTHORIZED_MSG, status=status.HTTP_401_UNAUTHORIZED)


class UserDetailsPermissions(APIView):
    permission_classes = (IsAuthenticated,)

    def get_object(self, pk):
        try:
            return User.objects.get(pk=pk)
        except User.DoesNotExist:
            raise Http404

    def get(self, request, pk):
        user = self.get_object(pk)
        if request.user.has_perm('umaa.list_all_users') or request.user == user:
            permissions = Permission.objects.filter(group__user=user)
            # serializer = user.user_permissions.all() | Permission.objects.filter(group__user=user)
            serializer = PermissionCodenameSerializer(permissions, many=True)
            return Response(serializer.data)
        else:
            return Response(UNAUTHORIZED_MSG, status=status.HTTP_401_UNAUTHORIZED)


class UserProfile(APIView):
    permission_classes = (IsAuthenticated,)

    def get(self, request):
        user = request.user
        serializer = UserProfileSerializer(user, many=False)
        return Response(serializer.data)

    def put(self, request):
        user = request.user
        serializer = UserProfileSerializer(user, data=request.DATA)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request):
        user = request.user
        serializer = UserWithPasswordProfileSerializer(user, data=request.DATA, partial=True)
        if serializer.is_valid():
            serializer.save()
            if 'password' in request.DATA:
                password = request.DATA['password']
                user.set_password(password)
                user.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class UserProfileGroup(APIView):
    permission_classes = (IsAuthenticated,)

    def get(self, request):
        user = request.user
        serializer = GroupSerializer(user.groups, many=True)
        return Response(serializer.data)


class UserProfilePermissions(APIView):
    permission_classes = (IsAuthenticated,)

    def get(self, request):
        user = request.user
        permissions = Permission.objects.filter(group__user=user)
        serializer = PermissionCodenameSerializer(permissions, many=True)
        return Response(serializer.data)


class GroupList(APIView):
    permission_classes = (IsAuthenticated,)

    def get(self, request):
            groups = Group.objects.all()
            serializer = GroupSerializer(groups, many=True)
            return Response(serializer.data)


class PermissionsList(APIView):
    permission_classes = (IsAuthenticated,)

    def get(self, request):
            permissions = Permission.objects.all()
            serializer = PermissionCodenameSerializer(permissions, many=True)
            return Response(serializer.data)


class CountryList(APIView):
    permission_classes = (IsAuthenticated,)

    def get(self, request):
        country_list = []
        for country in countries:
            country_list.append({'code': country[0], 'name': country[1]})
        return Response(country_list, status=status.HTTP_200_OK)