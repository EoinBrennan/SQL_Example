package com.eoin.sql_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by user on 17/11/2015.
 *
 * Test.
 */
public class SkillsLevel{
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_SKILL = "persons_skill";

    private static final String DATABASE_NAME = "SkillsLeveldb";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_NAME + " TEXT NOT NULL, " +
                            KEY_SKILL + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public SkillsLevel(Context c){
        ourContext = c;
    }

    public SkillsLevel open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long createEntry(String name, String skill) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_SKILL, skill);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);


    }

    public String getData() {
        // TODO Auto-generated method stub
        String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_SKILL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iSkill = c.getColumnIndex(KEY_SKILL);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iSkill) + "\n";
        }

        return result;
    }

    public String getName(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_SKILL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
        if (c != null){
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        }
        return null;
    }

    public String getSkillLevel(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_SKILL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
        if (c != null){
            c.moveToFirst();
            String skill = c.getString(2);
            return skill;
        }
        return null;
    }

    public void updateEntry(long lRow, String mName, String mSkill) throws SQLException{
        // TODO Auto-generated method stub
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_NAME, mName);
        cvUpdate.put(KEY_SKILL, mSkill);
        ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
    }

    public void deleteEntry(long lRow1) throws SQLException{
        // TODO Auto-generated method stub
        ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
    }
}
