__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from yaml import load

try:
    from yaml import CLoader as Loader
except ImportError:
    from yaml import Loader


def get_permissions():
    stream = file('init_permissions.yml', 'r')
    data = load(stream, Loader=Loader)

    permissions_table = []

    for module, permissions in data.iteritems():
        for permission in permissions:
            for name, description in permission.iteritems():
                permissions_table.append({'content_type': module, 'codename': name, 'description': description})

    return permissions_table


def print_permissions():
    permissions = get_permissions()
    for permission in permissions:
        print '%s.%s, %s' % (permission['content_type'], permission['codename'], permission['description'])


def get_groups():
    stream = file('init_groups.yml', 'r')
    data = load(stream, Loader=Loader)

    # get global permissions
    global_permissions = data['Global']
    del data['Global']

    # append global permission
    for group, permissions in data.iteritems():
        if permissions is None:
            data[group] = global_permissions
        else:
            data[group].extend(global_permissions)

    return data


def print_groups():
    groups = get_groups()
    for group, permissions in groups.iteritems():
        print '%s: %s' % (group, permissions)


def get_users():
    stream = file('init_users.yml', 'r')
    data = load(stream, Loader=Loader)
    return data


def print_users():
    for user in get_users():
        print user