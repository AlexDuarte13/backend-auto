package com.ebix.easi.auto.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ebix.easi.auto.model.api.enums.StatusType;
import com.ebix.easi.auto.model.entities.Vistoria;

@Repository
public interface VistoriaRepository extends CrudRepository<Vistoria, Long>, PagingAndSortingRepository<Vistoria, Long>{

	@Query(value="Select v from Vistoria v where v.status = ?1")
	Page<Vistoria> findAllByStatus(StatusType status, Pageable paging);


}
