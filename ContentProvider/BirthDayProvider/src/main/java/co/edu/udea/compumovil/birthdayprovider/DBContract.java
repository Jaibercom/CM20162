package co.edu.udea.compumovil.birthdayprovider;

import android.net.Uri;

/**
 * Created by jaiber on 13/04/16.
 */
public class DBContract {

    // DB specific constants
    public static final String DB_NAME = "BirthdayReminder.db"; //
    public static final int DB_VERSION = 1; //
    public static final String TABLE_NAME = "birthdayTable"; //
    public static final String DEFAULT_SORT = Column.NAME; //
    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE_NAME +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " birthday TEXT NOT NULL);";

    public class Column { //
        public static final String ID = "id"; //
        public static final String NAME = "name";
        public static final String BIRTHDAY = "birthday";
    }

}
