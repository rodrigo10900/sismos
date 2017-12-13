package com.rodrigo.sismos.fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.rodrigo.sismos.C;
import com.rodrigo.sismos.R;
import com.rodrigo.sismos.Utilities;
import com.rodrigo.sismos.dialog.DatePickerListener;
import com.rodrigo.sismos.ws.EarthquakeApi;
import com.rodrigo.sismos.ws.Features;
import com.rodrigo.sismos.ws.RetrofitClient;
import com.rodrigo.sismos.ws.ServiceResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rodrigo on 13/12/17.
 */

public class FragmentForm extends Fragment implements DatePickerListener {

    private final String TAG = getClass().getSimpleName();
    private final int REQUEST_STARTDATE = 100;
    private final int REQUEST_ENDDATE = 101;
    private SimpleDateFormat format;

    @BindView(R.id.layoutGeneral)
    View layoutGeneral;
    @BindView(R.id.txtMagnitude)
    AppCompatEditText txtMagnitude;
    @BindView(R.id.txtStartDate)
    AppCompatEditText txtStartDate;
    @BindView(R.id.txtEndDate)
    AppCompatEditText txtEndDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        format = new SimpleDateFormat(C.formatDateRequest);
        layoutGeneral.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utilities.hideSoftKeyboard(getActivity());
                return false;
            }
        });
    }

    @OnClick(R.id.imgStartDate)
    void selectStartDate() {
        Utilities.openDateDialog(getFragmentManager(), R.string.hintStartDate, Calendar.getInstance().getTimeInMillis(), 0, REQUEST_STARTDATE, this);
    }

    @OnClick(R.id.imgEndDate)
    void selectEndDate() {
        Utilities.openDateDialog(getFragmentManager(), R.string.hintEndDate, Calendar.getInstance().getTimeInMillis(), 0, REQUEST_ENDDATE, this);
    }

    @OnClick(R.id.btnSearch)
    void search() {
        if (Utilities.isConnected(getActivity())) {
            final String startDate = txtStartDate.getText().toString();
            final String endDate = txtEndDate.getText().toString();
            final String magnitude = txtMagnitude.getText().toString();
            if (magnitude.isEmpty()) {
                txtMagnitude.setError(getString(R.string.errorMagnitude));
            } else if (startDate.isEmpty()) {
                txtStartDate.setError(getString(R.string.errorStartDate));
            } else if (endDate.isEmpty()) {
                txtEndDate.setError(getString(R.string.errorEndDate));
            } else {
                EarthquakeApi ws = RetrofitClient.getClient().create(EarthquakeApi.class);
                ws.getList(C.formatRequest, startDate, endDate, magnitude).enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        if (response.isSuccessful()) {
                            try {
                                if (response.body().getFeatures() != null && response.body().getFeatures().size() > 0) {
                                    Fragment fragment = new FragmentList();
                                    Bundle args = new Bundle();
                                    args.putParcelableArrayList(C.data.DATA_LIST, response.body().getFeatures());
                                    args.putString(C.data.DATA_MAGNITUDE, magnitude);
                                    args.putString(C.data.DATA_START_DATE, startDate);
                                    args.putString(C.data.DATA_END_DATE, endDate);
                                    fragment.setArguments(args);
                                    Utilities.hideSoftKeyboard(getActivity());
                                    Utilities.replaceFragment(getFragmentManager(), fragment, R.id.content, true, true);
                                } else {
                                    Utilities.showSnackBar(layoutGeneral, R.string.errorNoResults, Snackbar.LENGTH_LONG);
                                }
                            } catch (Exception e) {
                                Log.e(TAG, "Error", e);
                                onFailure(null, null);
                            }
                        } else {
                            onFailure(null, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceResponse> call, Throwable t) {
                        Utilities.showSnackBar(layoutGeneral, R.string.errorServer, Snackbar.LENGTH_LONG);
                    }
                });
            }
        } else {
            Utilities.showSnackBar(layoutGeneral, R.string.errorNoConnection, Snackbar.LENGTH_LONG);
        }
    }

    @OnClick(R.id.btnLastSearch)
    void lastSearch() {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            Features selected = new Gson().fromJson(preferences.getString(C.data.DATA_SELECTED, ""), Features.class);
            FragmentDetail fragment = new FragmentDetail();
            Bundle args = new Bundle();
            args.putParcelable(C.data.DATA_SELECTED, selected);
            fragment.setArguments(args);
            Utilities.replaceFragment(getFragmentManager(), fragment, R.id.content, true, true);
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
            Utilities.showSnackBar(layoutGeneral, R.string.errorNoLast, Snackbar.LENGTH_LONG);
        }
    }

    @Override
    public void onDateSelection(long selectedDate, int requestCode) {
        switch (requestCode) {
            case REQUEST_STARTDATE:
                txtStartDate.setText(format.format(new Date(selectedDate)));
                break;
            case REQUEST_ENDDATE:
                txtEndDate.setText(format.format(new Date(selectedDate)));
                break;
        }
    }
}
