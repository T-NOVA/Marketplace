# Marketplace User Management, Authentication and Access Control

The root prefixes for this microservice are `/user-management/` for the user/profile management and `/auth/` for the authentication.

The microservice use these YAML files to initialize the permissions, groups and users:
- init_users.yml
- init_groups.yml
- init_permissions.yml

* Every partner can define the required permissions for own microservices.

## Authentication

Methods

```
POST /auth/
```

### User authentication request

```
POST /auth/
```

Request Body:

```
{"username":"admin", "password":"123456"}
```

Response Body:

```
{"token":" eyJhbGciOiJIUzI1N….iIsInR5cCI6IkpXV "}
```

Request Example with JWT authentication

```
GET /user-management/users/

Content-Type: application/json
Authentication: JWT eyJhbGciOiJIUzI1N….iIsInR5cCI6IkpXV
```

### Token Metadata

```json
{
    'username': 'admin',
    'user_id': 1,
    'email': 'g.alexiou@pasiphae.eu',
    'company_name': 'TEIC',
    'exp': 1436620914
}
```

## User Management

Methods

```
GET /user-management/users/
POST /user-management/users/

GET /user-management/users/{pk}/
PUT /user-management/users/{pk}/
PATCH /user-management/users/{pk}/
DELETE /user-management/users/{pk}/
GET /user-management/users/{pk}/groups/
GET /user-management/users/{pk}/permissions/

GET /user-management/profile/
PUT /user-management/profile/
PATCH /user-management/profile/
GET /user-management/profile/groups/
GET /user-management/profile/permissions/

GET /user-management/groups/
GET /user-management/countries/
```

### Listing all Users

This method return the user's list.

```
GET /user-management/users/
```

Response Body:

```json
[
  {
    "id": 1,
    "username": "admin",
    "groups": [
      3
    ],
    "company_name": "TEIC",
    "first_name": "",
    "last_name": "",
    "email": "g.alexiou@pasiphae.eu",
    "country": "GR",
    "city": "",
    "address": ""
  },
  {
    "id": 2,
    "username": "customer1",
    "groups": [
      1
    ],
    "company_name": "TEIC",
    "first_name": "",
    "last_name": "",
    "email": "customer1@t-nova.eu",
    "country": "GR",
    "city": "",
    "address": ""
  }
]
```

### Create New User

This method creates a new user.

```
POST /user-management/users/
```

Request Body:

```json
{
  "username": "new_user",
  "password": "123456",
  "groups": [
    3
  ],
  "company_name": "TEIC",
  "first_name": "",
  "last_name": "",
  "email": "g.alexiou@pasiphae.eu",
  "country": "GR",
  "city": "",
  "address": ""
}
```

Response Body:

```json
{
  "id": 10,
  "username": "new_user",
  "groups": [
    3
  ],
  "company_name": "TEIC",
  "first_name": "",
  "last_name": "",
  "email": "g.alexiou@pasiphae.eu",
  "country": "GR",
  "city": "",
  "address": ""
}
```

### Get User's info

This method return the user info.

```
GET /user-management/users/10/
```

Response Body:

```json
{
  "id": 10,
  "username": "new_user",
  "groups": [
    3
  ],
  "company_name": "TEIC",
  "first_name": "",
  "last_name": "",
  "email": "g.alexiou@pasiphae.eu",
  "country": "GR",
  "city": "",
  "address": ""
}
```

### Edit User's info

This method change user's info (PUT Method)

```
PUT /user-management/users/10/
```

Request Body:

```json
{
  "id": 10,
  "username": "new_user",
  "groups": [
    3
  ],
  "company_name": "TEIC",
  "first_name": "George",
  "last_name": "Alexiou",
  "email": "g.alexiou@pasiphae.eu",
  "country": "GR",
  "city": "Heraklion",
  "address": "Estaurmenos"
}
```

This method change user's info partially (PATCH Method)

```
PATCH /user-management/users/10/
```

Request Body:

```json
{
  "first_name": "George",
  "last_name": "Alexiou",
  "city": "Heraklion",
  "address": "Estaurmenos"
}
```

### Delete User

This method delete a user

```
DELETE /user-management/users/10/
```

### Get User's groups

This method return the user groups.

```
GET /user-management/users/10/groups/
```

Response Body:

```json
[
    {
        "id": 10,
        "name": "Customer",
        "permissions": [
            "umaa.edit_own_profile",
            "umaa.view_own_profile"
        ]
    }
]
```

### Get User's permissions

This method return the user permissions.

```
GET /user-management/users/10/permissions/
```

Response Body:

```json
[
    "umaa.edit_own_profile",
    "umaa.view_own_profile"
]
```

### Get User's profile

This method return the user's profile.

```
GET /user-management/profile/
```

Response Body:

```json
{
  "id": 10,
  "username": "new_user",
  "groups": [
    3
  ],
  "company_name": "TEIC",
  "first_name": "George",
  "last_name": "Alexiou",
  "email": "g.alexiou@pasiphae.eu",
  "country": "GR",
  "city": "Heraklion",
  "address": "Estaurmenos"
}
```

### Edit User's profile

This method change user's profile info (PUT Method)

```
PUT /user-management/profile/
```

Request Body:

```json
{
  "id": 10,
  "username": "new_user",
  "groups": [
    3
  ],
  "company_name": "TEIC",
  "first_name": "George",
  "last_name": "Alexiou",
  "email": "g.alexiou@pasiphae.eu",
  "country": "GR",
  "city": "Heraklion",
  "address": "Estaurmenos"
}
```

This method change user's profile info partially (PATCH Method)

```
PATCH /user-management/profile/
```

Request Body:

```json
{
  "first_name": "George",
  "last_name": "Alexiou",
  "city": "Heraklion",
  "address": "Estaurmenos"
}
```

### Get User's profile groups

This method return the user groups.

```
GET /user-management/profile/groups/
```

Response Body:

```json
[
    {
        "id": 10,
        "name": "Customer",
        "permissions": [
            "umaa.edit_own_profile",
            "umaa.view_own_profile"
        ]
    }
]
```

### Get User's profile permissions

This method return the user permissions.

```
GET /user-management/profile/permissions/
```

Response Body:

```json
[
    "umaa.edit_own_profile",
    "umaa.view_own_profile"
]
```

### Get Available Groups

This method return the available groups.

```
GET /user-management/groups/
```

Response Body:

```json
[
    {"id":3,"name":"Administrator"},
    {"id":1,"name":"Customer"},
    {"id":4,"name":"Function Provider"},
    {"id":2,"name":"Service Provider"}
]
```

### Get Available Countries

This method return the available countries.

```
GET /user-management/countries/
```

Response Body:

```json
[  
   {code":"AF","name":"Afghanistan"},
   ...
   {"code":"ZM","name":"Zambia"},
   {"code":"ZW","name":"Zimbabwe"}
]
```