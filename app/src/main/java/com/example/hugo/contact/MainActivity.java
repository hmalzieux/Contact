package com.example.hugo.contact;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button ButValider;
    EditText nom;
    EditText prenom;
    EditText telephone;
    ContactsDb myDb;
    Button ButAjouter;
    Button ButClear;
    TextView bdText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* init db*/
        myDb = new ContactsDb(this);
        ButValider = (Button) findViewById(R.id.but_val);
        ButAjouter = (Button) findViewById(R.id.but_add);
        ButClear = (Button) findViewById(R.id.but_clear);
        nom   = (EditText)findViewById(R.id.user_name);
        prenom   = (EditText)findViewById(R.id.user_firstname);
        telephone   = (EditText)findViewById(R.id.user_telephone);
        bdText = (TextView) findViewById(R.id.res_db);

        bdText.setMovementMethod(new ScrollingMovementMethod());

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        nom.setText(sharedPreferences.getString("nomUser", ""));
        prenom.setText(sharedPreferences.getString("prenomUser", ""));
        telephone.setText(sharedPreferences.getString("telephoneUser", ""));

        /*Bouton ajouter*/
        ButAjouter.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ArrayList<String[]> res = myDb.getAllRows(myDb.getWritableDatabase());
                        if(res.size()== 0){
                            return;
                        }
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < res.size (); i++){
                            stringBuffer.append("nom : "+res.get(i)[0]+"\n");
                            stringBuffer.append("prenom : "+res.get(i)[1]+"\n");
                            stringBuffer.append("tel : "+res.get(i)[2]+"\n");
                        }
                        bdText.setText(stringBuffer);
                    }
                }
        );

        ButClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                myDb.deleteAllData();
            }
        });
        /*bouton valider*/
        ButValider.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nomUser",nom.getText().toString());
                editor.putString("prenomUser",prenom.getText().toString());
                editor.putString("telephoneUser",telephone.getText().toString());

                String file_name = "data_file";
                try {
                    FileOutputStream fos = openFileOutput(file_name, MODE_PRIVATE);
                    String data = nom.getText().toString()+"\n"+prenom.getText().toString()+"\n"+telephone.getText().toString()+"\n";
                    fos.write(data.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(), R.string.data_saved, Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                boolean isInserted = myDb.insertData(nom.getText().toString(),prenom.getText().toString(),telephone.getText().toString());
                if (isInserted == true){
                    Toast.makeText(getApplicationContext(), R.string.contact_added, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_contact: {
                Intent intent = new Intent(MainActivity.this, ResultatActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
