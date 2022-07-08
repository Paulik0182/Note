package com.android.note.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Данный класс для сущностей.
 * здесь заводим поля - объекты (делаем структуру сущности). Все объекты (поля) private,
 * далее создаем для каждого объекта get и set это для того что-бы была возможность контролировать
 * запись, чтение объекта (поля - переменные).
 */

@Parcelize
data class NoteEntity(
    val id: Int,
    var title: String,
    var content: String,
    var color: Int
) : Parcelable