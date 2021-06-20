package com.techbase.practicespringboot.controller;

import com.techbase.practicespringboot.dto.SearchFormDTO;
import com.techbase.practicespringboot.dto.StudentDTO;
import com.techbase.practicespringboot.entity.StudentEntity;
import com.techbase.practicespringboot.service.StudentService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    private static List<StudentDTO> list;

    private static SearchFormDTO searchFormDTO;
    @Mock
    HttpServletRequest httpServletRequest;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;

    @BeforeAll
    public static void setup() {
        list = new ArrayList<>(List.of(
                new StudentDTO(1L, "pdang", 23, "male", "KienGiang", 4.5, "2021-06-19", "12:23:56"),
                new StudentDTO(2L, "test", 18, "female", "CT", 8.5, "2021-01-20", "10:06:16"),
                new StudentDTO(3L, "bha", 21, "female", "CaMau", 6.5, "2020-11-20", "08:56:16"),
                new StudentDTO(4L, "tdang", 21, "female", "HauGiang", 7.5, "2021-05-20", "10:26:36"),
                new StudentDTO(5L, "nand", 22, "male", "SocTrang", 6.9, "2020-12-20", "07:21:15")
        ));

        searchFormDTO = new SearchFormDTO();
    }

    @AfterAll
    public static void tearDown() {
        list.clear();
        searchFormDTO = null;
    }

    @Test
    @DisplayName("Search url found should render view")
    void testIndexSuccessWhenRenderView() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        Page<StudentDTO> page = new PageImpl<>(list, pageable, list.size());
        Integer currentPage = page.getNumber() + 1;
        when(studentService.search(searchFormDTO, 1, 5)).thenReturn(page);
        mockMvc.perform(get("/student/index")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attribute("currentPage", currentPage))
                .andExpect(model().attribute("page", page));

        verify(studentService, times(1)).search(searchFormDTO, 1, 5);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    @DisplayName("Search url found should render view")
    void testPagingSuccessWhenRenderView() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        Page<StudentDTO> page = new PageImpl<>(list, pageable, list.size());
        Integer currentPage = page.getNumber() + 1;
        when(studentService.search(searchFormDTO, 1, 5)).thenReturn(page);
        mockMvc.perform(get("/student/search")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("table"))
                .andExpect(model().attribute("currentPage", currentPage))
                .andExpect(model().attribute("page", page));

        verify(studentService, times(1)).search(searchFormDTO, 1, 5);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    @DisplayName("Search url not found")
    void testIndexFailWhenRender404View() throws Exception {
        mockMvc.perform(get("/students/index"))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("Create found should render view")
    void testCreateSuccessWhenRenderView() throws Exception {
        mockMvc.perform(get("/student/create")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attribute("student", new StudentEntity()));

    }

    @Test
    @DisplayName("Delete found should render view")
    void testDeleteSuccessWhenRenderView() throws Exception {
        StudentEntity studentEntity = new StudentEntity(1L, "pdang", 23, "male", "KienGiang", 4.5, 20210619, 122356);
        when(studentService.findOneStudent(1L)).thenReturn(studentEntity);
        mockMvc.perform(get("/student/{id}/delete", 1L)).andDo(print())
                .andExpect(redirectedUrl("/student/index"))
                .andExpect(flash().attribute("success", "Deleted student successfully!"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/student/index"));

    }
}
