package org.work.personelbilgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personelbilgi.model.Dosya;

import java.util.List;

public interface DosyaRepository extends JpaRepository<Dosya, Long>{
    List<Dosya> findByPersonelId(Long personelId);
}
