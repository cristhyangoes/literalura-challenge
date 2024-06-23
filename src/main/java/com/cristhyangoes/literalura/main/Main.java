package com.cristhyangoes.literalura.main;

import com.cristhyangoes.literalura.model.Autor;
import com.cristhyangoes.literalura.model.Livro;
import com.cristhyangoes.literalura.model.LivroDTO;
import com.cristhyangoes.literalura.model.ResultsDTO;
import com.cristhyangoes.literalura.repository.LivroRepository;
import com.cristhyangoes.literalura.service.ConsumoApi;
import com.cristhyangoes.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private List<Livro> livros = new ArrayList<>();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private LivroRepository repositorio;

    public Main(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibirMenu(){
        var opcao = -1;

        while(opcao != 0 || !String.valueOf(opcao).equals("0")) {

            var menu = """
                    -------------
                    Escolha o número da sua opção:
                    1 - Buscar livros por título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros em determinado idioma
                                    
                                    
                    0 - Sair
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch(opcao){
                case 1:
                    buscarLivrosPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        }
    }

    private void buscarLivrosPorTitulo() {
        System.out.println("Insira o nome do livro que deseja procurar: ");
        String nomeLivro = leitura.nextLine();
        try{
            String json = consumoApi.obterDados(ENDERECO+nomeLivro.toLowerCase().replace(" ", "%20"));
            ResultsDTO resultsDTO = converteDados.converterDados(json, ResultsDTO.class);
            List<LivroDTO> livroDTOList = resultsDTO.resultados().stream().toList();
            Livro livro = new Livro(livroDTOList.get(0));
            livros.add(livro);

            repositorio.save(livro);
            System.out.println("------- Livro -------"+ "\nTítulo: "+livro.getTitulo()+ "\nAutor: "+livro.getAutor()+ "\nIdioma: "+livro.getIdioma()+ "\nNúmero de downloads: "+livro.getDownloads()+ "\n---------------------\n");
        }catch(Exception e){
            System.out.println("O livro que você digitou, não foi encontrado!");
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = repositorio.findAll();
        livros.forEach(l -> System.out.println("------- Livro -------"+ "\nTítulo: "+l.getTitulo()+ "\nAutor: "+l.getAutor()+ "\nIdioma: "+l.getIdioma()+ "\nNúmero de downloads: "+l.getDownloads()+ "\n---------------------\n"));
    }

    private void listarAutores() {
        List<Autor> autores = repositorio.findByAutores();
        autores.forEach(a -> System.out.println("Autor: " + a.getNome() + "\nAno de Nascimento: " + a.getAnoNascimento()
        + "\nAno de Falecimento: " + a.getAnoFalecimento() + "\nLivros: "+a.getLivros()+"\n"));
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("Insira um ano que deseja pesquisar: ");
        var ano = leitura.nextInt();
        List<Autor> autores = repositorio.findByAno(ano);
        autores.forEach(a -> System.out.println("Autor: " + a.getNome() + "\nAno de Nascimento: " + a.getAnoNascimento()
                + "\nAno de Falecimento: " + a.getAnoFalecimento() + "\nLivros: "+a.getLivros()+"\n"));

    }

    private void listarLivrosDeterminadoIdioma() {
        System.out.println("Insira o idioma para realizar a busca: ");
        var opcoes = """
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """;
        System.out.println(opcoes);
        var idioma = leitura.nextLine();

        switch (idioma){
            case "es":
                listarPorIdioma(idioma);
                break;
            case "en":
                listarPorIdioma(idioma);
                break;
            case "fr":
                listarPorIdioma(idioma);
                break;
            case "pt":
                listarPorIdioma(idioma);
                break;
            default:
                System.out.println("A opção escolhida é inválida!");
                break;
        }

    }

    private void listarPorIdioma(String idioma){
        List<Livro> livros = repositorio.listarPorIdioma(idioma);
        if(!livros.isEmpty()) {
            for(int i = 0; i < livros.size(); i++){
                Livro livro = livros.get(i);
                System.out.println("------- Livro -------" + "\nTítulo: " + livro.getTitulo() + "\nAutor: " + livro.getAutor() + "\nIdioma: " + livro.getIdioma() + "\nNúmero de downloads: " + livro.getDownloads() + "\n---------------------\n");
            }
            //livros.forEach(System.out.println("------- Livro -------" + "\nTítulo: " + livros.g + "\nAutor: " + livros.getAutor() + "\nIdioma: " + livros.getIdioma() + "\nNúmero de downloads: " + livros.getDownloads() + "\n---------------------\n"));
        } else {
            System.out.println("Não há livros cadastrados com esse idioma!");
        }
            //livros.forEach(l -> System.out.println());

    }
}
