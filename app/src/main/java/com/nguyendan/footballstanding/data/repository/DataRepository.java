package com.nguyendan.footballstanding.data.repository;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;

import com.nguyendan.footballstanding.data.model.matches.Match;
import com.nguyendan.footballstanding.data.model.matches.Matches;
import com.nguyendan.footballstanding.data.model.scorers.Scorer;
import com.nguyendan.footballstanding.data.model.scorers.Scorers;
import com.nguyendan.footballstanding.data.model.standing.Standings;
import com.nguyendan.footballstanding.data.model.standing.Table;
import com.nguyendan.footballstanding.data.remote.DataService;
import com.nguyendan.footballstanding.data.remote.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    private MutableLiveData<List<Table>> dataTable = new MutableLiveData<>();
    private MutableLiveData<List<Match>> dataMatch = new MutableLiveData<>();
    private MutableLiveData<List<Scorer>> scorerMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Table>> getDataTable(String compeCode,Context context){
        final DataService dataService = RetrofitClient.getClient();
        dataService.getStandings("1478e253374a4e3f958c7d3685b59d3b",compeCode).enqueue(new Callback<Standings>() {
            @Override
            public void onResponse(Call<Standings> call, Response<Standings> response) {
                try {
                    dataTable.setValue(response.body().getStandings().get(0).getTable());
                }catch (Exception e){
                   // Toast.makeText(context,"Can't get data! Check internet connection",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Standings> call, Throwable t) {
                //Toast.makeText(context,"Connect error",Toast.LENGTH_LONG).show();
                t.printStackTrace();
                Toast.makeText(context,"Can't get data! Check internet connection",Toast.LENGTH_LONG).show();
            }
        });
        return dataTable;
    }
    public MutableLiveData<List<Match>> getDataMatch(String compeCode,Context context){
        DataService dataService = RetrofitClient.getClient();
        dataService.getMatches("1478e253374a4e3f958c7d3685b59d3b",compeCode).enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                try {
                    dataMatch.setValue(response.body().getMatches());
                }catch (Exception e){
                    //Toast.makeText(context,"Can't get data! Check internet connection",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Toast.makeText(context,"Can't get data! Check internet connection",Toast.LENGTH_LONG).show();
            }
        });
        return dataMatch;
    }
    public MutableLiveData<List<Scorer>> getScorerMutableLiveData(String compeCode,Context context) {
        final DataService dataService = RetrofitClient.getClient();
        dataService.getScorers("1478e253374a4e3f958c7d3685b59d3b",compeCode).enqueue(new Callback<Scorers>() {
            @Override
            public void onResponse(Call<Scorers> call, Response<Scorers> response) {
                try {
                    scorerMutableLiveData.setValue( response.body().getScorers());
                }catch (Exception e){
                    e.printStackTrace();
                  //  Toast.makeText(context,"Can't get data! Check internet connection",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Scorers> call, Throwable t) {
                Toast.makeText(context,"Can't get data! Check internet connection",Toast.LENGTH_LONG).show();
            }
        });
        return scorerMutableLiveData;
    }
}
