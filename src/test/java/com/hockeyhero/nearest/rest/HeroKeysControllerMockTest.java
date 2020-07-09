package com.hockeyhero.nearest.rest;

import org.junit.jupiter.api.BeforeEach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hockeyhero.nearest.domain.HeroKeys;
import com.hockeyhero.nearest.exception.HeroKeysException;
import com.hockeyhero.nearest.exception.RestExceptionHandler;
import com.hockeyhero.nearest.service.HeroKeysService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class HeroKeysControllerMockTest {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(HeroKeysControllerMockTest.class);
	
	private MockMvc mvc; 

	@Mock 
	private HeroKeysService heroKeysService;
	
	@InjectMocks
	private HeroKeysController heroKeysController; 
	
	// this object will be magically initialized by the initFields
	// method below
	private JacksonTester<HeroKeys> jsonHeroKeys; 
	
	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper()); 
		mvc = MockMvcBuilders.standaloneSetup(heroKeysController)
				.setControllerAdvice(new RestExceptionHandler())
				.build();
	}
	
	// create - good case - can create when id does not exist
	
	@Test
	public void canCreateWhenIdDoesNotExist() throws Exception {
		// given 
		given(heroKeysService.createHeroKeys(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)))
			.willReturn(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5));
		
		// when
		MockHttpServletResponse response = mvc.perform(
				post("/herokeys/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonHeroKeys.write(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)).getJson()))
				.andReturn().getResponse(); 
		
		// then 
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonHeroKeys.write(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)).getJson()
            );
	}
	
	// create - bad case - id exists
	
	@Test
	public void canCreateWhenIdDoesExist() throws Exception {
        // given
        given(heroKeysService.createHeroKeys(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)))
                .willThrow(new HeroKeysException("Entity already exists: 1"));
		
		// when
		MockHttpServletResponse response = mvc.perform(
				post("/herokeys/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonHeroKeys.write(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)).getJson()))
				.andReturn().getResponse(); 
		
        // then
		LOGGER.info("response :" + response.getErrorMessage());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        // assertThat(response.getContentAsString()).isEmpty();        
	}	
	
	// find all - good case - TODO
	
	// find all - bad case - TODO
	
	// find by id - good case
		
	@Test
	public void canRetrieveByIdWhenExists() throws Exception {
		// given 
		given(heroKeysService.findById(1L))
			.willReturn(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5));
		
		// when
		MockHttpServletResponse response = mvc.perform(
				get("/herokeys/1")
					.accept(MediaType.APPLICATION_JSON))
					.andReturn().getResponse(); 
		
		// then 
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonHeroKeys.write(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)).getJson()
            );
	}
	
	// find by id - bad case
	
    @Test
    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
        // given
        given(heroKeysService.findById(1L))
                .willThrow(new HeroKeysException("Entity does not exist: 1"));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/herokeys/1")
                	.accept(MediaType.APPLICATION_JSON))
                	.andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        //assertThat(response.getContentAsString()).isEmpty();
    }	
    
    // update - good case
    
	@Test
	public void canUpdateWhenIdDoesExist() throws Exception {
		// given 
		given(heroKeysService.updateHeroKeys(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)))
			.willReturn(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5));
		
		// when
		MockHttpServletResponse response = mvc.perform(
				put("/herokeys/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonHeroKeys.write(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)).getJson()))
				.andReturn().getResponse(); 
		
		// then 
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonHeroKeys.write(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)).getJson()
            );
	}
    
    // update - bad case
	
	@Test
	public void canUpdateWhenIdDoesNotExist() throws Exception {
        // given
        given(heroKeysService.updateHeroKeys(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)))
                .willThrow(new HeroKeysException("Entity already exists: 1"));
		
		// when
		MockHttpServletResponse response = mvc.perform(
				put("/herokeys/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonHeroKeys.write(new HeroKeys(1L, false, 42.982401, -81.272661, 15, 20, 5)).getJson()))
				.andReturn().getResponse(); 
		
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
//        assertThat(response.getContentAsString()).isEmpty();        
	}	
		
	// delete - good case - TODO
	
	@Test
	public void canDeleteWhenIdDoesExist() throws Exception {
		// given 
		given(heroKeysService.deleteById(1L))
			.willReturn(1L);
		
		// when
		MockHttpServletResponse response = mvc.perform(
				delete("/herokeys/1"))
				.andReturn().getResponse(); 
		
		// then 
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEmpty();  
	}
	
	// delete - bad case - TODO
	
	@Test
	public void canDeleteWhenIdDoesNotExist() throws Exception {
        // given
        given(heroKeysService.deleteById(1L))
                .willThrow(new HeroKeysException("Entity does not exist: 1"));
		
		// when
		MockHttpServletResponse response = mvc.perform(
				delete("/herokeys/1"))
				.andReturn().getResponse(); 
		
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        //assertThat(response.getContentAsString()).isEmpty();        
	}	
}
