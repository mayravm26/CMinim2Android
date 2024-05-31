package edu.upc.dsa.martianslog.service;

import java.util.List;

import edu.upc.dsa.martianslog.models.UsuariRanking;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRanking
{
    @GET("/stats/ranking/")
    Call<List<UsuariRanking>> getUserRanking();
}
