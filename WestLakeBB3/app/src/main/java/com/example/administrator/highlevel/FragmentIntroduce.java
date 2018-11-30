package com.example.administrator.highlevel;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/12/8.
 */

public class FragmentIntroduce extends Fragment{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_introduce,container,false);

        AnimationDrawable animationd;
        animationd= ((AnimationDrawable) ((ImageView) view.findViewById(R.id.imageView3)).getDrawable());
        animationd.start();

        return view;
    }
}
