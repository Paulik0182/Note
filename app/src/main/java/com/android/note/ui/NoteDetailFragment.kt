package com.android.note.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.note.App
import com.android.note.R
import com.android.note.domain.NoteEntity

/**
 * во фрагменте: intent отсутствует, но есть getArguments
 */
class NoteDetailFragment : Fragment() {
    private lateinit var noteEntity: NoteEntity
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private lateinit var idTv: TextView
    private lateinit var headingTitleEt: EditText
    private lateinit var contentEt: EditText
    private var noteId = 0
    private var noteColor = 0

    private fun setNoteEntity(noteEntity: NoteEntity?) {
        idTv.text = noteEntity!!.id.toString()
        headingTitleEt.setText(noteEntity.title)
        contentEt.setText(noteEntity.content)
        noteId = noteEntity.id //запомнили значение
        noteColor = noteEntity.color
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setListeners()
        noteEntity = arguments?.getParcelable(NOTE_ENTITY_KEY)!!
        setNoteEntity(noteEntity)
    }

    private fun initViews(view: View) {
        saveButton = view.findViewById(R.id.save_button)
        cancelButton = view.findViewById(R.id.cancel_button)
        idTv = view.findViewById(R.id.id_text_view)
        headingTitleEt = view.findViewById(R.id.heading_title_edit_text)
        contentEt = view.findViewById(R.id.content_edit_text)
    }

    private fun setListeners() {
        @SuppressLint("NonConstantResourceId") val OnClickListener =
            View.OnClickListener { view: View ->
                when (view.id) {
                    R.id.save_button -> {
                        val changedTitle = headingTitleEt.text.toString() //забрали изменение
                        val changedContent = contentEt.text.toString() //забрали изменение

                        //собрали новую заметку
                        val changedNote = NoteEntity(
                            noteId,
                            changedTitle,
                            changedContent,
                            noteColor
                        ) //получаем новые измененные данные. new NoteEntity
                        val noteRepo = app.noteRepo //достали репозеторий
                        noteRepo.update(changedNote) //добавляем новые данные
                        controller.onDataChanged() //для обновления списка на первом окне отправляем результат
                    }
                    R.id.cancel_button -> {}
                    else -> {}
                }
                controller.finishNoteDetailFragment()
            }
        saveButton.setOnClickListener(OnClickListener)
        cancelButton.setOnClickListener(OnClickListener)
    }

    private val app: App
        private get() = context?.applicationContext as App
    private val controller: Controller
        private get() = activity as Controller

    //это метод сработывает в момент присоединения фрагмента к активити
    override fun onAttach(context: Context) {
        super.onAttach(context)
        controller

        //этот метод с вызовом метода getController() своего рода костыль.
        // Он нам позволит свольть приложение раньше чем откроится фрагмент.
        // Приложение свалится если мы забудим в классе RootActivity
        // отнаследоватся от интерфейса (implements NoteListFragment.Controller).
    }

    //интерфейс для завершения обработки, обнавления данных
    internal interface Controller {
        fun onDataChanged() //обработка данных, обнавить данные
        fun finishNoteDetailFragment() // завершение фрагмента, финиш
    }

    companion object {
        private const val NOTE_ENTITY_KEY = "NOTE_ENTITY_KEY"

        //!!!!Вариант 2 !!!! - наиболее предпочтительно
        fun newInstance(noteEntity: NoteEntity?): NoteDetailFragment {
            val args = Bundle()
            args.putParcelable(NOTE_ENTITY_KEY, noteEntity)
            val fragment = NoteDetailFragment() // отдельно создаем фрагмент
            fragment.arguments = args // кладем во фрагмент аргументы
            return fragment
        }
    }
}