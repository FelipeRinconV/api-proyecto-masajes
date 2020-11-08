package com.software.masajes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.software.masajes.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {

}
