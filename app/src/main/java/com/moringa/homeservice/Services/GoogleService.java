package com.moringa.homeservice.Services;

import com.moringa.homeservice.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleService {
    private static Retrofit retrofit;
    public static GoogleApi getUser(){
        if (retrofit == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request  newRequest = chain.request().newBuilder()
                                    .addHeader("key", Constants.SEARCH_API_KEY)
                                    .build();
                                    return chain.proceed(newRequest);
                        }
                    })
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SEARCH_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
         return retrofit.create(GoogleApi.class);
    }
}
