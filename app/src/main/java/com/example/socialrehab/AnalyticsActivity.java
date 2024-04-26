package com.example.socialrehab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnalyticsActivity extends AppCompatActivity {
    private TextView socialPoints, proPoints;
    private static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_analytics);
        socialPoints = findViewById(R.id.socialPoints);
        proPoints = findViewById(R.id.proPoints);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainAnalytics), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences pointsPref = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);;

        int sPoints = pointsPref.getInt("BAD_POINTS", 0);
        int pPoints = pointsPref.getInt("GOOD_POINTS", 0);

        socialPoints.setText(String.valueOf(sPoints));
        proPoints.setText(String.valueOf(pPoints));
    }
}