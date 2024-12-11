package com.example.coffe;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTotalHarga;
    private int totalHarga = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotalHarga = findViewById(R.id.tvTotalHarga);

        Button btnAddKopi1 = findViewById(R.id.btnAddKopi1);
        Button btnAddKopi2 = findViewById(R.id.btnAddKopi2);

        btnAddKopi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalHarga += 25000;
                updateTotalHarga();
            }
        });

        btnAddKopi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalHarga += 20000;
                updateTotalHarga();
            }
        });

        Button btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic for checkout
            }
        });
    }

    private void updateTotalHarga() {
        tvTotalHarga.setText("Total: Rp " + totalHarga);
    }
}
