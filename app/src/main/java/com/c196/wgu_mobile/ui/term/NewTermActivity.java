package com.c196.wgu_mobile.ui.term;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.c196.wgu_mobile.R;

public class NewTermActivity extends AppCompatActivity {

    public static final String ADD_TERM_TITLE = "com.c196.wgu_mobile.EXTRA_TERM_TITLE";
    public static final String ADD_TERM_START = "com.c196.wgu_mobile.EXTRA_TERM_START";
    public static final String ADD_TERM_END = "com.c196.wgu_mobile.EXTRA_TERM_END";


    private EditText mAddTermTitle;
    private EditText mAddTermStart;
    private EditText mAddTermEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_term);
        mAddTermTitle = findViewById(R.id.add_term_title);
        mAddTermStart = findViewById(R.id.add_term_start);
        mAddTermEnd = findViewById(R.id.add_term_end);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mAddTermTitle.getText())
                    || TextUtils.isEmpty(mAddTermStart.getText())
                    || TextUtils.isEmpty(mAddTermEnd.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                Bundle termDetails = new Bundle();
                termDetails.putString("title", mAddTermTitle.getText().toString());
                termDetails.putString("start", mAddTermStart.getText().toString());
                termDetails.putString("end", mAddTermEnd.getText().toString());
                replyIntent.putExtra(ADD_TERM_TITLE, termDetails.getString("title"));
                replyIntent.putExtra(ADD_TERM_START, termDetails.getString("start"));
                replyIntent.putExtra(ADD_TERM_END, termDetails.getString("end"));
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

    }

    public void onReturnClicked(View view) {
        finish();
    }
}
