package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;
import me.dio.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO REMOVER Mock de Notícias
        List<News> news = new ArrayList<>();
        news.add(new News("Botafogo Tem Desfalque Importante ","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));
        news.add(new News("Botafogo Joga no Domingo","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));
        news.add(new News("Copa do Mundo Feminina Esta Terminando","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}