package com.cristhyangoes.literalura.service;

import java.util.List;

public interface IConverteDados {
    <T> T converterDados(String dados, Class<T> classe);
    <T> List<T> converterLista(String dados, Class<T> classe);
}
