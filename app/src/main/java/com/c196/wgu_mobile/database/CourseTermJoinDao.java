package com.c196.wgu_mobile.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.c196.wgu_mobile.entity.CourseEntity;
import com.c196.wgu_mobile.entity.CourseTermJoin;
import com.c196.wgu_mobile.entity.TermEntity;

import java.util.List;

@Dao
public interface CourseTermJoinDao {

    @Insert
    void insert(CourseTermJoin courseTermJoin);

    @Update
    void update(CourseTermJoin courseTermJoin);

    @Delete
    void delete(CourseTermJoin courseTermJoin);

    @Query("SELECT * FROM courses INNER JOIN course_term_join ON courses.id = course_term_join.courseId WHERE course_term_join.termId = :termId")
    LiveData<List<CourseEntity>> getCoursesForTerm(long termId);

    @Query("SELECT * FROM terms INNER JOIN course_term_join ON terms.id = course_term_join.termId WHERE course_term_join.courseId = :courseId")
    LiveData<List<TermEntity>> getTermsForCourse(long courseId);
}

