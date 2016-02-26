__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from django.contrib.auth.models import Group, Permission, ContentType
from users.models import User

from rest_framework import serializers


class PermissionCodenameSerializer(serializers.ModelSerializer):

    class Meta:
        model = Permission

    def to_representation(self, obj):
        return "%s.%s" % (obj.content_type.app_label, obj.codename)


class PermissionSerializer(serializers.ModelSerializer):
    codename = serializers.SerializerMethodField()
    description = serializers.SerializerMethodField()

    class Meta:
        model = Permission
        fields = ('codename', 'description')

    def get_codename(self, obj):
        return "%s.%s" % (obj.content_type.app_label, obj.codename)

    def get_description(self, obj):
        return "%s" % obj.name


class GroupSerializer(serializers.ModelSerializer):
    #permissions = PermissionCodenameSerializer(many=True)

    class Meta:
        model = Group
        fields = ('id', 'name')


class UserSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ('id', 'username', 'groups', 'company_name', 'first_name', 'last_name', 'email', 'country', 'city', 'address')


class UserWithPasswordSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ('id', 'username', 'password', 'groups', 'company_name', 'first_name', 'last_name', 'email', 'country', 'city', 'address')
        write_only_fields = ('password',)


class UserProfileSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ('id', 'username', 'groups', 'company_name', 'first_name', 'last_name', 'email', 'country', 'city', 'address')
        read_only_fields = ('username', 'groups',)


class UserWithPasswordProfileSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ('id', 'username', 'groups', 'company_name', 'first_name', 'last_name', 'email', 'country', 'city', 'address')
        read_only_fields = ('username', 'groups',)
        write_only_fields = ('password',)
