package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllExhibitJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllStuffJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ImageByIDResponse;


public interface ApiService {

    @GET("stuff/getall")
    Call<AllStuffJsonResponse> getAllStuffs();
    @GET("Exhibit/GetPaging")
    Call<AllExhibitJsonResponse> getExhibitByPage(@Query("pageindex") int index,
                                                    @Query("pagesize") int size);
    @GET("Exhibit/GetById")
    Call<ExhibitModel> getExhibitById(@Query("id") int id);

    @GET("Exhibit/GetImage")
    Call<String> getExhibitImageById(@Query("id") int id,
                                     @Query("thumbnail") boolean thumbnail);
    @GET("Exhibit/GetImages")
    Call<ImageByIDResponse> getAllExhibitImageById(@Query("id") int id,
                                                   @Query("thumbnail") boolean thumbnail);






}
