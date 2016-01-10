package com.example.epson.noteu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by epson on 2016/1/9.
 */
public class open_new extends AppCompatActivity {
    Button btnTXT,btnWord,btnPW,btnback;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opennew);
        btnTXT = (Button)findViewById(R.id.btnTXT);
        btnPW = (Button)findViewById(R.id.btnPW);
        btnWord = (Button)findViewById(R.id.btnWord);
        btnback = (Button)findViewById(R.id.btnback);

        btnTXT.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.setClass(open_new.this,simpletxt.class);
                startActivity(data);
                open_new.this.finish();
            }
        });//進入純文字介面

        btnWord.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data1 = new Intent();
                data1.setClass(open_new.this,word.class);
                startActivity(data1);
                open_new.this.finish();
            }
        });

        btnback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data2 = new Intent();
                data2.setClass(open_new.this,MainActivity.class);
                startActivity(data2);
                open_new.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
