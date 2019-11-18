package sda.catalunhab.assignment2_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;
import sda.catalunhab.assignment2_app.types.Email;

/**
 * DCU - SDA - Assignment 2
 *
 * This app allows the user to:
 * 1. Open the camera
 * 2. Open the gallery
 * 3. Compose an email
 * 4. Send an email
 *
 * @author Bianca Catalunha
 * @since November 2019
 */

public class MainActivity extends AppCompatActivity {

    static final String TAG = "AppDebug";
    static final int COMPOSE_EMAIL_REQUEST = 1;

    /**
     * On create the user is presented with the main screen which displays:
     * - Implicit Intent: Open Camera
     * - Implicit Intent: View Picture
     * - Explicit Intent: Call an activity
     * - Empty text view
     * - Disabled send button
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "App started");
    }

    /**
     * Creates an implicit intent and opens the camera
     * @param view allows us to access the layout objects
     */
    public void capturePhoto(View view) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            Log.d(TAG, "Camera opened");
        }
    }

    /**
     * Creates an implicit intent and opens the gallery
     * @param view allows us to access the layout objects
     */
    public void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("images/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            Log.d(TAG, "Gallery opened");
        }
    }

    /**
     * Creates an explicit intent and opens the Compose Email Activity
     * @param view allows us to access the layout objects
     */
    public void composeEmail(View view) {
        Intent intent = new Intent(this, ComposeEmailActivity.class);
        startActivityForResult(intent, COMPOSE_EMAIL_REQUEST);
    }

    /**
     * When Compose Email Activity returns, this method is called.
     * First it checks if the resultCode is ok and the requestCode is referent to the intent we are interested in
     * It then attaches the result to the fourth textView
     * If the target email and content of the email are set, the send button is enabled
     * When the send button is clicked, openEmailIntent method is called
     *
     * @param requestCode helps us identify which intent came back
     * @param resultCode returns the status of the activity OK/CANCELED
     * @param data contains the data sent back from the second activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COMPOSE_EMAIL_REQUEST && resultCode == RESULT_OK) {
            Log.d(TAG, "Compose email activity returned result");

            TextView textView = findViewById(R.id.textView4);
            final Email email = data.getParcelableExtra(ComposeEmailActivity.EMAIL);
            textView.setText(Objects.requireNonNull(email).toString());

            if (!email.getTo().equals("") && !email.getContent().equals("")) {
                Button button = findViewById(R.id.button);
                button.setEnabled(true);

                Log.d(TAG, "To and content set. Enabling button");

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openEmailIntent(email);
                    }
                });
            } else {
                Log.d(TAG, "To and content not set. Button still disabled");
            }
        }
    }

    /**
     * Creates an intent to open the email
     * Attaches the info inputted by the user
     * Starts the activity
     *
     * @param email object contains to, subject and content entered by the user
     */
    private void openEmailIntent(Email email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getTo()});
        intent.putExtra(Intent.EXTRA_SUBJECT, email.getSubject());
        intent.putExtra(Intent.EXTRA_TEXT, email.getContent());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            Log.d(TAG, "Email opened");
        }
    }
}
