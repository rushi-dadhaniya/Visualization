var app = angular.module('app', []);

app.controller('PhoneListController', function PhoneListController($scope) {
    $scope.phones = {
        name: 'Nexus S',
        snippet: 'Fast just got faster with Nexus S.'
    };
});