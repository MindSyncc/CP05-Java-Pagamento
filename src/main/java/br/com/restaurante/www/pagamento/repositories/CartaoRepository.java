package br.com.restaurante.www.pagamento.repositories;

import br.com.restaurante.www.pagamento.entities.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}
