package com.android.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //создали список сущностей
//    private List<NoteEntity> noteEntityList = new LinkedList<>();

    //создаем репозиторий (хранилище), указываем релиазацию хранилища.
    //для создания лучше использовать класс Add
//    private NoteRepo noteRepo = new NoteRepoImpl();

    //обращаемся к репозиторию (application)
    private NoteRepo noteRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //достаем репозиторий из application (класс Add)
//        noteRepo = ((Add) getApplication()).getNoteRepo();//вариант написания 1
        noteRepo = getAdd().getNoteRepo();//вариант написания 2 (более читаемый код)
    }

    private Add getAdd(){
        return (Add) getApplication();
    }
}