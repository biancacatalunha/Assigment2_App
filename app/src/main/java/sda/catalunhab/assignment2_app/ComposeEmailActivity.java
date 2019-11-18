package sda.catalunhab.assignment2_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sda.catalunhab.assignment2_app.types.Email;

public class ComposeEmailActivity extends AppCompatActivity {

    static final String EMAIL = "sda.catalunhab.assignment2_app.EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_email);

        Button textView=findViewById(R.id.button2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                EditText et = findViewById(R.id.editText1);
                EditText et2 = findViewById(R.id.editText2);
                EditText et3 = findViewById(R.id.editText3);

                Email email = new Email(et.getText().toString(), et2.getText().toString(), et3.getText().toString());

                result.putExtra(EMAIL, email);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }
}
