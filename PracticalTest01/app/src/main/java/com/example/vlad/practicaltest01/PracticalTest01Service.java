package com.example.vlad.practicaltest01;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.IntDef;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
/**
 * Created by vlad on 3/28/18.
 */

public class PracticalTest01Service extends Service {


    public static boolean isRunning = true;
    ProcessingThread processingThread;
    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PracticalTest01Service.isRunning = false;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        processingThread = new ProcessingThread(this,intent);
        processingThread.start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

