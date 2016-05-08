angular.module('appDATN.officer')
    .factory("OfficerInfoService", function ($http) {

        getOfficerInfo = function () {
            return $http.get('/session');
        };

        return {
            getOfficerInfo: getOfficerInfo
        };

    });