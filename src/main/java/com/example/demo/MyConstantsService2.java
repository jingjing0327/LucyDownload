package com.example.demo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by LiQiong on 2016/10/20.
 */

public interface MyConstantsService2 {

    @GET("JsonServlet")
    Observable<MyEntity> getListString();
}
