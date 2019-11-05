package br.com.gft.vendas.ApiEstoqueVendas.services;

import br.com.gft.vendas.ApiEstoqueVendas.exceptions.ObjectNotFoundException;
import br.com.gft.vendas.ApiEstoqueVendas.modelo.SubCategoria;
import br.com.gft.vendas.ApiEstoqueVendas.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubcategoriaIService {

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    public Page<SubCategoria> listar(Pageable paginacao) {
        return subcategoriaRepository.findAll(paginacao);
    }

    public SubCategoria encontrarPorId(Integer id) {
        Optional<SubCategoria> subCategoria = subcategoriaRepository.findById(id);
        return subCategoria.orElseThrow(() -> new ObjectNotFoundException(
                "Subcategoria com id: " + id + " não encontrado!, tipo: " + SubCategoria.class.getName()
        ));
    }

    public SubCategoria cadastrar(Object subCategoria) {
        SubCategoria subCat = (SubCategoria) subCategoria;
        subCat.setScatId(null);
        return subcategoriaRepository.save(subCat);
    }

    public SubCategoria atualizar(Integer id, Object subCategoria) {
        SubCategoria subCategoriaEncontradaPorId = encontrarPorId(id);
        SubCategoria subCat = (SubCategoria) subCategoria;
        subCategoriaEncontradaPorId.setScatId(subCat.getScatId());
        subCategoriaEncontradaPorId.setScatNome(subCat.getScatNome());
        subCategoriaEncontradaPorId.setCategoria(subCat.getCategoria());
        return subcategoriaRepository.save(subCategoriaEncontradaPorId);
    }

    public void deletar(Integer id) {
        encontrarPorId(id);
        subcategoriaRepository.deleteById(id);
    }
}
