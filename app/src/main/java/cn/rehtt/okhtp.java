package cn.rehtt;

import java.io.IOException;
import java.security.MessageDigest;

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

        String url="https://login.xunlei.com/sec2login/?csrf_token="+UidToMd5(new MainActivity().getCookie());
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


    public String[] getCookie() {
        String[] mainActivity=new MainActivity().getCookie();

        return mainActivity;
    }
    public String UidToMd5(String[] cookie){         //将WebView获取的Cookie中deviceid前32位转成MD5

        String cookieS=cookie[1];
        String[] cookieSS=cookieS.split("=");

        String cookie32="";
        char[] cookie32_2 =null;
        char[] cookie32_1=cookieSS[1].toCharArray();
        for(int i=0;i<32;i++){
            cookie32=cookie32+cookie32_1[i];
        }


        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = cookie32.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();




    }

}
