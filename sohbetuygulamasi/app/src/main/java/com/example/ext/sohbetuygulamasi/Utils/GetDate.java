package com.example.ext.sohbetuygulamasi.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;



public class GetDate {

    public static String getDate()
    {
        DateFormat df = new SimpleDateFormat("HH:mm");

        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date today = Calendar.getInstance().getTime();

        final String reportdate = df.format(today);


        return reportdate;
    }
}
