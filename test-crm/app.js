'use strict';

var myApp = angular.module("myApp", ['socket.io', 'ui.router']);

myApp.config(function ($socketProvider, $stateProvider, $urlRouterProvider) {
    
    $socketProvider.setConnectionUrl('http://localhost:4000');

     $urlRouterProvider.when("", "/activate");

     $stateProvider
        .state("activate", {
            url: "/activate",
            templateUrl: "client/app/agent/activate/activate.html",
            controller: 'ActivateCtrl'
        })
        .state("login", {
            url: "/login",
            templateUrl: "client/app/agent/login/login.html",
            controller: 'LoginCtrl'
        })
        .state("team", {
            url: "/team",
            templateUrl: "client/app/team/team.html",
            controller: 'TeamCtrl'
        })
        .state("work", {
            url: "/work",
            templateUrl: "client/app/work/work.html",
            controller: 'WorkCtrl'
        });
});

