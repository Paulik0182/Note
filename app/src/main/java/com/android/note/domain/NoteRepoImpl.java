package com.android.note.domain;

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
    private List<NoteEntity> data = new ArrayList<>();

    private int counter = 0;

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
    public int createRandomID() {
        return counter++;
    }
}
