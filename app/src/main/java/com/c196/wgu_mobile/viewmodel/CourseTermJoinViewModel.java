package com.c196.wgu_mobile.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.c196.wgu_mobile.entity.CourseEntity;
import com.c196.wgu_mobile.repository.CourseTermJoinRepository;

import java.util.List;

public class CourseTermJoinViewModel extends AndroidViewModel {
    private CourseTermJoinRepository mRepository;
    private LiveData<List<CourseEntity>> mCoursesByTerm;

    public CourseTermJoinViewModel(Application application) {
        super(application);
        mRepository = new CourseTermJoinRepository(application);
    }

    public LiveData<List<CourseEntity>> getCoursesForTerm(int termId) {
        mCoursesByTerm = mRepository.getCoursesForTerm(termId);
        return mCoursesByTerm;
    }
}

