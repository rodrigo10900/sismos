package com.rodrigo.sismos;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.rodrigo.sismos.dialog.DatePickerListener;
import com.rodrigo.sismos.dialog.DialogDatePicker;

/**
 * Created by rodrigo on 13/12/17.
 */

public class Utilities {

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }

    public static void showSnackBar(View view, int textResource, int duration) {
        Snackbar.make(view, textResource, duration).show();
    }

    public static void showSnackBar(View view, String text, int duration) {
        Snackbar.make(view, text, duration).show();
    }

    public static void replaceFragment(FragmentManager manager, Fragment nextFragment, int viewId, boolean effect, boolean backStack) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(viewId, nextFragment);
        if (backStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void openDateDialog(FragmentManager manager, int titleResource, long maxDate, long minDate, int requestCode, DatePickerListener dateListener) {
        DialogDatePicker dialog = new DialogDatePicker();
        dialog.setTitleResource(titleResource);
        dialog.setMaxDate(maxDate);
        dialog.setMinDate(minDate);
        dialog.setDateListener(dateListener);
        dialog.setRequestCode(requestCode);
        dialog.show(manager, null);
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("Utilities", "Error:", e);
        }
    }
}
