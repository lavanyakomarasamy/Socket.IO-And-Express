'use strict';

angular.module("myApp")
  .controller('ActivateCtrl', function Ctrl($scope, $socket, $http, $state) {
  	$scope.status = 'Deactivated';

  	$socket.on('echo', function (data) {
        $scope.serverResponse = data;
    });
/*
  	$scope.showWork= function showWork(){
	  console.log('showWork entered');
	}
  	$scope.showTeam= function showTeam(){
	  console.log('showTeam entered');
	}*/


    $scope.submit = function submit() {
        console.log('Activate Request submitted');
        var jsonData = JSON.stringify({
            userId: $scope.vm.username,
            password: $scope.vm.password
        });
       
        var config = {
            headers : {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin" : "*"
            }
         }
        $http.post("http://localhost:8080/CRMWebConnecter/api/agents/activateCommand", jsonData, config)
        .success(function (data, status, headers) {
            console.log("Success");
            
        })
        .error(function (data, status, header, config) {
            console.log("Failed");
        });

        //sent to server login
        $socket.emit('activateEvent', jsonData);
        //ccatch from server for login response
        $socket.on('activateResponseHandler', function(data){
            console.log('activateResponseHandler captured in client');
            $state.go('login');
        });
    };

     //ccatch from server for login response
    $socket.on('activateResponseHandler', function(data){
        console.log('activateResponseHandler captured in client');
        $state.go('login');
    });

});
