package com.drammatik.cinema.ui.detailMovie;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.drammatik.cinema.R;

import static com.drammatik.cinema.ui.home.HomeFragment.TITLE_KEY;

public class DetailFragment extends Fragment {

    private static final String TAG = "tag";
    private CheckBox mLikeCheckBox;
    private EditText mCommentEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_detail, container, false);

        TextView titleTextView = root.findViewById(R.id.title_detail_text_view);
        TextView descriptionTextView = root.findViewById(R.id.article_detail_text_view);
        ImageView pictureImageView = root.findViewById(R.id.film_photo_image_view);

        mLikeCheckBox = root.findViewById(R.id.like_detail_checkbox);
        mCommentEditText = root.findViewById(R.id.comment_detail_edit_text);

        assert getArguments() != null;
        String titleName = getArguments().getString(TITLE_KEY);

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

        return root;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "User liked the movie: " + mLikeCheckBox.isChecked());
        Log.d(TAG, "User post a comment: [" + mCommentEditText.getText() + "]");

        // may be not best practice
        getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        super.onDestroy();
    }
}
