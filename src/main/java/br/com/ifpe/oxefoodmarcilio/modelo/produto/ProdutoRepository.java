package br.com.ifpe.oxefoodmarcilio.modelo.produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

	List<Produto> findByEmpresaOrderByDescricaoAsc(String chaveEmpresa);
	Produto findByIdAndEmpresa(String id, String chaveEmpresa);
	Produto findByEmpresaAndDescricaoOrderByDescricaoAsc(String chaveEmpresa, String descricao);
}
