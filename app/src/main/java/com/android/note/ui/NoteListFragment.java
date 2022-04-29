package com.android.note.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.note.App;
import com.android.note.R;
import com.android.note.data.NoteRepoImpl;
import com.android.note.domain.NoteEntity;
import com.android.note.domain.NoteRepo;

public class NoteListFragment extends Fragment {


    private final NoteAdapter.InteractionListener listener = new NoteAdapter.InteractionListener() {
        @Override
        public void onItemClickListener(NoteEntity noteEntity) {
            String sb = "onItemClickListener -"
                    + noteEntity.getTitle()
                    + noteEntity.getContent();
            Toast.makeText(getContext(), sb, Toast.LENGTH_SHORT).show();

        }
    };
    private NoteRepo noteRepo; //чтобы данные взять
    private RecyclerView recyclerView; //для того чтобы показать список
    private NoteAdapter adapter; //для приобразования данных

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(Color.GREEN); //пример, демонстрация работы, окрасили view в цвет

        initViews(view);

        //достаем репозиторий из application (класс App)
        noteRepo = getAdd().getNoteRepo();//вариант написания 2 (более читаемый код)

        //Кладем данные в адаптер
        adapter.setData(noteRepo.getNotes());
    }

    private void initViews(View view) {
//        recyclerView = getView().findViewById(R.id.recycler_view);//вариант написания 1
        recyclerView = view.findViewById(R.id.recycler_view);// вариант написания 2 (лучше так. в этом случае нужно передать в метод view)

        //чтобы соединить noteRepo и recyclerView (передать данные во view)
        //передаем, способ как компановать экран
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//можно передать ориентацию и т.д.
        //обязательно нужно установить адаптер (это такая сущность котороя превращает noteEntity в
        adapter = new NoteAdapter(NoteRepoImpl.data, listener);
        recyclerView.setAdapter(adapter);
    }

    private App getAdd() {
        return (App) getActivity().getApplication(); // вариант написания 1
//        return (App) getContext().getApplicationContext(); // вариант написания 2
    }
}