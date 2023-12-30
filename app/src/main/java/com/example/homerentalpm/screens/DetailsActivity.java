package com.example.homerentalpm.screens;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.example.homerentalpm.R;


public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView price, description, shortDescription;
    String Sprice, Sdescription, SshortDescription, Simage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.imageView);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        shortDescription = findViewById(R.id.short_description);

        Sprice = getIntent().getStringExtra("price");
        Sdescription = getIntent().getStringExtra("description");
        SshortDescription = getIntent().getStringExtra("shortDescription");
        Simage = getIntent().getStringExtra("image");

        price.setText("Rp." + Sprice);
        description.setText(Sdescription);
        shortDescription.setText(SshortDescription);

        Glide.with(this)
                .load(Simage)
                .centerCrop()
                .placeholder(R.drawable.ic_account_circle)
                .into(imageView);


    }
}