package com.c196.wgu_mobile.ui.term;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.c196.wgu_mobile.R;
import com.c196.wgu_mobile.adapter.TermAdapter;
import com.c196.wgu_mobile.entity.TermEntity;
import com.c196.wgu_mobile.util.RecyclerItemClickListener;
import com.c196.wgu_mobile.viewmodel.TermViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class TermsActivity extends AppCompatActivity {

    private TermViewModel mTermViewModel;
    public static final int NEW_TERM_ACTIVITY_REQUEST_CODE = 1;

    final TermAdapter adapter = new TermAdapter(new TermAdapter.TermDiff());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        FloatingActionButton fabAddTerm = findViewById(R.id.fab_add_term);
        fabAddTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermsActivity.this, NewTermActivity.class);
                newTermActivityResultLauncher.launch(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview_terms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);

        LiveData<List<TermEntity>> termsList = mTermViewModel.getAllTerms();

        // Update the cached copy of the words in the adapter.
        mTermViewModel.getAllTerms().observe(this, adapter::submitList);

        //clickable recycler view item
        Context context = getApplicationContext();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener
                        .OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(TermsActivity.this,
                                TermDetailsActivity.class);
                        intent.putExtra("selected_term", Objects
                                .requireNonNull(termsList.getValue()).get(position));
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        Intent intent = new Intent(TermsActivity.this,
                                EditTermActivity.class);
                        intent.putExtra("selected_term", Objects
                                .requireNonNull(termsList.getValue()).get(position));
                        editTermActivityResultLauncher.launch(intent);
                    }
                })
        );

        // Add the functionality to swipe items in the
        // recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        TermEntity selectedTerm = adapter.getTermAtPosition(position);
                        Toast.makeText(TermsActivity.this, "Deleting " +
                                selectedTerm.getTitle(), Toast.LENGTH_LONG).show();

                        // Delete the term
                        mTermViewModel.delete(selectedTerm);
                    }
                });

        helper.attachToRecyclerView(recyclerView);

    }

    private ActivityResultLauncher<Intent> newTermActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String title = data.getStringExtra(NewTermActivity.ADD_TERM_TITLE);
                        String start = data.getStringExtra(NewTermActivity.ADD_TERM_START);
                        String end = data.getStringExtra(NewTermActivity.ADD_TERM_END);
                        TermEntity term = new TermEntity(title, start, end);
                        mTermViewModel.insert(term);
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.term_empty_not_saved,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });


    private ActivityResultLauncher<Intent> editTermActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        int id = Integer.parseInt(data.getStringExtra(EditTermActivity.TERM_ID));
                        String title = data.getStringExtra(EditTermActivity.EDIT_TERM_TITLE);
                        String start = data.getStringExtra(EditTermActivity.EDIT_TERM_START);
                        String end = data.getStringExtra(EditTermActivity.EDIT_TERM_END);
                        TermEntity term = new TermEntity(id, title, start, end);
                        mTermViewModel.update(term);
                        mTermViewModel.getAllTerms()
                                .observe(TermsActivity.this, adapter::submitList);

                        //debug
                        System.out.println(data.getStringExtra(EditTermActivity.EDIT_TERM_TITLE));
                        System.out.println(data.getStringExtra(EditTermActivity.TERM_ID));

                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.term_empty_not_saved,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

}
