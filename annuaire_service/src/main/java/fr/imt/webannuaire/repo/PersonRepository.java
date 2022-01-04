package fr.imt.webannuaire.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import fr.imt.webannuaire.data.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
	List<Person> findByName(String lastName);
	Person findById(int id);
	List<Person> findAll();
}