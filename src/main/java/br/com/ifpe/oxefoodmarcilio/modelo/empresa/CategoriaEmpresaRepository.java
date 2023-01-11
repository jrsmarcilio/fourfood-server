package br.com.ifpe.oxefoodmarcilio.modelo.empresa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaEmpresaRepository
		extends JpaRepository<CategoriaEmpresa, Long>, JpaSpecificationExecutor<CategoriaEmpresa> {

	List<CategoriaEmpresa> findByChaveEmpresaOrderByDescricaoAsc(String chaveEmpresa);

	@Query(value = "SELECT c FROM CategoriaEmpresa c WHERE c.chaveEmpresa = :chaveEmpresa AND c.descricao = :descricao")
	CategoriaEmpresa findByChaveAndDescricao(String chaveEmpresa, String descricao);

	@Query(value = "SELECT c FROM CategoriaEmpresa c WHERE c.id = :id AND c.chaveEmpresa = :chaveEmpresa")
	CategoriaEmpresa findByIdAndChave(Long id, String chaveEmpresa);

}

