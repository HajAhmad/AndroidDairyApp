package ir.plusrobot.dairyapp.database;


public class SqlContract {

    public static final String TABLE_DAIRY = "dairy";

    public static final String TD_COLUMN_ID = "id";
    public static final String TD_COLUMN_TITLE = "title";
    public static final String TD_COLUMN_CONTENT = "content";
    public static final String TD_COLUMN_DATE = "date";
    public static final String TD_COLUMN_FAV = "fav";


    /**
     * Table Dairy Creation Table
     */
    public static final String TABLE_DAIRY_CREATION = "CREATE TABLE " + TABLE_DAIRY + "("
            + TD_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TD_COLUMN_TITLE + " TEXT,"
            + TD_COLUMN_CONTENT + " TEXT,"
            + TD_COLUMN_DATE + " TEXT,"
            + TD_COLUMN_FAV + " TEXT"
            + ");";


    public static final String BOOLEAN_FALSE = "false";
    public static final String BOOLEAN_TRUE = "true";


}
