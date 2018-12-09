package mk.ukim.finki.mpip.lab_intents;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitActivity extends AppCompatActivity {

    private Button btnOK, btnCancel;
    private EditText etResult;

    private View.OnClickListener btnOkListener = v -> returnResult();

    private View.OnClickListener btnCancelListener = v -> cancelResult();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);
        initUI();
        bindEvents();
    }

    public void initUI() {
        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);
        etResult = findViewById(R.id.etResultText);
    }

    public void bindEvents() {
        btnOK.setOnClickListener(btnOkListener);
        btnCancel.setOnClickListener(btnCancelListener);
    }

    public void returnResult() {
        Intent resultIntent = new Intent();
        String text = etResult.getText().toString();
        resultIntent.putExtra(MainActivity.EXTRA_RESULT, text);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    public void cancelResult() {
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, resultIntent);
        finish();
    }
}
