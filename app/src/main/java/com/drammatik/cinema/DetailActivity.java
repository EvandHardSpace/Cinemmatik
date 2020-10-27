package com.drammatik.cinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.drammatik.cinema.MainActivity.TITLE_KEY;

public class DetailActivity extends AppCompatActivity {
    public static final String COMMENT_KEY = "comment";
    public static final String CHECKBOX_KEY = "checkbox";
    private CheckBox mLike;
    private EditText mComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = findViewById(R.id.title_detail_text_view);
        TextView description = findViewById(R.id.article_detail_text_view);
        ImageView picture = findViewById(R.id.film_photo_image_view);

        Intent intent = getIntent();
        String titleName = intent.getStringExtra(TITLE_KEY);
        mLike = findViewById(R.id.like_detail_checkbox);
        mComment = findViewById(R.id.comment_detail_edit_text);

        assert titleName != null;
        switch (titleName) {
            case "Титаник":
                title.setText(getText(R.string.titanic_title).toString());
                picture.setImageResource(R.drawable.titanic);
                description.setText(getText(R.string.titanic_description).toString());
                break;
            case "Однажды в Голливуде":
                title.setText(getText(R.string.once_upon_title).toString());
                picture.setImageResource(R.drawable.once_upon_a_time_in_hollywood);
                description.setText(getText(R.string.once_description).toString());
                break;
            case "Криминальное чтиво":
                title.setText(getText(R.string.pulp_title).toString());
                picture.setImageResource(R.drawable.pulp_fiction);
                description.setText(getText(R.string.pulp_description).toString());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(CHECKBOX_KEY, mLike.isChecked());
        intent.putExtra(COMMENT_KEY, mComment.getText().toString());
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}