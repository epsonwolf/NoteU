package com.example.epson.noteu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by epson on 2016/1/10.
 */
public class open_new_3 extends AppCompatActivity {

    Button btnNew_3, btnOld_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opennew3);
        btnNew_3 = (Button) findViewById(R.id.btnNew_3);
        btnOld_3 = (Button) findViewById(R.id.btnOld_3);

        btnNew_3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.setClass(open_new_3.this, picandtext.class);
                startActivity(data);
                open_new_3.this.finish();
            }
        });//開新檔案

    }
}
