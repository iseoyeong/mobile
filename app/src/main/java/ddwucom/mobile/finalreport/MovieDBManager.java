package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MovieDBManager {

    MovieDBHelper movieDBHelper = null;
    Cursor cursor = null;
    ArrayList<Movie> movieList;

    public MovieDBManager(Context context) {
        movieDBHelper = new MovieDBHelper(context);
        movieList = new ArrayList<Movie>();
    }

    public ArrayList<Movie> getMovieList() { return movieList; }

    public ArrayList<Movie> getAllMovie() {
        movieList.clear();

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
            String actor = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTOR));
            String genre = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_GENRE));
            String director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
            String date = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DATE));
            int imgIcon = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_IMAGE));
            movieList.add ( new Movie (id, title, actor, genre, director, date, imgIcon) );
        }

        cursor.close();
        movieDBHelper.close();
        return movieList;
    }

    //새로운 movie 추가
    public boolean addNewMovie(Movie newMovie) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(MovieDBHelper.COL_TITLE, newMovie.getTitle());
        value.put(MovieDBHelper.COL_ACTOR, newMovie.getActor());
        value.put(MovieDBHelper.COL_GENRE, newMovie.getGenre());
        value.put(MovieDBHelper.COL_DIRECTOR, newMovie.getDirector());
        value.put(MovieDBHelper.COL_DATE, newMovie.getDate());
        //value.put(MovieDBHelper.COL_IMGICON, newMovie.getImgIcon());
        long result = db.insert(MovieDBHelper.TABLE_NAME, null, value);
        movieDBHelper.close();
        if (result > 0) return true;
        return false;
    }

    //_id를 기준으로 movie 정보 변경
    public boolean modifyMovie(Movie movie) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(MovieDBHelper.COL_TITLE, movie.getTitle());
        row.put(MovieDBHelper.COL_ACTOR, movie.getActor());
        row.put(MovieDBHelper.COL_GENRE, movie.getGenre());
        row.put(MovieDBHelper.COL_DIRECTOR, movie.getDirector());
        row.put(MovieDBHelper.COL_DATE, movie.getDate());

        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(movie.get_id()) };
        int result = sqLiteDatabase.update(MovieDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        movieDBHelper.close();
        if(result > 0) return true;
        return false;
    }

    //_id를 기준으로 DB에서 movie 삭제
    public boolean removeMovie(long id) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        int result = sqLiteDatabase.delete(MovieDBHelper.TABLE_NAME, whereClause, whereArgs);
        movieDBHelper.close();
        if (result > 0) return true;
        return false;
    }

//    //영화 제목으로 DB 검색
    public ArrayList<Movie> getMovieByTitle(String key) {
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        String[] columns = { "genre" };
        String selection = "title=?";
        String[] selectArgs = new String[] { key };

        Cursor cursor = db.rawQuery("SELECT genre " + MovieDBHelper.TABLE_NAME +
                " WHERE title='key';", null);

        cursor.close();
        movieDBHelper.close();
        return movieList;
    }

    //close 수행
    public void close() {
        if (movieDBHelper != null) movieDBHelper.close();
        if (cursor != null) cursor.close();
    };
}
