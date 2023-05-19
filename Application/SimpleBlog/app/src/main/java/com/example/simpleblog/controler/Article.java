package com.example.simpleblog.controler;

import java.time.LocalDate;

public class Article {
    private String mTitre;
    private String mContenu;
    private String mDateCreation;
    private String mAutor;

    public Article(String titre, String contenu, String dateCreation, String autor) {
        mTitre = titre;
        mContenu = contenu;
        mDateCreation = dateCreation;
        mAutor = autor;
    }

    public String getTitre() {
        return mTitre;
    }

    public void setTitre(String titre) {
        mTitre = titre;
    }

    public String getContenu() {
        return mContenu;
    }

    public void setContenu(String contenu) {
        mContenu = contenu;
    }

    public String getDateCreation() {
        return mDateCreation;
    }

    public void setDateCreation(String dateCreation) {
        mDateCreation = dateCreation;
    }

    public String getAutor() {
        return mAutor;
    }

    public void setAutor(String autor) {
        mAutor = autor;
    }
}
