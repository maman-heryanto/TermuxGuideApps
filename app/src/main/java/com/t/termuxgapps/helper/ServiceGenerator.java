package com.t.termuxgapps.helper;

import com.t.termuxgapps.constant.Base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static Retrofit build(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Base.GITHUB_BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }


}
