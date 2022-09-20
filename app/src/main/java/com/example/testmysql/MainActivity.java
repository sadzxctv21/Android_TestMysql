package com.example.testmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button b01,b02;
    EditText e01,e02,e03,e04;
    MySQL mySQL=new MySQL("testMySql","testMySql","testMySql");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b01=(Button) findViewById(R.id.b01);
        b02=(Button) findViewById(R.id.b02);
        e01=(EditText) findViewById(R.id.e01);
        e02=(EditText) findViewById(R.id.e02);
        e03=(EditText) findViewById(R.id.e03);
        e04=(EditText) findViewById(R.id.e04);
        b01.setOnClickListener(b01());
        b02.setOnClickListener(b02());
        loadData();
    }
    private void loadData(){
        try{
            String result =mySQL.enterSQL("SELECT * FROM `test`");
            JSONArray array = new JSONArray(result);
            JSONObject jsonObject = array.getJSONObject(0);
            String a = jsonObject.getString("a");
            String b = jsonObject.getString("b");
            String c = jsonObject.getString("c");
            String d = jsonObject.getString("d");
            e01.setText(a);
            e02.setText(b);
            e03.setText(c);
            e04.setText(d);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }
    View.OnClickListener b01() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                loadData();
            }
        };
    }
    private void upData(){
        String SqlCode="REPLACE INTO `test`";
        SqlCode+=" (`id`, `a`, `b`, `c`, `d`) ";
        SqlCode+=" VALUES ('0',";
        SqlCode+=" '"+e01.getText()+"' ,";
        SqlCode+=" '"+e02.getText()+"' ,";
        SqlCode+=" '"+e03.getText()+"' ,";
        SqlCode+=" '"+e04.getText()+"' )";
        Log.d("aaaa",SqlCode);
        mySQL.enterSQL(SqlCode);
    }
    View.OnClickListener b02() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                upData();
            }
        };
    }
}