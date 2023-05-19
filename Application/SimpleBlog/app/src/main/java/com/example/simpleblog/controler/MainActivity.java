package com.example.simpleblog.controler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simpleblog.R;
import com.example.simpleblog.controler.AddArticle;
import com.example.simpleblog.controler.Article;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREF_ARTICLE_INFO = "article_info";
    private static final String SHARED_PREF_ARTICLE_INFO_TITLE = "title";
    private static final String SHARED_PREF_ARTICLE_INFO_CONTENT = "content";
    private static final String SHARED_PREF_ARTICLE_INFO_AUTHOR = "author";
    private static final String SHARED_PREF_ARTICLE_INFO_DATE = "date";

    private List<Article> articles;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) FloatingActionButton quitButton = findViewById(R.id.quit_add_article);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        // Recuperate les données des articles depuis les SharedPreferences
        articles = retrieveArticlesInfo();

        // Afficher les articles dans la console (remplacez cette partie par l'affichage dans votre interface utilisateur)
        for (Article article : articles) {
            System.out.println("Titre : " + article.getTitre());
            System.out.println("Contenu : " + article.getContenu());
            System.out.println("Auteur : " + article.getAutor());
            System.out.println("Date de création : " + article.getDateCreation());
        }
    }

    private void saveArticlesInfo(List<Article> articles) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_ARTICLE_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Effacer les anciennes données des articles
        editor.clear();

        // Sauvegarder les nouvelles données des articles
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            editor.putString(SHARED_PREF_ARTICLE_INFO_TITLE + i, article.getTitre());
            editor.putString(SHARED_PREF_ARTICLE_INFO_CONTENT + i, article.getContenu());
            editor.putString(SHARED_PREF_ARTICLE_INFO_AUTHOR + i, article.getAutor());
            editor.putString(SHARED_PREF_ARTICLE_INFO_DATE + i, article.getDateCreation());
        }

        editor.apply();
    }

    private List<Article> retrieveArticlesInfo() {
        List<Article> articles = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_ARTICLE_INFO, MODE_PRIVATE);
        int articleCount = sharedPreferences.getAll().size() / 4;

        for (int i = 0; i < articleCount; i++) {
            String titre = sharedPreferences.getString(SHARED_PREF_ARTICLE_INFO_TITLE + i, "");
            String contenu = sharedPreferences.getString(SHARED_PREF_ARTICLE_INFO_CONTENT + i, "");
            String auteur = sharedPreferences.getString(SHARED_PREF_ARTICLE_INFO_AUTHOR + i, "");
            String dateCreation = sharedPreferences.getString(SHARED_PREF_ARTICLE_INFO_DATE + i, "");

            if (!titre.isEmpty() && !contenu.isEmpty() && !auteur.isEmpty() && !dateCreation.isEmpty()) {
                Article article = new Article(titre, contenu, dateCreation, auteur);
                articles.add(article);
            }
        }

        return articles;
    }
}
