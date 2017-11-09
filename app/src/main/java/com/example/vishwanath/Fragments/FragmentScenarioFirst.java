package com.example.vishwanath.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vishwanath.CustomAdapters.ViewPagerFSFAdapter;
import com.example.vishwanath.testprojecthttpscenario.R;

;

/**
 * Created by android-linux-mv on 8/11/17.
 */
public class FragmentScenarioFirst extends Fragment implements View.OnClickListener {
    ViewPager viewPagerFSF;
    ViewPagerFSFAdapter viewPagerFSFAdapter;
    TextView lblFSFSelected;
    Button btnFSFItemFirst, btnFSFItemSecond, btnFSFItemThird, btnFSFItemFourth, btnFSFItemFifth;
    Button btnFSFGray, btnFSFPink, btnFSFYellow;
    LinearLayout layoutFSFChangeColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_scenario_first, container, false);
            viewPagerFSF = (ViewPager) view.findViewById(R.id.viewPagerFSF);
            lblFSFSelected = (TextView) view.findViewById(R.id.lblFSFSelected);
            btnFSFItemFirst = (Button) view.findViewById(R.id.btnFSFItemFirst);
            btnFSFItemSecond = (Button) view.findViewById(R.id.btnFSFItemSecond);
            btnFSFItemThird = (Button) view.findViewById(R.id.btnFSFItemThird);
            btnFSFItemFourth = (Button) view.findViewById(R.id.btnFSFItemFourth);
            btnFSFItemFifth = (Button) view.findViewById(R.id.btnFSFItemFifth);
            btnFSFGray = (Button) view.findViewById(R.id.btnFSFGray);
            btnFSFPink = (Button) view.findViewById(R.id.btnFSFPink);
            btnFSFYellow = (Button) view.findViewById(R.id.btnFSFYellow);
            layoutFSFChangeColor = (LinearLayout) view.findViewById(R.id.layoutFSFChangeColor);

            btnFSFGray.setOnClickListener(FragmentScenarioFirst.this);
            btnFSFPink.setOnClickListener(FragmentScenarioFirst.this);
            btnFSFYellow.setOnClickListener(FragmentScenarioFirst.this);

            btnFSFItemFirst.setOnClickListener(FragmentScenarioFirst.this);
            btnFSFItemSecond.setOnClickListener(FragmentScenarioFirst.this);
            btnFSFItemThird.setOnClickListener(FragmentScenarioFirst.this);
            btnFSFItemFourth.setOnClickListener(FragmentScenarioFirst.this);
            btnFSFItemFifth.setOnClickListener(FragmentScenarioFirst.this);

            viewPagerFSFAdapter = new ViewPagerFSFAdapter(getActivity().getSupportFragmentManager());
            viewPagerFSF.setAdapter(viewPagerFSFAdapter);

            return view;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnFSFItemFirst:
                case R.id.btnFSFItemSecond:
                case R.id.btnFSFItemThird:
                case R.id.btnFSFItemFourth:
                case R.id.btnFSFItemFifth:
                    Button button = (Button) v;
                    lblFSFSelected.setText(button.getText().toString());
                    break;
                case R.id.btnFSFGray:
                    layoutFSFChangeColor.setBackgroundColor(Color.GRAY);
                    break;
                case R.id.btnFSFPink:
                    layoutFSFChangeColor.setBackgroundColor(Color.MAGENTA);
                    break;
                case R.id.btnFSFYellow:
                    layoutFSFChangeColor.setBackgroundColor(Color.YELLOW);
                    break;
            }
        } catch (TypeNotPresentException ex) {
            ex.printStackTrace();
        }
    }
}
