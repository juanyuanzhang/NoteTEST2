package com.example.administrator.noteapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Intent i;
    private ListView listView;
    private MDBAdapter mdbAdapter;
    private SimpleCursorAdapter dataAdapter;
    ArrayList<ColorItem> colorItems;
    ColorAdapter colorAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.mainList);
        mdbAdapter = new MDBAdapter(this);
        showlistView();
        showcolorview();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor itemcursor = (Cursor) listView.getItemAtPosition(position);
                int itemid = itemcursor.getInt( itemcursor.getColumnIndexOrThrow("_id"));
                i = new Intent();
                i.putExtra("itemid",itemid);
                i.putExtra("key","edit");
                i.setClass(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });




    }
    public void showlistView(){
        Cursor cursor = mdbAdapter.listshow(); //取得資料的方法
        String[] columns = new String[]{
                mdbAdapter.KEY_DATE,
                mdbAdapter.KEY_TOP,
                mdbAdapter.KEY_CONT


        };
        int[] to = new int[]{
                R.id.tvdata,//list_item
                R.id.tvnote,
                R.id.tvcon

        };
        // 自訂的Adapter 需要使用SimpleCursorAdapter   來取資料放入listView中
        dataAdapter = new SimpleCursorAdapter(this,R.layout.listitem, cursor, columns, to, 0);
        listView.setAdapter(dataAdapter);


    }
    public void showcolorview(){
        Cursor cursor = mdbAdapter.showcolor();
        String[] colums = new  String[]{mdbAdapter.KEY_NOTIFY};
        Toast.makeText(this,colums[0],Toast.LENGTH_LONG).show();
        colorItems = new ArrayList<ColorItem>();
        colorItems.add(new ColorItem("#FF40DFFF"));
        colorAdapter =new ColorAdapter(this,colorItems);
        listView.setAdapter(colorAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbr,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,AddActivity.class);
        switch (item.getItemId()){
            case R.id.baradd:
                intent.putExtra("key","add");
                startActivity(intent);
                break;
//            case R.id.baredit:
//                intent.putExtra("key","edit");
//                startActivity(intent);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
