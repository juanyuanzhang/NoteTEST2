package com.example.administrator.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class MDBAdapter  { //自定Adapter
    public static final String KEY_ID ="_id";
    public static final String KEY_DATE ="date";
    public static final String KEY_TOP ="top";
    public static final String KEY_CONT ="cont";
    public static final String KEY_NOTIFY ="notify";
    public static final String TABLE_NAME ="notesource";
    private SQLiteDatabase mdb;
    private MDBHelper mdbHelper;
    private Context mCte;
    private Intent intent ;
    private ContentValues values ;

    public MDBAdapter(Context mCte) {
        this.mCte = mCte;
        open();
    }
    public void open(){   //開啟連結資料庫
        mdbHelper = new MDBHelper(mCte);
        mdb = mdbHelper.getWritableDatabase();
    }
    public void close(){
        if( mdbHelper!=null )
        mdbHelper.close();
    }
    public Cursor listshow (){ //產生CURSOR給 查詢完的資料存放，再將資料傳給主頁listview顯示
        Cursor mcursor = mdb.query(TABLE_NAME, new String[]{KEY_ID,KEY_DATE,KEY_TOP,KEY_CONT,KEY_NOTIFY},null,null,null,null,null);
        if(mcursor!=null)
            mcursor.moveToFirst();
        return mcursor;
    }
    //新增
    public long createdata (String date, String top , String con, String notify){ //回傳值為long 因為ContentValues為此資料型態
        try {
            values = new ContentValues();
            values.put(KEY_DATE,date);
            values.put(KEY_TOP,top);
            values.put(KEY_CONT,con);
            values.put(KEY_NOTIFY,notify);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Toast.makeText(mCte,"CREATE ",Toast.LENGTH_LONG).show();
        }
        return mdb.insert(TABLE_NAME,null,values);
    }
    // 修改
    public long updatedata(int id ,String date, String top , String con, String notify ){
        values =new ContentValues();
        values.put(KEY_DATE,date);
        values.put(KEY_TOP,top);
        values.put(KEY_CONT,con);
        values.put(KEY_NOTIFY,notify);
        Toast.makeText(mCte,"UPDATE",Toast.LENGTH_LONG).show();
        return mdb.update(TABLE_NAME,values,"_id="+id,null);
    }
    //刪除
    public  boolean deletedata (int id){
        String[] args = {Integer.toString(id)}; //將ID值轉換成字串
        mdb.delete(TABLE_NAME,"_id=?",args);
        Toast.makeText(mCte,"DELETE",Toast.LENGTH_LONG).show();
        return true;
    }
    //查詢
    public Cursor querydata(int id){ //使用ID
        Cursor mcursor = mdb.query(TABLE_NAME, new String[]{KEY_ID,KEY_DATE,KEY_TOP,KEY_CONT,KEY_NOTIFY},KEY_ID+"="+id,null,null,null,null,null);
        if(mcursor!=null)
            mcursor.moveToFirst();
        return mcursor;
    }

    public Cursor showcolor (){ //產生CURSOR給 查詢完的資料存放，再將資料傳給主頁listview顯示
        Cursor mcursor = mdb.query(TABLE_NAME, new String[]{KEY_NOTIFY},null,null,null,null,null);
        if(mcursor!=null)
            mcursor.moveToFirst();
        return mcursor;
    }

}
