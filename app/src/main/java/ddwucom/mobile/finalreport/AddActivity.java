package ddwucom.mobile.finalreport;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText editTitle;
    EditText editActor;
    EditText editGenre;
    EditText editDirector;
    EditText editDate;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTitle = findViewById(R.id.etTitle);
        editActor = findViewById(R.id.etActor);
        editGenre = findViewById(R.id.etGenre);
        editDirector = findViewById(R.id.etDirector);
        editDate = findViewById(R.id.etDate);

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                if (editTitle.getText().toString().equals("") || editActor.getText().toString().equals("")
                        || editGenre.getText().toString().equals("")) {
                    Toast.makeText(this, "항목을 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean result = movieDBManager.addNewMovie(
                            new Movie(editTitle.getText().toString(), editActor.getText().toString(),
                                    editGenre.getText().toString(), editDirector.getText().toString(),
                                    editDate.getText().toString()));

                    if (result) {  //정상 수행
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("movie", editTitle.getText().toString());
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {      //잘못된 수행
                        Toast.makeText(this, "추가 실패", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnAddCancel:   //취소 클릭시 처리
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
