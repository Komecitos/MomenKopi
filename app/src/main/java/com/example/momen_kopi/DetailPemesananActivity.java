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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    private DBManager dbManager;

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

        // Initialize DBManager
        dbManager = new DBManager(this);

        // Get the data passed from the previous activity
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
            itemsText.append(String.format("%s:\n", item))
                    .append(String.format("    Jenis   : %s\n", temperature))
                    .append(String.format("    Jumlah  : %d\n", quantity))
                    .append(String.format("    Total harga: Rp %,.0f\n\n", (double) price * quantity));
        }

        // Set the data into the views
        tvOrderedItems.setText(itemsText.toString());
        tvTotalPrice.setText("Total Harga: Rp " + totalPrice);

        // Set onClickListener for the submit button
        btnSubmitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ensure phone number is entered
                String phoneNumber = etPhoneNumber.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(DetailPemesananActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                    return; // exit the method if validation fails
                }

                // Ensure a payment method is selected
                int selectedPaymentId = radioGroupPayment.getCheckedRadioButtonId();

                if (selectedPaymentId == -1) {
                    Toast.makeText(DetailPemesananActivity.this, "Please select a payment method.", Toast.LENGTH_SHORT).show();
                } else {
                    // Get the selected payment method
                    String paymentMethod = selectedPaymentId == R.id.rbTransfer ? "QRIS" : "Tunai";

                    // Save order into database
                    dbManager.open();
                    long result = dbManager.insertOrder(phoneNumber,
                            String.join(", ", orderedItems),  // Join ordered items as a single string
                            String.join(", ", orderedTemperatures),
                            orderedQuantities.toString(),  // Convert list to string
                            orderedPrices.toString(),
                            totalPrice,
                            paymentMethod);
                    dbManager.close();

                    if (result != -1) {
                        Toast.makeText(DetailPemesananActivity.this, "Order successfully saved!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailPemesananActivity.this, "Failed to save order.", Toast.LENGTH_SHORT).show();
                    }

                    // Create intent to send data to the next activity (StrukPembayaranActivity)
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

                    // Creating receipt message for WhatsApp
                    StringBuilder receiptText = new StringBuilder();

                    receiptText.append("-------Momen Kopi-------------\n");
                    receiptText.append("==============================\n");

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String currentDateandTime = sdf.format(new Date());
                    receiptText.append("Tanggal: ").append(currentDateandTime).append("\n");
                    receiptText.append("==============================\n");

                    for (int i = 0; i < orderedItems.size(); i++) {
                        String item = orderedItems.get(i);
                        String temperature = orderedTemperatures.get(i);
                        int quantity = orderedQuantities.get(i);
                        int price = orderedPrices.get(i);

                        receiptText.append(String.format("%s (%s)\n", item, temperature))
                                .append(String.format("Quantity: %d\n", quantity))
                                .append(String.format("Harga: Rp %d\n", price))
                                .append("--------------------------------\n");
                    }

                    receiptText.append("================================\n");
                    receiptText.append(String.format("Total Harga: Rp %d\n", totalPrice));
                    receiptText.append("Metode Pembayaran: ").append(paymentMethod).append("\n");

                    receiptText.append("================================\n");
                    receiptText.append("Terima kasih telah memesan di Momen Kopi!\n");
                    receiptText.append("Silakan kunjungi kami lagi!\n");

                    // Modify phone number if needed (for Indonesian users)
                    if (!phoneNumber.startsWith("+62")) {
                        phoneNumber = "+62" + phoneNumber.replaceFirst("^0", "");
                    }

                    // WhatsApp URL to send order confirmation
                    String strukMessage = Uri.encode(receiptText.toString());
                    String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + strukMessage;

                    // Open WhatsApp with the confirmation message
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(sendIntent);
                }
            }
        });
    }
}
