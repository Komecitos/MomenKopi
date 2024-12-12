package com.example.momen_kopi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class Pin extends AppCompatActivity {

    private EditText pinEditText;
    private Button submitButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin);


        pinEditText = findViewById(R.id.pin_edit_text);
        submitButton = findViewById(R.id.submit_button);
        resultTextView = findViewById(R.id.result_text_view);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = pinEditText.getText().toString();

                if (pin.isEmpty()) {

                    Toast.makeText(Pin.this, "PIN tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    resultTextView.setText("PIN yang dimasukkan: " + pin);
                }
            }
   });
}
}
