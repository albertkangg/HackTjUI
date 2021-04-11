package com.example.hacktjui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TitleScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);
    }

    public void playGame(View view) {
        Intent i = new Intent(getApplicationContext(), LevelSelectScreen.class);
        startActivity(i);
    }

    public void viewRules(View view) {
        Intent i = new Intent(getApplicationContext(), RulesClass.class);
        startActivity(i);
    }
}
