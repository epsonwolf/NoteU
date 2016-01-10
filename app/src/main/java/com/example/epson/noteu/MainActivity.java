package com.example.epson.noteu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnTXT,btnWord,btnPW;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTXT = (Button)findViewById(R.id.btnTXT);
        btnPW = (Button)findViewById(R.id.btnPW);
        btnWord = (Button)findViewById(R.id.btnWord);

        btnTXT.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.setClass(MainActivity.this,open_new.class);
                startActivity(data);
                MainActivity.this.finish();
            }
        });//進入純文字介面

        btnWord.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data1 = new Intent();
                data1.setClass(MainActivity.this,open_new_2.class);
                startActivity(data1);
                MainActivity.this.finish();
            }
        });

        btnPW.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data2 = new Intent();
                data2.setClass(MainActivity.this,open_new_3.class);
                startActivity(data2);
                MainActivity.this.finish();
            }
        });


    }

}
