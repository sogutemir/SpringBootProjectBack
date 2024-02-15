package org.work.personelbilgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.personelbilgi.model.Etkinlik;

public interface EtkinlikRepository extends JpaRepository<Etkinlik, Long> {
}
