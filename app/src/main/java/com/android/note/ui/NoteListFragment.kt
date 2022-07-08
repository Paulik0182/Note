package com.android.note.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.note.App
import com.android.note.R
import com.android.note.data.NoteRepoImpl
import com.android.note.domain.NoteEntity
import com.android.note.domain.NoteRepo

class NoteListFragment : Fragment() {
    private val listener = { noteEntity: NoteEntity ->
        val sb = ("onItemClickListener -"
                + noteEntity.title
                + noteEntity.content)
        Toast.makeText(context, sb, Toast.LENGTH_SHORT).show()
        showNoteScreen(noteEntity) // открывам отдельный экран отдельной заметки. noteEntity - конкретно какую заметку передаем
    }

    private lateinit var noteRepo: NoteRepo//чтобы данные взять
    private lateinit var recyclerView: RecyclerView//для того чтобы показать список
    private lateinit var adapter: NoteAdapter//для приобразования данных

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(Color.GREEN) //пример, демонстрация работы, окрасили view в цвет
        initViews(view)

        //достаем репозиторий из application (класс App)
        noteRepo = add.noteRepo //вариант написания 2 (более читаемый код)

        //Кладем данные в адаптер
        adapter.setData(noteRepo.getNotes())
    }

    private fun initViews(view: View) {
//        recyclerView = getView().findViewById(R.id.recycler_view);//вариант написания 1
        recyclerView =
            view.findViewById(R.id.recycler_view) // вариант написания 2 (лучше так. в этом случае нужно передать в метод view)

        //чтобы соединить noteRepo и recyclerView (передать данные во view)
        //передаем, способ как компановать экран
        recyclerView.layoutManager = LinearLayoutManager(context) //можно передать ориентацию и т.д.
        //обязательно нужно установить адаптер (это такая сущность котороя превращает noteEntity в
        adapter = NoteAdapter(NoteRepoImpl.data, listener)
        recyclerView.adapter = adapter
    }

    // вариант написания 1
//        return (App) getContext().getApplicationContext(); // вариант написания 2
    private val add: App by lazy { requireActivity().application as App } // вариант написания 1

    //        return (App) getContext().getApplicationContext(); // вариант написания 2
//метод для открытия отдельной заметки
    private fun showNoteScreen(noteEntity: NoteEntity) {
        controller.showNoteScreen(noteEntity)
        //для того чтобы фрагмент знал что-то об активети,
        // лучше всего сделать связь (взаимодействие) через интерфейс,
        // в этом случае фрагмент напрямую не будет обращатся к активити,
        // все взаимодействие будет через интерфейс
    }

    //взаимодействие активити и фрагмента через контроллер
    private val controller: Controller by lazy { activity as Controller }

    //это метод сработывает в момент присоединения фрагмента к активити
    override fun onAttach(context: Context) {
        super.onAttach(context)
        controller

        //этот метод с вызовом метода getController() своего рода костыль.
        // Он нам позволит свольть приложение раньше чем откроится фрагмент.
        // Приложение свалится если мы забудим в классе RootActivity
        // отнаследоватся от интерфейса (implements NoteListFragment.Controller).
    }

    fun onDataChanged() {
        adapter.setData(noteRepo.getNotes()) //данные изменились, вставили их в адаптер
    }

    //сам контроллер. указываем метод через который вызываем фрагмент (фрагмент с деталями  замиси)
//обязательно нужно в активити имплементировать (наследоватся от) интерфейс
    internal interface Controller {
        fun showNoteScreen(noteEntity: NoteEntity?)
    }
}