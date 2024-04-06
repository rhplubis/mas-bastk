package com.tunasrent.auctionapps;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {
    Toolbar toolbar;

    EditText etPasswordold;
    EditText etNewpassword;
    EditText etRepassword;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change Password");
        toolbar.setTitleTextColor(Color.WHITE);

        etPasswordold = findViewById(R.id.et_passwordold);
        etNewpassword = findViewById(R.id.et_newpassword);
        etRepassword = findViewById(R.id.et_repassword);
        btnUpdate = findViewById(R.id.btn_update);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPasswordold.getText().toString().equals("")){
                    etPasswordold.setError("Please enter old password");
                }else if (etNewpassword.getText().toString().equals("")){
                    etNewpassword.setError("Please enter new password");
                }else if (etRepassword.getText().toString().equals("")){
                    etRepassword.setError("Please enter re-password");
                }else if (!etNewpassword.getText().toString().equals(etRepassword.getText().toString())){
                    Toast.makeText(ChangePasswordActivity.this,"New password and Re-password not match",Toast.LENGTH_SHORT).show();
                }else {
                    //update
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
