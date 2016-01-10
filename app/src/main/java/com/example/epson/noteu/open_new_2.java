package com.example.epson.noteu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by epson on 2016/1/10.
 */
public class open_new_2 extends AppCompatActivity {

    Button btnNew_2,btnOld_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opennew2);
        btnNew_2 = (Button)findViewById(R.id.btnNew_2);
        btnOld_2 = (Button)findViewById(R.id.btnOld_2);

        btnNew_2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.setClass(open_new_2.this,word.class);
                startActivity(data);
                open_new_2.this.finish();
            }
        });//開新檔案

    }

}

