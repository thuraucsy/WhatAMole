package com.acromyanmar.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Main Fragment
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    public MainFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ImageButton btn = (ImageButton) rootView.findViewById(R.id.imageButton1);
        btn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), OnGameActivity.class);

        // start next activity
        startActivity(intent);
        getActivity().finish();

    }
}
