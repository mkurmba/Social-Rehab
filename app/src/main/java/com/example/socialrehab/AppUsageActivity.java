package com.example.socialrehab;


import android.app.Activity;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
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

import android.text.format.DateUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class AppUsageActivity extends AppCompatActivity {
    private TextView textViewApp1, textViewApp2, textViewApp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_usage);
        textViewApp1 = findViewById(R.id.textViewApp1);
        textViewApp2 = findViewById(R.id.textViewApp2);
        textViewApp3 = findViewById(R.id.textViewApp3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.appUsage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        displayAppUsageStatistics();

    }
    private void displayAppUsageStatistics() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        long startTime = System.currentTimeMillis() - 1000 * 60 * 60 * 24; // Start time 24 hours ago
        long endTime = System.currentTimeMillis(); // End time is now
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);

        if (stats != null) {
            for (UsageStats usageStats : stats) {
                long timeInForeground = usageStats.getTotalTimeInForeground();
                long hours = timeInForeground / (1000 * 60 * 60);
                if (usageStats.getPackageName().equals("com.google.android.youtube")) {
                    runOnUiThread(() -> textViewApp1.setText("hrs used: " + hours));
                } else if (usageStats.getPackageName().equals("com.google.android.googlequicksearchbox")) {
                    runOnUiThread(() -> textViewApp2.setText("hrs used: " + hours));
                } else if (usageStats.getPackageName().equals("com.google.android.gm")) {
                    runOnUiThread(() -> textViewApp3.setText("hrs used: " + hours));
                }
            }
        }
    }
}