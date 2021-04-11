package com.example.hacktjui;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LevelSelectScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_select);
    }
    public void easy(View view) {
        Intent i = new Intent(LevelSelectScreen.this, MainActivity.class);
        int level = 0;
        i.putExtra("level", level);
        startActivity(i);
    }
    public void medium(View view) {
        Intent i = new Intent(LevelSelectScreen.this, MainActivity.class);
        int level = 1;
        i.putExtra("level", level);
        startActivity(i);
    }
    public void hard(View view) {
        Intent i = new Intent(LevelSelectScreen.this, MainActivity.class);
        int level = 2;
        i.putExtra("level", level);
        startActivity(i);
    }
//    public void survival(View view) {
//        Intent i = new Intent(LevelSelectScreen.this, MainActivity.class);
//        int level = 3;
//        i.putExtra("level", level);
//        startActivity(i);
//    }

    public void backToTitle(View view) {
        finish();
    }
}
