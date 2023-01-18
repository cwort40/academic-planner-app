package com.c196.wgu_mobile.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.c196.wgu_mobile.database.AppDatabase;
import com.c196.wgu_mobile.database.TermDao;
import com.c196.wgu_mobile.entity.TermEntity;

import java.util.List;

public class TermRepository {

    private TermDao termDao;
    private LiveData<List<TermEntity>> allTerms;

    public TermRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        termDao = database.termDao();
        allTerms = termDao.getAll();
    }

    public LiveData<List<TermEntity>> getAllTerms() {
        return allTerms;
    }

    public void insert(TermEntity term) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            termDao.insert(term);
        });
    }

    public void update(TermEntity term) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            termDao.update(term);
        });
    }

    public void delete(TermEntity term) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            termDao.delete(term);
        });
    }

}

