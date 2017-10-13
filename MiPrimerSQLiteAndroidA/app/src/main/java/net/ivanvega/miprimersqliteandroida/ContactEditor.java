package net.ivanvega.miprimersqliteandroida;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import modelo.Contacto;

public class ContactEditor extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_editor);
        Intent caller = getIntent();
        if(caller.getParcelableExtra("Contact") != null){
             //Posible error for calling method twice.
            Contacto ct = (Contacto) caller.getParcelableExtra("Contact");
            ((EditText)findViewById(R.id.txtName)).setText(ct.getNombre());
            ((EditText)findViewById(R.id.txtEmail)).setText(ct.getCorreo_electronico());
            ((EditText)findViewById(R.id.txtTwitter)).setText(ct.getTwitter());
            ((EditText)findViewById(R.id.txtPhone)).setText(ct.getTelefono());
            ((EditText)findViewById(R.id.txtBirthDate)).setText(ct.getFecha_nacimiento().toString());

        }

        Button btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText)findViewById(R.id.txtName)).getText().toString();
                String email = ((EditText)findViewById(R.id.txtEmail)).getText().toString();
                String twitter = ((EditText)findViewById(R.id.txtTwitter)).getText().toString();
                String phone = ((EditText)findViewById(R.id.txtPhone)).getText().toString();
                Date birthDate = DatePickerFragment.chosenDate;
                Contacto contacto = new Contacto(0,name, email, twitter, phone, birthDate);
                Intent intent = getIntent();
                intent.putExtra("Contact", contacto);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        public static Date chosenDate;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //Use the current date as he default date in he picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            //Initialize chosenDate as currentDate
            chosenDate = c.getTime();
            //Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            //Do something with the chosen date.
            final Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            chosenDate = c.getTime();
        }

    }


}
