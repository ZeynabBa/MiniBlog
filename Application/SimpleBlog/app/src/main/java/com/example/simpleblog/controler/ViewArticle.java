package com.example.simpleblog.controler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpleblog.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewArticle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);
        FloatingActionButton quitButton = findViewById(R.id.return_view_article);

        quitButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(ViewArticle.this, MainActivity.class);
            startActivity(intent);
        });


        Intent intent = getIntent();
        String titre = intent.getStringExtra("titre");
        String contenu = intent.getStringExtra("content");
        String dateCreation = intent.getStringExtra("dateCreation");
        String auteur = intent.getStringExtra("auteur");


        TextView titreTextView = findViewById(R.id.title_article_view);
        TextView contenuTextView = findViewById(R.id.contenu_article_view);
        TextView dateTextView = findViewById(R.id.date_view_article);
        TextView auteurTextView = findViewById(R.id.autor_view_article);

        titreTextView.setText(titre);
        contenuTextView.setText(contenu);
        dateTextView.setText(dateCreation);
        auteurTextView.setText(auteur);
    }
}
