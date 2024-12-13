package com.example.momen_kopi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class DetailPemesananActivity extends AppCompatActivity {

    private TextView tvOrderedItems, tvTotalPrice;
    private RadioGroup radioGroupPayment;
    private Button btnSubmitOrder;
    private ArrayList<String> orderedItems;
    private ArrayList<String> orderedTemperatures;
    private ArrayList<Integer> orderedQuantities;
    private ArrayList<Integer> orderedPrices;
    private TextInputLayout tilPhoneNumber;
    private TextInputEditText etPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemesanan);
        EdgeToEdge.enable(this);

        // Initialize views
        tvOrderedItems = findViewById(R.id.tvOrderedItems);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        btnSubmitOrder = findViewById(R.id.btnSubmitOrder);

        tilPhoneNumber = findViewById(R.id.tilPhoneNumber);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);


        // Get the data passed from previous activity
        Intent intent = getIntent();
        orderedItems = intent.getStringArrayListExtra("orderedItems");
        orderedTemperatures = intent.getStringArrayListExtra("orderedTemperatures");
        orderedQuantities = intent.getIntegerArrayListExtra("orderedQuantities");
        orderedPrices = intent.getIntegerArrayListExtra("orderedPrices");
        int totalPrice = intent.getIntExtra("totalPrice", 0);

        // Format the ordered items and display them
        StringBuilder itemsText = new StringBuilder();
        for (int i = 0; i < orderedItems.size(); i++) {
            String item = orderedItems.get(i);
            String temperature = orderedTemperatures.get(i);
            int quantity = orderedQuantities.get(i);
            int price = orderedPrices.get(i);
            itemsText.append(item).append(" (").append(temperature).append("), Quantity: \n");
            itemsText.append(quantity).append(", Harga: Rp \n");
            itemsText.append(price).append("\n");
        }

        // Set the data into the views
        tvOrderedItems.setText(itemsText.toString());
        tvTotalPrice.setText("Total Harga: Rp " + totalPrice);

        // Set onClickListener for the submit button
        btnSubmitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pastikan nomor telepon diisi
                String phoneNumber = etPhoneNumber.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(DetailPemesananActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                    return; // exit the method if validation fails
                }

                // Pastikan metode pembayaran dipilih
                int selectedPaymentId = radioGroupPayment.getCheckedRadioButtonId();

                if (selectedPaymentId == -1) {
                    Toast.makeText(DetailPemesananActivity.this, "Please select a payment method.", Toast.LENGTH_SHORT).show();
                } else {
                    // Ambil metode pembayaran yang dipilih
                    String paymentMethod = selectedPaymentId == R.id.rbTransfer ? "Transfer Bank" : "Cash on Delivery";

                    // Buat intent untuk mengirim data ke aktivitas berikutnya (StrukPembayaranActivity)
                    Intent intent2 = new Intent(DetailPemesananActivity.this, StrukPembayaranActivity.class);

                    // Pass the ordered items and other details
                    intent2.putStringArrayListExtra("orderedItems", orderedItems);
                    intent2.putStringArrayListExtra("orderedTemperatures", orderedTemperatures);
                    intent2.putIntegerArrayListExtra("orderedQuantities", orderedQuantities);
                    intent2.putIntegerArrayListExtra("orderedPrices", orderedPrices);
                    intent2.putExtra("totalPrice", totalPrice);
                    intent2.putExtra("paymentMethod", paymentMethod);
                    intent2.putExtra("phoneNumber", phoneNumber);  // Passing the phone number

                    // Start the StrukPembayaranActivity
                    startActivity(intent2);

                    // Mempersiapkan pesan struk
                    StringBuilder receiptText = new StringBuilder();
                    for (int i = 0; i < orderedItems.size(); i++) {
                        String item = orderedItems.get(i);
                        String temperature = orderedTemperatures.get(i);
                        int quantity = orderedQuantities.get(i);
                        int price = orderedPrices.get(i);
                        receiptText.append(item).append(" (").append(temperature).append("), Quantity: ").append(quantity).append(", Harga: Rp ").append(price).append("\n");
                    }
                    receiptText.append("Total Harga: Rp ").append(totalPrice).append("\n");
                    receiptText.append("Metode Pembayaran: ").append(paymentMethod);

                    // Pastikan nomor telepon dimulai dengan +62 jika belum ada
                    if (!phoneNumber.startsWith("+62")) {
                        phoneNumber = "+62" + phoneNumber.replaceFirst("^0", ""); // Menambahkan +62 di depan nomor telepon
                    }

                    // Kirim struk ke WhatsApp
                    String strukMessage = receiptText.toString();
                    String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(strukMessage);

                    Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(sendIntent); // Mulai WhatsApp untuk mengirim pesan
                }
            }
        });



    }
}
