package br.com.ifpe.oxefoodmarcilio.modelo.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
  @Query("SELECT p FROM Pedido p WHERE p.empresa.id = :id")
  List<Pedido> findAllByClienteId(Long id);
}
