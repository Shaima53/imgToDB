package com.example.shyom.retest;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.lang.Object;


import java.io.ByteArrayOutputStream;
import java.io.File;

public class MainActivity extends AppCompatActivity {

public static SQLiteHelper sqLiteHelper;
ImageView imageView,imageView2;
TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper= new SQLiteHelper(this,"PIC.db");
        String sql="CREATE TABLE IF NOT EXISTS PIC ( pic MEDIUMBLOB )";
        sqLiteHelper.queryData(sql);

        imageView=(ImageView)findViewById(R.id.imageView);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        textView2=(TextView)findViewById(R.id.textView2);
        ImageToByte();
    }


    public void btn_add(View view) {


        try{sqLiteHelper.insertData(ImageToByte());
            Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();

        } catch (Exception e){e.printStackTrace();}
        imageView.setImageResource(R.mipmap.ic_launcher);





    }




    public void btn_View(View view) {try{





        Cursor cursor=sqLiteHelper.getData();
           cursor.moveToFirst();
            byte[] image= cursor.getBlob(cursor.getColumnIndex("pic"));


        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView2.setImageBitmap(bmp);
        Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();





        }catch (Exception e){e.printStackTrace();   Toast.makeText(getApplicationContext(),"not added",Toast.LENGTH_SHORT).show();}




    }
    private byte[] ImageToByte(){




        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.logo);
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }
}
