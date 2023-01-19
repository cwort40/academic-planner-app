package com.c196.wgu_mobile.ui.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.c196.wgu_mobile.R;
import com.c196.wgu_mobile.entity.CourseEntity;
import com.c196.wgu_mobile.entity.TermEntity;

public class EditCourseActivity extends AppCompatActivity {

    public static final String EDIT_COURSE_TITLE = "com.c196.wgu_mobile.EDIT_COURSE_TITLE";
    public static final String EDIT_COURSE_START = "com.c196.wgu_mobile.EDIT_COURSE_START";
    public static final String EDIT_COURSE_END = "com.c196.wgu_mobile.EDIT_COURSE_END";
    public static final String EDIT_COURSE_STATUS = "com.c196.wgu_mobile.EDIT_COURSE_STATUS";
    public static final String EDIT_COURSE_INS_NAME = "com.c196.wgu_mobile.EDIT_COURSE_INS_NAME";
    public static final String EDIT_COURSE_INS_PHONE = "com.c196.wgu_mobile.EDIT_COURSE_INS_PHONE";
    public static final String EDIT_COURSE_INS_EMAIL = "com.c196.wgu_mobile.EDIT_COURSE_INS_EMAIL";
    public static final String EDIT_COURSE_NOTE = "com.c196.wgu_mobile.EDIT_COURSE_NOTE";
    public static final String COURSE_ID = "com.c196.wgu_mobile.COURSE_ID";

    private EditText mEditCourseTitle;
    private EditText mEditCourseStart;
    private EditText mEditCourseEnd;
    private EditText mEditCourseStatus;
    private EditText mEditCourseInsName;
    private EditText mEditCourseInsPhone;
    private EditText mEditCourseInsEmail;
    private EditText mEditCourseNote;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        CourseEntity selectedCourse = (CourseEntity) getIntent()
                .getSerializableExtra("selected_course");
        if (selectedCourse == null) {
            // Handle error: no term was passed in the intent
            System.out.println("no course passed");
        }

        assert selectedCourse != null;
        id = String.valueOf(selectedCourse.getId());

        mEditCourseTitle = findViewById(R.id.add_course_title);
        mEditCourseTitle.setText(selectedCourse.getTitle());

        mEditCourseStart = findViewById(R.id.add_course_start);
        mEditCourseStart.setText(selectedCourse.getStartDate());

        mEditCourseEnd = findViewById(R.id.add_course_end);
        mEditCourseEnd.setText(selectedCourse.getEndDate());

        mEditCourseStatus = findViewById(R.id.add_course_status);
        mEditCourseStatus.setText(selectedCourse.getStatus());

        mEditCourseInsName = findViewById(R.id.add_course_ins_name);
        mEditCourseInsName.setText(selectedCourse.getInstructorName());

        mEditCourseInsPhone = findViewById(R.id.add_course_ins_phone);
        mEditCourseInsPhone.setText(selectedCourse.getInstructorPhone());

        mEditCourseInsEmail = findViewById(R.id.add_course_ins_email);
        mEditCourseInsEmail.setText(selectedCourse.getInstructorEmail());

        mEditCourseNote = findViewById(R.id.add_course_note);
        mEditCourseNote.setText(selectedCourse.getNote());

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditCourseTitle.getText())
                    || TextUtils.isEmpty(mEditCourseStart.getText())
                    || TextUtils.isEmpty(mEditCourseEnd.getText())
                    || TextUtils.isEmpty(mEditCourseStatus.getText())
                    || TextUtils.isEmpty(mEditCourseInsName.getText())
                    || TextUtils.isEmpty(mEditCourseInsPhone.getText())
                    || TextUtils.isEmpty(mEditCourseInsEmail.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                Bundle courseDetails = new Bundle();
                courseDetails.putString("title", mEditCourseTitle.getText().toString());
                courseDetails.putString("start", mEditCourseStart.getText().toString());
                courseDetails.putString("end", mEditCourseEnd.getText().toString());
                courseDetails.putString("status", mEditCourseStatus.getText().toString());
                courseDetails.putString("ins_name", mEditCourseInsName.getText().toString());
                courseDetails.putString("ins_phone", mEditCourseInsPhone.getText().toString());
                courseDetails.putString("ins_email", mEditCourseInsEmail.getText().toString());
                courseDetails.putString("id", id);
                replyIntent.putExtra(EDIT_COURSE_TITLE, courseDetails.getString("title"));
                replyIntent.putExtra(EDIT_COURSE_START, courseDetails.getString("start"));
                replyIntent.putExtra(EDIT_COURSE_END, courseDetails.getString("end"));
                replyIntent.putExtra(EDIT_COURSE_STATUS, courseDetails.getString("status"));
                replyIntent.putExtra(EDIT_COURSE_INS_NAME,
                        courseDetails.getString("ins_name"));
                replyIntent.putExtra(EDIT_COURSE_INS_PHONE,
                        courseDetails.getString("ins_phone"));
                replyIntent.putExtra(EDIT_COURSE_INS_EMAIL,
                        courseDetails.getString("ins_email"));
                replyIntent.putExtra(COURSE_ID, courseDetails.getString("id"));

                if ((mEditCourseNote.getText()) != null) {
                    courseDetails.putString("note", mEditCourseNote.getText().toString());
                    replyIntent.putExtra(EDIT_COURSE_NOTE, courseDetails.getString("note"));
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