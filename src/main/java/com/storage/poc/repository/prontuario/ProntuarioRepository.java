package com.storage.poc.repository.prontuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storage.poc.model.Prontuario;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
