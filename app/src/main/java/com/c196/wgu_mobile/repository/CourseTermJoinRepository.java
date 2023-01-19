package com.c196.wgu_mobile.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.c196.wgu_mobile.database.AppDatabase;
import com.c196.wgu_mobile.database.CourseTermJoinDao;
import com.c196.wgu_mobile.entity.CourseEntity;
import com.c196.wgu_mobile.entity.CourseTermJoin;
import com.c196.wgu_mobile.entity.TermEntity;

import java.util.List;

public class CourseTermJoinRepository {
    private final CourseTermJoinDao courseTermJoinDao;

    public CourseTermJoinRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        courseTermJoinDao = db.courseTermJoinDao();
    }

    public void insert(final CourseTermJoin courseTermJoin) {
        AppDatabase.databaseWriteExecutor.execute(() -> courseTermJoinDao.insert(courseTermJoin));
    }

    public void delete(final CourseTermJoin courseTermJoin) {
        AppDatabase.databaseWriteExecutor.execute(() -> courseTermJoinDao.delete(courseTermJoin));
    }

    public LiveData<List<CourseEntity>> getCoursesForTerm(final int termId) {
        return courseTermJoinDao.getCoursesForTerm(termId);
    }

    public LiveData<List<TermEntity>> getTermsForCourses(final int courseId) {
        return courseTermJoinDao.getTermsForCourse(courseId);
    }
}

