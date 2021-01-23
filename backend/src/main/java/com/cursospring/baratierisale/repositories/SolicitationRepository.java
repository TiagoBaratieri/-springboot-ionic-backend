package com.cursospring.baratierisale.repositories;

import com.cursospring.baratierisale.entities.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationRepository extends JpaRepository<Solicitation,Integer> {
}
