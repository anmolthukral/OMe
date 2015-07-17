package com.ome.akashsachdeva.ome;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AkashSachdeva on 17-07-2015.
 */
public class menu1_fragment extends Fragment {
    View rootview;
    @Nullable

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.menu1_layout,container,false);
        return rootview;
    }
}
