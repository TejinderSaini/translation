package com.alphaseq.translation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.alphaseq.translation.db.EntryRepository;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TranslationModuleApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EntryRepository entryRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		entryRepository.deleteAll();
	}
	
	/*@Test
	public void shouldReturnRepositoryIndex() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				jsonPath("$._links.language").exists());
	}*/
	
	@Test
	public void shouldCreateEntity() throws Exception {

		mockMvc.perform(post("/language").contentType(MediaType.APPLICATION_JSON).content(
				"{\"key\":\"TEST_MAIN3\",\"organizations\":[{\"name\":\"Test Organization\",\"iconURL\":\"\",\"localeValueList\":[{\"value\":\"Main Test translation String(US)\",\"locale\":{\"code\":\"eng-us\",\"label\":\"English (US)\",\"nativeLabel\":\"English (US)\",\"isRTL\":false}}]}]}")).andExpect(
						status().isOk());
	}

}
