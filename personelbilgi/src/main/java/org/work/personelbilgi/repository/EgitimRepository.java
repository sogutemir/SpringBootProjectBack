package org.work.personelbilgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personelbilgi.model.Egitim;

import java.util.List;

public interface EgitimRepository extends JpaRepository<Egitim, Long> {
    List<Egitim> findByPersonelId(Long personelId);
}
