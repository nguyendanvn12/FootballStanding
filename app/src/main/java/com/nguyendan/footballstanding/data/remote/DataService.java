package com.nguyendan.footballstanding.data.remote;

import com.nguyendan.footballstanding.data.model.matches.Matches;
import com.nguyendan.footballstanding.data.model.scorers.Scorers;
import com.nguyendan.footballstanding.data.model.standing.Standings;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DataService {

    @GET("{compeCode}/standings")
    Call<Standings> getStandings(@Header("X-Auth-Token")String token, @Path("compeCode")String compeCode);
    @GET("{compeCode}/scorers")
    Call<Scorers> getScorers(@Header("X-Auth-Token")String token, @Path("compeCode")String compeCode);
    @GET("{compeCode}/matches")
    Call<Matches> getMatches(@Header("X-Auth-Token")String token, @Path("compeCode")String compeCode);

}
