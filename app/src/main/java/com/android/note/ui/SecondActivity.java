package com.android.note.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.note.App;
import com.android.note.R;
import com.android.note.domain.NoteEntity;
import com.android.note.domain.NoteRepo;

public class SecondActivity extends AppCompatActivity {

    public static final String TITLE_OUT_EXTRA_KEY = "TITLE_OUT_EXTRA_KEY";
    public static final String CONTENT_OUT_EXTRA_KEY = "CONTENT_OUT_EXTRA_KEY";
    public static final String ID_OUT_EXTRA_KEY = "ID_OUT_EXTRA_KEY";
    private static final String COLOR_OUT_EXTRA_KEY = "COLOR_OUT_EXTRA_KEY";

    private Button saveButton = null;
    private Button cancelButton = null;
    private TextView idTv = null;
    private EditText headingTitleEt = null;
    private EditText contentEt = null;

    private int noteId;
    private int noteColor;

    //метод для вызова данной активити
    public static Intent getLaunchIntent(
            Context context,
            int id,
            String title,
            String content,
            int color
    ) {
        Intent intent = new Intent(context, SecondActivity.class);

        intent.putExtra(ID_OUT_EXTRA_KEY, id);
        intent.putExtra(TITLE_OUT_EXTRA_KEY, title);
        intent.putExtra(CONTENT_OUT_EXTRA_KEY, content);
        intent.putExtra(COLOR_OUT_EXTRA_KEY, color);

        return intent;
    }

    private void initViews() {
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);
        idTv = findViewById(R.id.id_text_view);
        headingTitleEt = findViewById(R.id.heading_title_edit_text);
        contentEt = findViewById(R.id.content_edit_text);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        setListeners();

        Intent intent = getIntent();
        noteId = intent.getIntExtra(ID_OUT_EXTRA_KEY, 0);
        NoteEntity title = (NoteEntity) intent.getParcelableExtra(TITLE_OUT_EXTRA_KEY);
        NoteEntity content = (NoteEntity) intent.getParcelableExtra(CONTENT_OUT_EXTRA_KEY);
        noteColor = intent.getIntExtra(COLOR_OUT_EXTRA_KEY, 0);
        idTv.setText(String.valueOf(noteId));
        headingTitleEt.setText((CharSequence) title);
        contentEt.setText((CharSequence) content);

    }

    private App getApp() {
        return (App) getApplication();
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

                    setResult(RESULT_OK);//для обновления списка на первом окне отправляем результат
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED);
                    break;
                default:
            }
            finish();
        };

        saveButton.setOnClickListener(OnClickListener);
        cancelButton.setOnClickListener(OnClickListener);
    }

    @Override
    public void onBackPressed() {

        Intent resultIntent = new Intent();
//        intent.putExtra ( TITLE_EXTRA_KEY,  );
//        intent.putExtra (CONTENT_EXTRA_KEY,  );
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
