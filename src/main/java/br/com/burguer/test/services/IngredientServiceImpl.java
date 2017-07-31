package br.com.burguer.test.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.repositories.HamburguerRepository;
import br.com.burguer.test.repositories.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private HamburguerRepository hamburguerRepository;

	@Override
	public Iterable<Ingredient> listAllIngredients() {
		return ingredientRepository.findAll();
	}
	
	@Override
	public Ingredient findOne(Integer id) {
		return ingredientRepository.findOne(id);
	}
	
	@Override
	public Set<Ingredient> listDefaultAllIngredients() {
		ArrayList<Ingredient> ing = (ArrayList<Ingredient>) StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
		
		Set<Ingredient> ingredientes = new HashSet<Ingredient>();
		ing.forEach((ingredient) -> {
			if (ingredient.isDefaultIngredient()) {
				ingredientes.add(ingredient);
			}
		});
		
		return ingredientes;
	}

	@Override
	public Iterable<Ingredient> findIngredientsByHamburguerId(Integer hamburguerId) {
		Hamburguer hamburguer = hamburguerRepository.findOne(hamburguerId);
		return ingredientRepository.findByHamburguer(hamburguer);
	}
	
	

}
