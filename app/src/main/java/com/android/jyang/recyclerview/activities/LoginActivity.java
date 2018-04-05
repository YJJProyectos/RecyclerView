package com.android.jyang.recyclerview.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jyang.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.mail)
    TextView mailView;
    @BindView(R.id.password)
    TextView pwdView;
    @BindView(R.id.guardar)
    Switch guardarView;
    @BindView(R.id.login)
    Button login;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        prefs = getSharedPreferences("Datos", Context.MODE_PRIVATE);
        ButterKnife.bind(this);
        mailView.setText(prefs.getString("mail", ""));
        pwdView.setText(prefs.getString("pwd",""));
    }

    public void login(View view) {
        String mail = mailView.getText().toString();
        String password = pwdView.getText().toString();
        if (verificar(mail, password)) {
            irAMain();
            guardarDatos(mail, password);
        }
    }

    private boolean verificar(String mail, String pwd) {
        if (!mailValido(mail)) {
            Toast.makeText(this, "Mail no valido", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!pwdValido(pwd)) {
            Toast.makeText(this, "Contraseña no valida, al menos 4 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

    public void guardarDatos(String mail, String pwd) {
        if (guardarView.isChecked()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("mail", mail);
            editor.putString("pwd", pwd);
//            editor.commit();
            editor.apply();
        }
    }

    private boolean mailValido(String mail) {
        return !TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    private boolean pwdValido(String password) {
        return password.length() >= 4;
    }

    private void irAMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
