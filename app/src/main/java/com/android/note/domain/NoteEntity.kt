package com.android.note.domain

import android.graphics.Color
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
    var color: Int? = null//такое написание делает переменную (поле) не обязательной для заполнения (написания)
) : Parcelable

fun ololo() {
    val note = NoteEntity(1, "", "", Color.GRAY)
    note.content
}

//аргументы можно менять местами если их поименовать. Например
fun ololo2() {
    val note = NoteEntity(
        1,
        content = "",
        title = ""
    )
    note.content
}

//У data class есть иквелс сравнение, автоматтически генерится хеш код
fun ololo3() {
    val note = NoteEntity(
        1,
        content = "",
        title = ""
    )
    val note4 =
        note.copy(title = "Привет")// создали копию объекта и изменили одну переменную, все остальное остается прежним
}