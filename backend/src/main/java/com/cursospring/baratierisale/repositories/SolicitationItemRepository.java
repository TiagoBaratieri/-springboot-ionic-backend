package com.cursospring.baratierisale.repositories;

import com.cursospring.baratierisale.entities.SolicitationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationItemRepository extends JpaRepository<SolicitationItem,Integer> {
}
