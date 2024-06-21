package br.com.brunobrasilweb.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.brunobrasilweb.api.core.enums.TypeContract;
import br.com.brunobrasilweb.api.core.exception.SellerException;
import br.com.brunobrasilweb.api.dto.FilialResponse;
import br.com.brunobrasilweb.api.dto.SellerRequest;
import br.com.brunobrasilweb.api.dto.SellerResponse;
import br.com.brunobrasilweb.api.entity.Filial;
import br.com.brunobrasilweb.api.entity.Seller;
import br.com.brunobrasilweb.api.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

	@Mock
	private SellerRepository repository;

	@InjectMocks
	private SellerService service;

	private Seller seller;

	private Seller last;

	private SellerRequest request;

	private SellerResponse response;

	@BeforeEach
	void setUp() {
		seller = Seller.of(2L, "2-PJ", "Jose", LocalDate.now(), "04293373000159", "jose@teste.com", TypeContract.PJ,
				new Filial());
		request = SellerRequest.of("Jose", LocalDate.now(), "04293373000159", "jose@teste.com",
				TypeContract.PJ.getValue(), 1L);
		response = SellerResponse.of(2L, "2-PJ", "Jose", LocalDate.now(), "04293373000159", "jose@teste.com",
				TypeContract.PJ, new FilialResponse());
		last = Seller.of(1L, "1-PJ", "Jose Leite", LocalDate.now(), "04293373000159", "jose@teste.com", TypeContract.PJ,
				new Filial());
	}

	@Test
	void testPageable() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Seller> sellerPage = new PageImpl<>(Arrays.asList(seller));
		when(repository.findAll(pageable)).thenReturn(sellerPage);

		Page<SellerResponse> result = service.pageable(pageable);

		assertEquals(1, result.getTotalElements());
		verify(repository, times(1)).findAll(pageable);
	}

	@Test
	void testCreate() {
		when(repository.save(any())).thenReturn(seller);
		when(repository.findTopByOrderByIdDesc()).thenReturn(Optional.of(last));

		SellerResponse result = service.create(request);

		assertNotNull(result);
		assertEquals(response.getRegistration(), result.getRegistration());
		verify(repository, times(1)).save(any());
	}

	@Test
	void testUpdate() {
		when(repository.findById(1L)).thenReturn(Optional.of(seller));
		when(repository.save(any())).thenReturn(seller);

		SellerResponse result = service.update(1L, request);

		assertNotNull(result);
		assertEquals(response.getRegistration(), result.getRegistration());
		verify(repository, times(1)).findById(1L);
		verify(repository, times(1)).save(any());
	}

	@Test
	void testUpdateSellerNotFound() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		SellerException exception = assertThrows(SellerException.class, () -> {
			service.update(1L, request);
		});

		assertEquals("Seller with id 1 does not exist", exception.getMessage());
		verify(repository, times(1)).findById(1L);
		verify(repository, times(0)).save(any(Seller.class));
	}

	@Test
	void testDelete() {
		doNothing().when(repository).deleteById(1L);

		service.delete(1L);

		verify(repository, times(1)).deleteById(1L);
	}

	@Test
	void testById() {
		when(repository.findById(2L)).thenReturn(Optional.of(seller));

		SellerResponse result = service.byId(2L);

		assertNotNull(result);
		assertEquals(response, result);
		verify(repository, times(1)).findById(2L);
	}

}
