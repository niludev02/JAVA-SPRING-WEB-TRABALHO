package com.example.cadastro_produto.service;

import com.example.cadastro_produto.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private final List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public List<Produto> listar() {
        return produtos;
    }

    public void salvar(Produto produto) {
        produto.setId(proximoId);
        proximoId++;
        produtos.add(produto);
    }

    public void excluir(int id) {
        produtos.removeIf(p -> p.getId() == id);
    }
}