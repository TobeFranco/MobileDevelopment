package com.tobefranco.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Tobe on 08/09/2017.
 */

public class InsertContact extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_contact);
        Button btnAgregar = (Button) findViewById(R.id.btnAdd);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                String user = ((EditText)findViewById(R.id.txtUser)).getText().toString();
                String email = ((EditText)findViewById(R.id.txtEmail)).getText().toString();
                String phone = ((EditText)findViewById(R.id.txtPhone)).getText().toString();
                String twitter = ((EditText)findViewById(R.id.txtTwitter)).getText().toString();
                String birthdate = ((EditText)findViewById(R.id.txtBirthDate)).getText().toString();
                Contact contact = new Contact(user, email, twitter, phone, birthdate);

                Intent intent = getIntent();
                intent.putExtra("Contact", contact);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
