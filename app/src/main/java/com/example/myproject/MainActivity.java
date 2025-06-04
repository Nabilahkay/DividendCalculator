package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText etAmount, etRate, etMonth;
    Button btnCalculate;
    TextView etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        etRate = findViewById(R.id.etRate);
        etMonth = findViewById(R.id.etMonth);
        btnCalculate = findViewById(R.id.btnCalculate);
        etResult = findViewById(R.id.etResult);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        btnCalculate.setOnClickListener(view -> calculateDividend());
    }

    private void calculateDividend() {
            try {
                double amount = Double.parseDouble(etAmount.getText().toString());
                double rate = Double.parseDouble(etRate.getText().toString());
                int month = Integer.parseInt(etMonth.getText().toString());

                if (month < 1 || month > 12){
                    etResult.setText("Months must be between 1 and 12.");
                    return;
                }

                double monthlyDividend = (rate / 100.0 / 12) * amount;
                double totalDividend = monthlyDividend * month;

                String result = String.format("Monthly Dividend: RM %.2f\nTotal Dividend: RM %.2f",
                        monthlyDividend, totalDividend);

                etResult.setText(result);

            } catch (Exception e){
                etResult.setText("Please enter valid numbers in all fields.");
            }

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            if(item.getItemId() == R.id.menu_about){
                Intent intent = new Intent(this,AboutActivity.class);
                startActivity(intent);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }