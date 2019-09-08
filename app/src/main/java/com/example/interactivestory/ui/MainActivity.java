package com.example.interactivestory.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.interactivestory.R;

public class MainActivity extends AppCompatActivity {
    private TextView nameField;
    private Button startButton;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameField=findViewById(R.id.nameEditText);
        startButton=findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= nameField.getText().toString();
                //Toast.makeText(MainActivity.this , "Hi "+name , Toast.LENGTH_LONG).show();
                startStory();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        nameField.setText("");
    }

    private void startStory() {
        Intent intent =new Intent(this, StoryActivity.class);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
