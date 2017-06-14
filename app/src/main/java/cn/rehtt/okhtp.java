package cn.rehtt;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dsres on 2017/6/14.
 */

public class okhtp {
//   new Thread(new)
    public String okhtp(String p, String u){
        String url="https://login.xunlei.com/sec2login/?csrf_token=4436d329cb2c46db1cf2a1136257abd2";
        OkHttpClient client=new OkHttpClient();


        RequestBody requestBody=new FormBody.Builder()
                .add("p",p)
                .add("u",u)
                .add("verifycode","")
                .add("login_enable","0")
                .add("business_type","113")
                .add("v","101")
                .add("cachetime","1497439679701").build();
        Request request=new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String a=request.body().toString();

        String c=requestBody.toString();
        String s=a+"\n\n\n\n"+c;
        return s;
    }
}
