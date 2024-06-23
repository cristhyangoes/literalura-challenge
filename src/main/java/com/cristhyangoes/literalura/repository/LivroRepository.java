package com.cristhyangoes.literalura.repository;

import com.cristhyangoes.literalura.model.Autor;
import com.cristhyangoes.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT a FROM Autor a")
    List<Autor> findByAutores();

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND a.anoFalecimento > :ano")
    List<Autor> findByAno(int ano);

    @Query("SELECT l FROM Livro l WHERE l.idioma = :idioma")
    List<Livro> listarPorIdioma(String idioma);
}
