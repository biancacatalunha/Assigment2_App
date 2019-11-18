package sda.catalunhab.assignment2_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import sda.catalunhab.assignment2_app.types.Email;

public class MainActivity extends AppCompatActivity {

    static final int COMPOSE_EMAIL_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


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

            final Email email = data.getParcelableExtra(ComposeEmailActivity.EMAIL);

            textView.setText(Objects.requireNonNull(email).toString());

            if (!email.getTo().equals("") && !email.getContent().equals("")) {
                Button button = findViewById(R.id.button);
                button.setEnabled(true);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendEmail(email);
                    }
                });
            }
        }
    }

    private void sendEmail(Email email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getTo()});
        intent.putExtra(Intent.EXTRA_SUBJECT, email.getSubject());
        intent.putExtra(Intent.EXTRA_TEXT, email.getContent());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
