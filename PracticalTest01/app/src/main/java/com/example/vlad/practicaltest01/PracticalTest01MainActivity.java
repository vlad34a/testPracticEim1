package com.example.vlad.practicaltest01;

import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private EditText left;
    private EditText right;

    private ButtonListener buttonListener = new ButtonListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        Button button1 = (Button)findViewById(R.id.pressMe);
        Button button2 = (Button)findViewById(R.id.pressMeToo);
        button1.setOnClickListener(buttonListener);
        button2.setOnClickListener(buttonListener);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // apelarea metodei din activitatea parinte este recomandata, dar nu obligatorie
        super.onSaveInstanceState(savedInstanceState);
        // ...
        left = (EditText)findViewById(R.id.first);
        right = (EditText)findViewById(R.id.second);
        savedInstanceState.putString("left",left.getText().toString());
        savedInstanceState.putString("right",right.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // apelarea metodei din activitatea parinte este recomandata, dar nu obligatorie
        super.onRestoreInstanceState(savedInstanceState);
        // ...
        left = (EditText)findViewById(R.id.first);
        right = (EditText)findViewById(R.id.second);
        if(savedInstanceState != null){
            left.setText(savedInstanceState.getString("left"));
            right.setText(savedInstanceState.getString("right"));
        }
    }





    private class ButtonListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {

            EditText left = (EditText)findViewById(R.id.first);
            EditText right = (EditText)findViewById(R.id.second);

            if(((Button)v).getId() == R.id.pressMe){
                left.setText(String.valueOf(Integer.parseInt(left.getText().toString())+1), TextView.BufferType.EDITABLE);
            }

            if(((Button)v).getId() == R.id.pressMeToo){
                right.setText(String.valueOf(Integer.parseInt(right.getText().toString())+1), TextView.BufferType.EDITABLE);
            }
        }
    }
}
