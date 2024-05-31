package edu.upc.dsa.martianslog;

import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.martianslog.models.UsuariRanking;
import edu.upc.dsa.martianslog.service.ApiService;
import edu.upc.dsa.martianslog.service.LoginUsuari;
import edu.upc.dsa.martianslog.service.RankingAdapter;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
public class RankingActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RankingAdapter adapter;
    private List<UsuariRanking> usuariRankingList;
    private ApiService apiService;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        recyclerView =findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usuariRankingList=new ArrayList<>();
        adapter = new RankingAdapter(usuariRankingList);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        onClic();
    }

    private void onClic()
    {

        Call<List<UsuariRanking>> call = apiService.getRanking();

        call.enqueue(new Callback<List<UsuariRanking>>()
        {
            @Override
            public void onResponse(Call<List<UsuariRanking>> call, Response<List<UsuariRanking>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userList = response.body();
                    adapter = new RankingAdapter(usuariRankingList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.d(TAG, "Sin datos:" + response.code());
                    Toast.makeText(RankingActivity.this, "Sin ranking", Toast.LENGTH_LONG).show();
                    //return;
                }
            }

            @Override
            public void onFailure(Call<LoginUsuari> call, Throwable t)
            {
                Toast.makeText(RankingActivity.this, "HELLOOOO!", Toast.LENGTH_LONG);
                Toast.makeText(RankingActivity.this, "Error en la conecion del servico", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Error response from service retrofit:" + t.getMessage());
            }
        });
    }
}
