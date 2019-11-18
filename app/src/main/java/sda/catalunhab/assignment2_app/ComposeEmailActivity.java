package sda.catalunhab.assignment2_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ComposeEmailActivity extends AppCompatActivity {

    static final String TO = "sda.catalunhab.assignment2_app.TO";
    static final String SUBJECT = "sda.catalunhab.assignment2_app.SUBJECT";
    static final String CONTENT = "sda.catalunhab.assignment2_app.CONTENT";

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

                result.putExtra(TO, et.getText().toString());
                result.putExtra(SUBJECT, et2.getText().toString());
                result.putExtra(CONTENT, et3.getText().toString());

                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }
}
