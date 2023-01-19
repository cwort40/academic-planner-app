package com.c196.wgu_mobile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.c196.wgu_mobile.R;
import com.c196.wgu_mobile.database.AppDatabase;
import com.c196.wgu_mobile.database.TermDao;
import com.c196.wgu_mobile.viewmodel.TermViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
    }
}