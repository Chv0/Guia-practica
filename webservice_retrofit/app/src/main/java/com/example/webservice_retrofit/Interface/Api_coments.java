package com.example.webservice_retrofit.Interface;

import com.example.webservice_retrofit.Model.post_Coments;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_coments {
    @GET("posts/1/comments")
    Call<List<post_Coments>> getPosts();
}
