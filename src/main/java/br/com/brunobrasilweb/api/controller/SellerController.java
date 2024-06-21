package br.com.brunobrasilweb.api.controller;

import br.com.brunobrasilweb.api.dto.SellerRequest;
import br.com.brunobrasilweb.api.dto.SellerResponse;
import br.com.brunobrasilweb.api.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/seller")
@RequiredArgsConstructor
public class SellerController {

	private final SellerService service;

	@GetMapping
	public Page<SellerResponse> pagelable(Pageable pageable) {
		return service.pageable(pageable);
	}

	@PostMapping
	public SellerResponse create(@RequestBody @Valid SellerRequest request) {
		return service.create(request);
	}

	@PutMapping(value = "/{id}")
	public SellerResponse update(@PathVariable("id") Long id, @RequestBody @Valid SellerRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

	@GetMapping(value = "/{id}")
	public SellerResponse byId(@PathVariable("id") Long id) {
		return service.byId(id);
	}

}
