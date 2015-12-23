package com.amrit.recyclerviewgallery;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.OnItemClickListener {

    private static String TAG = MainActivity.class.getName();
    private RecyclerView myRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInfo = (TextView) findViewById(R.id.info);
        myRecyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, this);
        myRecyclerViewAdapter.setOnItemClickListener(this);

        textInfo.setMovementMethod(new ScrollingMovementMethod());
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(linearLayoutManager);

        prepareGallery();

    }

    private void prepareGallery() {
        String externalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        Log.i(TAG, ""+externalStorageDirectoryPath);
        //loadinh images from camera folder
        String targetPath = externalStorageDirectoryPath + "/DCIM/Camera/";//

        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
        File targetDirector = new File(targetPath);

        File[] files = targetDirector.listFiles();

        for (File file : files) {
            Uri uri = Uri.fromFile(file);
            myRecyclerViewAdapter.add(myRecyclerViewAdapter.getItemCount(),uri);
        }

    }


    @Override
    public void onItemClick(MyRecyclerViewAdapter.ItemHolder item, int position) {
        String stringitemUri = item.getItemUri();
        Toast.makeText(MainActivity.this, stringitemUri, Toast.LENGTH_SHORT).show();
    }
}
