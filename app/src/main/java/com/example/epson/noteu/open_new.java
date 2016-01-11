package com.example.epson.noteu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
        btnOld = (Button)findViewById(R.id.btnOld);

        btnNew.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.setClass(open_new.this,simpletxt.class);
                startActivity(data);
                open_new.this.finish();
            }
        });//開新檔案

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
}
