package com.olifia.welcome;

/**
 * Created by fiaolifia on 22/05/2017.
 */

public class Diari {
    String tanggal;
    String judul;
    String isi;
    String kategori;
    String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Diari(){

    }

    public Diari(String id, String tanggal, String judul,String isi, String kategori) {
        this.id = id;
        this.isi = isi;
        this.judul = judul;
        this.kategori = kategori;
        this.tanggal = tanggal;
    }


    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }
}
