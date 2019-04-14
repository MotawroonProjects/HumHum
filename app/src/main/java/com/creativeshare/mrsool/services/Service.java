package com.creativeshare.mrsool.services;

import com.creativeshare.mrsool.models.AppDataModel;
import com.creativeshare.mrsool.models.CommentDataModel;
import com.creativeshare.mrsool.models.NearDelegateDataModel;
import com.creativeshare.mrsool.models.NearbyStoreDataModel;
import com.creativeshare.mrsool.models.NotificationCountModel;
import com.creativeshare.mrsool.models.NotificationDataModel;
import com.creativeshare.mrsool.models.OrderDataModel;
import com.creativeshare.mrsool.models.OrderIdDataModel;
import com.creativeshare.mrsool.models.PlaceDetailsModel;
import com.creativeshare.mrsool.models.PlaceGeocodeData;
import com.creativeshare.mrsool.models.PlaceMapDetailsData;
import com.creativeshare.mrsool.models.SearchDataModel;
import com.creativeshare.mrsool.models.SliderModel;
import com.creativeshare.mrsool.models.UserModel;
import com.creativeshare.mrsool.models.WatingOrderData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Service {

    @GET("place/nearbysearch/json")
    Call<NearbyStoreDataModel> getNearbyStores(@Query(value = "location") String location,
                                               @Query(value = "radius") int radius,
                                               @Query(value = "type") String type,
                                               @Query(value = "key") String key
    );

    @GET("place/findplacefromtext/json")
    Call<SearchDataModel> getNearbyStoresWithKeyword(@Query(value = "locationbias") String location,
                                                     @Query(value = "inputtype") String inputtype,
                                                     @Query(value = "input") String input,
                                                     @Query(value = "fields") String fields,
                                                     @Query(value = "language") String language,
                                                     @Query(value = "key") String key
    );

    @GET("place/details/json")
    Call<PlaceDetailsModel> getPlaceDetails(@Query(value = "placeid") String placeid,
                                            @Query(value = "fields") String fields,
                                            @Query(value = "language") String language,
                                            @Query(value = "key") String key
    );


    @GET("place/findplacefromtext/json")
    Call<PlaceMapDetailsData> searchOnMap(@Query(value = "inputtype") String inputtype,
                                          @Query(value = "input") String input,
                                          @Query(value = "fields") String fields,
                                          @Query(value = "language") String language,
                                          @Query(value = "key") String key
    );

    @GET("geocode/json")
    Call<PlaceGeocodeData> getGeoData(@Query(value = "latlng") String latlng,
                                      @Query(value = "language") String language,
                                      @Query(value = "key") String key);

    @FormUrlEncoded
    @POST("/Api/signup")
    Call<UserModel> signUpWithoutImage(@Field("user_email") String user_email,
                                       @Field("user_phone") String user_phone,
                                       @Field("user_phone_code") String user_phone_code,
                                       @Field("user_full_name") String user_full_name,
                                       @Field("user_gender") String user_gender,
                                       @Field("user_country") String user_country,
                                       @Field("user_age") long user_age

    );


    @Multipart
    @POST("/Api/signup")
    Call<UserModel> signUpWithImage(@Part("user_email") RequestBody user_email,
                                    @Part("user_phone") RequestBody user_phone,
                                    @Part("user_phone_code") RequestBody user_phone_code,
                                    @Part("user_full_name") RequestBody user_full_name,
                                    @Part("user_gender") RequestBody user_gender,
                                    @Part("user_country") RequestBody user_country,
                                    @Part("user_age") RequestBody user_age,
                                    @Part MultipartBody.Part image
    );


    @FormUrlEncoded
    @POST("/Api/login")
    Call<UserModel> signIn(@Field("user_phone") String user_phone,
                           @Field("user_phone_code") String user_phone_code

    );

    @GET("/Api/appDetails")
    Call<AppDataModel> getAppData(@Query("type") int type);

    @FormUrlEncoded
    @POST("/Api/updateLocation")
    Call<ResponseBody> updateLocation(@Field("user_id") String user_id,
                                      @Field("user_google_lat") double user_google_lat,
                                      @Field("user_google_long") double user_google_long
    );

    @FormUrlEncoded
    @POST("/Api/updateToken")
    Call<ResponseBody> updateToken(@Field("user_id") String user_id,
                                   @Field("user_token_id") String user_token_id

    );

    @GET("/Api/slider")
    Call<SliderModel> getAds();

    @GET("/Api/driverList")
    Call<NearDelegateDataModel> getDelegate(@Query("mylat") double lat, @Query("mylong") double lng, @Query("page") int page_index);

    @FormUrlEncoded
    @POST("/Api/logout")
    Call<ResponseBody> logOut(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("/Api/addOrder")
    Call<OrderIdDataModel> sendOrder(@Field("client_id") String client_id,
                                     @Field("client_address") String client_address,
                                     @Field("client_lat") double client_lat,
                                     @Field("client_long") double client_long,
                                     @Field("driver_id") String driver_id,
                                     @Field("order_details") String order_details,
                                     @Field("place_google_id") String place_google_id,
                                     @Field("place_lat") double place_lat,
                                     @Field("place_long") double place_long,
                                     @Field("order_time_arrival") long order_time_arrival
    );

    @FormUrlEncoded
    @POST("/Api/visit")
    Call<ResponseBody> updateVisit(@Field("type") String type, @Field("day_date") String day_date);

    @Multipart
    @POST("/Api/beDriver")
    Call<UserModel> registerDelegate(@Part("user_id") RequestBody user_id,
                                     @Part("user_card_id") RequestBody user_card_id,
                                     @Part("user_address") RequestBody user_address,
                                     @Part MultipartBody.Part user_card_id_image,
                                     @Part MultipartBody.Part user_driving_license
    );

    @Multipart
    @POST("/Api/profile")
    Call<UserModel> updateImage(@Part("user_id") RequestBody user_id,
                                @Part("user_email") RequestBody user_email,
                                @Part("user_full_name") RequestBody user_full_name,
                                @Part("user_country") RequestBody user_country,
                                @Part("user_gender") RequestBody user_gender,
                                @Part("user_age") RequestBody user_age,
                                @Part("user_address") RequestBody user_address,
                                @Part("user_phone_code") RequestBody user_phone_code,
                                @Part("user_phone") RequestBody user_phone,

                                @Part MultipartBody.Part user_image
    );

    @Multipart
    @POST("/Api/profile")
    Call<UserModel> updateProfile(@Part("user_id") RequestBody user_id,
                                  @Part("user_email") RequestBody user_email,
                                  @Part("user_full_name") RequestBody user_full_name,
                                  @Part("user_country") RequestBody user_country,
                                  @Part("user_gender") RequestBody user_gender,
                                  @Part("user_age") RequestBody user_age,
                                  @Part("user_address") RequestBody user_address,
                                  @Part("user_phone_code") RequestBody user_phone_code,
                                  @Part("user_phone") RequestBody user_phone
    );

    @GET("/Api/placeOrders")
    Call<WatingOrderData> getWaitingOrders(@Query("place_id") String place_id, @Query("page") int page);

    @GET("/Api/clientOrders")
    Call<OrderDataModel> getClientOrders(@Query("user_id") String user_id, @Query("order_type") String order_type, @Query("page") int page);


    @GET("/Api/driverOrders")
    Call<OrderDataModel> getDelegateOrders(@Query("user_id") String user_id, @Query("order_type") String order_type, @Query("page") int page);

    @FormUrlEncoded
    @POST("/Api/alerts")
    Call<NotificationCountModel> getNotificationCount(@Field("user_id") String user_id, @Field("type") String type);

    @FormUrlEncoded
    @POST("/Api/alerts")
    Call<ResponseBody> readNotification(@Field("user_id") String user_id, @Field("type") String type);


    @GET("/Api/notification")
    Call<NotificationDataModel> getNotification(@Query("user_id") String user_id, @Query("user_type") String user_type, @Query("page") int page);


    @FormUrlEncoded
    @POST("/Api/driverAction")
    Call<ResponseBody> delegateAccept(@Field("driver_id") String driver_id,
                                      @Field("client_id") String client_id,
                                      @Field("order_id") String order_id,
                                      @Field("type") String type,
                                      @Field("driver_offer") String driver_offer
    );

    @FormUrlEncoded
    @POST("/Api/driverAction")
    Call<ResponseBody> delegateRefuse_Finish(@Field("driver_id") String driver_id,
                                             @Field("client_id") String client_id,
                                             @Field("order_id") String order_id,
                                             @Field("type") String type
    );

    @FormUrlEncoded
    @POST("/Api/clientAction")
    Call<ResponseBody> clientAccept_Refuse(@Field("client_id") String client_id,
                                           @Field("driver_id") String driver_id,
                                           @Field("order_id") String order_id,
                                           @Field("type") String type
    );

    @GET("/Api/comment")
    Call<CommentDataModel> getDelegateComment(@Query("user_id") String user_id, @Query("user_type") String user_type, @Query("page") int page);

    @FormUrlEncoded
    @POST("/Api/cancelOrder")
    Call<ResponseBody> clientCancelOrder(@Field("driver_id") String driver_id
    );

    @FormUrlEncoded
    @POST("/Api/changeDriver")
    Call<ResponseBody> resendOrderToDifferentDelegate(@Field("client_id") String client_id,
                                                      @Field("driver_id") String driver_id,
                                                      @Field("order_id") String order_id
    );

    @FormUrlEncoded
    @POST("/Api/moveOrder")
    Call<ResponseBody> movementDelegate(@Field("order_id") String order_id,
                                        @Field("order_movement") String order_movement
    );
}


