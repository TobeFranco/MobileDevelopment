package net.ivanvega.manejodefragmentos;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by alcohonsilver on 23/10/17.
 */

public class FragmentoUno extends Fragment {

    OnButtonClickedListener myListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragmento_uno, container);
    }

    public interface OnButtonClickedListener{
        public void onButtonClicked(int id);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            myListener = (OnButtonClickedListener) activity;
        }catch (ClassCastException ex){
            throw new ClassCastException(activity.toString() + "debe implementar onButtonClickedListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = (Button) view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.onButtonClicked(0);
            }
        });
    }
}
