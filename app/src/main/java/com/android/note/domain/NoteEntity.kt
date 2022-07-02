package com.android.note.domain

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

/**
 * Данный класс для сущностей.
 * здесь заводим поля - объекты (делаем структуру сущности). Все объекты (поля) private,
 * далее создаем для каждого объекта get и set это для того что-бы была возможность контролировать
 * запись, чтение объекта (поля - переменные).
 */
class NoteEntity : Parcelable {
    val id // setId(int id) не делаем
            : Int

    //если private то значение изменить нельзя, можно только получать зачение объекта
    var title: String?
    var content: String?
    var color: Int

    //конструктор принимает все параметры (можно делать много конструкторов с разным набором полей)
    constructor(id: Int, title: String?, content: String?, color: Int) {
        this.id = id
        this.title = title
        this.content = content
        this.color = color
    }

    //конструктор по умолчанию который на вход принимает Parcel
    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        title = `in`.readString()
        content = `in`.readString()
        color = `in`.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(title)
        dest.writeString(content)
        dest.writeInt(color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Creator<NoteEntity> = object : Creator<NoteEntity?> {
            override fun createFromParcel(`in`: Parcel): NoteEntity? {
                return NoteEntity(`in`)
            }

            override fun newArray(size: Int): Array<NoteEntity?> {
                return arrayOfNulls(size)
            }
        }
    }
}