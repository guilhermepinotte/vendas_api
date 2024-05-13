package io.github.guilhermepinotte.vendas.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Page<Cliente> findAllByAtivoTrue(Pageable pageable);
}
