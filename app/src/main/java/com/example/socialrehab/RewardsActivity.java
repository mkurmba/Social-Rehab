package com.example.socialrehab;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class RewardsActivity extends AppCompatActivity {

    int tPoints;
    TextView pointsCounter;
    private static final String PREFS_NAME = "MyPrefsFile";
    Button redeem1;
    Button redeem2;
    Button redeem3;
    Button redeem4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rewards);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainRewards), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pointsCounter = findViewById(R.id.textView2);
        SharedPreferences pointsPref = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);

        int sPoints = pointsPref.getInt("BAD_POINTS", 0);
        int pPoints = pointsPref.getInt("GOOD_POINTS", 0);

        tPoints = sPoints + pPoints;
        pointsCounter.setText("Points: " + tPoints);

        redeem1 = findViewById(R.id.redeem1);
        redeem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tPoints > 5000) {
                    Toast.makeText(RewardsActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                    tPoints -= 5000;
                    pointsCounter.setText("Points: " + tPoints);
                } else {
                    Toast.makeText(RewardsActivity.this, "Sorry, not enough points!",Toast.LENGTH_SHORT).show();
                }
            }
        });


        redeem2 = findViewById(R.id.redeem2);
        redeem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tPoints > 5000) {
                    Toast.makeText(RewardsActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                    tPoints -= 5000;
                    pointsCounter.setText("Points: " + tPoints);
                } else {
                    Toast.makeText(RewardsActivity.this, "Sorry, not enough points!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        redeem3 = findViewById(R.id.redeem3);
        redeem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tPoints > 20000) {
                    Toast.makeText(RewardsActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                    tPoints -= 20000;
                    pointsCounter.setText("Points: " + tPoints);
                } else {
                    Toast.makeText(RewardsActivity.this, "Sorry, not enough points!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        redeem4 = findViewById(R.id.redeem4);
        redeem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tPoints > 50000) {
                    Toast.makeText(RewardsActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                    tPoints -= 50000;
                    pointsCounter.setText("Points: " + tPoints);
                } else {
                    Toast.makeText(RewardsActivity.this, "Sorry, not enough points!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
