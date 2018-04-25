package org.faqrobot.textrecyclerview.http.api;

import org.faqrobot.textrecyclerview.http.bean.AccessToken;
import org.faqrobot.textrecyclerview.http.bean.EmpowerBean;
import org.faqrobot.textrecyclerview.http.bean.GetRobotResultV4;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yunwen on 2017/12/28 0028.
 */

public interface FaqRobotApi {

    //聊天的接口
    @FormUrlEncoded
    @POST("servlet/robotChat")
    Observable<GetRobotResultV4> robotChat(
            @Query("face_token") String face_token,
            @Query("s") String s,
            @Query("token") String token,
            @Query("timestamp") long timestamp,
            @Query("clientId") String clientId,
            @Query("sysNum") String sysNum,
            @Query("sourceId") String sourceId,
            @Query("userId") String userId,
            @Field("question") String question,
            @Query("original") boolean original);


    //获取token
    @Headers("Cache-Control:public,max-age=43200")
    @POST("token/getToken")
    Observable<AccessToken> getAccessToken(@Query("appId") String appId,
                                           @Query("secret") String secret);

    //授权1
//    @GET("servlet/robotChat")
//    Observable<EmpowerBean> emPower(@Query("s") String s,
//                                    @Query("clientId") String clientId,
//                                    @Query("token") String token,
//                                    @Query("timestamp") long timestamp,
//                                    @Query("sourceId") String sourceId,
//                                    @Query("original") String original,
//                                    @Query("version") String version,
//                                    @Query("purpose") int purpose,
//                                    @Query("width") int width,
//                                    @Query("height") int height,
//                                    @Query("deviceType") int deviceType,
//                                    @Query("latitude") int latitude,
//                                    @Query("longitude") int longitude,
//                                    @Query("sysNum") String sysNum
//    );

    //授权2
    @Headers("Cache-Control:public,max-age=43200")
    @FormUrlEncoded
    @POST("servlet/robotChat")
    Observable<EmpowerBean> getEmpowerResult(@FieldMap Map<String, Object> stringObjectMap);

}
