package com.android.note.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.note.R;

public class RootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        Fragment noteListFragment = new NoteListFragment(); //создали фрагмент
        getSupportFragmentManager()
                .beginTransaction() //начать транзакцию
                .add(R.id.container_layout, noteListFragment) //здесь мы укакзываем в какой layout мы вставляем фрагмент
                .commit(); //закончить транзакцию
    }
}