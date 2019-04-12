package fupp.mvp_demo.api;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fupp on 2017/7/10.
 */

public interface ApiService {

    /*//获取token
    @FormUrlEncoded
    @POST("token")
    Observable<TokenResult> token(@Field("username") String userName, @Field("grant_type") String grantType);*/


    //upload file
    @Multipart
    @Headers("Connection: keep-alive")
    @POST("improveinfo/uploadvideo")
    Observable<String> uploadVideo(
            @Part MultipartBody.Part file
    );

    //从网络获取图片
    @GET
    Observable<ResponseBody> getBitmapFromNet(@Url String fileUrl);

    //从网络获取图片
    @GET
    Observable<String> getBaiDu(@Url String fileUrl);


}
