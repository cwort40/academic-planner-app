package com.c196.wgu_mobile.ui.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.c196.wgu_mobile.R;

public class NewCourseActivity extends AppCompatActivity {

    public static final String ADD_COURSE_TITLE = "com.c196.wgu_mobile.EXTRA_COURSE_TITLE";
    public static final String ADD_COURSE_START = "com.c196.wgu_mobile.EXTRA_COURSE_START";
    public static final String ADD_COURSE_END = "com.c196.wgu_mobile.EXTRA_COURSE_END";
    public static final String ADD_COURSE_STATUS = "com.c196.wgu_mobile.EXTRA_COURSE_STATUS";
    public static final String ADD_COURSE_INS_NAME = "com.c196.wgu_mobile.EXTRA_COURSE_INS_NAME";
    public static final String ADD_COURSE_INS_PHONE = "com.c196.wgu_mobile.EXTRA_COURSE_INS_PHONE";
    public static final String ADD_COURSE_INS_EMAIL = "com.c196.wgu_mobile.EXTRA_COURSE_INS_EMAIL";
    public static final String ADD_COURSE_NOTE = "com.c196.wgu_mobile.EXTRA_COURSE_NOTE";


    private EditText mAddCourseTitle;
    private EditText mAddCourseStart;
    private EditText mAddCourseEnd;
    private EditText mAddCourseStatus;
    private EditText mAddCourseInsName;
    private EditText mAddCourseInsPhone;
    private EditText mAddCourseInsEmail;
    private EditText mAddCourseNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        mAddCourseTitle = findViewById(R.id.add_course_title);
        mAddCourseStart = findViewById(R.id.add_course_start);
        mAddCourseEnd = findViewById(R.id.add_course_end);
        mAddCourseStatus = findViewById(R.id.add_course_status);
        mAddCourseInsName = findViewById(R.id.add_course_ins_name);
        mAddCourseInsPhone = findViewById(R.id.add_course_ins_phone);
        mAddCourseInsEmail = findViewById(R.id.add_course_ins_email);
        mAddCourseNote = findViewById(R.id.add_course_note);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mAddCourseTitle.getText())
                    || TextUtils.isEmpty(mAddCourseStart.getText())
                    || TextUtils.isEmpty(mAddCourseEnd.getText())
                    || TextUtils.isEmpty(mAddCourseStatus.getText())
                    || TextUtils.isEmpty(mAddCourseInsName.getText())
                    || TextUtils.isEmpty(mAddCourseInsPhone.getText())
                    || TextUtils.isEmpty(mAddCourseInsEmail.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                Bundle courseDetails = new Bundle();
                courseDetails.putString("title", mAddCourseTitle.getText().toString());
                courseDetails.putString("start", mAddCourseStart.getText().toString());
                courseDetails.putString("end", mAddCourseEnd.getText().toString());
                courseDetails.putString("status", mAddCourseStatus.getText().toString());
                courseDetails.putString("name", mAddCourseInsName.getText().toString());
                courseDetails.putString("phone", mAddCourseInsPhone.getText().toString());
                courseDetails.putString("email", mAddCourseInsEmail.getText().toString());

                replyIntent.putExtra(ADD_COURSE_TITLE, courseDetails.getString("title"));
                replyIntent.putExtra(ADD_COURSE_START, courseDetails.getString("start"));
                replyIntent.putExtra(ADD_COURSE_END, courseDetails.getString("end"));
                replyIntent.putExtra(ADD_COURSE_STATUS, courseDetails.getString("status"));
                replyIntent.putExtra(ADD_COURSE_INS_NAME, courseDetails.getString("name"));
                replyIntent.putExtra(ADD_COURSE_INS_PHONE, courseDetails.getString("phone"));
                replyIntent.putExtra(ADD_COURSE_INS_EMAIL, courseDetails.getString("email"));

                if ((mAddCourseNote.getText()) != null) {
                    courseDetails.putString("note", mAddCourseNote.getText().toString());
                    replyIntent.putExtra(ADD_COURSE_NOTE, courseDetails.getString("note"));
                }

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }

    public void onReturnClicked(View view) {
        finish();
    }

}