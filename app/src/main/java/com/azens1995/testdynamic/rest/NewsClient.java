package com.azens1995.testdynamic.rest;

import com.azens1995.testdynamic.model.Data;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Azens Eklak on 8/7/18.
 */
public interface NewsClient {

    @GET("5b7d039c3300005d004a00c3")
    Call<List<Data>> getData();

}
