package br.com.brunobrasilweb.api.repository;

import br.com.brunobrasilweb.api.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>, JpaSpecificationExecutor<Seller> {

	Boolean existsByRegistration(String registration);

	Optional<Seller> findTopByOrderByIdDesc();

}
