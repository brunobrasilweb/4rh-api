package br.com.brunobrasilweb.api.service;

import br.com.brunobrasilweb.api.core.enums.TypeContract;
import br.com.brunobrasilweb.api.core.exception.SellerException;
import br.com.brunobrasilweb.api.dto.SellerRequest;
import br.com.brunobrasilweb.api.dto.SellerResponse;
import br.com.brunobrasilweb.api.entity.Seller;
import br.com.brunobrasilweb.api.repository.SellerRepository;
import br.com.brunobrasilweb.api.util.SellerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerService {

	private final SellerRepository repository;

	public Page<SellerResponse> pageable(Pageable pageable) {
		Page<Seller> sellers = repository.findAll(pageable);

		return sellers.map(SellerResponse::of);
	}

	public SellerResponse create(SellerRequest request) {
		Seller entity = request.toEntity();
		entity.withRegistration(registration(entity.getTypeContract()));

		entity = repository.save(entity);

		return SellerResponse.of(entity);
	}

	public SellerResponse update(Long id, SellerRequest request) {
		Seller existingSeller = repository.findById(id)
				.orElseThrow(() -> new SellerException("Seller with id " + id + " does not exist"));

		Seller entity = request.toEntity()
				.withId(id)
				.withRegistration(existingSeller.getRegistration());

		Seller updatedEntity = repository.save(entity);

		return SellerResponse.of(updatedEntity);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public SellerResponse byId(Long id) {
		Optional<Seller> entity = repository.findById(id);

		if (!entity.isPresent()) {
			throw new SellerException("Seller not exists");
		}

		return SellerResponse.of(entity.get());
	}

	private String registration(TypeContract typeContract) {
		Optional<Seller> last = repository.findTopByOrderByIdDesc();

        return last.map(seller -> SellerUtil.generateRegistration(seller.getId(), typeContract)).orElseGet(() -> SellerUtil.generateRegistration(1L, typeContract));
    }

}