package net.ivanvega.miprimersqliteandroida;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.Contacto;
import modelo.DAOContactos;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ListView lsvC;
    private List<Contacto> contactos;
    private AppCompatActivity activity;
    private final int INTENT_RESULT_UPDATE = 34;

    public MainActivityFragment() {

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            activity = (AppCompatActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final DAOContactos dao = new DAOContactos(getActivity());
/**

        try{
            SimpleDateFormat frm = new SimpleDateFormat("dd-MM-yyyy");
            Date dato = frm.parse("10-12-1995 00:00:00");
            dao.insert(new Contacto(0, "Tobe Franco", "tobe117@gmail.es", "@tobhelio", "4451250113",
                    dato));
        }catch (ParseException ex){
            Toast.makeText(this.getActivity(), "An error has ocurred", Toast.LENGTH_SHORT);
        }
 **/

        View v = inflater.inflate(R.layout.fragment_main, container, false);


        lsvC = v.findViewById(R.id.lsvContactos);
        contactos = dao.getAll();
        ArrayAdapter<Contacto> adp = new ArrayAdapter<Contacto>(getContext(),
                android.R.layout.simple_list_item_1, contactos);

        lsvC.setAdapter(adp);
        lsvC.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                Toast.makeText(activity,
                        "seleccionado el elemento " + position, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder menu = new AlertDialog.Builder(activity);
                CharSequence[] options = {"Editar", "Eliminar"};
                menu.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0: // Case Edit
                                Intent intent = new Intent(activity, ContactEditor.class);
                                intent.putExtra("Contact", contactos.get(position));
                                activity.startActivityForResult(intent, INTENT_RESULT_UPDATE);
                                break;
                            case 1: // Case Delete
                                dao.delete(contactos.get(position));
                                contactos = dao.getAll();
                                ((ArrayAdapter)lsvC.getAdapter()).notifyDataSetChanged();
                                break;
                        }
                    }
                });
                menu.create().show();
                return true;
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_RESULT_UPDATE){
            Contacto contact = (Contacto) data.getParcelableExtra("Contact");
            DAOContactos dao = new DAOContactos(activity);
            dao.update(contact);
            contactos = dao.getAll();
            ((ArrayAdapter)lsvC.getAdapter()).notifyDataSetChanged();
        }
    }
}
