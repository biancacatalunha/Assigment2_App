package sda.catalunhab.assignment2_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_GET = 1;

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
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            Uri fullPhotoUri = data.getData();

        }
    }
}
