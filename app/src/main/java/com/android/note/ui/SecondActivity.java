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

import com.android.note.R;

public class SecondActivity extends AppCompatActivity {

    public static final String TITLE_OUT_EXTRA_KEY = "TITLE_OUT_EXTRA_KEY";
    public static final String CONTENT_OUT_EXTRA_KEY = "CONTENT_OUT_EXTRA_KEY";

    private Button saveButton = null;
    private Button cancelButton = null;
    private TextView idTv = null;
    private EditText headingTitleEt = null;
    private EditText contentEt = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        setListeners();

        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE_OUT_EXTRA_KEY);
        String content = intent.getStringExtra(CONTENT_OUT_EXTRA_KEY);
        headingTitleEt.setText(title);
        contentEt.setText(content);

    }

    private void initViews() {
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);
        idTv = findViewById(R.id.id_text_view);
        headingTitleEt = findViewById(R.id.heading_title_edit_text);
        contentEt = findViewById(R.id.content_edit_text);
    }

    //метод для вызова данной активити
    public static Intent getLaunchIntent(Context context, String title, String content) {
        Intent intent = new Intent(context, SecondActivity.class);

        intent.putExtra(TITLE_OUT_EXTRA_KEY, title );
        intent.putExtra(CONTENT_OUT_EXTRA_KEY, content);

        context.startActivity(intent);
        return intent;
    }
    private void setListeners() {
        @SuppressLint("NonConstantResourceId") final View.OnClickListener OnClickListener = view -> {

            //проверяем нажатие кнопки и устанвливаем цвет экрана
            switch (view.getId()) {
                case R.id.save_button:
                    onBackPressed();
                    break;
                case R.id.cancel_button:
                    finish();
                    break;
                default:
            }
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
