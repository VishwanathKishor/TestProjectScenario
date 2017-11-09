package com.example.vishwanath.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vishwanath.testprojecthttpscenario.R;

/**
 * Created by android-linux-mv on 8/11/17.
 */
public class FragmentFirst extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Fragment 1", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
