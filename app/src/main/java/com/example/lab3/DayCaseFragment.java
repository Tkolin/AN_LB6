package com.example.lab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider;

public class DayCaseFragment extends Fragment {

    private List<DayCase> dayCases;
    private DayCaseAdapter adapter;
    private ListView listView;
    private DayCaseViewModel viewModel; // ViewModel для управления данными

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_case_list_fragment, container, false);
        listView = view.findViewById(R.id.listView);

        // Инициализируем ViewModel
        viewModel = new ViewModelProvider(this).get(DayCaseViewModel.class);

        dayCases = new ArrayList<>();

        // Создаем адаптер и устанавливаем его в ListView
        adapter = new DayCaseAdapter(requireContext(), dayCases);
        listView.setAdapter(adapter);

        // Обновляем список дел при изменениях в ViewModel
        viewModel.getDayCases().observe(getViewLifecycleOwner(), new Observer<List<DayCase>>() {
            @Override
            public void onChanged(List<DayCase> newDayCases) {
                dayCases.clear();
                dayCases.addAll(newDayCases);
                adapter.notifyDataSetChanged();
            }
        });

        // Устанавливаем обработчик долгого нажатия на элемент списка
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteConfirmationDialog(position);
                return true;
            }
        });

        return view;
    }

    // Метод для добавления элемента в список дел с использованием ViewModel
    public void addDayCase(DayCase dc) {
        viewModel.addDayCase(dc);
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Вы уверены, что хотите удалить этот элемент?")
                .setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Удаление элемента из ViewModel
                        viewModel.removeDayCase(position);
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}
