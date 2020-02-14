package com.nguyendan.footballstanding.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyendan.footballstanding.data.model.Compe;

import java.util.ArrayList;

public class ShareModel extends ViewModel {
    private MutableLiveData<ArrayList<Compe>> liveData = new MutableLiveData<>();
    public void selectCompe(ArrayList<Compe> item) {
        liveData.setValue(item);
    }

    public LiveData<ArrayList<Compe>> getSelected() {
        return liveData;
    }
}
