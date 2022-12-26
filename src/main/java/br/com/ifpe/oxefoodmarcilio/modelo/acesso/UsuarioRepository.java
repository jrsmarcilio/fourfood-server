package br.com.ifpe.oxefoodmarcilio.modelo.acesso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByUsername(String username);
}
