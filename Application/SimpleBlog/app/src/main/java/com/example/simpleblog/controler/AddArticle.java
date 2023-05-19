package com.example.simpleblog.controler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simpleblog.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddArticle extends AppCompatActivity {

    private List<Article> articles;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        FloatingActionButton quitButton = findViewById(R.id.quit_add_article);
        EditText mTitre = findViewById(R.id.titre_add_article);
        EditText mAuteur = findViewById(R.id.autor_add_article);
        TextInputEditText mContenu = findViewById(R.id.contenu_add_article);
        Button ajouterButton = findViewById(R.id.main_button_vald);

        // Récupérer les données des articles depuis les SharedPreferences
        articles = retrieveArticlesInfo(AddArticle.this);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddArticle.this, com.example.simpleblog.controler.MainActivity.class);
                startActivity(intent);
            }
        });

        ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titre = mTitre.getText().toString();
                String auteur = mAuteur.getText().toString();
                String contenu = mContenu.getText().toString();
                String dateCreation = LocalDate.now().toString();

                // Créer un nouvel objet Article avec les données saisies
                Article nouvelArticle = new Article(titre, contenu, dateCreation, auteur);

                // Ajouter l'article à la liste des articles
                articles.add(nouvelArticle);

                // Enregistrer les articles dans les SharedPreferences
                saveArticlesInfo(AddArticle.this, articles);

                // Retourner à l'activité principale
                Intent intent = new Intent(AddArticle.this, com.example.simpleblog.controler.MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveArticlesInfo(Context context, List<Article> articles) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("article_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("article_count", articles.size());

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);

            editor.putString("article_title_" + i, article.getTitre());
            editor.putString("article_content_" + i, article.getContenu());
            editor.putString("article_author_" + i, article.getAutor());
            editor.putString("article_date_" + i, article.getDateCreation());
        }

        editor.apply();
    }

    private List<Article> retrieveArticlesInfo(Context context) {
        List<Article> articles = new ArrayList<>();

        SharedPreferences sharedPreferences = context.getSharedPreferences("article_info", MODE_PRIVATE);
        int articleCount = sharedPreferences.getInt("article_count", 0);

        for (int i = 0; i < articleCount
                ; i++) {
            String titre = sharedPreferences.getString("article_title_" + i, "");
            String contenu = sharedPreferences.getString("article_content_" + i, "");
            String auteur = sharedPreferences.getString("article_author_" + i, "");
            String dateCreation = sharedPreferences.getString("article_date_" + i, "");

            if (!titre.isEmpty() && !contenu.isEmpty() && !auteur.isEmpty() && !dateCreation.isEmpty()) {
                Article article = new Article(titre, contenu, dateCreation, auteur);
                articles.add(article);
            }
        }

        return articles;
    }
}
