package fr.imt.webannuaire.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import fr.imt.webannuaire.data.Person;
import fr.imt.webannuaire.itf.DictionnaryItf;

@RestController
public class RestControl {

	@Autowired
	DictionnaryItf di;
	
	//Récuperer toutes les entrées
	@GetMapping("/entree")
	@CrossOrigin(origins = "*")
	public Collection<Person> getAll(){
		return di.getAll();
	}

	//Récupérer une entrée
	@GetMapping("/entree/{id}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getOne(@PathVariable int id){
		if (di.getFromId(id) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas de personne trouvee avec cet ID");
		}
		return ResponseEntity.status(HttpStatus.OK).body(di.getFromId(id));
	}
	
	//Ajouter une entrée
	@PostMapping("/entree")
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> add(@RequestBody Person newPerson){
		if (di.getFromId(newPerson.getId()) != null ) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Personne deja existante");
		}
		di.addPerson(newPerson);
		return ResponseEntity.status(HttpStatus.CREATED).body("http://localhost:8080/rechercher/"+newPerson.getId());
	}
	
	//Modifier une entrée
	@PutMapping("/entree")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> remplace(@RequestBody Person updatedPerson){
		if (di.getFromId(updatedPerson.getId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas de personne à mettre à jour avec cet ID");
		}
		di.addPerson(updatedPerson);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	//Supprimer entrée
	@DeleteMapping("/entree/{id}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> removeOne(@PathVariable int id){
		if (di.getFromId(id) == null) {
			return new ResponseEntity<>("Pas de personne à supprimer avec cet ID", HttpStatus.NOT_FOUND);
		}
		di.deleteFromId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
}
