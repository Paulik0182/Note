package com.android.note.domain;

import java.io.Serializable;

/**
 * Данный класс для сущностей.
 * здесь заводим поля - объекты (делаем структуру сущности). Все объекты (поля) private,
 * далее создаем для каждого объекта get и set это для того что-бы была возможность контролировать
 * запись, чтение объекта (поля - переменные).
 */

public class NoteEntity implements Serializable {

    private final int id; // setId(int id) не делаем
    private String title;
    private String content;
    private int color;

    //конструктор принимает все параметры (можно делать много конструкторов с разным набором полей)
    public NoteEntity(int id, String title, String content, int color) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    //если private то значение изменить нельзя, можно только получать зачение объекта
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
