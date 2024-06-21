package br.com.brunobrasilweb.api.controller;

import br.com.brunobrasilweb.api.core.enums.TypeContract;
import br.com.brunobrasilweb.api.dto.SellerRequest;
import br.com.brunobrasilweb.api.dto.SellerResponse;
import br.com.brunobrasilweb.api.service.SellerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SellerController.class)
public class SellerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SellerService service;

	@Autowired
	private ObjectMapper objectMapper;

	private SellerRequest request;

	private SellerResponse response;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		request = SellerRequest.of("Jose", LocalDate.now(), "04293373000159", "jose@teste.com",
				TypeContract.PJ.getValue(), 1L);
	}

	@Test
	public void testGetPageable() throws Exception {
		Page<SellerResponse> page = new PageImpl<>(Collections.emptyList());
		when(service.pageable(any(Pageable.class))).thenReturn(page);

		mockMvc.perform(get("/seller")).andExpect(status().isOk()).andExpect(jsonPath("$.content").isEmpty());
	}

	@Test
	public void testCreate() throws Exception {
		when(service.create(any(SellerRequest.class))).thenReturn(response);

		mockMvc.perform(
				post("/seller").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk());
	}

	@Test
	public void testCreateInvalid() throws Exception {
		request.setFilialId(null);

		when(service.create(any(SellerRequest.class))).thenReturn(response);

		mockMvc.perform(
				post("/seller").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testUpdate() throws Exception {
		when(service.update(any(Long.class), any(SellerRequest.class))).thenReturn(response);

		mockMvc.perform(
				put("/seller/1").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateInvalid() throws Exception {
		request.setFilialId(null);

		when(service.update(any(Long.class), any(SellerRequest.class))).thenReturn(response);

		mockMvc.perform(
				put("/seller/1").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(delete("/seller/1")).andExpect(status().isOk());
	}

	@Test
	public void testById() throws Exception {
		when(service.byId(any(Long.class))).thenReturn(response);

		mockMvc.perform(get("/seller/1")).andExpect(status().isOk());
	}

}
