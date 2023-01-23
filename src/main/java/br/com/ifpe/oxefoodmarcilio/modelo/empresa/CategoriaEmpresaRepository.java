package br.com.ifpe.oxefoodmarcilio.modelo.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaEmpresaRepository
		extends JpaRepository<CategoriaEmpresa, Long>, JpaSpecificationExecutor<CategoriaEmpresa> {

	@Query(value = "SELECT c FROM CategoriaEmpresa c WHERE c.descricao = :descricao")
	CategoriaEmpresa findByDescricao(String descricao);
}
