package com.abid.altair;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddingActivity extends Activity {


    Button Add;
    SQLiteDatabase db;
    SQLiteOpenHelper OpenHelper;
    EditText Word, Meaning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        mainfuntion();


    }

    public void mainfuntion(){

        OpenHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        db = OpenHelper.getWritableDatabase();

        Add = findViewById(R.id.button);
        Word = findViewById(R.id.etword);
        Meaning = findViewById(R.id.etmeaning);


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = Word.getText().toString();
                String meaning = Meaning.getText().toString();


                if (Word.length() != 0){
                    insertdata(word, meaning);
                    Word.setText("");
                    Meaning.setText("");
                    Toast.makeText(getApplicationContext(),"Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"NOT Successful", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
    public void insertdata(String words, String meaning){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL2,words);
        contentValues.put(DatabaseHelper.COL3,meaning);
        long result = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

    }
}
