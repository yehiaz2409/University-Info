package com.example.universityinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {
    private String webPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView nameTextView = findViewById(R.id.university_name);
        TextView domainTextView = findViewById(R.id.university_domain);

        Intent intent = getIntent();
        String name = intent.getStringExtra("university");
        String domain = intent.getStringExtra("domain");
        webPage = intent.getStringExtra("webPage");

        nameTextView.setText(name);
        domainTextView.setText(domain);

        findViewById(R.id.button_ok).setOnClickListener(v -> finish());
        findViewById(R.id.button_visit).setOnClickListener(this::visitWebsite);
    }

    public void visitWebsite(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webPage));
        startActivity(browserIntent);
    }
}