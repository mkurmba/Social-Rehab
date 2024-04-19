package com.example.socialrehab;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class RewardsActivity extends AppCompatActivity {


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


        redeem1 = findViewById(R.id.redeem1);
        redeem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RewardsActivity.this, "Success!!",Toast.LENGTH_SHORT).show();
            }
        });


        redeem2 = findViewById(R.id.redeem2);
        redeem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RewardsActivity.this, "Success!!",Toast.LENGTH_SHORT).show();
            }
        });


        redeem3 = findViewById(R.id.redeem3);
        redeem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RewardsActivity.this, "Sorry, not enough points!",Toast.LENGTH_SHORT).show();
            }
        });


        redeem4 = findViewById(R.id.redeem4);
        redeem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RewardsActivity.this, "Sorry, not enough points!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
