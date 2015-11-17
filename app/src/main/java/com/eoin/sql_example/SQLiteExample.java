package com.eoin.sql_example;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends AppCompatActivity implements View.OnClickListener {

    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    EditText sqlName, sqlSkill, sqlRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);

        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlName = (EditText) findViewById(R.id.etSQLname);
        sqlSkill = (EditText) findViewById(R.id.etSQLSkill);

        sqlView = (Button) findViewById(R.id.bSQLOpenView);
        sqlView.setOnClickListener(this);
        sqlUpdate.setOnClickListener(this);

        sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
        sqlModify = (Button) findViewById(R.id.bSQLmodify);
        sqlGetInfo = (Button) findViewById(R.id.bgetInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLdelete);
        sqlDelete.setOnClickListener(this);
        sqlModify.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bSQLUpdate:

                    boolean didItWork = true;
                    try {
                        String name = sqlName.getText().toString();
                        String skill = sqlSkill.getText().toString();

                        SkillsLevel entry = new SkillsLevel(SQLiteExample.this);
                        entry.open();
                        entry.createEntry(name, skill);
                        entry.close();

                    } catch (Exception e) {
                        didItWork = false;
                        String error = e.toString();
                        Dialog d = new Dialog(this);
                        d.setTitle("Dang it!");
                        TextView tv = new TextView(this);
                        tv.setText(error);
                        d.setContentView(tv);
                        d.show();
                    } finally {
                        if (didItWork) {
                            Dialog d = new Dialog(this);
                            d.setTitle("Heck Yea!");
                            TextView tv = new TextView(this);
                            tv.setText("Success");
                            d.setContentView(tv);
                            d.show();
                        }
                    }

                    break;
                case R.id.bSQLOpenView:
                    Intent i = new Intent(this,SQLView.class);
                    startActivity(i);
                    break;
                case R.id.bgetInfo:
                    try {
                        String s = sqlRow.getText().toString();
                        long l = Long.parseLong(s);
                        SkillsLevel hon = new SkillsLevel(this);
                        hon.open();
                        String returnedName = hon.getName(l);
                        String returnedSkill = hon.getSkillLevel(l);
                        hon.close();

                        sqlName.setText(returnedName);
                        sqlSkill.setText(returnedSkill);
                    } catch (Exception e) {

                        String error = e.toString();
                        Dialog d = new Dialog(this);
                        d.setTitle("Dang it!");
                        TextView tv = new TextView(this);
                        tv.setText(error);
                        d.setContentView(tv);
                        d.show();
                    }
                    break;

                case R.id.bSQLmodify:
                    try {
                        String mName = sqlName.getText().toString();
                        String mSkill = sqlSkill.getText().toString();
                        String sRow = sqlRow.getText().toString();
                        long lRow = Long.parseLong(sRow);

                        SkillsLevel ex = new SkillsLevel(this);
                        ex.open();
                        ex.updateEntry(lRow, mName, mSkill);
                        ex.close();
                    } catch (Exception e) {

                        String error = e.toString();
                        Dialog d = new Dialog(this);
                        d.setTitle("Dang it!");
                        TextView tv = new TextView(this);
                        tv.setText(error);
                        d.setContentView(tv);
                        d.show();
                    }
                    break;
                case R.id.bSQLdelete:
                    try {
                        String sRow1 = sqlRow.getText().toString();
                        long lRow1 = Long.parseLong(sRow1);
                        SkillsLevel ex1 = new SkillsLevel(this);
                        ex1.open();
                        ex1.deleteEntry(lRow1);
                        ex1.close();
                    } catch (Exception e) {

                        String error = e.toString();
                        Dialog d = new Dialog(this);
                        d.setTitle("Dang it!");
                        TextView tv = new TextView(this);
                        tv.setText(error);
                        d.setContentView(tv);
                        d.show();
                    }
                    break;
            }
    }
}
