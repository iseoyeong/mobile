//과제명: 영화 정보 관리 앱
//분반 : 01분반
//학번 : 20180990 성명 : 이서영
//제출일 : 2022년 6월 24일

package ddwucom.mobile.finalreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;
    final int SEARCH_CODE = 300;
    String data = "sy";

    ListView listView;
    MovieAdapter adapter;
    ArrayList<Movie> movieList = null;
    MovieDBManager movieDBManager;
    TextView textView;

    protected void onCreate(Bundle savedInstanceState, Configuration newConfig, Bundle outState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        movieDBManager = new MovieDBManager(this);

        movieList = movieDBManager.getMovieList();

        adapter = new MovieAdapter(this, R.layout.activity_custom, movieList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //수정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie movie = movieList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("movie", movie);
                startActivityForResult(intent, UPDATE_CODE);
            }
        }); //수정

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                //삭제 대화상자
                final int pos = position;
                Movie movie = movieList.get(pos);
                String title = movie.getTitle();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("영화 삭제")
                        .setMessage(title+"를 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                if (movieDBManager.removeMovie(movieList.get(pos).get_id())) {
                                    movieDBManager.getAllMovie();
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        }); //삭제

        textView = findViewById(R.id.textView);

        if(savedInstanceState != null) {
            data = savedInstanceState.getString("data");
    }

    @Override
    public void onConfigurationChange(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 가로 방향
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 세로 방향
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("data", data);
    }

    protected void onResume() {
        super.onResume();
        movieDBManager.getAllMovie();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:   //영화 추가
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                return true;
            case R.id.intro:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("개발자 소개")
                        .setIcon(R.mipmap.image)
                        .setMessage("01분반 20180990 이서영")
                        .setPositiveButton("확인", null)
                        .show();
                return true;
            case R.id.close:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
                return true;
            case R.id.search:
                Intent intent2 = new Intent(this, SearchActivity.class);
                startActivityForResult(intent2, REQ_CODE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {       //추가  AddActivity 호출 결과
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {   //수정  UpdateActivity 호출 결과
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == SEARCH_CODE) {   //수정  UpdateActivity 호출 결과
            switch (resultCode) {
                case RESULT_OK:

                    Toast.makeText(this, "검색 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "검색 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}