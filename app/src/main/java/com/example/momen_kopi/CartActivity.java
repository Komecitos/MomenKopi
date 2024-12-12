package com.example.momen_kopi;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ArrayList<CartItem> cartItems; // Daftar item dalam keranjang
    private CartAdapter cartAdapter; // Adapter untuk ListView
    private TextView tvTotalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        // Inisialisasi ListView dan TextView
        ListView lvCart = findViewById(R.id.lvCart);
        tvTotalHarga = findViewById(R.id.tvTotalHarga);
        Button btnCheckout = findViewById(R.id.btnCheckout);

        // Data dummy untuk item di keranjang
        cartItems = new ArrayList<>();
        cartItems.add(new CartItem("Kopi Arabica", 25000, 1));
        cartItems.add(new CartItem("Kopi Robusta", 20000, 2));

        // Adapter untuk menampilkan daftar item
        cartAdapter = new CartAdapter(this, cartItems);
        lvCart.setAdapter(cartAdapter);

        // Menghitung dan menampilkan total harga
        calculateTotalPrice();

        // Button checkout untuk melanjutkan ke halaman pembayaran
        btnCheckout.setOnClickListener(v -> {
            // Logika checkout bisa ditambahkan di sini
            // Bisa membuka activity pembayaran atau halaman konfirmasi
        });
    }

    // Fungsi untuk menghitung total harga
    private void calculateTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        tvTotalHarga.setText("Total: Rp " + total);
    }
}
