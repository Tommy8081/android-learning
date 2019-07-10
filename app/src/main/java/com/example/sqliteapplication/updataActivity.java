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
    private Button button_submit,button_2;
    private ListView lvStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
        dbHelper = new DBHelper(this);
        cursor = dbHelper.query(null,null,null);

        up_title_1 = (EditText) findViewById(R.id.up_title_1);
        up_note_1 = (EditText) findViewById(R.id.up_note_1);
        button_submit = (Button) findViewById(R.id.button_submit);
        button_2 = (Button) findViewById(R.id.button_2);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        final String _id = bundle.getString("id");
        //获取到游标
        cursor.moveToPosition(position);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String note = cursor.getString(cursor.getColumnIndex("note"));

        up_title_1.setText(title);
        up_note_1.setText(note);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                String title = up_title_1.getText().toString();
                String note = up_note_1.getText().toString();
                values.put("title",title);
                values.put("note",note);

                int count= dbHelper.updata(values,"_id = ?",new String[] { _id });
                if(count > 0){
                    cursor.requery();
                    lvStudent.deferNotifyDataSetChanged();
                }else{
                    Toast.makeText(updataActivity.this,"遗憾信息修改失败！",Toast.LENGTH_SHORT).show();
                }
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
