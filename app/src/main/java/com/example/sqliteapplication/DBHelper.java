package com.example.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "student.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "student";
    private Context context;
    public DBHelper(Context context){
        super(context,DB_Name,null,DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db,TABLE_NAME);
        insertRecords(db);
    }

    private void insertRecords(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // 通过键值对方式存放记录信息
        values.put("_id", 1600001);
        values.put("title", "李云龙");
        values.put("note", "男");
        // 调用db的插入方法，插入第1条记录
        db.insert(TABLE_NAME, null, values);

        // 通过键值对方式存放记录信息
        values.put("_id", 1600002);
        values.put("title", "李云龙");
        values.put("note", "男");
        // 调用db的插入方法，插入第2条记录
        db.insert(TABLE_NAME, null, values);

        // 通过键值对方式存放记录信息
        values.put("_id", 1600003);
        values.put("title", "李云龙");
        values.put("note", "男");
        // 调用db的插入方法，插入第3条记录
        db.insert(TABLE_NAME, null, values);

        // 通过键值对方式存放记录信息
        values.put("_id", 1600004);
        values.put("title", "李云龙");
        values.put("note", "男");

        // 调用db的插入方法，插入第4条记录
        db.insert(TABLE_NAME, null, values);

        // 通过键值对方式存放记录信息
        values.put("_id", 1600005);
        values.put("title", "李云龙");
        values.put("note", "男jkdahjkhasjkdhasjkdkjdgaksdgaskjdgasjkdjkadgajkdgajkfsdmabgdfgasjfgasdfgsdfgasdjbfjasdfjgasuyeygasfyasefgasyfasejgyfasejfgaseyfasejfbj");
        // 调用db的插入方法，插入第5条记录
        db.insert(TABLE_NAME, null, values);

        // 通过键值对方式存放记录信息
        values.put("_id", 1600006);
        values.put("title", "李云龙");
        values.put("note", "男");
        // 调用db的插入方法，插入第6条记录
        db.insert(TABLE_NAME, null, values);

        // 通过键值对方式存放记录信息
        values.put("_id", 1600007);
        values.put("title", "李云龙");
        values.put("note", "男");
        // 调用db的插入方法，插入第7条记录
        db.insert(TABLE_NAME, null, values);

        // 通过键值对方式存放记录信息
        values.put("_id", 1600008);
        values.put("title", "李云龙");
        values.put("note", "男");
        // 调用db的插入方法，插入第8条记录
        db.insert(TABLE_NAME, null, values);

        // 通过键值对方式存放记录信息
        values.put("_id", 1600009);
        values.put("title", "李云龙");
        values.put("note", "男");

        // 调用db的插入方法，插入第9条记录
        db.insert(TABLE_NAME, null, values);

        // 通过键值对方式存放记录信息
        values.put("_id", 1600010);
        values.put("title", "李云龙");
        values.put("note", "男");
        // 调用db的插入方法，插入第10条记录
        db.insert(TABLE_NAME, null, values);
    }

    private void createTable(SQLiteDatabase db,String tableName) {
        String strSQL = "CREATE TABLE " + tableName + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, title text, note text)";
        try{
            db.execSQL(strSQL);
            Toast.makeText(context,"欢迎使用本款软件！",Toast.LENGTH_SHORT).show();
        }catch (SQLException e){
            Toast.makeText(context,"遗憾，备忘录创建失败！",Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor query(String[] columns,String selection,String[] selectionArgs){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
    }
    public int delete(String whereClause, String[] whereArgs){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,whereClause,whereArgs);
    }
    public int updata(ContentValues values, String whereClause, String[] whereArgs){
        SQLiteDatabase db = getWritableDatabase();
        return db.update(TABLE_NAME,values,whereClause,whereArgs);
    }
    public long insert(ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        long rowld =  db.insert(TABLE_NAME,null,values);
        if(rowld > 0){
            Log.d("---------------", "insert: 插入成功！");
            return rowld;
        }else{
            Log.d("---------------", "insert: 插入失败！");
        }
        return rowld;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
