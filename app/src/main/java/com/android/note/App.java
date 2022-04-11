package com.android.note;
/**
 * Репозиторий должен быть в единственном экземпляре, не должно быть создания в каждой новой активити,
 * при открытии фрагмета, при повороте экрана и т.д.
 * Необходимо создать один раз Репозиторий и везде этим пользоватся, для этого есть синглтон (это класс
 * который создается только один раз), но в синглтоне много технических проблем,
 * или можно сохранить средствами андройд. Есть сущность которое представляет из себя приложение (application)
 * в manifests.
 * Данный класс для того чтобы воспользоватся application. В данном классе также есть жизненые цыкля.
 * В manifests обязательно необходимо прописать данный класс
 * android:name=".App"
 */

import android.app.Application;

import com.android.note.domain.NoteRepo;
import com.android.note.domain.NoteRepoImpl;

public class App extends Application {

    //создаем репозиторий (хранилище), указываем релиазацию хранилища.
    private final NoteRepo noteRepo = new NoteRepoImpl();

    //к данному репозиторию можно братится через этот метод
    public NoteRepo getNoteRepo() {
        return noteRepo;
    }
}
