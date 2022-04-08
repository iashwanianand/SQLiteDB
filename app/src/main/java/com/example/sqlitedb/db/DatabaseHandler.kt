package com.example.sqlitedb.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlitedb.model.PersonData
import com.example.sqlitedb.utilities.Constants

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DATA_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val createPersonTable = ("CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Constants.NAME + " TEXT,"
                + Constants.EMAIL + " TEXT," + Constants.PHONE + " TEXT )")
        db.execSQL(createPersonTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + Constants.DB_NAME)
        if (db != null) {
            onCreate(db)
        }
    }

    //add Person Details
    fun addPerson(personData: PersonData): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        /*contentValues.put(Constants.IMAGE, personData.image)*/
        contentValues.put(Constants.NAME, personData.name)
        contentValues.put(Constants.EMAIL, personData.email)
        contentValues.put(Constants.PHONE, personData.phone)
        return db.insert(Constants.TABLE_NAME, null, contentValues)
    }


    //function to fetch/read data
    fun getPerson(): ArrayList<PersonData> {
        val db = this.readableDatabase
        val cursorCourses = db.rawQuery("SELECT * FROM ${Constants.TABLE_NAME}", null)
        val dataArrayList: ArrayList<PersonData> = ArrayList()
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                dataArrayList.add(
                    PersonData(
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)
                    )
                )
            } while (cursorCourses.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close()
        return dataArrayList
    }


    //Update Person Details
    fun updatePerson(updateDetails: PersonData): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        /*contentValues.put(Constants.IMAGE, updateDetails.image)*/
        contentValues.put(Constants.NAME, updateDetails.name)
        contentValues.put(Constants.EMAIL, updateDetails.email)
        contentValues.put(Constants.PHONE, updateDetails.phone)

        return db.update(Constants.TABLE_NAME, contentValues, "id= ?", arrayOf(Constants.ID))
    }

    //delete single Person Details
    fun deletePerson(): Int {
        val db = this.writableDatabase

        return db.delete(Constants.TABLE_NAME, "id= ?", arrayOf(Constants.ID))
    }

    //delete all Person Details
    fun deleteAllPerson(): Int {
        val db = this.readableDatabase

        return db.delete(Constants.TABLE_NAME, null, null)
    }
}