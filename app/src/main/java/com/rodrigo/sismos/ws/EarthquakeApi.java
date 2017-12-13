package com.rodrigo.sismos.ws;

import com.rodrigo.sismos.C;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rodrigo on 13/12/17.
 */

public interface EarthquakeApi {

    @GET(C.url.EARTHQUAKE_LIST)
    public Call<ServiceResponse> getList(@Query("format") String format, @Query("starttime") String startTime, @Query("endtime") String endTime, @Query("minmagnitude") String magnitude);
}
