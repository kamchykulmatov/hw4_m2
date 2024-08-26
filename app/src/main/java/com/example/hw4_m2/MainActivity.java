 package com.example.hw4_m2;

 import android.annotation.SuppressLint;
 import android.graphics.drawable.Drawable;
 import android.os.Bundle;
 import android.text.Editable;
 import android.text.TextWatcher;
 import android.view.View;
 import android.view.WindowManager;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.activity.EdgeToEdge;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;
 import androidx.core.graphics.Insets;
 import androidx.core.view.ViewCompat;
 import androidx.core.view.WindowInsetsCompat;

 public class MainActivity extends AppCompatActivity {

     private Button btnGray;
     private Button btnOrange;
     private EditText etEmail;
     private EditText etPassword;
     private View constraint;
     private TextView textView;

     private Drawable orangeBackground;
     private Drawable grayBackground;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         EdgeToEdge.enable(this);
         setContentView(R.layout.activity_main);
         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
             Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
             return insets;
         });

         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

         btnGray = findViewById(R.id.btn_log_in);
         etEmail = findViewById(R.id.et_email);
         etPassword = findViewById(R.id.et_password);
         constraint = findViewById(R.id.constraint);
         textView = findViewById(R.id.tv_welcome2);
         orangeBackground = ContextCompat.getDrawable(this, R.drawable.btn_orange);
         grayBackground = ContextCompat.getDrawable(this, R.drawable.btn_gray);

         updateButtonState();

         etEmail.addTextChangedListener(textWatcher);
         etPassword.addTextChangedListener(textWatcher);

         btnGray.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String email = etEmail.getText().toString().trim();
                 String password = etPassword.getText().toString().trim();

                 if (email.equals("admin") && password.equals("admin")) {
                     Toast.makeText(MainActivity.this, "Вы успешно зарегистрировались" , Toast.LENGTH_SHORT).show();
                     showWelcomeScreen();
                 } else {
                     Toast.makeText(MainActivity.this, "Неправильный логин и пароль", Toast.LENGTH_SHORT).show();
                 }
             }
         });
     }

     private final TextWatcher textWatcher = new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
         }

         @Override
         public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             updateButtonState();
         }

         @Override
         public void afterTextChanged(Editable editable) {
         }
     };

     private void updateButtonState() {
         String email = etEmail.getText().toString().trim();
         String password = etPassword.getText().toString().trim();

         if (!email.isEmpty() && !password.isEmpty()) {
             btnGray.setBackground(orangeBackground);
             btnGray.setEnabled(true);
         } else {
             btnGray.setBackground(grayBackground);
             btnGray.setEnabled(false);
         }
     }

     private void showWelcomeScreen() {
         etEmail.setVisibility(View.GONE);
         etPassword.setVisibility(View.GONE);
         btnGray.setVisibility(View.GONE);
         constraint.setVisibility(View.GONE);
         textView.setVisibility(View.VISIBLE);
     }
 }