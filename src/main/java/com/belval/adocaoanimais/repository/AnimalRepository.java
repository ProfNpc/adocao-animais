package com.belval.adocaoanimais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.adocaoanimais.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

	 
}