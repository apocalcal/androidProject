package com.example.androidproject1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidproject1.R;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private TextView loginSuggest;
    private Button todayQuestBtn, selfCheckBtn, myTodayBtn, cognitveBtn, loginBtn, joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 로그인 상태 확인
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        String username = sharedPreferences.getString("username", "");  // 저장된 아이디 가져오기
        setContentView(R.layout.activity_main);
        // 레이아웃 요소 참조
        todayQuestBtn = findViewById(R.id.todayQuestBtn);
        selfCheckBtn = findViewById(R.id.selfCheckBtn);
        myTodayBtn = findViewById(R.id.myTodayBtn);
        cognitveBtn = findViewById(R.id.cognitveBtn);
        cognitveBtn = findViewById(R.id.cognitveBtn);
        loginBtn = findViewById(R.id.loginBtn);
        joinBtn = findViewById(R.id.joinBtn);
        loginSuggest = findViewById(R.id.loginSuggest);

        if (isLoggedIn) {
            loginBtn.setText("로그아웃");
            loginSuggest.setText("환영합니다, " + username + "님!");
            joinBtn.setVisibility(View.GONE);
        } else {
            loginBtn.setText("로그인");
            loginSuggest.setText("");
        }
        todayQuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLoggedIn) {
                    Toast.makeText(MainActivity.this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, TodayActivity.class);
                    startActivity(intent);
                }
            }
        });

        selfCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLoggedIn) {
                    Toast.makeText(MainActivity.this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, SelfcheckActivity.class);
                    startActivity(intent);
                }
            }
        });

        myTodayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLoggedIn) {
                    Snackbar.make(findViewById(android.R.id.content), "로그인이 필요합니다.", Snackbar.LENGTH_LONG)
                            .setAction("로그인하기", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            }).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, MyActivity.class);
                    startActivity(intent);
                }
            }
        });

        cognitveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLoggedIn) {
                    Toast.makeText(MainActivity.this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, CognitiveActivity.class);
                    startActivity(intent);
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedIn) {
                    logout();
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
    }
    private void logout() {
        // 로그인 상태 해제
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.putString("username", ""); // 사용자 아이디 초기화
        editor.apply();

        // 버튼을 다시 "로그인"으로 변경
        loginBtn.setText("로그인");
        loginSuggest.setText("");

        Toast.makeText(MainActivity.this, "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
        // 로그아웃 처리 후 로그인 화면으로 이동할 경우
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
