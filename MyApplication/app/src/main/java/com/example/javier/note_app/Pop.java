package com.example.javier.note_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;

public class Pop extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.3));
    }

    public void yes(View view) {
        Intent intent = new Intent();
        //intent.putExtra("RESULT_OK", intent);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void no(View view) {
        Intent intent = new Intent();
        //intent.putExtra("RESULT_CANCELLED", intent);
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}
