package com.software.masajes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.software.masajes.model.Factura;



public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
