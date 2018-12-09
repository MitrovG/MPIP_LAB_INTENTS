package mk.ukim.finki.mpip.lab_intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    public static final int EXPLICIT_INTENT_REQUEST_CODE = 1;
    public static final int SELECT_IMAGE_REQUEST_CODE = 2;
    public static final String EXTRA_RESULT = "mk.ukim.finki.mpip.lab_intents.RESULT";
    public static final String ACTION_IMPLICIT = "mk.ukim.finki.mpip.IMPLICIT_ACTION";

    private Button btnStartExplicit, btnStartImplicit, btnShareData, btnSelectImage;
    private TextView tvResult;

    Logger logger;

//    private View.OnClickListener btnStartExplicitListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            callExplicitActivity();
//        }
//    };

    private View.OnClickListener btnStartExplicitListener = (v) -> callExplicitActivity();
    private View.OnClickListener btnShareDataListener = (v) -> shareData();
    private View.OnClickListener btnStartImplicitListener = (v) -> callImplicitActivity();
    private View.OnClickListener btnSelectImageListener = (v) -> selectImage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        bindEvents();
        logger = Logger.getLogger(this.getClass().getName());
    }

    public void initUI() {
        btnStartExplicit = findViewById(R.id.btnStartExplicit);
        btnStartImplicit = findViewById(R.id.btnStartImplicit);
        btnShareData = findViewById(R.id.btnShareData);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        tvResult = findViewById(R.id.tvResult);
    }

    public void bindEvents() {
        btnStartExplicit.setOnClickListener(btnStartExplicitListener);
        btnShareData.setOnClickListener(btnShareDataListener);
        btnStartImplicit.setOnClickListener(btnStartImplicitListener);
        btnSelectImage.setOnClickListener(btnSelectImageListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EXPLICIT_INTENT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                tvResult.setText(data.getStringExtra(EXTRA_RESULT));
            }
            else {
                tvResult.setText("CANCELED");
            }
        }
        else if (requestCode == SELECT_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Uri pictureURI = data.getData();
            Log.i("PictureURI:", pictureURI.toString());
            Intent showIntent = new Intent(Intent.ACTION_VIEW);
            showIntent.setDataAndType(pictureURI, "image/*");
            if (showIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(showIntent);
            }
        }
    }

    public void callExplicitActivity() {
        Intent explicitIntent = new Intent(MainActivity.this, ExplicitActivity.class);
        startActivityForResult(explicitIntent, EXPLICIT_INTENT_REQUEST_CODE);
    }

    public void shareData() {
        Intent shareDataIntent = new Intent();
        shareDataIntent.setAction(Intent.ACTION_SEND);
        shareDataIntent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title");
        shareDataIntent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity");
        shareDataIntent.setType("text/plain");
//        logger.log(Level.INFO, "Vnatre sum vo share data");
        if (shareDataIntent.resolveActivity(getPackageManager()) != null) {
//            logger.log(Level.INFO, "Vnatre po resolve");
//            logger.log(Level.INFO, shareDataIntent.resolveActivity(getPackageManager()).getClassName());
            startActivity(Intent.createChooser(shareDataIntent, "Share data using:"));
        }
    }

    public void callImplicitActivity() {
        Intent implicitIntent = new Intent();
        implicitIntent.setAction(ACTION_IMPLICIT);
        if (implicitIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(implicitIntent);
        }
    }

    public void selectImage() {
        Intent selectIntent = new Intent();
        selectIntent.setAction(Intent.ACTION_PICK);
        selectIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(selectIntent, "Select image with:"),
                SELECT_IMAGE_REQUEST_CODE);
    }

}
