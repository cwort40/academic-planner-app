package com.c196.wgu_mobile.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "terms")
public class TermEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "start")
    private String startDate;

    @ColumnInfo(name = "end")
    private String endDate;

    //constructor
    @Ignore
    public TermEntity(@NonNull String title, @NonNull String startDate, @NonNull String endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TermEntity(@NonNull int id, @NonNull String title, @NonNull String startDate,
                      @NonNull String endDate) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTerm(){return this.title;}


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
