package com.example.my_from;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    public static final String PRO_URL = "https://www.wanandroid.com/";
    @GET("project/list/1/json")
    Observable<ProjectBean> getDatas(@Query("query") int query);
}
