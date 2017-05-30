package com.olifia.welcome;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WriteDiaryFragment extends Fragment {
    FrameLayout writeframe;


    public WriteDiaryFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_write_diary, container,false);
        writeframe = (FrameLayout) view.findViewById(R.id.writeframe);
        writeframe.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), tulis.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
