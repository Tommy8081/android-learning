package com.example.sqliteapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int EDIT_MENU_ITEM = 1;
    private static final int DELECT_MENU_ITEM = 2;
    private static final int UPDATA_MENU_ITEM = 3;
    private ListView lvStudent;
    private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private int position;

    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvStudent = (ListView) findViewById(R.id.lv_student);
        dbHelper = new DBHelper(this);

        cursor = dbHelper.query(null,null,null);
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_expandable_list_item_2,
                cursor,
                new String[] {"title","note"},
                new int[] {android.R.id.text1,android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        lvStudent.setAdapter(adapter);
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 游标定位到指定记录
                cursor.moveToPosition(position);

                // 获取当前记录各个字段值
                String _id = cursor.getString(cursor.getColumnIndex("_id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String note = cursor.getString(cursor.getColumnIndex("note"));

                // 将各个字段值拼接成一个备忘录信息
                StringBuilder builder = new StringBuilder();
                builder.append("备忘录信息\n\n");
                builder.append("编号：" + _id + "\n");
                builder.append("主题：" + title + "\n");
                builder.append("内容：" + note + "\n");
                String studentInfo = builder.toString();

                // 通过跳转显示备忘录信息
               Intent intent = new Intent(MainActivity.this,showActivity.class);
               intent.putExtra("Info",studentInfo);
               startActivity(intent);
                // Toast.makeText(MainActivity.this, studentInfo, Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(lvStudent);
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("count1");
        String str2 = "新建成功！现在在主页";
        if(str1 != "-1"){
            Toast.makeText(MainActivity.this,str2,Toast.LENGTH_SHORT).show();
        }else {
            Log.d("---------------", "onCreate: "+str2);
            Log.d("---------------", "onCreate: 传参错误！");
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;

        position = acmi.position;
        cursor.moveToPosition(position);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        menu.add(1,EDIT_MENU_ITEM,2,"新建一个备忘录！");
        menu.add(1,DELECT_MENU_ITEM, 2, "删除名为【"+ title +"】的备忘录信息");
        menu.add(1,UPDATA_MENU_ITEM,2,"修改名为【"+title+"】的备忘录信息");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        menu.setHeaderTitle("表记录操作");
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case UPDATA_MENU_ITEM:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle(R.mipmap.ic_launcher)
                        .setTitle("修改备忘录")
                        .setMessage("您是否要修改此备忘录？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String id = cursor.getString(cursor.getColumnIndex("_id"));
                                Intent intent = new Intent(MainActivity.this, updataActivity.class);
                                Bundle bundle = new Bundle();
                                intent.putExtra("position",position);
                                bundle.putString("id",id);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("否", null)
                        .create()
                        .show();
                break;
            case EDIT_MENU_ITEM:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle(R.mipmap.ic_launcher)
                        .setTitle("新建备忘录")
                        .setMessage("您是否要新建一个备忘录？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                    String id = cursor.getString(cursor.getColumnIndex("_id"));
                                Intent intent = new Intent(MainActivity.this, insertActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("id",id);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("否", null)
                        .create()
                        .show();
                break;
            case DELECT_MENU_ITEM:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setIcon(R.mipmap.ic_launcher)//设置图标
                        .setTitle("删除备忘录")
                        .setMessage("您是否要删除当前备忘录？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cursor.moveToPosition(position);
                                String _id = cursor.getString(cursor.getColumnIndex("_id"));
                                int count = dbHelper.delete("_id = ?", new String[] { _id });
                                if(count > 0){
                                    cursor.requery();
                                    lvStudent.deferNotifyDataSetChanged();
                                }else{
                                    Toast.makeText(MainActivity.this,"遗憾，记录删除失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("否", null)
                        .create()
                        .show();
                break;
        }
        return true;
    }

}
