package co.edu.udea.compumovil.birthdayprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

public class BirthDayProvider extends ContentProvider {

    // fields for my content provider
    // content://co.edu.udea.compumovil.birthdayprovider/birthTable
    public static final String AUTHORITY = "co.edu.udea.compumovil.birthdayprovider";
    public static final String URL = "content://" + AUTHORITY + "/friends";//+ DBContract.TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.parse(URL);

    // integer values used in content URI
    static final int FRIENDS = 1;           //all friend-birthday records
    static final int FRIENDS_ID = 2;        //one friend-birthday record

    public static final String BIRTHDAY_TYPE_ITEM = "vnd.android.cursor.item/vnd.co.edu.udea.compumovil.birthdayprovider.friends";
    public static final String BIRTHDAY_TYPE_DIR = "vnd.android.cursor.dir/vnd.co.edu.udea.compumovil.birthdayprovider.friends";

    // private DBHelper dbHelper;
    private SQLiteDatabase database;

    // projection map for a query
    private static HashMap<String, String> BirthMap;

    // maps content URI "patterns" to the integer values that were set above
    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "friends", FRIENDS);
        uriMatcher.addURI(AUTHORITY, "friends/#", FRIENDS_ID);
    }

    public BirthDayProvider() {
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DBHelper dbHelper = new DBHelper(context);
        // permissions to be writable
        database = dbHelper.getWritableDatabase();

        if (database == null)
            return false;
        else
            return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long row = database.insert(DBContract.TABLE_NAME, "", values);
        // If record is added successfully
        if (row > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        // the TABLE_NAME to query on
        queryBuilder.setTables(DBContract.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            // maps all database column names
            case FRIENDS:
                queryBuilder.setProjectionMap(BirthMap);
                break;
            case FRIENDS_ID:
                queryBuilder.appendWhere(DBContract.Column.ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (sortOrder == null || sortOrder == "") {
            // No sorting-> sort on names by default
            sortOrder = DBContract.DEFAULT_SORT;
        }
        Cursor cursor = queryBuilder.query(database, projection, selection,
                selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case FRIENDS:
                count = database.update(DBContract.TABLE_NAME, values, selection, selectionArgs);
                break;
            case FRIENDS_ID:
                count = database.update(DBContract.TABLE_NAME, values, DBContract.Column.ID +
                        " = " + uri.getLastPathSegment() +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case FRIENDS:
                // delete all the records of the table
                count = database.delete(DBContract.TABLE_NAME, selection, selectionArgs);
                break;
            case FRIENDS_ID:
                String id = uri.getLastPathSegment();    //gets the id
                count = database.delete(DBContract.TABLE_NAME, DBContract.Column.ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            // Get all friend-birthday records
            case FRIENDS:
                return BIRTHDAY_TYPE_DIR;
                //return "vnd.android.cursor.dir/vnd.example.friends";
            // Get a particular friend
            case FRIENDS_ID:
                return BIRTHDAY_TYPE_ITEM;
                //return "vnd.android.cursor.item/vnd.example.friends";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


}
