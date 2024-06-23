package com.cristhyangoes.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;
import java.util.List;

public class ConverteDados implements IConverteDados{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converterDados(String dados, Class<T> classe) {
        try{
            return mapper.readValue(dados, classe);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> List<T> converterLista(String dados, Class<T> classe) {
        CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, classe);
        try{
            return mapper.readValue(dados, type);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
