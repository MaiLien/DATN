angular.module('appDATN.auth')
    .factory("AuthService", function ($http) {

        var auth = {
            status : true,
            isAuthenticated : false,
            isStudent : false,
            isTeacher : false,
            isOfficer : false,
            isAdmin : false,
            user : null
        };

        initAuth = function () {
            auth = {
                status : true,
                isAuthenticated : false,
                isStudent : false,
                isTeacher : false,
                isOfficer : false,
                isAdmin : false,
                user : null
            };
        };

        setAuth = function () {
            getSession()
                .success(function(data){
                    if(data.body == null){
                        auth.isAuthenticated = false;
                    }else{
                        auth.user = data.body;
                        auth.isAuthenticated = true;
                        switch (data.body.typeOfUser){
                            case 0:
                                auth.isTeacher = true;
                                break;
                            case 1:
                                auth.isStudent = true;
                                break;
                            case 2:
                                auth.isOfficer = true;
                                break;
                            case 3:
                                $scope.isAdmin = true;
                                break;
                        }
                    }
                })
                .error(function(error){
                    auth.status = false;
                    auth.isAuthenticated = false;
                })
        };

        getAuth = function () {
            return auth;
        };

        login = function (user) {
            return $http.post(
                '/login',
                $.param(user),
                { headers: {'content-type': 'application/x-www-form-urlencoded'}});
        };

        getSession = function (){
            return $http.get('/session')
        };

        logout = function () {
            return $http.get('/logout')
        };

        return{
            login:login,
            getSession:getSession,
            logout:logout,
            getAuth:getAuth,
            setAuth:setAuth,
            initAuth:initAuth
        };

    });