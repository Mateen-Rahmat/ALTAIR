package com.abid.altair;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Vocab_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "word";
    public static final String COL3 = "meaning";

    private final Context myContext;
    private static final String DATABASE_NAME = "myglosary.db";
    private static final int DATABASE_VERSION = 1;
    private String pathToSaveDBFile;




    public DatabaseHelper(Context context, String filePath) {
        super(context, DATABASE_NAME,null,1);
        this.myContext = context;

        pathToSaveDBFile = new StringBuffer(filePath).append("/").append(DATABASE_NAME).toString();

    }

    public void prepareDatabase() throws IOException {
        boolean dbExist = checkDataBase();

        if(dbExist) {

            int currentDBVersion = getVersionId();

            if (DATABASE_VERSION > currentDBVersion) {

                deleteDb();

                try {
                    copyDatabase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {

            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(pathToSaveDBFile);
            checkDB = file.exists();
        } catch(SQLiteException e) {
            e.printStackTrace();
        }
        return checkDB;
    }

    private int getVersionId() {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
        String query = "SELECT version_id FROM dbVersion";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int v =  cursor.getInt(0);
        db.close();
        return v;
    }

    private void copyDatabase() throws IOException {

        OutputStream os = new FileOutputStream(pathToSaveDBFile);
        InputStream is = myContext.getAssets().open("forAltair/" +DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.flush();
        os.close();
    }

    public void deleteDb() {

        File file = new File(pathToSaveDBFile);
        if(file.exists()) {
            file.delete();
        }
    }
    // function get All meanings (all*)


    public Cursor getData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    /*
    public Cursor getItemID(String word){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 +" = '" + word + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor MeaningID(String meaning){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL3   +" = '" + meaning + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateWord(String newword, int id, String oldword){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newword + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldword + "'";
        db.execSQL(query);
    }
    public void updateMeaing(String newMeaning, int id, String oldMeaning){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL3 +
                " = '" + newMeaning + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + oldMeaning + "'";
        db.execSQL(query);
    }
    public void deleteMeaning(int id, String meaning){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + meaning + "'";
        db.execSQL(query);
    }
    public void deleteName(int id, String word){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + word + "'";
        db.execSQL(query);
    }
*/
    /*
    // function get All Definition's (class) word
    public List<String> getWord(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        // Make sure this names are your column names in database
        String [] sqlSelect = { "word"};
        String tableName = TABLE_NAME;
        qb.setTables(tableName);

        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do{

                result.add(cursor.getString(cursor.getColumnIndex("word")));
            }
            while (cursor.moveToNext());
        }
        return result;
    }*/

}
