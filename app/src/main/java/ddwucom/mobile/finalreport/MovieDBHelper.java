package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MovieDBHelper extends SQLiteOpenHelper {
    final static String TAG = "MovieDBHelper";

    final static String DB_NAME = "movies.db";
    public final static String TABLE_NAME = "movie_table";

    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_ACTOR = "actor";
    public final static String COL_GENRE = "genre";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_DATE = "date";
    public final static String COL_IMAGE = "img";

    public MovieDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_TITLE + " TEXT, " + COL_ACTOR + " TEXT, " + COL_GENRE + " TEXT, " + COL_DIRECTOR +
                " TEXT, " + COL_DATE + " TEXT, "+ COL_IMAGE + " TEXT)";
        Log.d(TAG, sql);
        db.execSQL(sql);

        insetSample(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insetSample(SQLiteDatabase db) {
        db.execSQL("insert into " + TABLE_NAME + " values (null, 'in', '앤 해서웨이', 'SF/모험', null, null, null )");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '쥬라기 월드', '샘 닐', '모험', null, null, null)");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '닥터 스트레인지', '베네딕트 컴버배치', '액션', null, null, null)");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '범죄도시', '마동석', '액션', null, null, null)");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '브로커', '송강호', '드라마', null, null, null)");
    }
}
