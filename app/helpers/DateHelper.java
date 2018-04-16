package helpers;

import configurations.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    /**
     * Convert String date time with format yyyy-MM-dd hh:mm into Date
     * @param dateTimeString the datetime string
     * @return parsed date
     */
    public Date convertStringToDateTime(String dateTimeString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.YEAR_MONTH_DAY);
        return simpleDateFormat.parse(dateTimeString);

    }


    public String convertDateTimeToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.YEAR_MONTH_DAY);
        String stringDateTime = "";

        return simpleDateFormat.format(date);
    }

    public String getCurrentDateString() {
        return convertDateTimeToString(new Date());
    }

    public boolean dateIsPast(Date date) {
        return date.getTime() < new Date().getTime();
    }

    public boolean dateIsPast(String dateString) throws ParseException {
        Date date = convertStringToDateTime(dateString);
        return date.getTime() < new Date().getTime();
    }



}
