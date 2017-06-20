package cn.rehtt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText editText_u,editText_p;
    Button button_login;
    ImageView imageView;

    private static final String ACTIVITY_TAG="LogDemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("tTT",new okhtp().UidToMd5(getCookie()));
        editText_p=(EditText)findViewById(R.id.login_p);
        editText_u=(EditText)findViewById(R.id.login_u);
        button_login=(Button)findViewById(R.id.login_b);
        imageView=(ImageView)findViewById(R.id.imageView);
        yzm();  //加载验证码










           button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://login.xunlei.com/sec2login/?csrf_token="+new okhtp().UidToMd5(getCookie());

                Log.e("rewrw", url);


            }
        });
    }

    //显示验证码
    public void yzm(){

        final OkHttpClient client = new OkHttpClient();

        //创建OkHttpClient针对某个url的数据请求
        Request request = new Request.Builder().url("http://verify1.xunlei.com/image?t=MVA&cachetime=").build();

        Call call = client.newCall(request);

        //请求加入队列
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //此处处理请求失败的业务逻辑
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //我写的这个例子是请求一个图片
                //response的body是图片的byte字节
                byte[] bytes = response.body().bytes();

                //response.body().close();

                //把byte字节组装成图片
                final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                //回调是运行在非ui主线程，
                //数据请求成功后，在主线程中更新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //网络图片请求成功，更新到主线程的ImageView
                        imageView.setImageBitmap(bmp);
                    }
                });
            }
        });


    }

    //通过WebView获取Cookie
    public  String[] getCookie(){
        WebView webView ;
        webView=(WebView)findViewById(R.id.webView);
        webView.loadUrl("http://yuancheng.xunlei.com/");
        CookieManager cookieManager=CookieManager.getInstance();
        String cookieStr=cookieManager.getCookie("http://yuancheng.xunlei.com/");
        Log.e("TTTTTTTTTTT","cookie="+cookieStr);
        String[] cookieString =cookieStr.split(";");

        return cookieString;
    }



}
