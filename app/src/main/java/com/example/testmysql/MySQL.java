package com.example.testmysql;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    String DB_Name = "";
    String DB_User = "";
    String DB_Pass = "";

    public MySQL(String DB_Name, String DB_User, String DB_Pass) {
        this.DB_Name = DB_Name;
        this.DB_User = DB_User;
        this.DB_Pass = DB_Pass;
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public String enterSQL(String SQLCode) {
        String result ="";
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://140.131.13.136/tool/enterSQL.php");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("DB_Name", DB_Name));
            params.add(new BasicNameValuePair("DB_User", DB_User));
            params.add(new BasicNameValuePair("DB_Pass", DB_Pass));
            params.add(new BasicNameValuePair("SQLCode", SQLCode));
            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();
            result = EntityUtils.toString(resEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }
}
