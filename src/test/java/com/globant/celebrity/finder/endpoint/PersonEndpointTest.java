package com.globant.celebrity.finder.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;
import com.globant.celebrity.finder.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonEndpoint.class)
public class PersonEndpointTest {

    @MockBean
    PersonService service;

    @Autowired
    private MockMvc mockMvc;

    private List<Person> people;
    private ObjectMapper mapper;
    private Person charles;
    private Person valtteri;
    private Person lewis;

    @Before
    public void setup(){
        people = new LinkedList<>();
        mapper = new ObjectMapper();
        charles = new Person(1,"Charles");
        valtteri = new Person(2, "Valtteri");
        lewis = new Person(3, "Lewis");
        people.add(charles);
        people.add(valtteri);
        people.add(lewis);
    }

    @Test
    public void getAllReturnsCollection() throws Exception {
        given(service.getAll()).willReturn(people);

        mockMvc.perform(get("/persons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveReturnPersonObject() throws Exception {
        Person daniel = new Person(4, "Daniel");
        given(service.registerPerson(daniel)).willReturn(daniel);

        mockMvc.perform(post("/persons")
                .content(mapper.writeValueAsBytes(daniel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(daniel.getName()));
    }

    @Test
    public void findByIdOneReturnCharles() throws Exception {
        given(service.findById(1)).willReturn(new Person(1, "Charles"));

        mockMvc.perform(get("/persons/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charles"));
    }

    @Test
    public void getPersonRelationsContainsCollection() throws Exception {
        Person daniel = new Person(4, "Daniel");
        daniel.setPersonSet(new HashSet<>(people));

        given(service.findById(4)).willReturn(daniel);

        mockMvc.perform(get("/persons/id/4/relations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Charles")))
                .andExpect(jsonPath("$[1].name", is("Valtteri")))
                .andExpect(jsonPath("$[2].name", is("Lewis")));
    }

    @Test
    public void saveRelationIsSuccessful() throws Exception {
        Relation relation = new Relation(charles, lewis);

        given(service.establishRelation(relation)).willReturn(relation);

        mockMvc.perform(post("/persons/relations")
                .content(mapper.writeValueAsBytes(relation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void thereIsNoCelebrityWithoutRelation() throws Exception {
        given(service.getAll()).willReturn(people);

        mockMvc.perform(get("/persons/celebrities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void lewisIsCelebrity() throws Exception {
        charles.getPersonSet().add(lewis);
        valtteri.getPersonSet().add(lewis);
        given(service.getAll()).willReturn(people);

        mockMvc.perform(get("/persons/celebrities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is("Lewis")));


    }

    @Test
    public void lewisIsNotCelebrityIfThereIsANewPerson() throws Exception {
        charles.getPersonSet().add(lewis);
        valtteri.getPersonSet().add(lewis);
        people.add(new Person(4, "Daniel"));
        given(service.getAll()).willReturn(people);

        mockMvc.perform(get("/persons/celebrities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void lewisTurnsAgainCelebrityIfNewPersonKnowsHim() throws Exception {
        charles.getPersonSet().add(lewis);
        valtteri.getPersonSet().add(lewis);
        Person daniel = new Person(4, "Daniel");
        daniel.getPersonSet().add(lewis);
        people.add(daniel);
        given(service.getAll()).willReturn(people);

        mockMvc.perform(get("/persons/celebrities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is("Lewis")));
    }
}
