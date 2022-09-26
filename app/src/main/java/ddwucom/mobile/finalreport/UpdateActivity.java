package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    Movie movie;

    EditText etT;
    EditText etA;
    EditText etG;
    EditText etDir;
    EditText etD;

    MovieDBManager movieDBManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        movie = (Movie) getIntent().getSerializableExtra("movie");

        etT = findViewById(R.id.etUTitle);
        etA = findViewById(R.id.etUActor);
        etG = findViewById(R.id.etUGenre);
        etDir = findViewById(R.id.etUDirector);
        etD = findViewById(R.id.etUDate);

        etT.setText(movie.getTitle());
        etA.setText(movie.getActor());
        etG.setText(movie.getGenre());
        etDir.setText(movie.getDirector());
        etD.setText(movie.getDate());

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnUpdate:
                if (etT.getText().toString().equals("") || etA.getText().toString().equals("")
                    || etG.getText().toString().equals("")) {
                    Toast.makeText(this, "항목을 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    movie.setTitle(etT.getText().toString());
                    movie.setActor(etA.getText().toString());
                    movie.setGenre(etG.getText().toString());
                    movie.setDirector(etDir.getText().toString());
                    movie.setDate(etD.getText().toString());

                    if (movieDBManager.modifyMovie(movie)) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("movie", movie);
                        setResult(RESULT_OK, resultIntent);
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                }
                break;

            case R.id.btnUpdateCancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
