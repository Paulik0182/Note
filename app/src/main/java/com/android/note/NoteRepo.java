package com.android.note;

import java.util.List;

/**
 * Repository
 * CRUD хранилище - это такое хранилище которое предлогает четыре способа обработки данных
 * - Create Создавать
 * - Read Читать
 * - Update Обновлять
 * - Delete Удалять
 *
 * Взаимодействия всегда осуществляется через интерфейсы
 */

public interface NoteRepo {

    //Интерфейсы взаимодействия
    void addNote(NoteEntity noteEntity);// добавить заметку

    List<NoteEntity> getNoteEntity();//получить список всех заметок (чтение)

    void deleteNoteById(int id);//удалить заметку по ее id

}
