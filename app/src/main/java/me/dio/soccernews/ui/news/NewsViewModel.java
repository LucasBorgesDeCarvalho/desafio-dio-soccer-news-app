package me.dio.soccernews.ui.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

import me.dio.soccernews.domain.News;
import me.dio.soccernews.remote.SoccerNewsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR;
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();
    private final SoccerNewsApi api;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lucasborgesdecarvalho.github.io/desafio-dio-soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(SoccerNewsApi.class);

        this.findNews();
    }

    private void findNews() {
        state.setValue(State.DOING);
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                    state.setValue(State.DONE);
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, Throwable t) {
                t.printStackTrace();
                state.setValue(State.ERROR);
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }

    public LiveData<State> getState() {
        return this.state;
    }
}