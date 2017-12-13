package com.rodrigo.sismos.ws;

import com.rodrigo.sismos.C;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rodrigo on 13/12/17.
 */

public class RetrofitClient {

    private static Retrofit client;

    public static Retrofit getClient() {
        if (client == null) {
            client = new Retrofit.Builder()
                    .baseUrl(C.url.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return client;
    }
}
