package com.example.momen_kopi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CartItem> cartItems;

    public CartAdapter(Context context, ArrayList<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }

        CartItem cartItem = cartItems.get(position);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvQuantity = convertView.findViewById(R.id.tvQuantity);
        TextView tvTotalPrice = convertView.findViewById(R.id.tvTotalPrice);

        tvName.setText(cartItem.getName());
        tvPrice.setText("Rp " + cartItem.getPrice());
        tvQuantity.setText("Qty: " + cartItem.getQuantity());
        tvTotalPrice.setText("Total: Rp " + (cartItem.getPrice() * cartItem.getQuantity()));

        return convertView;
    }
}
