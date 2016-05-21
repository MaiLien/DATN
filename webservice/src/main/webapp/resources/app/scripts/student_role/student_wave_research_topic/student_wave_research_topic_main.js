'use strict';

angular.module('appDATN.student_wave')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student_role.student_wave_research_topic', {
                parent: 'student_role.student_session',
                url: '/wave/research_topic',
                views: {
                    content_view: {
                        controller:'StudentResearchTopicCtrl',
                        templateUrl: '/resources/app/scripts/student_role/student_wave_research_topic/views/student_wave_research_topic.html'
                    }
                }
            })
    });