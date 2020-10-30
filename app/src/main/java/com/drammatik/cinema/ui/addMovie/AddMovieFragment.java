package com.drammatik.cinema.ui.addMovie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.drammatik.cinema.R;
import com.drammatik.cinema.ui.home.HomeFragment;

public class AddMovieFragment extends Fragment {

    public static final String ADD_TITLE_KEY = "Title";
    public static final String ADD_DESCRIPTION_KEY = "Description";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_movie, container, false);

        final EditText mAddTitleEditText = root.findViewById(R.id.add_title_edit_text);
        final EditText mAddDescriptionEditText = root.findViewById(R.id.add_description_edit_text);
        Button mAddMovieButton = root.findViewById(R.id.add_movie_button);

        mAddMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mAddDescriptionEditText.getText().toString().equals("") &&
                        !mAddTitleEditText.getText().toString().equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ADD_TITLE_KEY, mAddTitleEditText.getText().toString());
                    bundle.putString(ADD_DESCRIPTION_KEY, mAddDescriptionEditText.getText().toString());
                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.fragment_close_enter, R.anim.fragment_close_exit)
                            .replace(R.id.home_container, homeFragment, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroy() {
        // maybe not best practice
        getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        super.onDestroy();
    }
}
