package com.storage.poc.repository.arquivo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storage.poc.model.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
	List<Arquivo> findByProntuarioId(Long prontuarioId);
}
