package com.example.hugo.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResultatActivity extends AppCompatActivity {
    private Cursor mCursor;
    private ListView listView;
    ContactsDb myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        listView = (ListView) findViewById(R.id.listView);
        myDb = new ContactsDb(this);
        mCursor = myDb.getAllRows();
        String[]fromColumns = new String[]{ContactsContract.Data.DISPLAY_NAME,ContactsContract.Data.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        int[] toViews = new int[]{R.id.user_name_item,R.id.user_firstname_item,R.id.user_telephone_item};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_list,mCursor, fromColumns,toViews,0);
        listView.setAdapter(adapter);
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long arg3) {
                Cursor cur = (Cursor) customAdapter.getItem(position);
                cur.moveToPosition(position);
                long id = (long)cur.getInt(cur.getColumnIndexOrThrow("_id"));
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                Log.d("myDb.getPhoneNumber(id)",""+myDb.getPhoneNumber(id));
                callIntent.setData(Uri.parse(getString(R.string.telephone_dial,myDb.getPhoneNumber(id))));
                if (ActivityCompat.checkSelfPermission(ResultatActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
