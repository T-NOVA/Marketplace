(function () {
    'use strict';

    angular.module('dashboard').factory('alertService', alertService);

    alertService.$inject = ['$timeout'];

    function alertService($timeout) {
        var service = {
            add: add,
            closeAlert: closeAlert,
            closeAlertIdx: closeAlertIdx,
            clear: clear,
            get: get
        },
        alerts = [];

        return service;

        //function add(type, msg) {
        //    return alerts.push({
        //        type: type,
        //        msg: msg,
        //        close: function() {
        //            return closeAlert(this);
        //        }
        //    });
        //}

        function add(type, msg, position) {

            if (position === undefined) {
                position = 'top-right';
            }

            var alert = {
                type: type,
                msg: msg,
                position:position,
                close: function () {
                    return closeAlert(this);
                }
            };
            $timeout(closeAlert, 3500, true, alert);
            return alerts.push(alert);
        }

        function closeAlert(alert) {
            return closeAlertIdx(alerts.indexOf(alert));
        }

        function closeAlertIdx(index) {
            return alerts.splice(index, 1);
        }

        function clear(){
            alerts = [];
        }

        function get() {
            return alerts;
        }
    }
})();