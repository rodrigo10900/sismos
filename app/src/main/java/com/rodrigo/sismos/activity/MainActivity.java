package com.rodrigo.sismos.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.rodrigo.sismos.R;
import com.rodrigo.sismos.Utilities;
import com.rodrigo.sismos.fragment.FragmentForm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_simple);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Fragment fragment = new FragmentForm();
        Utilities.replaceFragment(getFragmentManager(), fragment, R.id.content, false, false);
    }
}
