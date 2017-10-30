package net.ivanvega.manejodefragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * Created by alcohonsilver on 23/10/17.
 */

public class FragemntoDos extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.layout_fragmeno_dos,container);
        if(savedInstanceState != null){
            String text = savedInstanceState.getString("daString");
            ((EditText) v.findViewById(R.id.editText)).setText(text);
        }
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_fragment_dos, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        EditText daBox = (EditText) getView().findViewById(R.id.editText);
        outState.putString("daString", daBox.getText().toString());
        super.onSaveInstanceState(outState);
    }


}
