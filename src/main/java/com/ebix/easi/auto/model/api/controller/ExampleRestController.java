package com.ebix.easi.auto.model.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebix.easi.auto.model.entities.Example;
import com.ebix.easi.auto.model.service.ExampleService;

@RestController
@RequestMapping("/example")
public class ExampleRestController {

	@Autowired
	private ExampleService exampleService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listExamples(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "sort", defaultValue = "asc") String sort,
			@RequestParam(value = "objOrd", defaultValue = "id") String objOrd) {

		try {

			String obj = verificaCamposSort(objOrd);

			Pageable paging = null;

			if ("asc".equalsIgnoreCase(sort)) {
				paging = PageRequest.of(page, pageSize, Sort.by(obj).ascending());
			} else {
				paging = PageRequest.of(page, pageSize, Sort.by(obj).descending());
			}

			Page<Example> examples = exampleService.findAll(paging);

			if (examples != null && examples.getContent().size() > 0) {

				return new ResponseEntity<Page<Example>>(examples, HttpStatus.OK);

			} else {

				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

			}

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> save(@RequestBody @Valid Example example) {

		try {

			exampleService.save(example);

			return new ResponseEntity<Example>(example, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	/**
	 * Método responsavel por verificar a ordem do filtro
	 *
	 * @param objOrd
	 * @return
	 */
	private String verificaCamposSort(String objOrd) {
		String obj;
		if ("id".equals(objOrd) || "name".equals(objOrd)) {

			obj = objOrd;

		} else {

			obj = "id";

		}
		return obj;
	}

	@RequestMapping(value = "/{ID}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> update(@PathVariable("ID") Long id, @Valid @RequestBody Example example) {

		try {

			Example exampleOriginal = exampleService.findById(id);

			if (exampleOriginal == null) {

				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

			}

			exampleService.update(example, exampleOriginal);

			return new ResponseEntity<Example>(HttpStatus.ACCEPTED);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(value = "/{ID}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> delete(@PathVariable("ID") Long id) {

		try {

			Example example = exampleService.findById(id);

			if (example == null) {

				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

			}

			exampleService.delete(example);

			return new ResponseEntity<Object>(HttpStatus.ACCEPTED);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
