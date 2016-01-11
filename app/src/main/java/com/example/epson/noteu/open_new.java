package com.example.epson.noteu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by epson on 2016/1/9.
 */
public class open_new extends AppCompatActivity {

    Button btnNew,btnOld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opennew);
        btnNew = (Button)findViewById(R.id.btnNew);


        btnNew.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.setClass(open_new.this, simpletxt.class);
                startActivity(data);
                open_new.this.finish();
            }
        });//開新檔案
        btnOld = (Button)findViewById(R.id.btnOld);

        btnOld.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent, 0);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri uri = data.getData();
            if(uri != null){
                try {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(uri.getPath()), "Big5");
                    BufferedReader br = new BufferedReader(isr);
                    List<String> cont  = new ArrayList<String>();
                    String line ="";
                    while((line = br.readLine()) != null) {
                        cont.add(line);

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                setTitle("無效的檔案路徑 !!");
            }
        }
        else{
            setTitle("取消選擇檔案 !!");
        }
    }


}
