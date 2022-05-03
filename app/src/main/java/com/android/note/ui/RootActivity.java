package com.android.note.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.note.R;
import com.android.note.domain.NoteEntity;

public class RootActivity extends AppCompatActivity implements NoteListFragment.Controller, NoteDetailFragment.Controller {

    private static final String TAG_MAIN_CONTAINER_LAYOUT_KEY = "TAG_MAIN_CONTAINER_LAYOUT_KEY";
    private static final String TAG_DETAIL_CONTAINER_LAYOUT_KEY = "TAG_DETAIL_CONTAINER_LAYOUT_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        Fragment noteListFragment = new NoteListFragment(); //создали фрагмент
        getSupportFragmentManager()
                .beginTransaction() //начать транзакцию
                .add(R.id.main_container_layout, noteListFragment, TAG_MAIN_CONTAINER_LAYOUT_KEY) //здесь мы укакзываем в какой layout мы вставляем фрагмент
                .commit(); //закончить транзакцию
    }

    @Override
    public void showNoteScreen(NoteEntity noteEntity) {
        Fragment noteDetailFragment = NoteDetailFragment.newInstance(noteEntity); //создали фрагмент
        getSupportFragmentManager()
                .beginTransaction() //начать транзакцию

                //здесь мы укакзываем в какой layout мы вставляем фрагмент
                // (внимание! в данном случае у нас уже есть один фрагмент, этот фрагмент будет раздут повех первого фрагмента).
                .add(R.id.detail_container_layout, noteDetailFragment, TAG_DETAIL_CONTAINER_LAYOUT_KEY)  //добавляем тэг (teg - )
                .addToBackStack(null)//это список операций по нажатию кнопки назад. Если добавление фрагмента в данной транзакией, значит он его удалит.

                .commit(); //закончить транзакцию
    }

    @Override
    public void onDataChanged() {
        NoteListFragment fragment = (NoteListFragment) getSupportFragmentManager().findFragmentByTag(TAG_MAIN_CONTAINER_LAYOUT_KEY);// находим нужный фрагмент
        if (fragment != null) {
            fragment.onDataChanged();//если изменили передаем данные обратно
        }
    }

    @Override
    public void finishNoteDetailFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_DETAIL_CONTAINER_LAYOUT_KEY);
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragment) // удалить самого себя
                    .commit();
        }
    }
}