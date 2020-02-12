package com.ebix.easi.auto.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ebix.easi.auto.model.entities.Example;

@Repository
public interface ExampleRepository extends CrudRepository<Example, Long>, PagingAndSortingRepository<Example, Long>{

}
