package com.example.Thanks_Giving_Story2;

import com.example.Thanks_Giving_Story2.Entity.Characterroom;
import com.example.Thanks_Giving_Story2.Entity.ObjectFrame;
import com.example.Thanks_Giving_Story2.Entity.Object_registry;
import com.example.Thanks_Giving_Story2.Repository.ObjectRepository;
import com.example.Thanks_Giving_Story2.Repository.RoomRepository;
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

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

	@MockBean
	RoomRepository roomrepo;

	@Before
	public void setup() {

	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void test_getObject() throws Exception {
		ObjectFrame objf = new ObjectFrame();
		when(objrepo.save(objf)).thenReturn(objf);
		mocmvc.perform(MockMvcRequestBuilders.get("/object/generate/abc/Warrior"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print());

		verify(objrepo, times(1)).save(isA(ObjectFrame.class));
		verifyNoMoreInteractions(objrepo);

	}


	@Test
	public void test_moveRoom() throws Exception {

		Characterroom room = new Characterroom();
		room.setRoomid(4L);
		int[] arr = new int[3];
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		room.setExits(arr);

		ObjectFrame objf = new ObjectFrame();
		objf.setId(1L);
		objf.setLocation(4);


		when(objrepo.findById(1L))
				.thenReturn(Optional.of(objf));
		when(roomrepo.findById(4L)).thenReturn(Optional.of(room));

		mocmvc.perform(MockMvcRequestBuilders.post("movecharacter/1/to/5")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		verify(objrepo, times(1)).save(isA(ObjectFrame.class));

	}

	@Test
	public void test_pickupitem() throws Exception {
		ObjectFrame objf = new ObjectFrame();
		when(objrepo.save(objf)).thenReturn(objf);
		mocmvc.perform(MockMvcRequestBuilders.post("/inventory/pickup/1/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());

		verify(objrepo, times(1)).save(isA(ObjectFrame.class));

	}

	@Test
	public void test_dropitem() throws Exception {
		ObjectFrame objf = new ObjectFrame();
		when(objrepo.save(objf)).thenReturn(objf);
		mocmvc.perform(MockMvcRequestBuilders.post("/inventory/drop/1/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());

		verify(objrepo, times(1)).save(isA(ObjectFrame.class));

	}

	@Test
	public void test_attack() throws Exception{
		ObjectFrame obj1 = new ObjectFrame();
		ObjectFrame obj2 = new ObjectFrame();
		obj1.setId(1L);
		obj1.setHitpoints(10);
		obj2.setId(2L);
		obj2.setHitpoints(15);
		when(objrepo.findById(1L))
				.thenReturn(Optional.of(obj1));
		when(objrepo.findById(2L))
				.thenReturn(Optional.of(obj2));
		mocmvc.perform(MockMvcRequestBuilders.post("/battle/1/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		verify(objrepo, times(1)).save(isA(ObjectFrame.class));

	}
}
