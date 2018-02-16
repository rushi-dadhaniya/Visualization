angular.module('todoController', ['ngFlash'])

	// inject the Todo service factory into our controller
    .controller('mainController', ['$scope','$http','$interval', '$window','$timeout', 'Todos','Flash',
     function($scope, $http, $interval, $timeout, $window, Todos, Flash) {
		$scope.formData = "";
		$scope.loading = true;


        function reloadPage() {
            $window.location.reload();
        };

        $interval(function(){ $scope.getData(); }, 5000);

         function callAtInterval() {
             console.log("Interval occurred");
         }

//
//        setIntvl = $interval(reloadPage, 300);

		// GET =====================================================================

            $scope.getData = function() {
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/api/getJobs'
                    }).then(function successCallback(response) {
                        $scope.todos = response.data;
                        $scope.siblingsList = [];
                        $scope.nodeLookUp = {};
                        $scope.loading = false;
                    }, function errorCallback(response) {
                });
            };

        $scope.getData();

        $scope.instantiateHelpers = function(nodes){
            $scope.nodeLookUp = {};
            $scope.siblingsList = [];
            for(var i in nodes){
                $scope.nodeLookUp[nodes[i].event_id] = nodes[i];
                if(nodes[i].child.length>1){
                    for(var j in nodes[i].child){
                        $scope.siblingsList.push(nodes[i].child[j]);
                    }
                }
            }
//            console.log($scope.nodeLookUp['n1'].status);
        }

        $scope.isDrawable = function(data){
            if($scope.siblingsList.indexOf(data.event_id)>-1){
                return false;
            } else {
                return true;
            }
        };


		// CREATE ==================================================================
        $scope.createTodo = function() {
            $http({
                method: 'POST',
                headers:{
                    'Content-Type' :'application/json'
                },
                data : $scope.formData,
                url: 'http://localhost:8080/api/workflow_template'
                }).then(function successCallback(response) {
                    console.log(response);
                    $scope.formData = "";
                }, function errorCallback(response) {
                    console.log(response);
                    $scope.formData = "";
            });
        };

            $scope.success = function (id) {
        var message =  id;
        Flash.create('success', message);
    };
    $scope.info = function (id) {
        var message = id;
        Flash.create('info', message);
    };
    $scope.warning = function (id) {
        var message = id;
        Flash.create('warning', message);
    };
    $scope.danger = function (id) {
        var message = id;
        Flash.create('danger', message);
    };
    $scope.pause = function (id) {
        Flash.pause();
    };

		// DELETE ==================================================================
		// delete a todo after checking it
//		$scope.deleteTodo = function(id) {
//			$scope.loading = true;
//
//			Todos.delete(id)
//				// if successful creation, call our get function to get all the new todos
//				.success(function(data) {
//					$scope.loading = false;
//					$scope.todos = data; // assign our new list of todos
//				});
//		};
	}]);