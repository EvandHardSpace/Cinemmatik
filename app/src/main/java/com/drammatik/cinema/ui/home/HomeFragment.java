package com.drammatik.cinema.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.drammatik.cinema.ui.detailMovie.DetailFragment;
import com.drammatik.cinema.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private static final String TITANIC_TITLE_COLOR = "titanic";
    private static final String ONCE_UPON_A_TIME_IN_HOLLYWOOD_COLOR = "once upon a time in Hollywood";
    private static final String PULP_FICTION_TITLE_COLOR = "pulp fiction";

    private String titleName;

    private TextView mTitleTitanicTextView;
    private TextView mTitleOnceUponATimeTextView;
    private TextView mTitlePulpFictionTextView;

    public final static String TITLE_KEY = "title key";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTitleTitanicTextView = root.findViewById(R.id.titanic_title_text_view);
        mTitleOnceUponATimeTextView = root.findViewById(R.id.once_upon_a_time_title_text_view);
        mTitlePulpFictionTextView = root.findViewById(R.id.pulp_fiction_title_text_view);

        Button titanicDetailButton = root.findViewById(R.id.detail_titanic_button);
        Button onceUponATimeDetailButton = root.findViewById(R.id.detail_once_upon_a_tome_in_hollywood_button);
        Button pulpFictionDetailButton = root.findViewById(R.id.detail_pulp_fiction_button);

        if (savedInstanceState != null) {
            int color = savedInstanceState.getInt(TITANIC_TITLE_COLOR);
            mTitleTitanicTextView.setTextColor(color);
            color = savedInstanceState.getInt(ONCE_UPON_A_TIME_IN_HOLLYWOOD_COLOR);
            mTitleOnceUponATimeTextView.setTextColor(color);
            color = savedInstanceState.getInt(PULP_FICTION_TITLE_COLOR);
            mTitlePulpFictionTextView.setTextColor(color);
        }

        titanicDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleName = getText(R.string.titanic_title).toString();
                mTitleTitanicTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                Bundle bundle = new Bundle();
                bundle.putString(TITLE_KEY, titleName);
                DetailFragment detailFrag = new DetailFragment();
                detailFrag.setArguments(bundle);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.home_container, detailFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        onceUponATimeDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleName = getText(R.string.once_upon_a_time_title).toString();
                mTitleOnceUponATimeTextView.setTextColor(getResources().getColor(R.color.colorPrimary));

                Bundle bundle = new Bundle();
                bundle.putString(TITLE_KEY, titleName);

                DetailFragment detailFrag = new DetailFragment();
                detailFrag.setArguments(bundle);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.home_container, detailFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        pulpFictionDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleName = getText(R.string.pulp_fiction_title).toString();
                mTitlePulpFictionTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                Bundle bundle = new Bundle();
                bundle.putString(TITLE_KEY, titleName);
                DetailFragment detailFrag = new DetailFragment();
                detailFrag.setArguments(bundle);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.home_container, detailFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return root;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TITANIC_TITLE_COLOR, mTitleTitanicTextView.getCurrentTextColor());
        outState.putInt(ONCE_UPON_A_TIME_IN_HOLLYWOOD_COLOR, mTitleOnceUponATimeTextView.getCurrentTextColor());
        outState.putInt(PULP_FICTION_TITLE_COLOR, mTitlePulpFictionTextView.getCurrentTextColor());
    }

}
