package br.com.curso.udemy.productapi.adapters.out.persistence.repository;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
}
