package com.example.momen_kopi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StrukPembayaranActivity extends AppCompatActivity {

    private TextView tvOrderedItemsDetails, tvTotalPriceDetail, tvPaymentMethod;
    private Button btnBackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk_pembayaran);

        // Initialize views
        tvOrderedItemsDetails = findViewById(R.id.tvOrderedItemsDetails);
        tvTotalPriceDetail = findViewById(R.id.tvTotalPriceDetail);
        tvPaymentMethod = findViewById(R.id.tvPaymentMethod);
        btnBackToHome = findViewById(R.id.btnBackToHome);

        // Get the data passed from DetailPemesananActivity
        Intent intent = getIntent();
        ArrayList<String> orderedItems = intent.getStringArrayListExtra("orderedItems");
        ArrayList<String> orderedTemperatures = intent.getStringArrayListExtra("orderedTemperatures");
        ArrayList<Integer> orderedQuantities = intent.getIntegerArrayListExtra("orderedQuantities");
        ArrayList<Integer> orderedPrices = intent.getIntegerArrayListExtra("orderedPrices");
        int totalPrice = intent.getIntExtra("totalPrice", 0);
        String paymentMethod = intent.getStringExtra("paymentMethod");

        // Format the ordered items for display
        StringBuilder itemsText = new StringBuilder();
        for (int i = 0; i < orderedItems.size(); i++) {
            String item = orderedItems.get(i);
            String temperature = orderedTemperatures.get(i);
            int quantity = orderedQuantities.get(i);
            int price = orderedPrices.get(i);
            itemsText.append(item).append(" (").append(temperature).append("), Quantity: ")
                    .append(quantity).append(", Harga: Rp ").append(price).append("\n");
        }

        // Set the data into the views
        tvOrderedItemsDetails.setText(itemsText.toString());
        tvTotalPriceDetail.setText("Total Price: Rp " + totalPrice);
        tvPaymentMethod.setText("Payment Method: " + paymentMethod);

        // Handle the back button click event
        btnBackToHome.setOnClickListener(v -> {
            // Navigate back to the main activity or the home activity
            Intent homeIntent = new Intent(StrukPembayaranActivity.this, Home.class);
            startActivity(homeIntent);
        });
    }
}
