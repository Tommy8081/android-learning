package com.example.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class insertActivity extends AppCompatActivity {
    private ListView lvStudent;
    private Cursor cursor;
    private int position;
    private DBHelper dbHelper;
    private EditText editText;
    EditText up_note,up_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this);
        super.onCreate(savedInstanceState);
     //   final Bundle bundle = this.getIntent().getExtras();
        setContentView(R.layout.activity_insert);
        up_title = (EditText) findViewById(R.id.up_title);
        up_note = (EditText) findViewById(R.id.up_note);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String title = up_title.getText().toString();
                String note = up_note.getText().toString();
                //Toast.makeText(insertActivity.this,title+"-----------"+note,Toast.LENGTH_LONG).show();
                ContentValues values = new ContentValues();
                values.put("title",title);
                values.put("note",note);
                Intent intent = new Intent();
                long count = dbHelper.insert(values);
                intent.putExtra("count1",count);
                if(count > 0 ){
                    intent = new Intent(insertActivity.this,MainActivity.class);
                    cursor.requery();
                    lvStudent.deferNotifyDataSetChanged();
                    startActivity(intent);
                }
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
