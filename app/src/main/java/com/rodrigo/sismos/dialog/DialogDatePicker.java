package com.rodrigo.sismos.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;


import com.rodrigo.sismos.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rodrigo on 13/12/2017.
 */

public class DialogDatePicker extends DialogFragment {

    private final String TAG = getClass().getSimpleName();
    private long maxDate, minDate;
    private int titleResource, requestCode;
    private DatePickerListener dateListener;
    @BindView(R.id.datePicker)
    protected DatePicker datePicker;
    @BindView(R.id.lblTitle)
    protected TextView lblTitle;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null, false);
        ButterKnife.bind(this, view);
        if (titleResource == 0)
            lblTitle.setVisibility(View.GONE);
        else
            lblTitle.setText(titleResource);
        if (maxDate > 0)
            datePicker.setMaxDate(maxDate);
        if (minDate > 0)
            datePicker.setMinDate(minDate);

        builder.setView(view);
        return builder.create();
    }

    @OnClick(R.id.btnCancel)
    protected void cancel() {
        getDialog().dismiss();
    }

    @OnClick(R.id.btnAccept)
    protected void selectDate() {
        if (dateListener != null) {
            Calendar selected = Calendar.getInstance();
            selected.set(Calendar.YEAR, datePicker.getYear());
            selected.set(Calendar.MONTH, datePicker.getMonth());
            selected.set(Calendar.DATE, datePicker.getDayOfMonth());
            selected.set(Calendar.HOUR_OF_DAY, 0);
            selected.set(Calendar.MINUTE, 0);
            dateListener.onDateSelection(selected.getTimeInMillis(), requestCode);
        }
        getDialog().dismiss();
    }

    public long getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(long maxDate) {
        this.maxDate = maxDate;
    }

    public long getMinDate() {
        return minDate;
    }

    public void setMinDate(long minDate) {
        this.minDate = minDate;
    }

    public int getTitleResource() {
        return titleResource;
    }

    public void setTitleResource(int titleResource) {
        this.titleResource = titleResource;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public DatePickerListener getDateListener() {
        return dateListener;
    }

    public void setDateListener(DatePickerListener dateListener) {
        this.dateListener = dateListener;
    }
}
