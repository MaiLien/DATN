package datn.interfaces.util;

import ch.smartlinksa.intern.interfaces.constant.FormatConstant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");

    public static boolean isFormatDate(String dateInputString) {
        simpleDateFormat.setLenient(false);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateInputString);
        }catch (ParseException e){
            return false;
        }
        return true;
    }

    public static boolean isFutureDate(String dateInputString){
        simpleDateFormat.setLenient(false);
        Date dateInput = null;
        Date currentDate = new Date();
        try{
            currentDate = simpleDateFormat.parse(currentDate.toString());
            dateInput = simpleDateFormat.parse(dateInputString);

        }catch (ParseException e) {
            return false;
        }
        return compareToDate(currentDate, dateInput);
    }

    private static boolean compareToDate (Date currentDate, Date inputDate){
        if(inputDate.compareTo(currentDate) == 1){
            return false;
        }
        return true;
    }

    public static Date convertStringToDate(String dateString, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}