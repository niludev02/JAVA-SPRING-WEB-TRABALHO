package com.example.cadastro_produto.controller;

import com.example.cadastro_produto.model.Produto;
import com.example.cadastro_produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // GET / → redireciona para /cadastrar
    @GetMapping("/")
    public String index() {
        return "redirect:/cadastrar";
    }

    // GET /cadastrar → exibe o formulário vazio
    @GetMapping("/cadastrar")
    public String exibirFormulario(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastrar";
    }

    // POST /cadastrar → valida, salva e redireciona
    @PostMapping("/cadastrar")
    public String cadastrarProduto(@Valid @ModelAttribute Produto produto,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "cadastrar";
        }
        produtoService.salvar(produto);
        return "redirect:/lista";
    }

    // GET /lista → exibe todos os produtos cadastrados
    @GetMapping("/lista")
    public String exibirLista(Model model) {
        model.addAttribute("produtos", produtoService.listar());
        return "lista";
    }

    // POST /excluir/{id} → remove o produto e volta para a lista
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) {
        produtoService.excluir(id);
        return "redirect:/lista";
    }
}