package br.com.aramosdev.zapimoveis.model.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.core.BaseContract;
import io.reactivex.Flowable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class RestClient implements Interceptor {

    private Context mContext;
    private Services mServices;


    public RestClient(Context context) {
        mContext = context;
        init(mContext.getString(R.string.base_url));
    }

    private void init(String baseUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(this);

        Gson gson = new GsonBuilder().create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(httpClient.build());

        mServices = builder.build().create(Services.class);
    }

    public Services getServices() {
        return mServices;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().build());
    }

    public <T> ApiSubscriber buildRequest(Flowable<T> observable, BaseContract.BaseInteraction<T> interaction) {
        return new ApiSubscriber<>(observable, interaction);
    }
}
