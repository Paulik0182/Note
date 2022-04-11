package com.android.note.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.note.App;
import com.android.note.R;
import com.android.note.domain.NoteEntity;
import com.android.note.domain.NoteRepo;
import com.android.note.domain.NoteRepoImpl;

public class MainActivity extends AppCompatActivity {

    private static final int NOTE_REQUEST_CODE = 100;
    //создали список сущностей

    //создаем репозиторий (хранилище), указываем релиазацию хранилища.
    //для создания лучше использовать класс App
//    private NoteRepo noteRepo = new NoteRepoImpl();

    //обращаемся к репозиторию (application)
    private NoteRepo noteRepo;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    private final NoteAdapter.InteractionListener listener = new NoteAdapter.InteractionListener() {
        @Override
        public void onItemClickListener(NoteEntity noteEntity) {
            String sb = "onItemClickListener -"
                    + noteEntity.getTitle()
                    + noteEntity.getContent();
            Toast.makeText(MainActivity.this, sb, Toast.LENGTH_SHORT).show();

            Intent intent = SecondActivity.getLaunchIntent
                    (
                            MainActivity.this,
                            noteEntity.getId(),
                            noteEntity.getTitle(),
                            noteEntity.getContent(),
                            noteEntity.getColor()
                    );
            startActivityForResult(intent, NOTE_REQUEST_CODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            adapter.setData(noteRepo.getNotes());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        //достаем репозиторий из application (класс App)
        noteRepo = getAdd().getNoteRepo();//вариант написания 2 (более читаемый код)

        //Кладем данные в адаптер
        adapter.setData(noteRepo.getNotes());
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);

        //чтобы соединить noteRepo и recyclerView (передать данные во view)
        //передаем способ как компановать экран
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//можно передать ориентацию и т.д.
        //обязательно нужно установить адаптер (это такая сущность котороя превращает noteEntity в
        adapter = new NoteAdapter(NoteRepoImpl.data, listener);
        recyclerView.setAdapter(adapter);
    }

    private App getAdd() {
        return (App) getApplication();
    }
}