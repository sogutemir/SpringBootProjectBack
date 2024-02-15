package org.work.personelbilgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personelbilgi.model.Deneyim;

import java.util.List;

public interface DeneyimRepository extends JpaRepository<Deneyim, Long> {

    List<Deneyim> findByPersonelId(Long personelId);
}
