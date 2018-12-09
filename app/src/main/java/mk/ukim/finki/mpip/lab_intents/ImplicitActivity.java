package mk.ukim.finki.mpip.lab_intents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class ImplicitActivity extends AppCompatActivity {

    private TextView tvActivities;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
        initUI();
        showActivities();
    }

    public void initUI() {
        tvActivities = findViewById(R.id.tvShowActivities);
    }

    public void showActivities() {

        StringBuilder sb = new StringBuilder();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> info = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo infoElement : info) {
            sb.append(infoElement.activityInfo.packageName + "\n");
        }

        tvActivities.setText(sb.toString());

    }
}
