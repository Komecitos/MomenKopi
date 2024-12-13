package com.example.momen_kopi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import androidx.activity.EdgeToEdge;

public class Home extends AppCompatActivity {

    private List<String> orderedItems = new ArrayList<>();
    private List<Integer> orderedQuantities = new ArrayList<>();
    private List<String> orderedTemperatures = new ArrayList<>();
    private List<Integer> orderedPrices = new ArrayList<>();

    private LinearLayout popupPriceLayout, popupCheckoutLayout;
    private TextView tvPopupItem, tvPopupPrice, tvPopupTemperature, tvCheckoutPrice;
    private Button btnCancel, btnAddToOrder, btnProceedToPayment, btnIncrease, btnDecrease;
    private ImageView imgPopupItem;

    private TextView tvNamaKopi1, tvNamaKopi2, tvNamaMkn1, tvNamaMkn2;
    private TextView tvHargaKopi1, tvHargaKopi2, tvHargaMkn1, tvHargaMkn2, etQuantity;

    private RadioGroup radioGroupSuhu;
    private RadioButton radioPanas, radioDingin;

    private int totalHarga = 0;
    private String selectedItem = "";
    private String selectedTemperature = "";
    private int itemCount = 0;


    private int totalPrice = 0; // Total harga produk yang ditambahkan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_page);

        // Inisialisasi Views
        popupPriceLayout = findViewById(R.id.popupPriceLayout);
        popupCheckoutLayout = findViewById(R.id.popupCheckoutLayout);
        tvPopupItem = findViewById(R.id.tvPopupItem);
        tvPopupPrice = findViewById(R.id.tvPopupPrice);
        tvPopupTemperature = findViewById(R.id.tvPopupTemperature);
        tvCheckoutPrice = findViewById(R.id.tvCheckoutPrice);
        btnCancel = findViewById(R.id.btnCancel);
        btnAddToOrder = findViewById(R.id.btnAddToOrder);
        btnProceedToPayment = findViewById(R.id.btnProceedToPayment);
        imgPopupItem = findViewById(R.id.imgPopupItem);

        tvNamaKopi1 = findViewById(R.id.tvNamaKopi1);
        tvNamaKopi2 = findViewById(R.id.tvNamaKopi2);
        tvNamaMkn1 = findViewById(R.id.tvNamaMkn1);
        tvNamaMkn2 = findViewById(R.id.tvNamaMkn2);

        tvHargaKopi1 = findViewById(R.id.tvHargaKopi1);
        tvHargaKopi2 = findViewById(R.id.tvHargaKopi2);
        tvHargaMkn1 = findViewById(R.id.tvHargaMkn1);
        tvHargaMkn2 = findViewById(R.id.tvHargaMkn2);

        // Inisialisasi RadioGroup dan RadioButton
        radioGroupSuhu = findViewById(R.id.radioGroupSuhu);
        radioPanas = findViewById(R.id.radioPanas);
        radioDingin = findViewById(R.id.radioDingin);

        // Referensi elemen dari layout
        Button btnAddKopi1 = findViewById(R.id.btnAddKopi1);
        Button btnAddKopi2 = findViewById(R.id.btnAddKopi2);
        Button btnAddMkn1 = findViewById(R.id.btnAddMkn1);
        Button btnAddMkn2 = findViewById(R.id.btnAddMkn2);


        // Inisialisasi RadioGroup dan RadioButton
        radioGroupSuhu = findViewById(R.id.radioGroupSuhu);
        radioPanas = findViewById(R.id.radioPanas);
        radioDingin = findViewById(R.id.radioDingin);

        btnDecrease = findViewById(R.id.btn_decrease);
        btnIncrease = findViewById(R.id.btn_increase);
        etQuantity = findViewById(R.id.etQuantity)    ;
        etQuantity.setText(String.valueOf(itemCount));

        // Increase button logic
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCount++; // Increment item count
                etQuantity.setText(String.valueOf(itemCount)); // Update EditText
                btnDecrease.setEnabled(itemCount > 0); // Enable "Decrease" button if count > 0
            }
        });

        // Decrease button logic
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemCount > 0) {
                    itemCount--; // Decrement item count
                    etQuantity.setText(String.valueOf(itemCount)); // Update EditText
                    btnDecrease.setEnabled(itemCount > 0); // Disable "Decrease" button if count == 0
                }
            }
        });

        // Initially disable "Decrease" button
        btnDecrease.setEnabled(false);

        // Menambahkan Event Listener untuk Tombol "Add" Kopi 1
        btnAddKopi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menampilkan Popup untuk Kopi Robusta
                String kopiName = tvNamaKopi1.getText().toString();
                String kopiPriceString = tvHargaKopi1.getText().toString();
                final int kopiPrice = Integer.parseInt(kopiPriceString.replaceAll("[^\\d]", ""));
                etQuantity.setText("0");

                // Menampilkan Popup
                tvPopupItem.setText("Item: " + kopiName);
                tvPopupPrice.setText("Harga: Rp " + kopiPrice);
                imgPopupItem.setImageResource(R.drawable.latte);  // Menampilkan gambar produk
                tvPopupTemperature.setText("Suhu: -");

                // Menampilkan Popup pertama
                popupPriceLayout.setVisibility(View.VISIBLE);

                // Update total harga
                totalHarga += kopiPrice;
                selectedItem = kopiName;
            }
        });

            // Menambahkan Event Listener untuk Tombol "Add" Kopi 2
            btnAddKopi2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Menampilkan Popup untuk Kopi Robusta
                    String kopiName = tvNamaKopi2.getText().toString();
                    String kopiPriceString = tvHargaKopi2.getText().toString();
                    final int kopiPrice = Integer.parseInt(kopiPriceString.replaceAll("[^\\d]", ""));

                    // Menampilkan Popup
                    tvPopupItem.setText("Item: " + kopiName);
                    tvPopupPrice.setText("Harga: Rp " + kopiPrice);
                    imgPopupItem.setImageResource(R.drawable.cappucinno);  // Menampilkan gambar produk
                    tvPopupTemperature.setText("Suhu: -");

                    // Menampilkan Popup pertama
                    popupPriceLayout.setVisibility(View.VISIBLE);

                    // Update total harga
                    totalHarga += kopiPrice;
                    selectedItem = kopiName;
                }
            });

            // Menambahkan Event Listener untuk Tombol Cancel di Popup
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Menyembunyikan Popup
                    popupPriceLayout.setVisibility(View.GONE);
                }
            });

        // Menambahkan Event Listener untuk Tombol "Add" Makanan 2
        btnAddMkn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kopiName = tvNamaMkn1.getText().toString();
                String kopiPriceString = tvHargaMkn1.getText().toString();
                final int kopiPrice = Integer.parseInt(kopiPriceString.replaceAll("[^\\d]", ""));

                tvPopupItem.setText("Item: " + kopiName);
                tvPopupPrice.setText("Harga: Rp " + kopiPrice);
                imgPopupItem.setImageResource(R.drawable.taco);  // Menampilkan gambar produk

                popupPriceLayout.setVisibility(View.VISIBLE);

                // Update total harga
                totalHarga += kopiPrice;
                selectedItem = kopiName;
            }
        });

        btnAddMkn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kopiName = tvNamaMkn2.getText().toString();
                String kopiPriceString = tvHargaMkn2.getText().toString();
                final int kopiPrice = Integer.parseInt(kopiPriceString.replaceAll("[^\\d]", ""));

                tvPopupItem.setText("Item: " + kopiName);
                tvPopupPrice.setText("Harga: Rp " + kopiPrice);
                imgPopupItem.setImageResource(R.drawable.brownies);  // Menampilkan gambar produk

                popupPriceLayout.setVisibility(View.VISIBLE);

                // Update total harga
                totalHarga += kopiPrice;
                selectedItem = kopiName;
            }
        });

        // Menambahkan Event Listener untuk Tombol Cancel di Popup
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menyembunyikan Popup
                popupPriceLayout.setVisibility(View.GONE);
            }
        });

            // Menambahkan Event Listener untuk Tombol Add to Order di Popup
        btnAddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menangani pilihan suhu dan jumlah yang diinginkan
                String suhu = "";
                if (radioPanas.isChecked()) {
                    suhu = "Panas";
                } else if (radioDingin.isChecked()) {
                    suhu = "Dingin";
                }

                int quantity = Integer.parseInt(etQuantity.getText().toString()); // Mengambil jumlah produk yang dipilih

                if (!suhu.isEmpty() && quantity > 0) {
                    // Update total harga berdasarkan jumlah yang dipilih
                    int kopiPrice = Integer.parseInt(tvPopupPrice.getText().toString().replaceAll("[^\\d]", ""));
                    totalHarga += (quantity - 1) * kopiPrice; // Menghitung total harga

                    // Menambah item yang dipilih ke dalam daftar pesanan
                    orderedItems.add(selectedItem);
                    orderedTemperatures.add(suhu);
                    orderedQuantities.add(quantity);
                    orderedPrices.add(kopiPrice * quantity);

                    // Menampilkan pop-up checkout jika suhu dipilih
                    String harga = "Total Harga: Rp " + totalHarga;
                    tvCheckoutPrice.setText(harga);
                    popupCheckoutLayout.setVisibility(View.VISIBLE);
                    popupPriceLayout.setVisibility(View.GONE);
                }
            }
        });

            // Menambahkan Event Listener untuk Tombol Lanjut ke Pembayaran
            btnProceedToPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Mengarahkan ke halaman Detail Pemesanan
                    Intent intent = new Intent(Home.this, DetailPemesananActivity.class);

                    // Kirim data ke DetailPemesananActivity
                    intent.putStringArrayListExtra("orderedItems", new ArrayList<>(orderedItems));
                    intent.putStringArrayListExtra("orderedTemperatures", new ArrayList<>(orderedTemperatures));
                    intent.putIntegerArrayListExtra("orderedQuantities", new ArrayList<>(orderedQuantities));
                    intent.putIntegerArrayListExtra("orderedPrices", new ArrayList<>(orderedPrices));
                    intent.putExtra("totalPrice", totalHarga);

                    startActivity(intent);

                    // Menyembunyikan Popup Checkout setelah lanjut ke Pembayaran
                    popupCheckoutLayout.setVisibility(View.GONE);
                }
            });


        }
    }

