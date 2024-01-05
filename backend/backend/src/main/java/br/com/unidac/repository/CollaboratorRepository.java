package br.com.unidac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unidac.model.Collaborator;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

    
}
