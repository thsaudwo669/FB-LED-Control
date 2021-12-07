// RTDB : https://fir-led-test-default-rtdb.firebaseio.com/
// 스케치 코드 : Firebase_LED_Control.ico
package com.example.fd_led_control;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button onButton;
    Button offButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //찾아오기
        onButton = findViewById(R.id.btn01);
        offButton = findViewById(R.id.btn02);
        textView = findViewById(R.id.textView);
        setTitle(("LED Remote Control"));

        // 파이어베이스 연결
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("LED_STATUS");

        myRef.setValue("Hello, World!");
        textView.setText(myRef.getKey());

        // 데이터베이스 불러오기
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ledState = (String) dataSnapshot.getValue();
                textView.setText("LED is " + ledState);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setBackgroundColor(Color.RED);
                //데이터베이스 변경 "ON"
                myRef.setValue("ON");
            }
        });

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setBackgroundColor(Color.GRAY);
                //데이터베이쓰 변경 "OFF"
                myRef.setValue("OFF");
            }
        });
    }
}