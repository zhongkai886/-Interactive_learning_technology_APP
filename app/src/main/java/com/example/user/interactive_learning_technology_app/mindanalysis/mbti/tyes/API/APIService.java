package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API;


import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.BindMetaData.OutPutData;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.LoginApi.Example;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.LoginApi.InputLogin;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.PostData.OutputPost;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Report.Data;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Report.Datum;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by a2734043 on 2018/6/6.
 */
public interface APIService {

    @retrofit2.http.POST("/account/api/v1/login")
//    @FormUrlEncoded
    Call<Example> Login(@Body InputLogin inputLogin);
//
    @retrofit2.http.POST("/datas/api/v1/brainwavesessions/")
    Call<OutputPost> DataPost(@Body com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.PostData.Data params);

    @GET("/distributor/api/v1/itemorders/")
    Call<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.ConfirmData.Data> ConfirmMetaData(@Query("user") String user, @Query("storeItem__sku") String storeItem__sku, @Query("meta__isnull") String meta__isnull);




//    @GET("/distributor/api/v1/customers/reports/a08db323-8c69-4c78-a168-1af9fa1a7e4f/")
//    Call<com.alchemy.NetWork.API.GetReport.Data> getReport(@Query("json") boolean json);

    @PATCH("/distributor/api/v1/itemorders/{id}/")
    Call<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.BindMetaData.Data> bindData(@Body OutPutData outPutData, @Path("id") int id);
   //54.174.149.227:8080/calculate16Personality
        @POST("/calculate16Personality")
        Call<Data> Report(@Body ArrayList<Datum> datum);
//    @FormUrlEncoded


}
