package com.example.hugo.contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class NouveauContactActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_contact);
        textView = (TextView) findViewById(R.id.new_data);
        String message;
        FileInputStream fis = null;
        try {
            fis = openFileInput("data_file");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer stringBuffer = new StringBuffer();
            while((message=br.readLine()) != null){
                stringBuffer.append(message+"\n");
            }
            String[] myRes = stringBuffer.toString().split("\n");

            textView.setText(myRes[0]+" "+myRes[1]+" "+myRes[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
