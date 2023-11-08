package com.example.lab3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DayCaseViewModel extends ViewModel {
    private MutableLiveData<List<DayCase>> dayCasesLiveData = new MutableLiveData<>();

    public LiveData<List<DayCase>> getDayCases() {
        return dayCasesLiveData;
    }

    public void addDayCase(DayCase dayCase) {
        List<DayCase> dayCases = dayCasesLiveData.getValue();
        if (dayCases == null) {
            dayCases = new ArrayList<>();
        }
        dayCases.add(dayCase);
        dayCasesLiveData.setValue(dayCases);
    }
    public void removeDayCase(int position) {
        List<DayCase> dayCases = dayCasesLiveData.getValue();
        if (dayCases != null && position >= 0 && position < dayCases.size()) {
            dayCases.remove(position);
            dayCasesLiveData.setValue(dayCases);
        }
    }
}
