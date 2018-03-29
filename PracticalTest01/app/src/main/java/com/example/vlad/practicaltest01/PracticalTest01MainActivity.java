package com.example.vlad.practicaltest01;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private EditText left;
    private EditText right;
    private Button switchButton;
    private final int prag = 5;
    private Intent intent;
    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;
    private IntentFilter startedServiceIntentFilter;
    private ButtonListener buttonListener = new ButtonListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        Button button1 = (Button)findViewById(R.id.pressMe);
        Button button2 = (Button)findViewById(R.id.pressMeToo);
        left = (EditText)findViewById(R.id.first);
        right = (EditText)findViewById(R.id.second);
        switchButton = (Button)findViewById(R.id.switchButton);

        switchButton.setOnClickListener(buttonListener);
        button1.setOnClickListener(buttonListener);
        button2.setOnClickListener(buttonListener);

        intent = new Intent();
        intent.setComponent(new ComponentName("com.example.vlad.practicaltest01","com.example.vlad.practicaltest01.PracticalTest01Service"));

        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver();
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction("First-action");
        startedServiceIntentFilter.addAction("Second-action");
        startedServiceIntentFilter.addAction("Third-action");
    }

    @Override
    protected void onStart(){
        super.onStart();
       // registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        stopService(intent);
        super.onDestroy();
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // apelarea metodei din activitatea parinte este recomandata, dar nu obligatorie
        super.onSaveInstanceState(savedInstanceState);
        // ...

        savedInstanceState.putString("left",left.getText().toString());
        savedInstanceState.putString("right",right.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // apelarea metodei din activitatea parinte este recomandata, dar nu obligatorie
        super.onRestoreInstanceState(savedInstanceState);
        // ...
       /* left = (EditText)findViewById(R.id.first);
        right = (EditText)findViewById(R.id.second);*/

        if(savedInstanceState.containsKey("left"))
            left.setText(savedInstanceState.getString("left"));
        else
            left.setText("0");

        if(savedInstanceState.containsKey("right")){
            right.setText(savedInstanceState.getString("right"));
        }else
            right.setText("0");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PracticalTest01MainActivity.requestCode:
                if(resultCode == RESULT_OK){
                    Log.d("button", "finishedOk");
                    Toast.makeText(this, "finishedOk " + data.getExtras().getString("numberOfClicks"),Toast.LENGTH_LONG).show();
                }
                if(resultCode == RESULT_CANCELED){
                    Log.d("button", "finishedNotOk");
                    Toast.makeText(this, "finished with Cancel ",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private final static int requestCode = 2017;

    private class ButtonListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {

            if(((Button)v).getId() == R.id.pressMe){
                left.setText(String.valueOf(Integer.parseInt(left.getText().toString())+1), TextView.BufferType.EDITABLE);
            }

            if(((Button)v).getId() == R.id.pressMeToo){
                right.setText(String.valueOf(Integer.parseInt(right.getText().toString())+1), TextView.BufferType.EDITABLE);
            }

            switch (v.getId()) {
                case R.id.switchButton:
                    Intent intent = new Intent(getApplication(), PracticalTest01SecondActivity.class);
                    intent.putExtra("numberOfClistartcks", String.valueOf(Integer.valueOf(left.getText().toString()) + Integer.valueOf(right.getText().toString())));
                    startActivityForResult(intent, requestCode);
                    Log.d("switch","test");
                    break;
            }

            if(prag == (Integer.parseInt(left.getText().toString()) + Integer.parseInt(right.getText().toString()) ) ){
                intent.putExtra("dataLeft", Integer.parseInt(left.getText().toString()));
                intent.putExtra("dataRight", Integer.parseInt(right.getText().toString()));
                Log.d("service","startedServiceBEGIN");
                startService(intent);

            }

        }
    }

}
