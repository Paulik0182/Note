package com.android.note.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.note.App;
import com.android.note.R;
import com.android.note.domain.NoteEntity;
import com.android.note.domain.NoteRepo;
import com.android.note.domain.NoteRepoImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TITLE_SAVE_OUT_EXTRA_KEY = "title_save_out_extra_key";
    private static final String DETAIL_SAVE_OUT_EXTRA_KEY = "detail_save_out_extra_key";
    //создали список сущностей
//    private List<NoteEntity> noteEntityList = new LinkedList<>();

    //создаем репозиторий (хранилище), указываем релиазацию хранилища.
    //для создания лучше использовать класс App
//    private NoteRepo noteRepo = new NoteRepoImpl();

    //обращаемся к репозиторию (application)
    private NoteRepo noteRepo;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private ActivityResultLauncher<Intent> secondActivityLauncher;

    String receiveTitleMainActivity = null;
    String receiveContentMainActivity = null;


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
                            noteEntity.getTitle(),
                            noteEntity.getContent()
                    );
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        //достаем репозиторий из application (класс App)
//        noteRepo = ((App) getApplication()).getNoteRepo();//вариант написания 1
        noteRepo = getAdd().getNoteRepo();//вариант написания 2 (более читаемый код)

        //достаем данные из репозитория
        List<NoteEntity> notes = noteRepo.getNotes();
        //Кладем данные в адаптер
        adapter.setData(noteRepo.getNotes());

//        Intent intent = getIntent ();
//        receiveTitleMainActivity = intent.getStringExtra ( TITLE_SAVE_OUT_EXTRA_KEY );
//        receiveContentMainActivity = intent.getStringExtra ( DETAIL_SAVE_OUT_EXTRA_KEY );
//        NoteRepoImpl.data.add ( new NoteEntity ( receiveTitleMainActivity, receiveContentMainActivity ) );//выводим данные

//        getResultLaunchIntent();
    }

    private void getResultLaunchIntent() {
        secondActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    receiveTitleMainActivity = data.getStringExtra(SecondActivity.TITLE_OUT_EXTRA_KEY);
                    receiveContentMainActivity = data.getStringExtra(SecondActivity.CONTENT_OUT_EXTRA_KEY);
                }
            }
        });
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