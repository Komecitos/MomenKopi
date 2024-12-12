package com.example.momen_kopi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private int totalPrice = 0; // Total harga produk yang ditambahkan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_page);

        // Referensi elemen dari layout
        Button btnAddKopi1 = findViewById(R.id.btnAddKopi1);
        Button btnAddKopi2 = findViewById(R.id.btnAddKopi2);
        Button btnAddMkn1 = findViewById(R.id.btnAddMkn1);
        Button btnAddMkn2 = findViewById(R.id.btnAddMkn2);
        Button btnProceed = findViewById(R.id.btnProceed);
        Button btnClosePopup = findViewById(R.id.btnClosePopup);
        TextView tvPopupPrice = findViewById(R.id.tvPopupPrice);
        LinearLayout popupPriceLayout = findViewById(R.id.popupPriceLayout);

        // Inisialisasi popup agar tersembunyi saat aplikasi diluncurkan
        popupPriceLayout.setVisibility(View.GONE);

        // Logika tombol tambah kopi 1
        btnAddKopi1.setOnClickListener(v -> {
            totalPrice += 15000; // Tambahkan harga kopi 1 ke total
            tvPopupPrice.setText("Total: Rp " + totalPrice); // Perbarui teks harga
            popupPriceLayout.setVisibility(View.VISIBLE); // Tampilkan popup
        });

        // Logika tombol tambah kopi 1
        btnAddMkn1.setOnClickListener(v -> {
            totalPrice += 18000; // Tambahkan harga kopi 1 ke total
            tvPopupPrice.setText("Total: Rp " + totalPrice); // Perbarui teks harga
            popupPriceLayout.setVisibility(View.VISIBLE); // Tampilkan popup
        });

        // Logika tombol tambah kopi 1
        btnAddMkn2.setOnClickListener(v -> {
            totalPrice += 20000; // Tambahkan harga kopi 1 ke total
            tvPopupPrice.setText("Total: Rp " + totalPrice); // Perbarui teks harga
            popupPriceLayout.setVisibility(View.VISIBLE); // Tampilkan popup
        });

        btnAddKopi2.setOnClickListener(v -> {
            totalPrice += 18000; // Tambahkan harga kopi 1 ke total
            tvPopupPrice.setText("Total: Rp " + totalPrice); // Perbarui teks harga
            popupPriceLayout.setVisibility(View.VISIBLE); // Tampilkan popup
        });

        // Logika tombol lanjutkan (ke aktivitas berikutnya)
        btnProceed.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, CartActivity.class); // Ubah ke aktivitas tujuan
            intent.putExtra("totalPrice", totalPrice); // Kirim total harga ke aktivitas berikutnya
            startActivity(intent);
        });

        // Logika tombol tutup popup
        btnClosePopup.setOnClickListener(v -> popupPriceLayout.setVisibility(View.GONE));
    }
}
