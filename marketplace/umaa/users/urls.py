__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from django.conf.urls import patterns, url
from views import UserList, UserDetails, UserDetailsGroups, UserDetailsPermissions, GroupList, PermissionsList
from views import UserProfile, UserProfileGroup, UserProfilePermissions
from views import CountryList
from views import UserRegister

urlpatterns = patterns(
    'users.views',

    url(r'^users/$', UserList.as_view(), name='user_list'),
    url(r'^groups/$', GroupList.as_view(), name='group_list'),
    #url(r'^permissions/$', PermissionsList.as_view(), name='permissions_list'),

    url(r'^countries/$', CountryList.as_view(), name='country_list'),

    url(r'^users/(?P<pk>[0-9]+)/$', UserDetails.as_view(), name='user_details'),
    url(r'^users/(?P<pk>[0-9]+)/groups/$', UserDetailsGroups.as_view(), name='user_details_groups'),
    url(r'^users/(?P<pk>[0-9]+)/permissions/$', UserDetailsPermissions.as_view(), name='user_details_permissions'),

    url(r'^profile/$', UserProfile.as_view(), name='user_profile'),
    url(r'^profile/groups/$', UserProfileGroup.as_view(), name='user_profile_groups'),
    url(r'^profile/permissions/$', UserProfilePermissions.as_view(), name='user_profile_permissions'),

    url(r'^register/$', UserRegister.as_view(), name='user_register'),
)