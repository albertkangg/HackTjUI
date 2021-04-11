package com.example.hacktjui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class RulesClass extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules_page);
    }

    public void backToTitle(View view) {
        finish();
    }
}
