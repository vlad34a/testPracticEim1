package com.example.vlad.intentlab;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private  ButtonListener buttonListener = new ButtonListener();
    private Button show_hide,save,cancel;
    private LinearLayout linearLayoutInvisible;
    private EditText name;
    private EditText phoneNumberBox;
    private EditText email;
    private EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        address = (EditText)findViewById(R.id.address);

        show_hide = (Button)findViewById(R.id.showAdditional);
        save = (Button)findViewById(R.id.Savebutton);
        cancel = (Button)findViewById(R.id.Cancelbutton);

        linearLayoutInvisible = (LinearLayout)findViewById(R.id.invisibleLayout);


        Intent intent = getIntent();
        if(intent != null){
            String phoneNumber = intent.getStringExtra("phone_number_key");
            phoneNumberBox = (EditText)findViewById(R.id.number);
            phoneNumberBox.setText(phoneNumber);
        }

        show_hide.setOnClickListener(buttonListener);
        save.setOnClickListener(buttonListener);
        cancel.setOnClickListener(buttonListener);
    }
    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            Log.d("alo","decEnu??");

            if(((Button) v).getText().toString().equals("Show additional fields")){
                Log.d("alo","decE??");
                show_hide.setText("Hide additional fields");
                linearLayoutInvisible.setVisibility(LinearLayout.VISIBLE);
            }else if(((Button) v).getText().toString().equals("Hide additional fields")){
                Log.d("alo","decE??");
                show_hide.setText("Show additional fields");
                linearLayoutInvisible.setVisibility(LinearLayout.INVISIBLE);
            }

            if(v.getId() == R.id.Cancelbutton){
                setResult(Activity.RESULT_CANCELED,new Intent());
                finish();
               /* Log.d("Debug","insideCancelIf");
                name.clearComposingText();
                name.setText("");

                name.setHint("Name");
                address.clearComposingText();
                address.setHint("Address");
                email.clearComposingText();
                email.setHint("Email");*/
            }

            if(v.getId() == R.id.Savebutton) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                if(name.getText().toString() != null){
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
                }

                startActivityForResult(intent, 2); //Constants.Contacts_Manager_Request_Code
            }


        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1: //Constants.Contacts_Manager_Request_Code
                setResult(resultCode, new Intent());
                finish();
                break;
        }
    }
}
