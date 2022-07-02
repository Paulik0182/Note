package com.android.note.data;

import android.graphics.Color;

import com.android.note.domain.NoteEntity;
import com.android.note.domain.NoteRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * этот клас для реализации интерфейса (NoteRepo)
 * здесь реализуем методы которые указаны в NoteRepo
 */
public class NoteRepoImpl implements NoteRepo {

    //заводим массив сущьности (хранилище заметок на основе массива)
    public static List<NoteEntity> data = new ArrayList<>();

    private int counter = 0;

    //добавляем данные (создаем список)
    public NoteRepoImpl() {
        addNote(new NoteEntity(
                createRandomId(),
                "Заголовок",
                "Привет, Привет",
                Color.RED
        ));
        addNote(new NoteEntity(
                createRandomId(),
                "Название",
                "Дорогой дневник",
                Color.BLUE
        ));
        addNote(new NoteEntity(
                createRandomId(),
                "Hello",
                "Привет, Привет",
                Color.YELLOW
        ));
        addNote(new NoteEntity(
                createRandomId(),
                "Заголовок",
                "Ура",
                Color.BLACK
        ));
    }

    @Override
    public void addNote(NoteEntity noteEntity) {
        data.add(noteEntity);//добавляем заметку в список
    }

    @Override
    public List<NoteEntity> getNotes() {
        return new ArrayList<>(data);//отдаем копию списка, копию коллекции (лучше отдавать копию чтобы никто не повредил оригинальные данные)
    }

    @Override
    public void deleteNoteById(int id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == id) {
                data.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    @Override
    public int createRandomId() {
        return counter++;
    }

    @Override
    public void update(NoteEntity changedNote) {
        int id = changedNote.getId();//это id который мы хотим изменить

        //поиск старой заметки
        NoteEntity oldNote = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == id) {//находим нужный id
                oldNote = data.get(i);//получаем нужный элемент
                break;
            }
        }
        if (oldNote == null) {//если не нашли заметку, то добавляем заметку
            addNote(changedNote);
        } else {
            //если заметка существует, то меняем в ней setTitle, setContent, setColor
            oldNote.setTitle(changedNote.getTitle());
            oldNote.setContent(changedNote.getContent());
            oldNote.setColor(changedNote.getColor());
        }
    }
}
