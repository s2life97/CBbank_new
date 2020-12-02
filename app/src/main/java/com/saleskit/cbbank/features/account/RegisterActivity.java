package com.saleskit.cbbank.features.account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saleskit.cbbank.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.bt_ok)
    Button btOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etPass.getText().toString();
                int a = 0;
                int b = lastIndexOfUCL(text);
                if(b != -1 ){
                    Toast.makeText(RegisterActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                }
                if(!etPass.getText().toString().equals(etUserName.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Sai!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int lastIndexOfUCL(String str) {
        for(int i=str.length()-1; i>=0; i--) {
            if(Character.isUpperCase(str.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}
