package br.com.brunobrasilweb.api.repository;

import br.com.brunobrasilweb.api.entity.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long>, JpaSpecificationExecutor<Filial> {

}
