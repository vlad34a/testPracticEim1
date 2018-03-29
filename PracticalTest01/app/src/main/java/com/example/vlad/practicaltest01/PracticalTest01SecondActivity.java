package com.example.vlad.practicaltest01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by vlad on 3/28/18.
 */

public class PracticalTest01SecondActivity extends AppCompatActivity {


    TextView message2;
    Button buttonOk;
    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        message2  = (TextView)findViewById(R.id.intentMessage);
        buttonOk =  (Button)findViewById(R.id.okButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        Intent intent = getIntent();
        if(intent != null){
            if(intent.getExtras().containsKey("numberOfClicks"))
                message2.setText(intent.getExtras().getString("numberOfClicks"));
            else Log.d("bundle", "nu contine cheia");
        }

        ButtonListener bl = new ButtonListener();
        buttonOk.setOnClickListener(bl);
        cancelButton.setOnClickListener(bl);

        //important!!!
/*
        Intent intentToParent = new Intent();
        setResult(RESULT_OK,intentToParent);
*/

    }
    private class ButtonListener implements Button.OnClickListener{

        private Intent intentToParent = new Intent();
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.okButton:
                    Log.d("button","okButtonwasPress");
                    intentToParent.putExtra("numberOfClicks",message2.getText().toString());
                    setResult(RESULT_OK,intentToParent);
                    finish();
                    break;
                case R.id.cancelButton:
                    Log.d("button", "cancelButtonwasPress");
                    setResult(RESULT_CANCELED,intentToParent);
                    finish();
                    break;

            }
        }
    }
}
