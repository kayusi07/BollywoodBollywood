package com.kayushi07.bollywood.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "bollywoodgamedb";

    private static final String B_TABLE_NAME = "levels";
    private static final String E_TABLE_NAME = "engli";


    private static final String KEY_ID = "id";
    private static final String KEY_MOVIE = "movies";
    private static final String KEY_SCORE = "score";
    private static final String KEY_UNLOCK_SCORE = "unlock_score";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + B_TABLE_NAME + "(\n" +
                        "    id INTEGER PRIMARY KEY,\n" +
                        "    movies INTEGER,\n" +
                        "    score INTEGER,\n" +
                        "    unlock_score INTEGER\n" +
                        ");"
        );
        b_addLevel(db);

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + E_TABLE_NAME + "(\n" +
                        "    id INTEGER PRIMARY KEY,\n" +
                        "    movies INTEGER,\n" +
                        "    score INTEGER,\n" +
                        "    unlock_score INTEGER\n" +
                        ");"
        );

        e_addLevel(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void b_addLevel(SQLiteDatabase db) {
        try {
            String insertSQL = "INSERT INTO levels \n" +
                    "(id, movies, score, unlock_score)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?);";

            //using the same method execsql for inserting values
            //this time it has two parameters
            //first is the sql string and second is the parameters that is to be binded with the query

            db.execSQL(insertSQL, new Integer[]{1, 0, 0, 50});
            db.execSQL(insertSQL, new Integer[]{2, 0, 0, 100});
            db.execSQL(insertSQL, new Integer[]{3, 0, 0, 100});
            db.execSQL(insertSQL, new Integer[]{4, 0, 0, 70});
            db.execSQL(insertSQL, new Integer[]{5, 0, 0, 70});
            db.execSQL(insertSQL, new Integer[]{6, 0, 0, 70});
            db.execSQL(insertSQL, new Integer[]{7, 0, 0, 70});
            db.execSQL(insertSQL, new Integer[]{8, 0, 0, 70});
            db.execSQL(insertSQL, new Integer[]{9, 0, 0, 70});
            db.execSQL(insertSQL, new Integer[]{10, 0, 0, 70});
//            db.close();
        } catch (Exception e) {
        }
    }
    public void e_addLevel(SQLiteDatabase db) {
            try {
                String insertSQL = "INSERT INTO engli \n" +
                        "(id, movies, score, unlock_score)\n" +
                        "VALUES \n" +
                        "(?, ?, ?, ?);";

                //using the same method execsql for inserting values
                //this time it has two parameters
                //first is the sql string and second is the parameters that is to be binded with the query

                db.execSQL(insertSQL, new Integer[]{1, 0, 0, 100});
                db.execSQL(insertSQL, new Integer[]{2, 0, 0, 50});
                db.execSQL(insertSQL, new Integer[]{3, 0, 0, 200});
                db.execSQL(insertSQL, new Integer[]{4, 0, 0, 150});
                db.execSQL(insertSQL, new Integer[]{5, 0, 0, 200});
                db.execSQL(insertSQL, new Integer[]{6, 0, 0, 150});
                db.execSQL(insertSQL, new Integer[]{7, 0, 0, 150});
                db.execSQL(insertSQL, new Integer[]{8, 0, 0, 200});
                db.execSQL(insertSQL, new Integer[]{9, 0, 0, 200});
                db.execSQL(insertSQL, new Integer[]{10, 0, 0, 150});
//            db.close();
            } catch (Exception e) {
            }

    }

    public void updateScore(int id, int score, int type) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql;
        if (type==0) //bolly
        {
            sql = "UPDATE levels \n" +
                    "SET score = ? \n" +
                    "WHERE id = ?;\n";
        }
        else   //engli
        {
            sql = "UPDATE engli \n" +
                    "SET score = ? \n" +
                    "WHERE id = ?;\n";
        }


        db.execSQL(sql, new Integer[]{score, id});
    }

    public int[] getScore(int id, int type) {
        int scores = 0, unlockScore = 100;
        String selectQuery;
        if(type==0)   //bolly
        {
            selectQuery = "SELECT  * FROM " + B_TABLE_NAME + " where " + KEY_ID + " = " + id;
        }
        else  //engli
        {
            selectQuery = "SELECT  * FROM " + E_TABLE_NAME + " where " + KEY_ID + " = " + id;
        }


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                scores = cursor.getInt(2);
                unlockScore = cursor.getInt(3);
            } while (cursor.moveToNext());
        }

        int sc[] = {scores, unlockScore};
        return sc;
    }



    public ArrayList<Model> getAllLevels(int type)
    {
    SQLiteDatabase db = this.getReadableDatabase();
    ArrayList<Model> levelListFinal = new ArrayList<>();
    //we used rawQuery(sql, selectionargs) for fetching all the levels
        Cursor cursorLevels;

        if(type ==0) //bolly
        {
            cursorLevels = db.rawQuery("SELECT * FROM levels", null);
        }
        else //engli
        {
            cursorLevels = db.rawQuery("SELECT * FROM engli", null);

        }
    int id, movies, score, unlock_score;
    //if the cursor has some data
        int prev_diff_score = 0;

        if(cursorLevels.moveToFirst())
        {
        do {
            id = cursorLevels.getInt(0);
            movies = cursorLevels.getInt(1);
            score = cursorLevels.getInt(2);
            unlock_score = cursorLevels.getInt(3);

            if(id==1)
            {
                levelListFinal.add(new Model(Model.OPEN_LEVEL, "Level " + id, id, movies, score, unlock_score));
                prev_diff_score = unlock_score - score;
            }
            else {
//               diff_score = unlock_score - score;
//System.out.println("Id " + id + " unlock " + unlock_score + " score " + score + " diff " + prev_diff_score);
                if (prev_diff_score > 0)
                    levelListFinal.add(new Model(Model.CLOSED_LEVEL, "Level " + id, id, movies, score, unlock_score));
                else
                    levelListFinal.add(new Model(Model.OPEN_LEVEL, "Level " + id, id, movies, score, unlock_score));

                prev_diff_score = unlock_score - score;

            }

        } while (cursorLevels.moveToNext());
    }
        cursorLevels.close();
        return levelListFinal;
}

//    public List<Level> getAllLevels() {
//        List<Level> contactList = new ArrayList<Level>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Level contact = new Level();
//                contact.setId(Integer.parseInt(cursor.getString(0)));
//                contact.setMovies(cursor.getInt(1));
//                contact.setScore(cursor.getInt(2));
//                // Adding contact to list
//                contactList.add(contact);
//            } while (cursor.moveToNext());
//        }
    }
