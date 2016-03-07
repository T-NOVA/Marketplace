function generateUIDNotMoreThan1million() {
    return ("0000" + (Math.random()*Math.pow(36,4) << 0).toString(36)).slice(-4)
}

angular.module('dashboard').controller('ImageListCtrl', ['Restangular', '$scope', '$rootScope', 'ModalService','alertService', ImageListCtrl]);
angular.module('dashboard').controller('ImageUploadCtrl', ['$scope', '$cookies', 'Upload', ImageUploadCtrl]);

function ImageListCtrl(Restangular, $scope, $rootScope, ModalService) {

    $scope.images = {};

    $scope.loadImages = function () {
        $rootScope.root_loading = true;
        Restangular.all('vnfs/images').getList().then(
            function (response) {
                $scope.images = response;
                console.log("GetImageList " + response.length + " Images found");
                $rootScope.root_loading = false;
            }, function (response) {
                console.log("GetImageList error with status code " + response.status);
                console.log("GetImageList error message: " + response.data.detail);
                $rootScope.root_loading = false;
            });
    };

    $scope.deleteImage = function (image_name) {
        $rootScope.root_loading = true;
        Restangular.one("vnfs/images", image_name).remove().then(
            function () {
                $rootScope.root_loading = false;
                console.log("Image  " + image_name + " has been successfully deleted");
                $scope.loadImages();

                alertService.add('success', "Image  " + image_name + " has been successfully deleted");
            }, function (response) {
                console.log("DeleteImage error with status code " + response.status);
                console.log("DeleteImage error message: " + response.data);
                $rootScope.root_loading = false;

                alertService.add('danger', "Delete Image Error, " + response.data);

            });
    };

    $scope.ImageDeleteConfirm = function (image_name) {

        //var confirm = $mdDialog.confirm()
        //    .parent(angular.element(document.body))
        //    .title('VNF Delete')
        //    .content('Are you sure you want to delete VNF ' + vnf_name + '?')
        //    .ok('YES')
        //    .cancel('NO');
        //$mdDialog.show(confirm).then(function () {
        //    $scope.deleteVNF(id, vnf_name);
        //});
        $scope.deleteImage(image_name);
    };

    $scope.init = function () {

         $scope.loadImages();

    };

    $scope.init();


    $scope.showImageUploadAModal = function() {


         ModalService.showModal({
          templateUrl: "/static/dashboard/templates/modals/image_upload.html",
             controller: function () {
                 this.closeModal = function () {
                     $scope.loadImages();
                     close(); // close, but give 500ms for bootstrap to animate
                 };
             },
          controllerAs : "ImageUploadCtrl"
        }).then(function(modal) {
          // only called on success...
             modal.element.modal();
        }).catch(function(error) {
          // error contains a detailed error message.
          console.log(error);
        });

  };


}


function ImageUploadCtrl($scope, $cookies, Upload) {
    // upload later on form submit or something similar
    $scope.submit = function() {
      if (form.file.$valid && $scope.file) {
        $scope.upload($scope.file);
      }
    };

    $scope.image_types = ['ami', 'ari', 'aki', 'vhd', 'vmdk', 'raw', 'qcow2', 'vdi', 'iso'];
    $scope.image_file_type = 'qcow2';
    $scope.image_upload_filename = '';
    $scope.image_upload_progress = 0;
    $scope.image_upload_completed = false;
    $scope.image_upload_failed= false;

    // upload on file select or drop
    $scope.upload = function (file) {
        $scope.image_upload_filename = file.name;

        Upload.upload({
            url: '/NFS/files',
            method: 'POST',
            data: {
                file: file,
            },
            headers: {
                "MD5SUM":$scope.image_md5sum,
                "Image-Type":$scope.image_file_type,
                "Provider-ID":4
            }
            //headers: {Authorization: 'JWT ' + $cookies.get('token')}
            //url: '/vnfs/images/upload/',
            //data: {file: file, md5sum:$scope.image_md5sum, image_type:$scope.image_file_type},
            //headers: {Authorization: 'JWT ' + $cookies.get('token')}

        }).then(function (resp) {
            $scope.image_upload_completed = true;
            console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
        }, function (resp) {
            $scope.image_upload_failed= true;
            $scope.image_upload_failed_message = resp.data.detail;
            console.log('Error status: ' + resp.status);
        }, function (evt) {
            $scope.image_upload_progress = parseInt(100.0 * evt.loaded / evt.total);
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
        });
    };
    // for multiple files:
    //$scope.uploadFiles = function (files) {
    //  if (files && files.length) {
    //    for (var i = 0; i < files.length; i++) {
    //      Upload.upload({..., data: {file: files[i]}, ...})...;
    //    }
    //    // or send them all together for HTML5 browsers:
    //    Upload.upload({..., data: {file: files}, ...})...;
    //  }
    //}
}