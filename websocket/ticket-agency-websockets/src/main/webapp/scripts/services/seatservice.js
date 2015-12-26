'use strict';
angular.module('ticketApp').service('SeatService',
    function SeatService($resource) {
        return $resource('rest/seat/:seatID', {
            seatID: '@id'
        },
        {
            query: {
                method: 'GET',
                isArray: true
            },
            book: {
                method: 'POST'
            }
        });
    }
);