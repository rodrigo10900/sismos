package com.rodrigo.sismos.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rodrigo.sismos.C;
import com.rodrigo.sismos.R;
import com.rodrigo.sismos.Utilities;
import com.rodrigo.sismos.adapter.AdapterData;
import com.rodrigo.sismos.adapter.AdapterIndexListener;
import com.rodrigo.sismos.ws.EarthquakeApi;
import com.rodrigo.sismos.ws.Features;
import com.rodrigo.sismos.ws.RetrofitClient;
import com.rodrigo.sismos.ws.ServiceResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rodrigo on 13/12/17.
 */

public class FragmentList extends Fragment implements AdapterIndexListener {

    private final String TAG = getClass().getSimpleName();
    private ArrayList<Features> listElements;
    private AdapterData adapter;
    private String startDate, endDate, magnitude;

    @BindView(R.id.layoutGeneral)
    View layoutGeneral;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        listElements = args.getParcelableArrayList(C.data.DATA_LIST);
        magnitude = args.getString(C.data.DATA_MAGNITUDE);
        startDate = args.getString(C.data.DATA_START_DATE);
        endDate = args.getString(C.data.DATA_END_DATE);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (listElements != null) {
            adapter = new AdapterData(getActivity(), listElements);
            adapter.setAdapterListener(this);
            list.setAdapter(adapter);
        } else {

        }
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                EarthquakeApi ws = RetrofitClient.getClient().create(EarthquakeApi.class);
                ws.getList(C.formatRequest, startDate, endDate, magnitude).enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        if (response.isSuccessful()) {
                            try {
                                if (response.body().getFeatures() != null && response.body().getFeatures().size() > 0) {
                                    adapter = new AdapterData(getActivity(), response.body().getFeatures());
                                    adapter.setAdapterListener(FragmentList.this);
                                    list.setAdapter(adapter);
                                } else {
                                    Utilities.showSnackBar(layoutGeneral, R.string.errorNoResults, Snackbar.LENGTH_LONG);
                                }
                                swipeRefresh.setRefreshing(false);
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
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
    }

    @Override
    public void onAdapterItemClick(int index) {
        Features element = listElements.get(index);
        FragmentDetail fragment = new FragmentDetail();
        Bundle args = new Bundle();
        args.putParcelable(C.data.DATA_SELECTED, element);
        fragment.setArguments(args);
        Utilities.replaceFragment(getFragmentManager(), fragment, R.id.content, true, true);
    }
}
