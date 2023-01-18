package com.c196.wgu_mobile.ui.term;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.c196.wgu_mobile.R;
import com.c196.wgu_mobile.entity.TermEntity;

public class TermDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        TermEntity selectedTerm = (TermEntity)getIntent()
                .getSerializableExtra("selected_term");
        if (selectedTerm == null) {
            // Handle error: no term was passed in the intent
            System.out.println("no term passed");
        }

        TextView termTitleView = findViewById(R.id.term_title);
        termTitleView.setText(selectedTerm.getTitle());

        TextView termStartDateView = findViewById(R.id.term_start_date);
        termStartDateView.setText(selectedTerm.getStartDate());

        TextView termEndDateView = findViewById(R.id.term_end_date);
        termEndDateView.setText(selectedTerm.getEndDate());

    }


    public void onReturnClicked(View view) {
        finish();
    }

}
