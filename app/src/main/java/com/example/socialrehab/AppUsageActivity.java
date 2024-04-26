package com.example.socialrehab;


import android.app.Activity;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class AppUsageActivity extends AppCompatActivity {
    private TextView textViewApp1, textViewApp2, textViewApp3, totalPoints;
    int badPoints = 0;
    int goodPoints = 0;
    int tTime = 0;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_LAST_UPDATE_DATE = "last_update_date";
    private static final String KEY_POINTS = "points";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_usage);
        textViewApp1 = findViewById(R.id.textViewApp1);
        textViewApp2 = findViewById(R.id.textViewApp2);
        textViewApp3 = findViewById(R.id.textViewApp3);
        totalPoints = findViewById(R.id.totalPoints);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.appUsage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        displayAppUsageStatistics();

        Button updateButton = findViewById(R.id.update_button);
        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        long lastUpdateDate = prefs.getLong(KEY_LAST_UPDATE_DATE, 0);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if (!isSameDay(lastUpdateDate, currentDate)) {
              //   Toast.makeText(updateButton.getContext(), "It has NOT been 24 hours!", Toast.LENGTH_SHORT).show();
             //   } else {
                    Toast.makeText(updateButton.getContext(), "Successfully updating!", Toast.LENGTH_SHORT).show();
                    // Create an intent to start the second activity
                SharedPreferences pointsPref = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);;
                SharedPreferences.Editor editor = pointsPref.edit();
                editor.putInt("BAD_POINTS", badPoints);
                editor.apply();

// Storing points for type 2
                editor.putInt("GOOD_POINTS", goodPoints);
                editor.apply();
           //     }
            }
        });
    }
    private boolean isSameDay(long date1, long date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(date1);
        cal2.setTimeInMillis(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
    private void displayAppUsageStatistics() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        long startTime = System.currentTimeMillis() - 1000 * 60 * 60 * 24; // Start time 24 hours ago
        long endTime = System.currentTimeMillis(); // End time is now
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);

        if (stats != null) {
            for (UsageStats usageStats : stats) {
                long timeInForeground = usageStats.getTotalTimeInForeground();
                long minutes = timeInForeground / (1000);
                if (usageStats.getPackageName().equals("com.google.android.youtube")) {
                    runOnUiThread(() -> textViewApp1.setText("hrs used: " + minutes));
                    badPoints += calculatePoints(minutes);
                    tTime += (int) minutes;
                } else if (usageStats.getPackageName().equals("com.google.android.googlequicksearchbox")) {
                    runOnUiThread(() -> textViewApp2.setText("hrs used: " + minutes));
                    goodPoints += calculatePoints(minutes);
                    tTime += (int) minutes;
                } else if (usageStats.getPackageName().equals("com.google.android.gm")) {
                    runOnUiThread(() -> textViewApp3.setText("hrs used: " + minutes));
                    goodPoints += calculatePoints(minutes);
                    tTime += (int) minutes;
                }
                runOnUiThread(() -> totalPoints.setText("Total Hrs: " + tTime));
            }

        }
    }
    private int calculatePoints(long minutes) {
        // Define your logic for calculating points based on minutes here
        // For example, every 60 minutes (1 hour) of app usage gives 10 points
        return (int) minutes * 100;
    }

}

