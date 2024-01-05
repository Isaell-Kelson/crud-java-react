package br.com.unidac.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unidac.model.Breakfast;

@Repository
public interface BreakfastRepository extends JpaRepository<Breakfast, Long> {

    List<Breakfast> findByDateAfter(Date currentDate);

    List<Breakfast> findByDateAndCollaboratorCpf(Date date, String cpf);

    List<Breakfast> findByBreakfastOptionAndDate(String breakfastOption, Date date);

    
}
