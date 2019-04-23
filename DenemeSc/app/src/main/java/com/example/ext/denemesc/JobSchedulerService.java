package com.example.ext.denemesc;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class JobSchedulerService extends JobService {
    private static final String TAG = "JobSchedulerService";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob:");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob:");
        return false;
    }

}

