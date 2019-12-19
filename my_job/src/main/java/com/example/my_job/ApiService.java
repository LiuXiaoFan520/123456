package com.example.my_job;

import com.example.my_job.bean.FuLiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String STRINGURL="http://gank.io/api/";
    @GET("data/福利/20/1")
    Observable<FuLiBean> getData();
}
