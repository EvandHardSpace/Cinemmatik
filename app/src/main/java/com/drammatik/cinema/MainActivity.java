package com.drammatik.cinema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TITANIC_TITLE_COLOR = "titanic";
    private static final String ONCE_UPON_A_TIME_IN_HOLLYWOOD_COLOR = "once upon a time in Hollywood";
    private static final String PULP_FICTION_TITLE_COLOR = "pulp fiction";
    private static final int REQUEST_CODE = 42;

    private String titleName;

    private TextView mTitleTitanicTextView;
    private TextView mTitleOnceUponATimeTextView;
    private TextView mTitlePulpFictionTextView;

    public final static String TITLE_KEY = "title key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleTitanicTextView = findViewById(R.id.title_titanic);
        mTitleOnceUponATimeTextView = findViewById(R.id.title_once);
        mTitlePulpFictionTextView = findViewById(R.id.title_pulp);

        if (savedInstanceState != null) {
            int color = savedInstanceState.getInt(TITANIC_TITLE_COLOR);
            mTitleTitanicTextView.setTextColor(color);
            color = savedInstanceState.getInt(ONCE_UPON_A_TIME_IN_HOLLYWOOD_COLOR);
            mTitleOnceUponATimeTextView.setTextColor(color);
            color = savedInstanceState.getInt(PULP_FICTION_TITLE_COLOR);
            mTitlePulpFictionTextView.setTextColor(color);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void detailTitanicButton(View view) {
        titleName = getText(R.string.titanic_title).toString();
        mTitleTitanicTextView.setTextColor(Color.BLUE);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(TITLE_KEY, titleName);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void detailOnceUponATimeInHollywoodButton(View view) {
        titleName = getText(R.string.once_upon_title).toString();
        mTitleOnceUponATimeTextView.setTextColor(Color.BLUE);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(TITLE_KEY, titleName);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void detailPulpFictionButton(View view) {
        titleName = getText(R.string.pulp_title).toString();
        mTitlePulpFictionTextView.setTextColor(Color.BLUE);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(TITLE_KEY, titleName);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void shareButton(View view) {
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setText(getString(R.string.share_text))
                .setChooserTitle(R.string.share_with)
                .startChooser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                boolean isLiked = data.getBooleanExtra(DetailActivity.CHECKBOX_KEY, false);
                String comment = data.getStringExtra(DetailActivity.COMMENT_KEY);
                Log.d(TAG, "User liked the movie: " + isLiked);
                Log.d(TAG, "User post a comment: [ " + comment + " ]");

            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TITANIC_TITLE_COLOR, mTitleTitanicTextView.getCurrentTextColor());
        outState.putInt(ONCE_UPON_A_TIME_IN_HOLLYWOOD_COLOR, mTitleOnceUponATimeTextView.getCurrentTextColor());
        outState.putInt(PULP_FICTION_TITLE_COLOR, mTitlePulpFictionTextView.getCurrentTextColor());
    }
}