package com.example.lishangqian.writeandread;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;



public class Writeread extends AppCompatActivity {

    private final static String MyFileName = "MyTest.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writeread);


        Button btnWrite = (Button) findViewById(R.id.write);
        Button btnRead = (Button) findViewById(R.id.read);
        final TextView textView =(TextView) findViewById(R.id.textView);
        final EditText editText =(EditText) findViewById(R.id.editText);


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                OutputStream out = null;
                try {
                    FileOutputStream fileOutputStream = openFileOutput(MyFileName, MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);

                    String content = editText.getText().toString();
                    try {
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    } finally {
                        if (out != null)
                            out.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in = null;
                try {
                    FileInputStream fileInputStream = openFileInput(MyFileName);
                    in = new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder stringBuilder = new StringBuilder("");
                    try {
                        while ((c = in.read()) != -1) {
                            stringBuilder.append((char) c);
                        }
                        textView.setText(stringBuilder.toString());
                    } finally {
                        if (in != null)
                            in.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}

