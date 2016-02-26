__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from django.contrib.auth.models import ContentType, Permission, Group
from dynamic_initial_data.base import BaseInitialData
from manager_utils import upsert, get_or_none
from users.models import User
import aa_parser
import requests


class InitialData(BaseInitialData):
    def update_initial_data(self):

        # create permissions
        permissions = aa_parser.get_permissions()
        for permission in permissions:
            content_type_obj, created = upsert(ContentType.objects, app_label=permission['content_type'], name='user', model='user')
            if created:
                print 'New content type created, "%s"' % content_type_obj.app_label
            perm_obj, created = upsert(Permission.objects, name=permission['description'], content_type=content_type_obj, codename=permission['codename'])
            if created:
                print 'New permission created, "%s"' % perm_obj.codename

        # create groups
        groups = aa_parser.get_groups()
        for group, group_permissions in groups.iteritems():
            group_obj, created = upsert(Group.objects, name=group)
            if created:
                print 'New group created, "%s"' % group_obj.name
            for group_permission in group_permissions:
                content_type = get_or_none(ContentType.objects, model='user', app_label=group_permission.split('.')[0])
                perm = get_or_none(Permission.objects, content_type=content_type, codename=group_permission.split('.')[1])
                if perm:
                    group_obj.permissions.add(perm)
                    print 'Permission "%s" assigned to group "%s"' % (perm.codename, group_obj.name)

        # create users
        users = aa_parser.get_users()
        for user in users:
            user_obj, created = upsert(User.objects, username=user['username'], email=user['email'], company_name=user['company_name'])
            if created:
                user_obj.set_password(user['password'])
                user_obj.save()
                print 'New user created, "%s"' % user_obj.username
            for user_group in user['groups']:
                g = get_or_none(Group.objects, name=user_group)
                if g:
                    user_obj.groups.add(g)
                    print 'Group "%s" assigned to user "%s"' % (g.name, user_obj.username)

            if user_obj.groups.filter(name__in=['Service Provider', 'Function Provider']).exists():
                try:
                    sla_response = requests.post('http://sla.docker:9040/providers', auth=('user', 'password'), json={"uuid": user_obj.id, "name": user_obj.username})
                    if sla_response.status_code == 201:
                        print 'SLA provider created %s-%s' % (user_obj.id, user_obj.username)
                    else:
                        print 'SLA provider creation failed %s-%s' % (user_obj.id, user_obj.username)
                except:
                    print 'SLA provider creation failed SLA MODULE TIMEOUT......'