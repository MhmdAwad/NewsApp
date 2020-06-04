package com.mhmdawad.newsapp.ui.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhmdawad.newsapp.R;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedFragment extends DaggerFragment {


    @Inject
    public SavedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }
}
