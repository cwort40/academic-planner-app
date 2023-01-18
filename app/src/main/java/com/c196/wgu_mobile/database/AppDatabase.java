package com.c196.wgu_mobile.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.c196.wgu_mobile.entity.TermEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Currently commenting out other classes that are yet to exist

//@Database(entities = {TermEntity.class, CourseEntity.class, AssessmentEntity.class,
//        NoteEntity.class, InstructorEntity.class, CourseInstructorJoinEntity.class}, version = 1)
@Database(entities = {TermEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract TermDao termDao();
//    public abstract CourseDao courseDao();
//    public abstract AssessmentDao assessmentDao();
//    public abstract NoteDao noteDao();
//    public abstract InstructorDao instructorDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app-database")
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}

