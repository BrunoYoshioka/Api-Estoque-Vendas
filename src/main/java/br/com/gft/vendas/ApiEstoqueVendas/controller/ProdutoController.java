package br.com.gft.vendas.ApiEstoqueVendas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.vendas.ApiEstoqueVendas.modelo.Produto;
import br.com.gft.vendas.ApiEstoqueVendas.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api("API REST PRODUTOS")
@CrossOrigin(origins="*") // definir qual dominio irá ser liberado, no meu caso todos
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@ApiOperation(value = "Retorna uma lista de Produtos")
	@GetMapping("/produtos")
	public List<Produto> listaProdutos(){
		return produtoRepository.findAll();
	}
	
	@ApiOperation(value = "Retorna um cliente unico")
	@GetMapping("/produto/{id}")
	public Produto listaProdutoUnico(@PathVariable Integer id) {
		return produtoRepository.findById(id);
	}
	
	@ApiOperation(value = "Salva um produto")
	@PostMapping("/produto")
	public Produto salvaProduto(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@ApiOperation(value = "Deleta um produto")
	@DeleteMapping("/produto")
	public void deletaProduto(@RequestBody @Valid Produto produto) {
		produtoRepository.delete(produto);
	}

	@ApiOperation(value = "Atualiza um produto")
	@PutMapping("/produto")
	public Produto atualizaProduto(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}

}
