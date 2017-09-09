package com.tobefranco.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lsview;
    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contacts = new ArrayList<Contact>();
        contacts.add(new Contact("Tobe Franco", "tobe@gmail.com", "@TobeFranco", "445-125-01-14", "December 10th 1995"));
        contacts.add(new Contact("Juan Pito", "jcguz@outlook.com", "@johnGuz", "445-140-20-28", "January 3rd 1996"));
        contacts.add(new Contact("Mane Perez", "mane_shippuden", "@maneGaara", "443-482-25-69", "God knows when"));

        this.lsview = (ListView)findViewById(R.id.lsv);
        lsview.setAdapter(new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_2,android.R.id.text1, contacts){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(contacts.get(position).getUser());
                text2.setText(contacts.get(position).getEmail());
                return view;
            }
        });

        Button btnAgregar = (Button) findViewById(R.id.btnAdd);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InsertContact.class);
                startActivityForResult(intent, 150);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 150 && resultCode == RESULT_OK){
            Contact contact =  (Contact) data.getSerializableExtra("Contact");
            contacts.add(contact);
            ((ArrayAdapter)lsview.getAdapter()).notifyDataSetChanged();
        }
    }

}
