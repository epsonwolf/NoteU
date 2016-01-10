package com.example.epson.noteu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by epson on 2016/1/8.
 */
public class simpletxt extends AppCompatActivity {

    Button btnSave,btnCancel;
    EditText txt1;
    String filename;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.txt);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        txt1 = (EditText)findViewById(R.id.txt1);

        btnCancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(simpletxt.this)
                        .setTitle("確認視窗")
                        .setMessage("尚未存檔，確定要離開?")
                        .setPositiveButton("否，繼續編輯", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("是，資料將不會存入", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent data = new Intent();
                                data.setClass(simpletxt.this, open_new.class);
                                startActivity(data);
                                simpletxt.this.finish();
                            }
                        })
                        .show();


            }
        });

        btnSave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(simpletxt.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                new AlertDialog.Builder(simpletxt.this)
                        .setTitle("儲存檔案")
                        .setMessage("輸入檔名")
                        .setView(input)
                        .setPositiveButton("存檔", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                filename = input.getText().toString();
                                try {
                                    File mSDFile = null;
                                    mSDFile = Environment.getExternalStorageDirectory();
                                    File mFile = new File(mSDFile.getParent() + "/" + mSDFile.getName() + "/NoteU_txt");
                                    if(!mFile.exists()) {
                                        mFile.mkdirs();
                                    }
                                    FileWriter mFileWriter = new FileWriter( mSDFile.getParent() + "/" + mSDFile.getName() + "/NoteU_txt/" + filename + ".txt" );
                                    mFileWriter.write(txt1.getText().toString());
                                    mFileWriter.close();
                                    Toast.makeText(simpletxt.this, "已儲存檔案", Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
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
        switch (item.getItemId()){
            case R.id.action_plus:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
