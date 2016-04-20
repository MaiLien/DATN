angular.module('appDATN.common')
    .filter('sortDateRange', function () {

        var LARGER = 0;
        var EQUAL = 1;
        var SMALLER = 2;

        convertToyyyymmdd = function (dateTime) {
            var arrDateTime = dateTime.split(' ');
            var arrDate = arrDateTime[0].split('/');
            var out = "";

            for (var i = arrDate.length - 1; i > 0; i--) {
                out = out + arrDate[i];
                out = out + "/";
            }
            out = out + arrDate[0] + " ";
            out = out + arrDateTime[1];

            return out;
        };

        compareDate = function (dateTime1, dateTime2) {

            var dt1 = Date.parse(convertToyyyymmdd(dateTime1));
            var dt2 = Date.parse(convertToyyyymmdd(dateTime2));

            if (dt1 > dt2)
                return LARGER;

            if (dt1 == dt2)
                return EQUAL;

            if (dt1 < dt2)
                return SMALLER;
        };

        /* If(dateTime1 >= dateTime2) true; */
        compareDateTimes = function (dateTimes1, dateTimes2) {
            var starts = [];
            var ends = [];
            var arrDateTime;
            var temp;

            arrDateTime = dateTimes1.split(" - ");
            starts.push(arrDateTime[0]);
            ends.push(arrDateTime[1]);

            arrDateTime = dateTimes2.split(" - ");
            starts.push(arrDateTime[0]);
            ends.push(arrDateTime[1]);

            temp = compareDate(starts[0], starts[1]);
            if (temp == LARGER) {
                return true;
            }

            if (temp == EQUAL) {
                temp = compareDate(ends[0], ends[1]);
                if (temp != SMALLER)
                    return true;
            }

            return false;
        };

        addLastPosition = function (item, list) {
            list.push(item);
            return list;
        };

        addCurrentPosition = function (item, currentPosition, list) {
            var arrTemp = [];

            var i = currentPosition;
            var size = list.length;
            while(i<size){
                arrTemp.push(list.pop());
                i++;
            }

            list.push(item);

            i=0;
            size=arrTemp.length;
            while(i<size){
                list.push(arrTemp.pop());
                i++;
            }

            return list;
        };

        return function (list) {
            var result = [];

            if (angular.isArray(list) && list.length > 0) {
                result.push(list[0]);
                for (var i = 1; i < list.length; i++) {
                    for (var j = 0; j < result.length; j++) {
                        if (compareDateTimes(list[i], result[j])) {
                            addCurrentPosition(list[i], j, result);
                            break;
                        }
                    }
                }
            }
            return result;
        }
    });