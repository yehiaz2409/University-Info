package com.example.universityinfo;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class University extends AppCompatActivity {
    private ListView listView;
    private UniversityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);

        listView = findViewById(R.id.university_list);
        String country = getIntent().getStringExtra("country");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        fetchUniversities(country);
    }

    private void fetchUniversities(String country) {
        String apiUrl = "http://universities.hipolabs.com/search?country=" + country;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                parseAndDisplayUniversities(response.toString());
            } else {
                runOnUiThread(() -> Toast.makeText(University.this, "Error: Unable to fetch data from the API", Toast.LENGTH_SHORT).show());
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(University.this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    private void parseAndDisplayUniversities(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            List<Uni> universities = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Uni university = new Uni();
                university.setName(jsonObject.getString("name"));
                university.setWebPages(jsonObject.getJSONArray("web_pages").getString(0));
                university.setDomain(jsonObject.getJSONArray("domains").getString(0));
                universities.add(university);
            }

            runOnUiThread(() -> {
                adapter = new UniversityAdapter(University.this, universities);
                listView.setAdapter(adapter);
            });
        } catch (JSONException e) {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(University.this, "Failed to parse response", Toast.LENGTH_SHORT).show());
        }
    }
}