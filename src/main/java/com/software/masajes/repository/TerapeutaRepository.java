package com.software.masajes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.software.masajes.dto.ClientDto;
import com.software.masajes.model.Terapeuta;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {
	

}
