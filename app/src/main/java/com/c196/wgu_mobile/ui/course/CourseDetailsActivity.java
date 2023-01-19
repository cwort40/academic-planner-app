package com.c196.wgu_mobile.ui.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.c196.wgu_mobile.R;
import com.c196.wgu_mobile.entity.CourseEntity;
import com.c196.wgu_mobile.entity.TermEntity;

public class CourseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        CourseEntity selectedCourse = (CourseEntity)getIntent()
                .getSerializableExtra("selected_course");
        if (selectedCourse == null) {
            // Handle error: no term was passed in the intent
            System.out.println("no course passed");
        }

        TextView courseTitleView = findViewById(R.id.course_title);
        courseTitleView.setText(selectedCourse.getTitle());

        TextView courseStartDateView = findViewById(R.id.course_start);
        courseStartDateView.setText(selectedCourse.getStartDate());

        TextView courseEndDateView = findViewById(R.id.course_end);
        courseEndDateView.setText(selectedCourse.getEndDate());

        TextView courseStatusDateView = findViewById(R.id.course_status);
        courseStatusDateView.setText(selectedCourse.getStatus());

        TextView courseInsNameView = findViewById(R.id.course_ins_name);
        courseInsNameView.setText(selectedCourse.getInstructorName());

        TextView courseInsPhoneView = findViewById(R.id.course_ins_phone);
        courseInsPhoneView.setText(selectedCourse.getInstructorPhone());

        TextView courseInsEmailView = findViewById(R.id.course_ins_email);
        courseInsEmailView.setText(selectedCourse.getInstructorEmail());

        TextView courseNoteView = findViewById(R.id.course_note);
        courseNoteView.setText(selectedCourse.getNote());

    }

    public void onReturnClicked(View view) {
        finish();
    }

}