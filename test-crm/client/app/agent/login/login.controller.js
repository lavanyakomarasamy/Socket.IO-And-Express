'use strict';

angular.module("myApp")
  .controller('LoginCtrl', function login($scope, $socket, $state, $http) {

  	$scope.resourceColourMap = function() {

    	console.log('Inside resourceColourMap()-----> ' +$scope.status);

		var voice = angular.element( document.querySelector( '#voice' ) );
	  	var webchat = angular.element( document.querySelector( '#webchat' ) );
	  	var email = angular.element( document.querySelector( '#email' ) );
	  	var sms = angular.element( document.querySelector( '#sms' ) );
	  	var social = angular.element( document.querySelector( '#social' ) );

	  	var voiceNotify = angular.element( document.querySelector( '#voiceNotify' ) );
	  	var webchatNotify = angular.element( document.querySelector( '#webchatNotify' ) );
	  	
	  	if($scope.status =="Not Ready") {

	  		voice.removeClass('mdl-color--green');  
	  		webchat.removeClass('mdl-color--green');  
	  		email.removeClass('mdl-color--green');  
	  		sms.removeClass('mdl-color--green');  
	  		social.removeClass('mdl-color--green'); 

	  		voice.removeClass('mdl-color--grey');  
	  		webchat.removeClass('mdl-color--grey');  
	  		email.removeClass('mdl-color--grey');  
	  		sms.removeClass('mdl-color--grey');  
	  		social.removeClass('mdl-color--grey'); 

	  		voice.addClass('mdl-color--orange');  
	  		webchat.addClass('mdl-color--orange');  
	  		email.addClass('mdl-color--orange');  
	  		sms.addClass('mdl-color--orange');  
	  		social.addClass('mdl-color--orange');  

	  		//notification hide
	  		voiceNotify.addClass('hide');
	  		webchatNotify.addClass('hide');

	  	} else if($scope.status =="Ready") {

	  		voice.removeClass('mdl-color--orange');  
	  		webchat.removeClass('mdl-color--orange');  
	  		email.removeClass('mdl-color--orange');  
	  		sms.removeClass('mdl-color--orange');  
	  		social.removeClass('mdl-color--orange'); 

	  		voice.removeClass('mdl-color--grey');  
	  		webchat.removeClass('mdl-color--grey');  
	  		email.removeClass('mdl-color--grey');  
	  		sms.removeClass('mdl-color--grey');  
	  		social.removeClass('mdl-color--grey'); 

	  		voice.addClass('mdl-color--green');  
	  		webchat.addClass('mdl-color--green');  
	  		email.addClass('mdl-color--green');  
	  		sms.addClass('mdl-color--green');  
	  		social.addClass('mdl-color--green'); 

	  		//notification show
	  		voiceNotify.removeClass('hide');
	  		webchatNotify.removeClass('hide');

	  	} else {
	  		//(Activated / Logged Out / Deactivated)
	  		voice.removeClass('mdl-color--orange');  
	  		webchat.removeClass('mdl-color--orange');  
	  		email.removeClass('mdl-color--orange');  
	  		sms.removeClass('mdl-color--orange');  
	  		social.removeClass('mdl-color--orange'); 

	  		voice.removeClass('mdl-color--green');  
	  		webchat.removeClass('mdl-color--green');  
	  		email.removeClass('mdl-color--green');  
	  		sms.removeClass('mdl-color--green');  
	  		social.removeClass('mdl-color--green'); 

	  		voice.addClass('mdl-color--grey');  
	  		webchat.addClass('mdl-color--grey');  
	  		email.addClass('mdl-color--grey');  
	  		sms.addClass('mdl-color--grey');  
	  		social.addClass('mdl-color--grey'); 

	  		//notification hide
	  		voiceNotify.addClass('hide');
	  		webchatNotify.addClass('hide');
	  	}
	};

	$scope.postCall = function(url, reqObj) {
		$scope.resStatus = '';
		var config = {
            headers : {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin" : "*"
            }
         }
        $http.post(url, reqObj, config)
        .success(function (data, status, headers) {
            console.log("Success");
            return $scope.responseStatus = 'Success';
        })
        .error(function (data, status, header, config) {
            console.log("Failed");
            return $scope.responseStatus = 'Failed';
        });
	}

  	$scope.status = 'Activated';
  	$scope.resourceColourMap();

  	var workTab = angular.element( document.querySelector( '#work' ) );
  	var teamTab = angular.element( document.querySelector( '#team' ) );
	workTab.removeClass('hide');
	teamTab.removeClass('hide'); 

	workTab.addClass('is-active');
	teamTab.addClass('is-active');

  	$socket.on('echo', function (data) {
        $scope.serverResponse = data;
    });


  	$scope.login = function login(status) {

  		console.log('Login Request submitted');
        var jsonData = JSON.stringify({
            ägetnName: "agent",
            event: "login"
        });
       
       	var res = $scope.postCall("http://localhost:8080/CRMWebConnecter/api/agents/loginCommand", jsonData);
       	console.log('$scope.responseStatus =  '+res);

        //sent to server login
        $socket.emit('loginEvent', jsonData);
        //ccatch from server for login response
        $socket.on('loginResponseEvent', function(status){
            console.log('loginResponseEvent captured in client status is '+ status);
            $scope.status = 'Ready';
	  		$scope.resourceColourMap();

		  	var login = angular.element( document.querySelector( '#agent-login' ));
		  	var activate = angular.element( document.querySelector( '#agent-activate' ));			
		  	var deactivate = angular.element( document.querySelector( '#agent-deactivate' ));
	  		var goready = angular.element( document.querySelector( '#agent-goready' ));
		  	var gonotready = angular.element( document.querySelector( '#agent-gonotready' ));
		  	var logout = angular.element( document.querySelector( '#agent-logout' ));
		  	var resourcecard = angular.element( document.querySelector( '#resource-card' ));


		  	login.addClass('hide');  
			activate.addClass('hide'); 
			deactivate.addClass('hide');
			// goready.removeClass('hide');
			gonotready.removeClass('hide'); 
			logout.removeClass('hide');
            // $state.go('login');
        });

  		
  	}

  	$scope.logout = function logout() {
  		console.log('Logout Request submitted');
        var jsonData = JSON.stringify({
            ägetnName: "agent",
            event: "Logout"
        });
        var res = $scope.postCall("http://localhost:8080/CRMWebConnecter/api/agents/logoutCommand", jsonData);
       	console.log('$scope.responseStatus =  '+res);

        //sent to server logout
        $socket.emit('logoutEvent', jsonData);
        //ccatch from server for logout response
        $socket.on('logoutResponseEvent', function(data){
            console.log('logoutResponseEvent captured in client');

	  		$scope.status = 'Logged Out';
	  		$scope.resourceColourMap();

		  	var login = angular.element( document.querySelector( '#agent-login' ) );
		  	var activate = angular.element( document.querySelector( '#agent-activate' ) );
		  	var deactivate = angular.element( document.querySelector( '#agent-deactivate' ) );
	  		var goready = angular.element( document.querySelector( '#agent-goready' ) );
		  	var gonotready = angular.element( document.querySelector( '#agent-gonotready' ) );
		  	var logout = angular.element( document.querySelector( '#agent-logout' ) );
		  	var resourcecard = angular.element( document.querySelector( '#resource-card' ) );

			login.removeClass('hide');
			deactivate.removeClass('hide');
			activate.addClass('hide'); 
		  	// resourcecard.addClass('hide');
			goready.addClass('hide');
			gonotready.addClass('hide'); 
			logout.addClass('hide');
		});
  	}

  	$scope.deactivate = function deactivate() {

  		console.log('Deactivate Request submitted');

        // console.log($scope.vm.username + ' ' + $scope.vm.password);
        var jsonData = JSON.stringify({
            ägetnName: "agent",
            event: "Deactivate"
        });

        var res = $scope.postCall("http://localhost:8080/CRMWebConnecter/api/agents/deactivate", jsonData);
       	console.log('$scope.responseStatus =  '+res);

        //sent to server Deactivate
        $socket.emit('deactivateEvent', jsonData);
        //ccatch from server for Deactivate response
        $socket.on('deactivateResponseEvent', function(data){
	        console.log('deactivateResponseEvent captured in client');
		  	$scope.status = 'Deactivated';
		  	$scope.resourceColourMap();
		  	$state.go('activate');
	  	});
  	}

  	$scope.goready = function goready() {

  		console.log('GoReady Request submitted');
        // console.log($scope.vm.username + ' ' + $scope.vm.password);
        var jsonData = JSON.stringify({
            ägetnName: "agent",
            event: "GoReady"
        });
       
        var res = $scope.postCall("http://localhost:8080/CRMWebConnecter/api/agents/startCommand", jsonData);
       	console.log('$scope.responseStatus =  '+res);
        //sent to server GoReady
        $socket.emit('readyEvent', jsonData);
        //ccatch from server for GoReady response
        $socket.on('readyResponseEvent', function(data){
            console.log('readyResponseEvent captured in client');


	  		$scope.status = 'Ready';
	  		$scope.resourceColourMap();
	  		console.log('$scope.status' +$scope.status);

		  	var login = angular.element( document.querySelector( '#agent-login' ) );
		  	var activate = angular.element( document.querySelector( '#agent-activate' ) );

		  	var deactivate = angular.element( document.querySelector( '#agent-deactivate' ) );
	  		var goready = angular.element( document.querySelector( '#agent-goready' ) );
		  	var gonotready = angular.element( document.querySelector( '#agent-gonotready' ) );
		  	var logout = angular.element( document.querySelector( '#agent-logout' ) );
		  	var resourcecard = angular.element( document.querySelector( '#resource-card' ) );

		  	gonotready.removeClass('hide'); 
			logout.removeClass('hide');

			login.addClass('hide');
			deactivate.addClass('hide');
			activate.addClass('hide'); 
		  	// resourcecard.addClass('hide');
		  	goready.addClass('hide');
		});
  	}

  	$scope.gonotready = function gonotready() {

  		console.log('GoNotReady Request submitted');
        // console.log($scope.vm.username + ' ' + $scope.vm.password);
        var jsonData = JSON.stringify({
            ägetnName: "agent",
            event: "GoNotReady"
        });
       
        var res = $scope.postCall("http://localhost:8080/CRMWebConnecter/api/agents/stopCommand", jsonData);
       	console.log('$scope.responseStatus =  '+res);

        //sent to server GoNotReady
        $socket.emit('notreadyEvent', jsonData);
        //ccatch from server for GoNotReady response
        $socket.on('notReadyResponseEvent', function(data){
            console.log('notReadyResponseEvent captured in client');

	  		$scope.status = 'Not Ready';
	  		$scope.resourceColourMap();

		  	var login = angular.element( document.querySelector( '#agent-login' ) );
		  	var activate = angular.element( document.querySelector( '#agent-activate' ) );
		  	var deactivate = angular.element( document.querySelector( '#agent-deactivate' ) );
	  		var goready = angular.element( document.querySelector( '#agent-goready' ) );
		  	var gonotready = angular.element( document.querySelector( '#agent-gonotready' ) );
		  	var logout = angular.element( document.querySelector( '#agent-logout' ) );
		  	var resourcecard = angular.element( document.querySelector( '#resource-card' ) );

		  	goready.removeClass('hide');
			logout.removeClass('hide');
			login.addClass('hide');
			deactivate.addClass('hide');
			activate.addClass('hide'); 

			gonotready.addClass('hide'); 
		});
  	}

});