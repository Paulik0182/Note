package com.android.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //создали список сущностей
    private List<NoteEntity> noteEntityList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}