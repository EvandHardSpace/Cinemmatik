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
    private CheckBox mLikeCheckBox;
    private EditText mCommentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView titleTextView = findViewById(R.id.title_detail_text_view);
        TextView descriptionTextView = findViewById(R.id.article_detail_text_view);
        ImageView pictureImageView = findViewById(R.id.film_photo_image_view);

        Intent intent = getIntent();
        String titleName = intent.getStringExtra(TITLE_KEY);
        mLikeCheckBox = findViewById(R.id.like_detail_checkbox);
        mCommentEditText = findViewById(R.id.comment_detail_edit_text);

        assert titleName != null;
        switch (titleName) {
            case "Титаник":
                titleTextView.setText(getText(R.string.titanic_title).toString());
                pictureImageView.setImageResource(R.drawable.titanic);
                descriptionTextView.setText(getText(R.string.titanic_description).toString());
                break;
            case "Однажды в Голливуде":
                titleTextView.setText(getText(R.string.once_upon_a_time_title).toString());
                pictureImageView.setImageResource(R.drawable.once_upon_a_time_in_hollywood_photo);
                descriptionTextView.setText(getText(R.string.once_upon_a_time_description).toString());
                break;
            case "Криминальное чтиво":
                titleTextView.setText(getText(R.string.pulp_fiction_title).toString());
                pictureImageView.setImageResource(R.drawable.pulp_fiction_photo);
                descriptionTextView.setText(getText(R.string.pulp__fiction_description).toString());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(CHECKBOX_KEY, mLikeCheckBox.isChecked());
        intent.putExtra(COMMENT_KEY, mCommentEditText.getText().toString());
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}