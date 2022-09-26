package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    EditText etSearch;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.etSearch);

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSearch:
                ArrayList<Movie> result = movieDBManager.getMovieByTitle(etSearch.getText().toString());
                Intent resultIntent = new Intent();
                resultIntent.putExtra("movie", result);
                setResult(RESULT_OK, resultIntent);
                break;
            case R.id.btnSearchCancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

}
