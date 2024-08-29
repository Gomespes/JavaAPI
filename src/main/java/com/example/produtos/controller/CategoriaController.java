package com.example.produtos.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.produtos.modelo.Categoria;
import com.example.produtos.servico.CategoriaServico;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaServico categoriaServico;
    
    public CategoriaController(CategoriaServico categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria){
        return categoriaServico.save(categoria);
    }

    @GetMapping
    public List<Categoria> listarCategorias(){
        return categoriaServico.findAll();
    }

    @DeleteMapping("/{id}")
    public void excluirCategoria(@PathVariable Long id){
        categoriaServico.deleteById(id);
    }
    @PutMapping("/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        
        
        return entity;
    }
    public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria novaCategoria){
        return categoriaServico.findById(id)
        .map(categoria -> {
            categoria.setNome(novaCategoria.getNome());
            return categoriaServico.save(categoria);
        })
        .orElseGet(() ->{
            novaCategoria.setId(id);
            return categoriaServico.save(novaCategoria);

        });
    }

    @GetMapping("/{id}")
    public Optional<Categoria> buscarProdutoPorId(@PathVariable Long id){
        return categoriaServico.findById(id);
    }
    
}

