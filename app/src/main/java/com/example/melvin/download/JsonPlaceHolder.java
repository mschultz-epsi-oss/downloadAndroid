package com.example.melvin.download;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by melvin on 02/06/17.
 */

public interface JsonPlaceHolder {
    @GET("users")
    Call<List<User>> listUsers();
}
