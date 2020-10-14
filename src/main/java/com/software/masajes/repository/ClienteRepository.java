package com.software.masajes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.software.masajes.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
