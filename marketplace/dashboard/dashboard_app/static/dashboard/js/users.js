angular.module('dashboard').controller('UsersListCtrl', ['Restangular', '$scope', '$rootScope', UsersListCtrl]);
angular.module('dashboard').controller('UserCreateCtrl', ['Restangular', '$scope', '$rootScope', '$state', UserCreateCtrl]);
angular.module('dashboard').controller('UserEditCtrl', ['Restangular', '$scope', '$rootScope', '$stateParams', UserEditCtrl]);
angular.module('dashboard').controller('UserProfileCtrl', ['Restangular', '$scope', '$rootScope', UserProfileCtrl]);


function UsersListCtrl(Restangular, $scope, $rootScope) {

    $scope.loading_users = false;
    $scope.users = {};

    $scope.loadUsers = function () {
        $scope.loading_users = true;
        Restangular.all('user-management/users').getList().then(
            function (response) {
                $scope.users = response;
                console.log("GetUserList " + response.length + " users found");
                $scope.loading_users = false;
            }, function (response) {
                console.log("GetUserList error with status code " + response.status);
                console.log("GetUserList error message: " + response.data.detail);
                $scope.loading_users = false;
            });
    };

    $scope.deleteUser = function (id, username) {
        $scope.loading_users = true;
        Restangular.one("user-management/users", id).remove().then(
            function () {
                $scope.loading_users = false;
                console.log("User " + username + " has been successfully deleted");
                $scope.loadUsers();
            }, function (response) {
                console.log("DeleteUser error with status code " + response.status);
                console.log("DeleteUser error message: " + response.data.detail);
                $scope.loading_users = false;

            });
    };

    $scope.userDeleteConfirm = function (id, username) {

        //@TODO add confirm modal

        //var confirm = $mdDialog.confirm()
        //    .parent(angular.element(document.body))
        //    .title('User Delete')
        //    .content('Are you sure you want to delete user ' + username + '?')
        //    .ok('YES')
        //    .cancel('NO');
        //$mdDialog.show(confirm).then(function () {
        //    $scope.deleteUser(id, username);
        //});
        $scope.deleteUser(id, username);
    };

    $scope.init = function () {

         $scope.loadUsers();

    };

    $scope.init();

}


function UserEditCtrl(Restangular, $scope, $rootScope, $stateParams) {

    $scope.loading_edit_user = false;
    $scope.user = {};

    $scope.loadUser = function () {
        $scope.loading_edit_user = true;
        Restangular.one("user-management/users", $stateParams.userID).get().then(
            function (response) {
                $scope.user = response;
                $scope.loading_edit_user = false;
                console.log("GetUser " + response.username + " has been successfully loaded");
            }, function (response) {
                console.log("GetUser error with status code " + response.status);
                console.log("GetUser error message: " + response.data.detail);
                $scope.loading_edit_user = false;
            });
    };

    $scope.updateUser = function () {
        $scope.loading_edit_user = true;
        $scope.user.put().then(
            function (response) {
                $scope.loading_edit_user = false;
                console.log("UpdateUser " + response.username + " has been successfully completed");
                //$alert({title: '', content: "User profile updated!", placement: 'bot-right', type: 'info', show: true, duration:3});
                //$mdToast.show($mdToast.simple().content("User profile updated!").position('top right').hideDelay(3000));
            }, function (response) {
                console.log("GetUser error with status code " + response.status);
                console.log("GetUser error message: " + response.data.detail);
                $scope.loading_edit_user = false;
                //$alert({title: '', content: "User profile update failed.", placement: 'bot-right', type: 'info', show: true, duration:3});
                //$mdToast.show($mdToast.simple().content("User profile update failed").position('top right').hideDelay(3000));
            });
    };

    $scope.init = function () {
        $scope.loadUser();
    };

    $scope.init();

}

function UserCreateCtrl(Restangular, $scope, $rootScope, $state) {

    $scope.loading_create_user = false;
    $scope.user = {};

    $scope.createUser = function () {
        $scope.loading_create_user = true;
        Restangular.all('user-management/users').post($scope.user).then(
            function (response) {
                $scope.loading_create_user = false;
                console.log("CreateUser " + response.username + " has been successfully completed");
                //$alert({title: '', content: "User successfully created!", placement: 'bot-right', type: 'success', show: true, duration:3});
                $state.go('index.users.list');
            }, function (response) {
                console.log("CreateUser error with status code " + response.status);
                console.log("CreateUser error message: " + response.data.detail);
                $scope.loading_create_user = false;
                //$alert({title: '', content: "Failed to create user.", placement: 'bot-right', type: 'danger', show: true, duration:3});
            });
    };

    $scope.init = function () {

    };

    $scope.init();

}


function UserProfileCtrl(Restangular, $scope, $rootScope) {

    $scope.loading_user_profile = false;
    $scope.user = {};

    $scope.loadUserProfile = function () {
        $scope.loading_user_profile = true;
        Restangular.one("user-management/profile").get().then(
            function (response) {
                $scope.user = response;
                $scope.loading_user_profile = false;
                console.log("GetUserProfile " + response.username + " has been successfully loaded");
            }, function (response) {
                console.log("GetUserProfile error with status code " + response.status);
                console.log("GetUserProfile error message: " + response.data.detail);
                $scope.loading_user_profile = false;
            });
    };

    $scope.updateUserProfile = function () {
        $scope.loading_user_profile = true;
        Restangular.one("user-management/profile").customPUT($scope.user).then(
            function (response) {
                $scope.loading_user_profile = false;
                console.log("UpdateUser " + response.username + " has been successfully completed");
                //$alert({title: '', content: "User profile updated!", placement: 'bot-right', type: 'info', show: true, duration:3});
                //$mdToast.show($mdToast.simple().content("User profile updated!").position('top right').hideDelay(3000));
            }, function (response) {
                console.log("GetUser error with status code " + response.status);
                console.log("GetUser error message: " + response.data.detail);
                $scope.loading_user_profile = false;
                //$alert({title: '', content: "User profile update failed.", placement: 'bot-right', type: 'danger', show: true, duration:3});
                //$mdToast.show($mdToast.simple().content("User profile update failed").position('top right').hideDelay(3000));
            });
    };

    $scope.init = function () {
        $scope.loadUserProfile();
    };

    $scope.init();

}