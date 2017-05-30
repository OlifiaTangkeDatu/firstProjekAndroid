package com.olifia.welcome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by fiaolifia on 22/05/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public static String DB_NAME = "db_diari";
    public static int DB_VERSION = 1;
    public static final String NAMA_TABEL = "diari";
    public static final String KOLOM_TANGGAL = "tanggal";
    public static final String KOLOM_JUDUL = "judul";
    public static final String KOLOM_ISI = "isi";
    public static final String KOLOM_KATEGORI = "kategori";
    public static final String KOLOM_ID = "id";

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + NAMA_TABEL + "( " +KOLOM_ID+" TEXT, "+ KOLOM_TANGGAL +" TEXT, "+ KOLOM_JUDUL + " TEXT, "+ KOLOM_ISI +" TEXT, "
                +KOLOM_KATEGORI + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    String iniID(){
        String id;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String SQL = "SELECT "+KOLOM_ID+" FROM "+ NAMA_TABEL+" ORDER BY "+KOLOM_ID+" DESC";
        Cursor idCursor = sqLiteDatabase.rawQuery(SQL,null);
        if(idCursor.moveToFirst()){
            return (Integer.parseInt(idCursor.getString(0)))+1+"";
        }else{
            return "1";
        }
    }

    void addDiari(Diari diari){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOLOM_ID,iniID());
        contentValues.put(KOLOM_TANGGAL,diari.getTanggal());
        contentValues.put(KOLOM_JUDUL,diari.getJudul());
        contentValues.put(KOLOM_ISI,diari.getIsi());
        contentValues.put(KOLOM_KATEGORI,diari.getKategori());

        sqLiteDatabase.insert(NAMA_TABEL,null,contentValues);
        sqLiteDatabase.close();
    }

    int deleteDiari(Diari diari){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String whereClause = "id = "+diari.getId();
        int row = sqLiteDatabase.delete(NAMA_TABEL,whereClause,null);
        sqLiteDatabase.close();
        return row;
    }

    int updateDiari(Diari newDiari){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOLOM_TANGGAL,newDiari.getTanggal());
        contentValues.put(KOLOM_JUDUL,newDiari.getJudul());
        contentValues.put(KOLOM_ISI,newDiari.getIsi());
        contentValues.put(KOLOM_KATEGORI,newDiari.getKategori());

        String whereClause = KOLOM_ID+" = "+newDiari.getId();

        int row = sqLiteDatabase.update(NAMA_TABEL,contentValues,whereClause,null);
        sqLiteDatabase.close();
        return row;
    }

    ArrayList<Diari> getDiari(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SELECTALL = "select * from "+NAMA_TABEL;
        ArrayList<Diari> allDiari = new ArrayList<>();

        Cursor diari = sqLiteDatabase.rawQuery(SELECTALL,null);

        if(diari.moveToFirst()){
            do{
                Diari diariTemp = new Diari(diari.getString(0),diari.getString(1),diari.getString(2),diari.getString(3),diari.getString(4));
                allDiari.add(diariTemp);
            }while (diari.moveToNext());

        }
        return allDiari;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

