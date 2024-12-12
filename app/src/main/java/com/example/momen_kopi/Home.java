package com.example.momen_kopi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        // Get references to the buttons and TextViews
        Button btnAddKopi1 = findViewById(R.id.btnAddKopi1);
        Button btnProceed = findViewById(R.id.btnProceed);
        Button btnClosePopup = findViewById(R.id.btnClosePopup);
        TextView tvPopupPrice = findViewById(R.id.tvPopupPrice);
        LinearLayout popupPriceLayout = findViewById(R.id.popupPriceLayout);

        // Button add kopi 1
        btnAddKopi1.setOnClickListener(v -> {
            totalPrice += 25000; // Harga kopi 1
            tvPopupPrice.setText("Item: Rp " + totalPrice); // Update text
            popupPriceLayout.setVisibility(View.VISIBLE); // Show popup
        });

        // Proceed to next layout (e.g. CartActivity or Checkout)
        btnProceed.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, CartActivity.class); // Ganti dengan aktivitas tujuan
            startActivity(intent);
        });

        // Close popup logic
        btnClosePopup.setOnClickListener(v -> {
            popupPriceLayout.setVisibility(View.GONE); // Hide popup
        });
    }
}
