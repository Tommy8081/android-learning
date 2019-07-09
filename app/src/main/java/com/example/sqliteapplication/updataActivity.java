package com.example.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class updataActivity extends AppCompatActivity {
    private EditText up_title_1, up_note_1;
    private DBHelper dbHelper;
    private Cursor cursor;
    private Button button_1,button_2;
    private ListView lvStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);

        dbHelper = new DBHelper(this);
        up_title_1 = (EditText) findViewById(R.id.up_title_1);
        up_note_1 = (EditText) findViewById(R.id.up_note_1);
        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = getIntent();
//                final String pot = intent.getStringExtra("position");
//                final int position = Integer.valueOf(pot).intValue();
//                String str1 = up_title_1.getText().toString();
//                String str2 = up_note_1.getText().toString();
//                cursor.moveToPosition(position);
//                String _id = cursor.getString(cursor.getColumnIndex("_id"));
//                Log.d("---------", "onClick: "+_id);
//                //Toast.makeText(updataActivity.this,str1+"---"+str2,Toast.LENGTH_SHORT).show();
//                ContentValues values = new ContentValues();
//                values.put("str1",str1);
//                values.put("str2",str2);
//
//                String[] args = { _id };
//                int count = dbHelper.updata(values,"_id = ?",args);
//                if(count > 0){
//                    Log.d("------------", "onClick: 执行成功！");
//                    intent = new Intent(updataActivity.this,MainActivity.class);
//                    cursor.requery();
//                    lvStudent.deferNotifyDataSetChanged();
//                    startActivity(intent);
//                }else {
//                    Log.d("------------", "onClick: 执行失败！");
//                }
           }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
