package com.drammatik.cinema.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.drammatik.cinema.ui.addMovie.AddMovieFragment;
import com.drammatik.cinema.ui.detailMovie.DetailFragment;
import com.drammatik.cinema.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.drammatik.cinema.ui.addMovie.AddMovieFragment.ADD_DESCRIPTION_KEY;
import static com.drammatik.cinema.ui.addMovie.AddMovieFragment.ADD_TITLE_KEY;

public class HomeFragment extends Fragment {

    private static final String TITANIC_TITLE_COLOR = "titanic";
    private static final String ONCE_UPON_A_TIME_IN_HOLLYWOOD_COLOR = "once upon a time in Hollywood";
    private static final String PULP_FICTION_TITLE_COLOR = "pulp fiction";

    private static final String THIS_ADD_TITLE_KEY = "add title key";
    private static final String THIS_ADD_DESCRIPTION_KEY = "add description key";
    private static final String MOVIE_KEY = "movie key";

    private String titleName;

    private TextView mTitleTitanicTextView;
    private TextView mTitleOnceUponATimeTextView;
    private TextView mTitlePulpFictionTextView;

    ArrayList<String> movie = new ArrayList<>(0);
    String descriptionAdd;
    String titleAdd;

    public final static String TITLE_KEY = "title key";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMovieFragment addMovieFragment = new AddMovieFragment();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_close_exit)
                        .replace(R.id.home_container, addMovieFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mTitleTitanicTextView = root.findViewById(R.id.titanic_title_text_view);
        mTitleOnceUponATimeTextView = root.findViewById(R.id.once_upon_a_time_title_text_view);
        mTitlePulpFictionTextView = root.findViewById(R.id.pulp_fiction_title_text_view);

        if (savedInstanceState != null) {
            int color = savedInstanceState.getInt(TITANIC_TITLE_COLOR);
            mTitleTitanicTextView.setTextColor(color);
            color = savedInstanceState.getInt(ONCE_UPON_A_TIME_IN_HOLLYWOOD_COLOR);
            mTitleOnceUponATimeTextView.setTextColor(color);
            color = savedInstanceState.getInt(PULP_FICTION_TITLE_COLOR);
            mTitlePulpFictionTextView.setTextColor(color);
            titleAdd = savedInstanceState.getString(THIS_ADD_TITLE_KEY);
            descriptionAdd = savedInstanceState.getString(THIS_ADD_DESCRIPTION_KEY);
            movie = savedInstanceState.getStringArrayList(MOVIE_KEY);
        }

        Button titanicDetailButton = root.findViewById(R.id.detail_titanic_button);
        Button onceUponATimeDetailButton = root.findViewById(R.id.detail_once_upon_a_tome_in_hollywood_button);
        Button pulpFictionDetailButton = root.findViewById(R.id.detail_pulp_fiction_button);

        ImageView titanicDetailImageView = root.findViewById(R.id.titanic_image_view);
        ImageView onceUponATimeDetailImageView = root.findViewById(R.id.once_upon_a_time_in_hollywood_image_view);
        ImageView pulpFictionDetailImageView = root.findViewById(R.id.pulp_fiction_image_view);


        titanicDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(mTitleTitanicTextView, R.string.titanic_title);
            }
        });
        onceUponATimeDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(mTitleOnceUponATimeTextView, R.string.once_upon_a_time_title);
            }
        });
        pulpFictionDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(mTitlePulpFictionTextView, R.string.pulp_fiction_title);
            }
        });

        titanicDetailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(mTitleTitanicTextView, R.string.titanic_title);
            }
        });
        onceUponATimeDetailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(mTitleOnceUponATimeTextView, R.string.once_upon_a_time_title);
            }
        });
        pulpFictionDetailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(mTitlePulpFictionTextView, R.string.pulp_fiction_title);
            }
        });
///////
        if (getArguments() != null) {
            titleAdd = getArguments().getString(ADD_TITLE_KEY);
            descriptionAdd = getArguments().getString(ADD_DESCRIPTION_KEY);
            movie.add(titleAdd);
            movie.add(descriptionAdd);
        }

        if (!movie.isEmpty()) {
            LinearLayout mainLayout = root.findViewById(R.id.film_list_linear_layout);

            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView addTitleTextView = new TextView(getContext());
            TextView addDescriptionTextView = new TextView(getContext());

            addTitleTextView.setText(movie.get(0));
            addDescriptionTextView.setText(movie.get(1));


            addTitleTextView.setLayoutParams(layoutParams);
            addDescriptionTextView.setLayoutParams(layoutParams);

            mainLayout.addView(addTitleTextView);
            mainLayout.addView(addDescriptionTextView);
        }
        ///////////
        return root;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TITANIC_TITLE_COLOR, mTitleTitanicTextView.getCurrentTextColor());
        outState.putInt(ONCE_UPON_A_TIME_IN_HOLLYWOOD_COLOR, mTitleOnceUponATimeTextView.getCurrentTextColor());
        outState.putInt(PULP_FICTION_TITLE_COLOR, mTitlePulpFictionTextView.getCurrentTextColor());
        outState.putString(THIS_ADD_TITLE_KEY, titleAdd);
        outState.putString(THIS_ADD_DESCRIPTION_KEY, descriptionAdd);
        outState.putStringArrayList(MOVIE_KEY, movie);
    }

    public void onClickDetail(TextView title, int getTextTitle) {
        titleName = getText(getTextTitle).toString();
        title.setTextColor(getResources().getColor(R.color.colorPrimary));
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, titleName);
        DetailFragment detailFrag = new DetailFragment();
        detailFrag.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_close_exit)
                .replace(R.id.nav_host_fragment, detailFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }
}