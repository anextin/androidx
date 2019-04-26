package com.example.ext.asansor;

import android.app.AlarmManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.annotation.TargetApi;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ext.asansor.Activities.ArizaTanimlamaActivity;
import com.example.ext.asansor.Activities.YapilacakBakimlarActivity;
import com.example.ext.asansor.Schedule.MyJobService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button bakimButton,tahsilatYapButton,arizaTanimlaButton,yapilacakBakimlarButton;
    public JobScheduler jobScheduler;
    FirebaseAuth auth;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tanimla();
        kontrol();

        int MYJOBID=1;
        jobScheduler= (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        ComponentName jobService = new ComponentName(getPackageName(), MyJobService.class.getName());


        JobInfo jobInfo = new JobInfo.Builder(MYJOBID,jobService).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY). build();
        jobScheduler.schedule(jobInfo);
        JobInfo.Builder builder = new JobInfo.Builder( 2,  new ComponentName( getPackageName(), MyJobService.class.getName() ) );
        builder.setPeriodic( 900000 );


    }




    public void tanimla() {

        //kullanıcı işleri
        auth = FirebaseAuth.getInstance();
        user= auth.getCurrentUser();


        bakimButton = findViewById(R.id.bakimButton);
        bakimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArizaActivity.class);
                startActivity(intent);
            }
        });

        tahsilatYapButton = findViewById(R.id.tahsilatYapButton);
        tahsilatYapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TahsilatYapActivity.class);
                startActivity(intent);
            }
        });

        arizaTanimlaButton = findViewById(R.id.arizaTanimlaButton);
        arizaTanimlaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArizaTanimlamaActivity.class);
                startActivity(intent);
            }
        });

        yapilacakBakimlarButton = findViewById(R.id.yapilacakBakimlarButton);
        yapilacakBakimlarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, YapilacakBakimlarActivity.class);
                startActivity(intent);
            }
        });

    }


    public void kontrol()
    {
        if(user==null)
        {
            Intent intent= new Intent(MainActivity.this,GirisActivity.class);
            startActivity(intent);
            finish();
        }
    }



}
