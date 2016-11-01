package com.example.demo;

import com.chazuo.czlib.net.ConstantsService;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by LiQiong on 2016/10/20.
 */

public interface MyConstantsService {

    @GET("JsonServlet")
    Observable<MyEntity> getListString();
}
