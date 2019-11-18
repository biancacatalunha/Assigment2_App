package sda.catalunhab.assignment2_app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import sda.catalunhab.assignment2_app.types.Email;

public class MainActivity extends AppCompatActivity {

    static final int COMPOSE_EMAIL_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    Reference: https://developer.android.com/guide/components/intents-common.html#java
     */
    public void capturePhoto(View view) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("images/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void composeEmail(View view) {
        Intent intent = new Intent(this, ComposeEmailActivity.class);
        startActivityForResult(intent, COMPOSE_EMAIL_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COMPOSE_EMAIL_REQUEST && resultCode == RESULT_OK) {

            TextView textView = findViewById(R.id.textView4);

            Email email = new Email();

            email.setTo(data.getStringExtra(ComposeEmailActivity.TO));
            email.setSubject(data.getStringExtra(ComposeEmailActivity.SUBJECT));
            email.setContent(data.getStringExtra(ComposeEmailActivity.CONTENT));

            textView.setText(email.toString());

            if (email.getTo() != null && email.getContent() != null) {
                Button button = findViewById(R.id.button);
                button.setEnabled(true);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }

    private void sendEmail() {

    }
}
