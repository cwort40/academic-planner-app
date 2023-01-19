package com.c196.wgu_mobile.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"courseId", "termId"}, tableName = "course_term_join")
public class CourseTermJoin {
    @ColumnInfo(name = "courseId")
    public long courseId;

    @ColumnInfo(name = "termId")
    public long termId;

    public CourseTermJoin(long courseId, long termId) {
        this.courseId = courseId;
        this.termId = termId;
    }
}

