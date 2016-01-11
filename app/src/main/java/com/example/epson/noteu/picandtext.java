package com.example.epson.noteu;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.FileNotFoundException;

/**
 * Created by epson on 2016/1/10.
 */
public class picandtext extends AppCompatActivity {

    EditText edtMix;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictureandword);

        edtMix = (EditText)findViewById(R.id.edtMix);
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
                getImage();

            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 0) {
            ContentResolver resolver = getContentResolver();
            Uri originalUri = data.getData();
            String temp = edtMix.getText().toString();
            //ImageView img = new ImageView();
            //int x = Integer.valueOf(temp);
            //Drawable pic = img.getDrawable();
            SpannableString spnString = new SpannableString(temp);
            try {
                bitmap = BitmapFactory.decodeStream(resolver.openInputStream(originalUri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageSpan imageSpan = new ImageSpan(picandtext.this,bitmap);

            spnString.setSpan(imageSpan, 0, temp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 將圖片追加到EditText中光光標所在位置
            int index = edtMix.getSelectionStart(); // 光標所在位置
            Editable edit_text = edtMix.getEditableText();
            if (index < 0 || index >= edit_text.length()) {
                edit_text.append(spnString);
            } else {
                edit_text.insert(index, spnString);
            }
            System.out.println(spnString.toString());
        }
    }
}
