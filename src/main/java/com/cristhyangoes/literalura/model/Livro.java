package com.cristhyangoes.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Integer downloads;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Livro(LivroDTO livroDTO){
        this.titulo = livroDTO.titulo();
        this.idioma = livroDTO.idioma().get(0);
        this.autor = new Autor(livroDTO.autor().get(0));
        this.downloads = livroDTO.downloads();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Livro() {

    }

    public String toString(){
        return this.getTitulo();
    }
}
