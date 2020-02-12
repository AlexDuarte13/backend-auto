package com.ebix.easi.auto.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ebix.easi.auto.model.entities.Automovel;

@Repository
public interface AutomovelRepository extends CrudRepository<Automovel, Long>, PagingAndSortingRepository<Automovel, Long>{

}
