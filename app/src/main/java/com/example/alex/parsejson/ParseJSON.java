package com.example.alex.parsejson;


import android.support.v4.app.Fragment;

public class ParseJSON extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ParserJSONFragment();
    }
}
