package com.nguyendan.footballstanding.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nguyendan.footballstanding.data.model.matches.Match;
import com.nguyendan.footballstanding.data.model.scorers.Scorer;
import com.nguyendan.footballstanding.data.model.standing.Table;
import com.nguyendan.footballstanding.data.repository.DataRepository;

import java.util.List;

public class DataViewModel extends ViewModel {
    private DataRepository repository = new DataRepository();

    public LiveData<List<Table>> getListTable(String compeCode, Context context) {
        return repository.getDataTable(compeCode,context);
    }

    public LiveData<List<Scorer>> getListScorer(String compeCode, Context context) {

        return repository.getScorerMutableLiveData(compeCode,context);
    }

    public LiveData<List<Match>> getListMatch(String compeCode,Context context) {
        return repository.getDataMatch(compeCode,context);
    }

}
