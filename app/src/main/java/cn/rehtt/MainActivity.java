package cn.rehtt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText editText_u,editText_p;
    Button button_login;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_p=(EditText)findViewById(R.id.login_p);
        editText_u=(EditText)findViewById(R.id.login_u);
        button_login=(Button)findViewById(R.id.login_b);
        imageView=(ImageView)findViewById(R.id.imageView);
        String imageUrl="http://verify1.xunlei.com/image?t=MVA&cachetime=";

        //将验证码用图片的形式显示出来

        final okhtp okhtp=new okhtp();
//        try {
//        OkHttpClient httpClient=new OkHttpClient();
//        Request request=new Request.Builder().url(imageUrl).build();
//
//            Response response=httpClient.newCall(request).execute();
//            byte[] bytes = response.body().bytes();
//            final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//
//            imageView.setImageBitmap(bmp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        final OkHttpClient client = new OkHttpClient();

        //创建OkHttpClient针对某个url的数据请求
        Request request = new Request.Builder().url(imageUrl).build();

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
                             String q=response.header("Cookie");

                             Toast.makeText(getApplicationContext(),q,Toast.LENGTH_LONG).show();
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



           button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                Bundle bundle=new Bundle();
                bundle.putString("s",okhtp.okhtp(editText_p.getText().toString(),editText_u.getText().toString()));
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }


//    private void getPicture() {
//        OkHttpClient httpClient = new OkHttpClient();
//        //创建一个Request
//        final Request request = new Request.Builder()
//                .url("http://verify1.xunlei.com/image?t=MVA&cachetime=1497447615617")
//                .build();
//        //new call
//        Call call = mOkHttpClient.newCall(request);
//        //请求加入调度
//        call.enqueue(new Callback() {
//            //失败的回调
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i(TAG, "errro");
//            }
//
//            //成功的回调
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                //刷新ui，okhttp网络请求后，不是在主线程中，如果要刷新ui，必须的主线程中；
//                if (response.isSuccessful()) {
//                    InputStream is = response.body().byteStream();
//                    Bitmap bm = BitmapFactory.decodeStream(is);
////                  //  image.setImageBitmap(bm);
//                    mHandler.sendEmptyMessage(0);
//                    Message msg = new Message();
//                    msg.obj = bm;//可以是基本类型，可以是对象，可以是List、map等；
//                    mHandler.sendMessage(msg);
//                }
//                Headers headers = response.headers();
//                Log.d(TAG, "header " + headers);
//                List<String> cookies = headers.values("Set-Cookie");
//                String session = cookies.get(0);
//                Log.d(TAG, "onResponse-size: " + cookies);
//                s = session.substring(0, session.indexOf(";"));
//                Log.i(TAG, "session is  :" + s);
//            }
//        });
//    }
//
//    Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//                    //完成主界面更新,拿到数据
//                    Bitmap bm = (Bitmap) msg.obj;
//                    image.setImageBitmap(bm);
//                    break;
//                default:
//                    break;
//            }
//        }
//
//    };






}
