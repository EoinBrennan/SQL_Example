package com.eoin.sql_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listview);
        //final ListView listview = (ListView) findViewById(R.id.listview);


        setContentView(R.layout.sqlview);
        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);


        SkillsLevel info = new SkillsLevel(this);

        info.open();
        String data = info.getData();
        //String [] mydbData = new String [] {data};
        info.close();

        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.label,mydbData);
        //setListAdapter(adapter);
        tv.setText(data);

    }
}
