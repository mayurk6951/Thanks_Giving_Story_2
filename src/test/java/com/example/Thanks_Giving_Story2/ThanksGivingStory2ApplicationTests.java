package com.example.Thanks_Giving_Story2;

import com.example.Thanks_Giving_Story2.Entity.ObjectFrame;
import com.example.Thanks_Giving_Story2.Entity.Object_registry;
import com.example.Thanks_Giving_Story2.Repository.ObjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ThanksGivingStory2ApplicationTests {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MockMvc mocmvc;

	@MockBean
	ObjectRepository objrepo;

	@Before
	public void setup() {

	}

	@Test
	public void contextLoads() {
	}
	/*@Test
	public void test_getObject() throws Exception{
		ObjectFrame objf = new ObjectFrame();
		when(objrepo.save(objf)).thenReturn(objf);

//
	}*/

}
