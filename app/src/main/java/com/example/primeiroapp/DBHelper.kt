package com.example.primeiroapp
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "CADASTRO_DE_PESSOAS"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "pessoas"
        val ID_COL = "id"
        val NAME_COL = "nome"
        val AGE_COL = "idade"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COL + " TEXT," +
                AGE_COL + " TEXT" + ")"
                )

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addName(name: String, age: String) {
        val values = ContentValues()

        values.put(NAME_COL, name)
        values.put(AGE_COL, age)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun getName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }
}