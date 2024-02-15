package org.work.personelbilgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personelbilgi.model.Proje;

public interface ProjeRepository extends JpaRepository<Proje, Long> {
}
