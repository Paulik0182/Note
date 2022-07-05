package com.android.note.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.note.R
import com.android.note.domain.NoteEntity

private const val TAG_MAIN_CONTAINER_LAYOUT_KEY = "TAG_MAIN_CONTAINER_LAYOUT_KEY"
private const val TAG_DETAIL_CONTAINER_LAYOUT_KEY = "TAG_DETAIL_CONTAINER_LAYOUT_KEY"

class RootActivity : AppCompatActivity(), NoteListFragment.Controller,
    NoteDetailFragment.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        val noteListFragment: Fragment = NoteListFragment() //создали фрагмент
        supportFragmentManager
            .beginTransaction() //начать транзакцию
            .add(
                R.id.main_container_layout,
                noteListFragment,
                TAG_MAIN_CONTAINER_LAYOUT_KEY
            ) //здесь мы укакзываем в какой layout мы вставляем фрагмент
            .commit() //закончить транзакцию
    }

    override fun onDataChanged() {
        val fragment =
            supportFragmentManager.findFragmentByTag(TAG_MAIN_CONTAINER_LAYOUT_KEY) as NoteListFragment // находим нужный фрагмент
        fragment.onDataChanged()
    }

    override fun finishNoteDetailFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(TAG_DETAIL_CONTAINER_LAYOUT_KEY)
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .remove(fragment) // удалить самого себя
                .commit()
        }
    }

    override fun showNoteScreen(noteEntity: NoteEntity?) {
        val noteDetailFragment: Fragment =
            NoteDetailFragment.newInstance(noteEntity) //создали фрагмент
        supportFragmentManager
            .beginTransaction() //начать транзакцию
            //здесь мы укакзываем в какой layout мы вставляем фрагмент
            // (внимание! в данном случае у нас уже есть один фрагмент, этот фрагмент будет раздут повех первого фрагмента).
            .add(
                R.id.detail_container_layout,
                noteDetailFragment,
                TAG_DETAIL_CONTAINER_LAYOUT_KEY
            ) //добавляем тэг (teg - )
            .addToBackStack(null) //это список операций по нажатию кнопки назад. Если добавление фрагмента в данной транзакией, значит он его удалит.
            .commit() //закончить транзакцию
    }
}