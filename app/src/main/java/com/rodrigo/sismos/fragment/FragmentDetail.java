package com.rodrigo.sismos.fragment;

import android.Manifest;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.rodrigo.sismos.C;
import com.rodrigo.sismos.R;
import com.rodrigo.sismos.ws.Features;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by rodrigo on 13/12/17.
 */

public class FragmentDetail extends Fragment implements OnMapReadyCallback {

    private final String TAG = getClass().getSimpleName();
    private final int REQUEST_LOCATION = 100;
    private Features feature;
    private MapFragment mapFragment;
    private GoogleMap map;

    @BindView(R.id.lblTitle)
    TextView lblTitle;
    @BindView(R.id.lblMagnitude)
    TextView lblMagnitude;
    @BindView(R.id.lblDate)
    TextView lblDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        feature = getArguments().getParcelable(C.data.DATA_SELECTED);
        mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lblTitle.setText(feature.getProperties().getPlace());
        lblMagnitude.setText(String.format(getString(R.string.lblMagnitudeFormat), feature.getProperties().getMagnitude()));
        lblDate.setText(String.format(getString(R.string.lblDateFormat), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date(feature.getProperties().getTime()))));
        saveLastSelection();
    }

    private void saveLastSelection() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(C.data.DATA_SELECTED, new Gson().toJson(feature));
        editor.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        LatLng point = new LatLng(feature.getGeometry().getCoordinates().get(1), feature.getGeometry().getCoordinates().get(0));
        map.addMarker(new MarkerOptions().position(point).title(feature.getProperties().getTitle()));
        map.moveCamera(CameraUpdateFactory.newLatLng(point));
        showMyLocation();
    }

    private void showMyLocation() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                map.setMyLocationEnabled(true);
                map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        Location dest = new Location("dest");
                        dest.setLatitude(feature.getGeometry().getCoordinates().get(1));
                        dest.setLongitude(feature.getGeometry().getCoordinates().get(0));
                        float distance = location.distanceTo(dest);
                        if (distance < 300000) {
                            Notification.Builder builder = new Notification.Builder(getActivity());
                            builder.setContentTitle(getString(R.string.app_name));
                            builder.setContentText(getString(R.string.notificationText));
                            builder.setColor(getResources().getColor(R.color.colorPrimary));
                            builder.setAutoCancel(true);
                            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                            builder.setSound(uri);
                            builder.setSmallIcon(R.mipmap.ic_launcher);
                            NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                            manager.notify(new Random().nextInt(10000), builder.build());
                        }
                    }
                });
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showMyLocation();
            }
        }
    }
}
