package ir.plusrobot.dairyapp.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ir.plusrobot.dairyapp.Note;
import ir.plusrobot.dairyapp.NoteItem;

public class SqlHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mDb;

    public static final String DATABASE_NAME = "dairydb";
    public static final int DATABASE_VERSION = 2;


    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlContract.TABLE_DAIRY_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SqlContract.TABLE_DAIRY);
    }

    /**
     * Get All Notes.
     *
     * @return A List of Notes. see {@link NoteItem} for more information.
     */
    public List<NoteItem> getAllNotes() {
        mDb = getReadableDatabase();

        List<NoteItem> noteList = new ArrayList<>();
        NoteItem note = new NoteItem();

        Cursor c = mDb.rawQuery("SELECT * FROM " + SqlContract.TABLE_DAIRY, null);
        if (c.moveToFirst()) {
            do {
                note.setId(c.getInt(c.getColumnIndex(SqlContract.TD_COLUMN_ID)));
                note.setTitle(c.getString(c.getColumnIndex(SqlContract.TD_COLUMN_TITLE)));
                note.setDate(c.getString(c.getColumnIndex(SqlContract.TD_COLUMN_DATE)));
                note.setFav(Boolean.valueOf(c.getString(c.getColumnIndex(SqlContract.TD_COLUMN_FAV))));
                noteList.add(note);
            } while (c.moveToNext());
            c.close();
        }

        mDb.close();

        return noteList;

    }

    /**
     * Insert New Note.
     *
     * @param note Your Note Item based On {@link Note} Class.
     * @return Inserted Note <b>Id</b>.
     */
    public long insertNote(Note note) {
        mDb = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SqlContract.TD_COLUMN_FAV, false);//بخاطر اینکه وقتی خاطره برای اولین بار اضافه میشه معلوم نیست موردعلاقه هست یا نه و این مورد توی صفحه اصلی تعیین میشه
        values.put(SqlContract.TD_COLUMN_TITLE, note.getTitle());
        values.put(SqlContract.TD_COLUMN_CONTENT, note.getContent());
        values.put(SqlContract.TD_COLUMN_DATE, note.getDate());

        long retId = mDb.insert(SqlContract.TABLE_DAIRY, null, values);

        mDb.close();
        return retId;
    }

    /**
     * Delete Note Based On Id;
     *
     * @param id Note Id.
     * @return Effected Rows Count.
     */
    public int deleteNote(int id) {
        mDb = getWritableDatabase();
        int effectedRowsCount = mDb.delete(SqlContract.TABLE_DAIRY, SqlContract.TD_COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        mDb.close();
        return effectedRowsCount;
    }

    /**
     * Get Only <b>One</b> Note.
     *
     * @param id Note <b>Id</b>.
     * @return A Note Object. see {@link Note} Class.
     */
    public Note getNote(int id) {
        mDb = getReadableDatabase();
        Cursor c = mDb.query(
                SqlContract.TABLE_DAIRY,//نام جدول
                null, //نام ستون های که می خواهیم برگردانیم، اگر بخواهیم تمام ستون ها را برگردانیم null قرار میدهیم
                SqlContract.TD_COLUMN_ID + " = ?",//شرط اصلی
                new String[]{String.valueOf(id)},//مقدار شرط
                null,//شرط GroupBy ،اگر نداشته باشیم null قرار می دهیم
                null,//شرط Having، اگر نداشته باشیم null قرار می دهیم
                null); //اینکه رکورد های برگشتی بصورت عمودی صعودی مرتب شوند یا نزولی
        Note note = new Note();

        if (c.moveToFirst()) {
            note.setTitle(c.getString(c.getColumnIndex(SqlContract.TD_COLUMN_TITLE)));
            note.setDate(c.getString(c.getColumnIndex(SqlContract.TD_COLUMN_DATE)));
            note.setContent(c.getString(c.getColumnIndex(SqlContract.TD_COLUMN_CONTENT)));

            c.close();
        }

        return note;
    }


}
