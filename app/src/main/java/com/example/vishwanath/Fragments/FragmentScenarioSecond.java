package com.example.vishwanath.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vishwanath.DataBinding.TravelGuidance;
import com.example.vishwanath.Utility.Utility;
import com.example.vishwanath.testprojecthttpscenario.R;
import com.example.vishwanath.testprojecthttpscenario.databinding.FragmentScenarioSecondBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android-linux-mv on 8/11/17.
 */

public class FragmentScenarioSecond extends Fragment implements
        Spinner.OnItemSelectedListener, View.OnClickListener {
    Spinner spnFSSNames;
    TextView lblFSSfromcentral;
    Button btnFSSNavigate;
    Activity activity;
    JSONArray result;
    List<String> spnName;
    int selectedPosition;
    private FragmentScenarioSecondBinding binding;
    TravelGuidance travelGuidance;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_scenario_second, container, false);
            View view = binding.getRoot();
            spnFSSNames = (Spinner) view.findViewById(R.id.spnFSSNames);
            lblFSSfromcentral = (TextView) view.findViewById(R.id.lblFSSfromcentral);
            btnFSSNavigate = (Button) view.findViewById(R.id.btnFSSNavigate);
            spnName = new ArrayList<>();
            spnFSSNames.setOnItemSelectedListener(FragmentScenarioSecond.this);
            btnFSSNavigate.setOnClickListener(FragmentScenarioSecond.this);
            activity = getActivity();
            if (Utility.isNetworkAvailable(activity)) {
                getJsonData();
            } else {
                Toast.makeText(activity, "We sorry you don't have internet connection", Toast.LENGTH_SHORT).show();
            }

            travelGuidance = new TravelGuidance("");
            travelGuidance.setFromcentral("");
            binding.setTravelGuidance(travelGuidance);
            // binding.setActivity(activity);
            return view;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            selectedPosition = position;
            JSONObject jsonObject = result.getJSONObject(position);
            JSONObject fromcentral = jsonObject.getJSONObject("fromcentral");
            String textData = "";
            if (fromcentral.has("car")) {
                textData = "Car : " + fromcentral.getString("car");
            }
            if (fromcentral.has("train")) {
                textData = textData
                        + "\n" + "Train : " + fromcentral.getString("train");
            }
            travelGuidance.setFromcentral(textData);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFSSNavigate:
                try {
                    JSONObject jsonObject = result.getJSONObject(selectedPosition);
                    JSONObject location = jsonObject.getJSONObject("location");
                    Uri gmmIntentUri = Uri.parse("geo:" + location.getString("latitude") + "," + location.getString("longitude"));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(activity.getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    private void getJsonData() {
        try {
            final ProgressDialog progressDialog = ProgressDialog.show(activity, "",
                    "Loding..");
            progressDialog.setContentView(R.layout.custom_progressbar);
            RequestQueue queue = Volley.newRequestQueue(activity);
            String url = "http://express-it.optusnet.com.au/sample.json";

            JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {
                            result = response;
                            if (response != null) {
                                spnName = new ArrayList<>();
                                for (int i = 0; i < result.length(); i++) {
                                    try {
                                        JSONObject jsonObject = result.getJSONObject(i);
                                        spnName.add(jsonObject.getString("name"));
                                    } catch (JSONException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                spnFSSNames.setAdapter(new ArrayAdapter(activity,
                                        android.R.layout.simple_spinner_dropdown_item, spnName));
                            }
                            progressDialog.dismiss();
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Auto-generated method stub
                    Toast.makeText(activity, "Unable to connect server..!", Toast.LENGTH_LONG).show();
                }
            });
            queue.add(jsObjRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
