package mk.ukim.finki.mpip.lab_intents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.mpip.lab_intents.adapters.TextViewAdapter;

public class RecyclerImplicitActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextViewAdapter textViewAdapter;

    private List<String> data;

    public RecyclerImplicitActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_implicit);
        initUI();
    }

    public void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        data = getData();
        textViewAdapter = new TextViewAdapter(RecyclerImplicitActivity.this, data);
        recyclerView.setAdapter(textViewAdapter);
    }

    public  List<String> getData() {

        List<String> list = new ArrayList<>();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> info = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo infoElement : info) {
            list.add(infoElement.activityInfo.packageName);
        }
        return list;
    }
}
