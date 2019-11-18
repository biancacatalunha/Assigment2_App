package sda.catalunhab.assignment2_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import sda.catalunhab.assignment2_app.types.Email;

/**
 * DCU - SDA - Assignment 2
 *
 * Activity that allows the user to compose an email
 *
 * @author Bianca Catalunha
 * @since November 2019
 */

public class ComposeEmailActivity extends AppCompatActivity {

    static final String EMAIL = "sda.catalunhab.assignment2_app.EMAIL";

    /**
     * On create opens a page where the user can input:
     * - To
     * - Subject
     * - Content
     *
     * When the save button is clicked, an intent is created, the email object is populated
     * with the user input, the email object is attached to the intent, result code is set to OK and
     * this activity is finished returning the user input to the main activity
     *
     * @param savedInstanceState A mapping from String keys to various parcelable values
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_email);

        Log.d(MainActivity.TAG, "Compose email activity called");

        Button button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                EditText to = findViewById(R.id.editText1);
                EditText subject = findViewById(R.id.editText2);
                EditText content = findViewById(R.id.editText3);

                Email email = new Email(to.getText().toString(), subject.getText().toString(), content.getText().toString());

                result.putExtra(EMAIL, email);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }
}
