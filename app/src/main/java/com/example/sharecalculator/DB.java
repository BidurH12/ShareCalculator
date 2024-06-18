package com.example.sharecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {

    public static final String DB_NAME="database";
    Context context;

    public DB(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry=  "create table TShares ( id integer primary key autoincrement, name text, quantity text,  bp text, total text, per text)";
        sqLiteDatabase.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TShares" );
        onCreate(sqLiteDatabase);
    }

    public String add(String name,String quantity,String bp,String total,String per){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("quantity",quantity);
        cv.put("bp",bp);
        cv.put("total",total);
        cv.put("per",per);

        float re= db.insert("TShares",null,cv);
        if(re==-1)
            return "Failed";
        else
            return "Successfully Inserted";
    }

    public Cursor read(){
        SQLiteDatabase db=this.getReadableDatabase();
        String qry="select * from TShares order by id desc";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }

    public void Delete(int ID){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete("TShares",  "id = ?",new String[]{String.valueOf(ID)});
        if(result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
