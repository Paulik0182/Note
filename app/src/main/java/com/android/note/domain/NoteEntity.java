package com.android.note.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Данный класс для сущностей.
 * здесь заводим поля - объекты (делаем структуру сущности). Все объекты (поля) private,
 * далее создаем для каждого объекта get и set это для того что-бы была возможность контролировать
 * запись, чтение объекта (поля - переменные).
 */

public class NoteEntity implements Parcelable {

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

    public static final Creator<NoteEntity> CREATOR = new Creator<NoteEntity>() {
        @Override
        public NoteEntity createFromParcel(Parcel in) {
            return new NoteEntity(in);
        }

        @Override
        public NoteEntity[] newArray(int size) {
            return new NoteEntity[size];
        }
    };

    //конструктор по умолчанию который на вход принимает Parcel
    protected NoteEntity(Parcel in) {
        id = in.readInt();
        title = in.readString();
        content = in.readString();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
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
