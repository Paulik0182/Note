package com.android.note.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.note.R;
import com.android.note.domain.NoteEntity;

public class RootActivity extends AppCompatActivity implements NoteListFragment.Controller, NoteDetailFragment.Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        Fragment noteListFragment = new NoteListFragment(); //создали фрагмент
        getSupportFragmentManager()
                .beginTransaction() //начать транзакцию
                .add(R.id.container_layout, noteListFragment, "P") //здесь мы укакзываем в какой layout мы вставляем фрагмент
                .commit(); //закончить транзакцию
    }

    @Override
    public void showNoteScreen(NoteEntity noteEntity) {
        Fragment noteDetailFragment = NoteDetailFragment.newInstance(noteEntity); //создали фрагмент
        getSupportFragmentManager()
                .beginTransaction() //начать транзакцию

                //здесь мы укакзываем в какой layout мы вставляем фрагмент
                // (внимание! в данном случае у нас уже есть один фрагмент, этот фрагмент будет раздут повех первого фрагмента).
                .add(R.id.container_layout, noteDetailFragment, "u")  //добавляем тэг (teg - )
                .addToBackStack(null)//это список операций по нажатию кнопки назад. Если добавление фрагмента в данной транзакией, значит он его удалит.

                .commit(); //закончить транзакцию
    }

    @Override
    public void onDataChanged() {
        NoteListFragment fragment = (NoteListFragment) getSupportFragmentManager().findFragmentByTag("P");// находим нужный фрагмент
        if (fragment != null) {
            fragment.onDataChanged();//если изменили передаем данные обратно
        }
    }

    @Override
    public void finishNoteDetailFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("u");
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragment) // удалить самого себя
                    .commit();
        }
    }
}