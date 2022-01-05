package fr.imt.webannuaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.imt.webannuaire.data.Person;
import fr.imt.webannuaire.itf.DictionnaryItf;

@Controller
public class DictionnaryController {

	@Autowired
	DictionnaryItf ds;

	@GetMapping("/annuaire/recherche")
	public String recherche(Model model,
			@RequestParam(name = "name", required = false, defaultValue = "*") String name) {
		if (name.equals("*"))
			model.addAttribute("entries", ds.getAll());
		else
			model.addAttribute("entries", ds.getFromName(name));
		return "annuaire/recherche";
	}

	@GetMapping("/annuaire/ajouter")
	public String ajouterEntree() {
		return "/annuaire/ajout";
	}

	@PostMapping("/annuaire/ajouter")
	public String add(Person p) {
		ds.addPerson(p);
		return "redirect:/annuaire/recherche";
	}

	@GetMapping("/annuaire/modifier/{id}")
	public String modifierEntree(Model model, @PathVariable int id) {
		model.addAttribute("entry", ds.getFromId(id));
		return "/annuaire/modif";
	}

	@PostMapping("/annuaire/modifier/{id}")
	public String modifierEntree(@PathVariable int id, @RequestParam String name,@RequestParam String surname,@RequestParam String phone,@RequestParam String city) {
		Person p = new Person(id,name,surname,phone,city);
		ds.addPerson(p);
		return "redirect:/annuaire/recherche";
	}
	
	@GetMapping("/annuaire/supprimer/{id}")
	public String supprimeModel(Model model, @PathVariable int id) {
		ds.deleteFromId(id);
		model.addAttribute("entries", ds.getAll());
		return "annuaire/recherche";	
	}
	
}
