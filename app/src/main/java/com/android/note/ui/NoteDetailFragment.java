package com.android.note.ui;
/**
 * во фрагменте: intent отсутствует, но есть getArguments
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.note.App;
import com.android.note.R;
import com.android.note.domain.NoteEntity;
import com.android.note.domain.NoteRepo;


public class NoteDetailFragment extends Fragment {

    private static final String NOTE_ENTITY_KEY = "NOTE_ENTITY_KEY";
    private NoteEntity noteEntity;

    private Button saveButton = null;
    private Button cancelButton = null;
    private TextView idTv = null;
    private EditText headingTitleEt = null;
    private EditText contentEt = null;
    private int noteId;
    private int noteColor;

    //!!!!Вариант 1 !!!!
//    public NoteDetailFragment(NoteEntity noteEntity) {
    /**
     * Arguments - это своего рода костыль придуманный разработчиками
     * в основном для поворота экрана, это на подобии Bundle (корзины. ключ - значение)
     * !!сюда можно положить маленькие данные!!
     */
//        Bundle args = new Bundle();
//        args.putParcelable(NOTE_ENTITY_KEY, noteEntity);
//        setArguments(args);
//    }

    /**
     * в связи с тем, что при повороте экрана активити пересоздаются,
     * а фрагменты мы создали сами, соответственно автоматически фрагменты
     * не могут создаться, не чего не известно noteEntity
     * поэтому необходим пустой конструктор
     * <p>
     * обязательно должен быть public
     */
    public NoteDetailFragment() {
    }

    //!!!!Вариант 2 !!!! - наиболее предпочтительно
    static NoteDetailFragment newInstance(NoteEntity noteEntity) {
        Bundle args = new Bundle();
        args.putParcelable(NOTE_ENTITY_KEY, noteEntity);

        NoteDetailFragment fragment = new NoteDetailFragment();// отдельно создаем фрагмент
        fragment.setArguments(args);// кладем во фрагмент аргументы
        return fragment;
    }

    private void setNoteEntity(NoteEntity noteEntity) {
        idTv.setText(String.valueOf(noteEntity.getId()));
        headingTitleEt.setText(noteEntity.getTitle());
        contentEt.setText(noteEntity.getContent());
        noteId = noteEntity.getId();//запомнили значение
        noteColor = noteEntity.getColor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setListeners();

        noteEntity = getArguments().getParcelable(NOTE_ENTITY_KEY);
        setNoteEntity(noteEntity);
    }

    private void initViews(View view) {
        saveButton = view.findViewById(R.id.save_button);
        cancelButton = view.findViewById(R.id.cancel_button);
        idTv = view.findViewById(R.id.id_text_view);
        headingTitleEt = view.findViewById(R.id.heading_title_edit_text);
        contentEt = view.findViewById(R.id.content_edit_text);
    }

    private void setListeners() {
        @SuppressLint("NonConstantResourceId") final View.OnClickListener OnClickListener = view -> {

            //проверяем нажатие кнопки и устанвливаем цвет экрана
            switch (view.getId()) {
                case R.id.save_button:
                    String changedTitle = headingTitleEt.getText().toString();//забрали изменение
                    String changedContent = contentEt.getText().toString();//забрали изменение

                    //собрали новую заметку
                    NoteEntity changedNote = new NoteEntity(
                            noteId,
                            changedTitle,
                            changedContent,
                            noteColor);//получаем новые измененные данные. new NoteEntity

                    NoteRepo noteRepo = getApp().getNoteRepo();//достали репозеторий

                    noteRepo.update(changedNote);//добавляем новые данные

                    getController().onDataChanged();//для обновления списка на первом окне отправляем результат
                    break;
                case R.id.cancel_button:
                    break;
                default:
            }
            getController().finishNoteDetailFragment();

            //можно сделать финиширование фрагмента следующим образом.
            // !!НО ЛУЧШЕ ТАК НЕ ДЕЛАТЬ!!
            // здесь может быть ошибка, если данный фрагмент
            // показал в другом месте все упадет
//            getActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .remove(this) // удалить самого себя
//                    .commit();
        };

        saveButton.setOnClickListener(OnClickListener);
        cancelButton.setOnClickListener(OnClickListener);
    }

    private App getApp() {
        return (App) getContext().getApplicationContext();
    }

    private Controller getController() {
        return (Controller) getActivity();
    }

    //это метод сработывает в момент присоединения фрагмента к активити
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getController();

        //этот метод с вызовом метода getController() своего рода костыль.
        // Он нам позволит свольть приложение раньше чем откроится фрагмент.
        // Приложение свалится если мы забудим в классе RootActivity
        // отнаследоватся от интерфейса (implements NoteListFragment.Controller).
    }

    //интерфейс для завершения обработки, обнавления данных
    interface Controller {
        void onDataChanged(); //обработка данных, обнавить данные

        void finishNoteDetailFragment(); // завершение фрагмента, финиш
    }
}