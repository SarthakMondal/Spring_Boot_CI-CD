package com.example.crudtest.crudtest;

import com.example.crudtest.crudtest.Model.StudentModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CrudtestApplicationTests {


        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private RestTemplate restApi;

        String mainUrl = "http://localhost:8080/backend";

        @Test
        @Order(1)
        void addStudentTest() throws Exception {

                String sModel = "{\"name\" : \"Gunjan Ganguly\", \"year\" : \"4th Year\",\"marks\": 82}";

                this.mockMvc
                                .perform(
                                                post(mainUrl + "/addstudent")
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .content(sModel))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.name").value("Gunjan Ganguly"))
                                .andExpect(jsonPath("$.year").value("4th Year"))
                                .andExpect(jsonPath("$.marks").value(82))
                                .andDo(print());
        }

        @Test
        @Order(3)
        void getAllStudentsTest() throws Exception {

                this.mockMvc
                                .perform(
                                                get(mainUrl + "/getstudents"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].name").value("Gunjan Ganguly"))
                                .andExpect(jsonPath("$[0].year").value("4th Year"))
                                .andExpect(jsonPath("$[0].marks").value(82))
                                .andDo(print());
        }

        @Test
        @Order(6)
        public void addStudentIntegrantionTest() throws  Exception{
                final String baseUrl = mainUrl + "/addstudent";
                URI uri = new URI(baseUrl);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("X-RAM-PERSIST", "true");
                httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                StudentModel sModel = new StudentModel();
                sModel.setName("Souvik Sen");
                sModel.setYear("3rd Year");
                sModel.setMarks(60);

                HttpEntity httpEntity = new HttpEntity<>(sModel, httpHeaders);

                ResponseEntity<String> response = restApi.exchange(uri, HttpMethod.POST, httpEntity, String.class);
                System.out.println(response);

                Assertions.assertAll(
                        () -> Assertions.assertEquals(200, response.getStatusCodeValue()),
                        () -> Assertions.assertEquals(sModel, httpEntity.getBody())
                );
        }

        @Test
        @Order(7)
        public void getAllStudentsIntegrantionTest() throws  Exception{
                final String baseUrl = mainUrl + "/getstudents";
                URI uri = new URI(baseUrl);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("X-RAM-PERSIST", "true");
                httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                HttpEntity httpEntity = new HttpEntity<>(httpHeaders);

                ResponseEntity<List> response = restApi.exchange(uri, HttpMethod.GET, httpEntity, List.class);
                System.out.println(response);

                Assertions.assertAll(
                        () -> Assertions.assertEquals(200, response.getStatusCodeValue()),
                        () -> Assertions.assertEquals(response.getBody().size(), 2)
                );
        }

}
