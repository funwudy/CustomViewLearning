package com.example.fun.mytopbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopBar topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(MainActivity.this, "left clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(MainActivity.this, "right clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
