package com.dev.ext.asansor.Schedule;

import android.app.job.JobParameters;
import android.app.job.JobService;


import com.dev.ext.asansor.OnConnection;

public class MyJobService extends JobService {

    public MyJobService() {
    }

      public static String ilk_online_durum = "1";
    public static boolean x=true;
    public MyJobService(boolean x) {
        this.x = x;
    }

    @Override
    public boolean onStartJob(JobParameters params) {


        /*
         * True - if your service needs to process
         * the work (on a separate thread).
         * False - if there's no more work to be done for this job.
         */
       //ArizaActivity arizaActivity = new ArizaActivity();
        //arizaActivity.ilaniYayinla();
  //      internetConnectionActivity internetConnectionActivity = new internetConnectionActivity();
//        internetConnectionActivity.isNetworkConnected();


        if(ilk_online_durum=="1" && x==true) {
            OnConnection onConnection = new OnConnection();
            onConnection.readfromStorageariza(getApplicationContext());
            onConnection.readfromStoragebakim(getApplicationContext());
            ilk_online_durum="2";
            x = true;
        }


        return true;


    }

    @Override
    public boolean onStopJob(JobParameters params) {


        ilk_online_durum="1";

        return true;
    }



    




}