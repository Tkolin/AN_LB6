package com.example.lab3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DayCaseViewModel extends ViewModel {
    // Список
    private MutableLiveData<List<DayCase>> dayCasesLiveData = new MutableLiveData<>();

    // Метод для получения списка
    public LiveData<List<DayCase>> getDayCases() {
        return dayCasesLiveData;
    }

    // Метод Добавление указаной задачи в список
    public void addDayCase(DayCase dayCase) {
        List<DayCase> dayCases = dayCasesLiveData.getValue();
        if (dayCases == null) {
            dayCases = new ArrayList<>();
        }
        dayCases.add(dayCase); // Добавление значения в список
        dayCasesLiveData.setValue(dayCases); // Установка списка в dayCasesLiveData
    }
     // Метод Добавление указаной задачи из списка
    public void removeDayCase(int position) {
        List<DayCase> dayCases = dayCasesLiveData.getValue();
        if (dayCases != null && position >= 0 && position < dayCases.size()) {
            dayCases.remove(position);
            dayCasesLiveData.setValue(dayCases);
        }
    }
}
