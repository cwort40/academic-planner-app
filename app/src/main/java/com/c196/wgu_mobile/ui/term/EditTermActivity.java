package com.c196.wgu_mobile.ui.term;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.c196.wgu_mobile.R;
import com.c196.wgu_mobile.entity.TermEntity;

public class EditTermActivity extends AppCompatActivity {

    public static final String EDIT_TERM_TITLE = "com.c196.wgu_mobile.EDIT_TERM_TITLE";
    public static final String EDIT_TERM_START = "com.c196.wgu_mobile.EDIT_TERM_START";
    public static final String EDIT_TERM_END = "com.c196.wgu_mobile.EDIT_TERM_END";
    public static final String TERM_ID = "com.c196.wgu_mobile.TERM_ID";

    private EditText mEditTermTitle;
    private EditText mEditTermStart;
    private EditText mEditTermEnd;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);

        TermEntity selectedTerm = (TermEntity)getIntent()
                .getSerializableExtra("selected_term");
        if (selectedTerm == null) {
            // Handle error: no term was passed in the intent
            System.out.println("no term passed");
        }

        assert selectedTerm != null;
        id = String.valueOf(selectedTerm.getId());

        mEditTermTitle = findViewById(R.id.add_term_title);
        mEditTermTitle.setText(selectedTerm.getTitle());

        mEditTermStart = findViewById(R.id.add_term_start);
        mEditTermStart.setText(selectedTerm.getStartDate());

        mEditTermEnd = findViewById(R.id.add_term_end);
        mEditTermEnd.setText(selectedTerm.getEndDate());

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditTermTitle.getText())
                    || TextUtils.isEmpty(mEditTermStart.getText())
                    || TextUtils.isEmpty(mEditTermEnd.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
//                TermEntity term = new TermEntity(mEditTermTitle.getText().toString(), mEditTermStart.getText().toString(), mEditTermEnd.getText().toString());
                Bundle termDetails = new Bundle();
                termDetails.putString("title", mEditTermTitle.getText().toString());
                termDetails.putString("start", mEditTermStart.getText().toString());
                termDetails.putString("end", mEditTermEnd.getText().toString());
                termDetails.putString("id", id);
                replyIntent.putExtra(EDIT_TERM_TITLE, termDetails.getString("title"));
                replyIntent.putExtra(EDIT_TERM_START, termDetails.getString("start"));
                replyIntent.putExtra(EDIT_TERM_END, termDetails.getString("end"));
                replyIntent.putExtra(TERM_ID, termDetails.getString("id"));
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }

    public void onReturnClicked(View view) {
        finish();
    }

}