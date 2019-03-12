package fupp.mvp_demo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import fupp.mvp_demo.App;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fupp on 2017/7/10.
 */

public class Api {

    //    public static final String BASE_URL = "http://192.168.2.240/";
    private static final String BASE_URL = "http://" + "192.168.9.161" + ":" + "90/";

    private static final int CONNECT_TIMEOUT = 10000;//连接超时时间
    private static final int WRITE_TIMEOUT = 10000;//读写超时时间

    private Retrofit mRetrofit;
    public ApiService mService;


    private Interceptor mInterceptor = (chain) -> chain.proceed(
            chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
    );

    //构造方法私有
    private Api() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(App.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 100MB

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .readTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)//读取时间
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)//读取时间
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)//连接时间
                .addInterceptor(mInterceptor)
                .addInterceptor(interceptor)
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS").serializeNulls()
                .create();

        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mService = mRetrofit.create(ApiService.class);
    }

    public Api changeBaseUrl(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(App.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 100MB

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .readTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)//读取时间
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)//读取时间
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)//连接时间
                .addInterceptor(mInterceptor)
                .addInterceptor(interceptor)
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS").serializeNulls()
                .create();

        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        mService = mRetrofit.create(ApiService.class);

        return this;
    }

    //内部类创建单例
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    //获取单例
    public static Api get() {
        return SingletonHolder.INSTANCE;
    }
}
