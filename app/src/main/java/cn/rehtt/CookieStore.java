package cn.rehtt;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by dsres on 2017/6/18.
 */

public class CookieStore {
//    public CookieStore(){
//        OkHttpClient.Builder builder= new OkHttpClient.Builder();
//        builder.cookieJar(new CookieJar() {
//            private HashMap<String,List<Cookie>> cookieStore=new HashMap<String, List<Cookie>>();
//
//            @Override
//            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                cookieStore.put(url.host(),cookies);
//            }
//
//            @Override
//            public List<Cookie> loadForRequest(HttpUrl url) {
//                List<Cookie>cookies=cookieStore.get(url.host());
//                return cookies != null ? cookies :new ArrayList<Cookie>();
//            }
//        });
//
//    }

public void aa(){
    OkHttpClient okHttpClient=new OkHttpClient();

    CookieJar cookieJar=new CookieJar() {
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            List
            k=cookies;
            Log.v("tr", String.valueOf(k));
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            return null;
        }
    };
}

}
