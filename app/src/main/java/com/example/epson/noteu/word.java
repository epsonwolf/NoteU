package com.example.epson.noteu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by epson on 2016/1/9.
 */
public class word extends AppCompatActivity {

    EditText edtWord;
    String[] textSizes = {"20","22","24","26","30"};
    String[] textColor = {"黑色","藍色","綠色","紅色","黃色","淡藍色"};
    Spinner spnsize,spncolor;
    Button btnSave_word,btnBack_word;
    String filename;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word);

        edtWord = (EditText) findViewById(R.id.edtWord);
        spnsize = (Spinner)findViewById(R.id.spnsize);
        spncolor = (Spinner)findViewById(R.id.spncolor);
        btnBack_word = (Button)findViewById(R.id.btnBack_word);
        btnSave_word = (Button)findViewById(R.id.btnSave_word);


        ArrayAdapter<String> adapsize = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,textSizes);
        spnsize.setAdapter(adapsize);
        spnsize.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = spnsize.getItemAtPosition(position).toString();
                int sizee = Integer.valueOf(temp);
                edtWord.setTextSize(sizee);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapcolor = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,textColor);
        spncolor.setAdapter(adapcolor);
        spncolor.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tempcolor = spncolor.getItemAtPosition(position).toString();
                int colorid;
                switch (tempcolor) {
                    case "紅色":
                        colorid = Color.RED;
                        edtWord.setTextColor(colorid);
                        break;
                    case "藍色":
                        colorid = Color.BLUE;
                        edtWord.setTextColor(colorid);
                        break;
                    case "綠色":
                        colorid = Color.GREEN;
                        edtWord.setTextColor(colorid);
                        break;
                    case "黑色":
                        colorid = Color.BLACK;
                        edtWord.setTextColor(colorid);
                        break;
                    case "黃色":
                        colorid = Color.YELLOW;
                        edtWord.setTextColor(colorid);
                        break;
                    case "淡藍色":
                        colorid = Color.CYAN;
                        edtWord.setTextColor(colorid);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnBack_word.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(word.this)
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
                                data.setClass(word.this, MainActivity.class);
                                startActivity(data);
                                word.this.finish();
                            }
                        })
                        .show();
            }
        });

        btnSave_word.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(word.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                new AlertDialog.Builder(word.this)
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
                                    File mFile = new File(mSDFile.getParent() + "/" + mSDFile.getName() + "/NoteU_word");
                                    if(!mFile.exists()) {
                                        mFile.mkdirs();
                                    }
                                    FileWriter mFileWriter = new FileWriter( mSDFile.getParent() + "/" + mSDFile.getName() + "/NoteU_txt/" + filename + ".txt" );
                                    mFileWriter.write(edtWord.getText().toString());
                                    mFileWriter.close();
                                    Toast.makeText(word.this, "已儲存檔案", Toast.LENGTH_SHORT).show();
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


}
