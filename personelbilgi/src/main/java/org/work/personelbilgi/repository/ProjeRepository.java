package org.work.personelbilgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personelbilgi.model.Proje;

import java.util.List;

public interface ProjeRepository extends JpaRepository<Proje, Long> {
    List<Proje> findByPersonelId(Long personelId);
}
