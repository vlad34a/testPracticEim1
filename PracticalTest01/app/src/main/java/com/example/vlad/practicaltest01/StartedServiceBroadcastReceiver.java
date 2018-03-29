package com.example.vlad.practicaltest01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vlad on 3/28/18.
 */

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    final static List<String> actions = new ArrayList<String>(Arrays.asList("First-action", "Second-action", "Third-action"));
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String data = null;

        if(actions.contains(action)){
            Log.d("onReceive", action);
        }


    }
}
