package org.work.personelbilgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personelbilgi.model.Dosya;

public interface DosyaRepository extends JpaRepository<Dosya, Long>{
}
