package com.example.vlad.practicaltest01;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ProcessingThread extends Thread{

    Context context;
    Intent intent;

    //List<String> actions = Constants.actions;  de ce nu merge???????/
    List<String> actions = new ArrayList<String>(Arrays.asList("First-action", "Second-action", "Third-action"));
   public ProcessingThread(Context context,Intent intent){
        this.context = context;
        this.intent = intent;
   }
   @Override
    public void run(){
       Log.d("thread", "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        while(PracticalTest01Service.isRunning){
            sendMessage();
            sleep();
        }

    }

    public void sleep(){
        try{
            Thread.sleep(10000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
    public String Ma_Mg(int a,int b){
        Log.d("Ma_Mg",String.valueOf(a) + " : " + String.valueOf(b));
        return String.valueOf( (a+b)/2) + " " + String.valueOf(Math.sqrt(a*b));
    }
    public void sendMessage(){

        Intent newIntent = new Intent();
        Random random = new Random();
        String action = actions.get(random.nextInt(actions.size()));
        newIntent.setAction(action);
        String message = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        newIntent.putExtra("threadData", message + Ma_Mg(Integer.valueOf(intent.getExtras().get("dataLeft").toString()),
                Integer.valueOf(intent.getExtras().get("dataRight").toString())));


        context.sendBroadcast(newIntent);
    }

}
