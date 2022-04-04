package com.android.note.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.note.Add;
import com.android.note.domain.NoteEntity;
import com.android.note.domain.NoteRepo;
import com.android.note.R;
import com.android.note.domain.NoteRepoImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //создали список сущностей
//    private List<NoteEntity> noteEntityList = new LinkedList<>();

    //создаем репозиторий (хранилище), указываем релиазацию хранилища.
    //для создания лучше использовать класс Add
//    private NoteRepo noteRepo = new NoteRepoImpl();

    //обращаемся к репозиторию (application)
    private NoteRepo noteRepo;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    private NoteAdapter.InteractionListener listener = new NoteAdapter.InteractionListener() {
        @Override
        public void onItemClickListener(NoteRepoImpl noteRepoImpl) {
           String sb = "onItemClickListener -" + noteRepoImpl.getNotes();
            Toast.makeText(MainActivity.this, sb, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        //достаем репозиторий из application (класс Add)
//        noteRepo = ((Add) getApplication()).getNoteRepo();//вариант написания 1
        noteRepo = getAdd().getNoteRepo();//вариант написания 2 (более читаемый код)

        //достаем данные из репозитория
        List<NoteEntity> notes = noteRepo.getNotes();
        //Кладем данные в адаптер

        adapter.setData(noteRepo.getNotes());
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);

        //чтобы соединить noteRepo и recyclerView (передать данные во view)
        //передаем способ как компановать экран
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//можно передать ориентацию и т.д.
        //обязательно нужно установить адаптер (это такая сущность котороя превращает notEntity в
        adapter =  new NoteAdapter();
        recyclerView.setAdapter(adapter);
    }

    private Add getAdd(){
        return (Add) getApplication();
    }
}