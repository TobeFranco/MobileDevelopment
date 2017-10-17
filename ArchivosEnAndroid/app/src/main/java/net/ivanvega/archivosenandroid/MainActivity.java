package net.ivanvega.archivosenandroid;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText txt ;
    RadioGroup optg ;
    RadioButton optSelecccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (EditText)findViewById(R.id.txt);
        optg = (RadioGroup)findViewById(R.id.optgAlmacenamiento);
        Log.d("CICLOVIDA", "onCreate");
    }

    public void btnGuardar_click(View v) {
        if(	optg.getCheckedRadioButtonId()
                == R.id.optExterna){
            saveExternal();
        }else{
            saveInternal();
        }
    }
    public void btnAbrir_click(View v) {
        if(optg.getCheckedRadioButtonId()
                == R.id.optExterna ){
            openExternal();
        }else
        {openInternal();}
    }

    public void saveExternal() {
		File dirAppPath = getExternalFilesDir(null);
        Toast.makeText(getBaseContext(),
                Environment.getExternalStorageState(),
                Toast.LENGTH_LONG).show();


//        File dirAppPath =
//                Environment.getExternalStoragePublicDirectory
//                        (Environment.DIRECTORY_MUSIC);



        Toast.makeText(getBaseContext(),
                dirAppPath.getAbsolutePath(),
                Toast.LENGTH_LONG).show();



        File miArchivo =
                new File(dirAppPath, "notita.txt");

        try {

            FileOutputStream fos =
                    new FileOutputStream(miArchivo, false);

            OutputStreamWriter osw = new
                    OutputStreamWriter(fos);

            osw.write(txt.getText().toString());

            osw.flush();
            osw.close();

            Toast.makeText(getBaseContext(),
                    "GUARDADO EXTERNA",
                    Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    public void saveInternal() {
        File dirAppPath = getFilesDir();

        Toast.makeText(getBaseContext(),
                dirAppPath.getAbsolutePath(),
                Toast.LENGTH_LONG).show();

        File miArchivo = new File(dirAppPath, "notita.txt");

        try {

            FileOutputStream fos =
                    new FileOutputStream(miArchivo, false);

            OutputStreamWriter osw = new
                    OutputStreamWriter(fos);

            osw.write(txt.getText().toString());

            osw.flush();
            osw.close();

            Toast.makeText(getBaseContext(),
                    "GUARDADO INTERNA",
                    Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void openInternal() {
        File routeAppFile = getFilesDir();
        File myFile = new File(routeAppFile, "notita.txt");

        try{
            FileInputStream fis = new FileInputStream(myFile);
            InputStreamReader isr = new InputStreamReader(fis);
            String text = "";
            Scanner sc = new Scanner(isr);
            while(sc.hasNext()){
                text += sc.next();
            }
            txt.setText("" + text);
            sc.close();
            isr.close();
            fis.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openExternal() {
        File rutaAppFile =  getExternalFilesDir(null);
        File miArchivo = new File(rutaAppFile, "notita.txt");

        try {
            FileInputStream fis = new FileInputStream(miArchivo);
            InputStreamReader isr = new InputStreamReader(fis);
            Scanner sc = new Scanner(isr);
            String texto ="";
            while(sc.hasNext()){
                texto += sc.next();
            }
            txt.setText(texto);
            sc.close();
            isr.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
