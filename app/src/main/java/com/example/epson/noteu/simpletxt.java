package com.example.epson.noteu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by epson on 2016/1/8.
 */
public class simpletxt extends AppCompatActivity {

    Button btnSave,btnCancel,btnOld;
    EditText txt1;
    String filename;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.txt);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        txt1 = (EditText)findViewById(R.id.txt1);
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
                                data.setClass(simpletxt.this, MainActivity.class);
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
                                    File mFile = new File(mSDFile.getParent() + "/" + mSDFile.getName() + "/NoteU_txt/" + filename + ".txt");
                                    if (!mFile.exists()) {
                                        mFile.mkdirs();
                                    }
                                    mFile.createNewFile();
                                    FileOutputStream fOut = new FileOutputStream(mFile);
                                    OutputStreamWriter myOutWriter =
                                            new OutputStreamWriter(fOut);
                                    myOutWriter.append(txt1.getText());
                                    myOutWriter.close();
                                    fOut.close();
                                    Toast.makeText(simpletxt.this, "已儲存檔案", Toast.LENGTH_SHORT).show();
                                    Intent data = new Intent();
                                    data.setClass(simpletxt.this, MainActivity.class);
                                    startActivity(data);
                                    simpletxt.this.finish();
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
