package zarag.sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class MainActivity extends Activity {

    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //myDb = openOrCreateDatabase("my.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
    }

    public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "MyFirstDB";

        private DatabaseHelper m;
        private SQLiteDatabase db;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DATABASE_NAME);
            m = new DatabaseHelper(getApplicationContext());
            db = m.getWritableDatabase();
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }


    }

    public Context getApplicationContext() {
        return this;
    }

}
