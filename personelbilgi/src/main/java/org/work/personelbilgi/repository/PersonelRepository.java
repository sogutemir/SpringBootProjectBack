package org.work.personelbilgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personelbilgi.model.Personel;

public interface PersonelRepository extends JpaRepository<Personel, Long> {
}
