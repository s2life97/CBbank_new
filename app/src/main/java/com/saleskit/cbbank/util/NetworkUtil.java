package com.saleskit.cbbank.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class NetworkUtil {
    private static final String TAG = "NetworkUtil";
//    private static final String BASE_CB_API = "http://118.68.171.86/";
    private static final String BASE_CB_API = "https://api.saleskit.cbbank.vn/";
//    private static final String BASE_CB_API = "http://192.168.1.230:8001/";
    public static OkHttpClient client;
    private static String API_BAY_GOLF_IDENTITY;
    private static Retrofit retrofitBayGolfBooking5000 = null;
    private static Retrofit retrofit = null;

    /**
     * Returns true if the Throwable is an instance of RetrofitError with an http status code equals
     * to the given one.
     */
    public static boolean isHttpStatusCode(Throwable throwable, int statusCode) {
        return throwable instanceof HttpException
                && ((HttpException) throwable).code() == statusCode;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static Retrofit getCBclient(Context context) {
        if (retrofit == null) {
            try {
                OkHttpClient.Builder builder;
                builder = new OkHttpClient.Builder();
                client = null;
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Timber.e("\n" + message));
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                client = builder
                        .addNetworkInterceptor(logging)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS).build();
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_CB_API)
                        .addConverterFactory(GsonConverterFactory.create())
//                        .client(client)
                        .build();

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return retrofit;
    }
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            try {
                OkHttpClient.Builder builder;
                builder = new OkHttpClient.Builder();

                client = null;
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Timber.e("\n" + message));
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                client = builder
                        .addNetworkInterceptor(logging)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS).build();
                retrofit = new Retrofit.Builder()
                        .baseUrl(API_BAY_GOLF_IDENTITY)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

            } catch (Exception e) {
                Timber.e(e.toString());
                e.printStackTrace();

            }
        }
        return retrofit;
    }
}
