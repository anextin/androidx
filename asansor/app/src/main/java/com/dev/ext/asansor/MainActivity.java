package com.dev.ext.asansor;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dev.ext.asansor.Activities.ArizaSecActivity;
import com.dev.ext.asansor.Activities.ArizaTanimlamaActivity;
import com.dev.ext.asansor.Activities.BakimEksiklikleriActivity;
import com.dev.ext.asansor.Activities.BakimSecActivity;
import com.dev.ext.asansor.Activities.BekleyenArizalarActivity;
import com.dev.ext.asansor.Activities.TahsilatYapActivity;
import com.dev.ext.asansor.Activities.YapilacakBakimlarActivity;
import com.dev.ext.asansor.Schedule.MyJobService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button bakimButton,tahsilatYapButton,arizaTanimlaButton,yapilacakBakimlarButton,bekleyenArizalarButton,arizaButton,bakimEksikleriButton;
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
        user = auth.getCurrentUser();


        arizaButton = findViewById(R.id.arizaButton);
        arizaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArizaSecActivity.class);
                startActivity(intent);
            }
        });

        bakimButton = findViewById(R.id.bakimButton);
        bakimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BakimSecActivity.class);
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


        bekleyenArizalarButton = findViewById(R.id.bekleyenArizalarButton);
        bekleyenArizalarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BekleyenArizalarActivity.class);
                startActivity(intent);
            }
        });

        bakimEksikleriButton = findViewById(R.id.bakimEksikleriButton);
        bakimEksikleriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BakimEksiklikleriActivity.class);
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
