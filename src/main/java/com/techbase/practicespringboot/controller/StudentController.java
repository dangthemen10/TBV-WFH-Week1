package com.techbase.practicespringboot.controller;

import com.techbase.practicespringboot.dto.SearchFormDTO;
import com.techbase.practicespringboot.dto.StudentDTO;
import com.techbase.practicespringboot.entity.StudentEntity;
import com.techbase.practicespringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;


@Controller
public class StudentController {
    private static final Integer PAGE_NO = 1;
    private static final Integer PAGE_SIZE = 5;

    @Autowired
    private StudentService studentService;

    @GetMapping({"/student/index", "/"})
    public String index(SearchFormDTO searchFormDTO, Model model,
                                @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "size", required = false, defaultValue = "5") Integer pageSize,
                                @RequestParam(name = "fname", required = false, defaultValue = "") String fname,
                                @RequestParam(name = "fage", required = false, defaultValue = "") String fage,
                                @RequestParam(name = "fdate", required = false, defaultValue = "") String fdate) {
        Page<StudentDTO> page = studentService.search(searchFormDTO, pageNo, pageSize);
        Integer currentPage = page.getNumber() + 1;
        StringBuilder stringBuilder = new StringBuilder().append("?fname=").append(fname).append("&fage=").append(fage)
                .append("&fdate=").append(fdate);
        model.addAttribute("url", stringBuilder);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("page", page);
        return "list";
    }

    @GetMapping("/student/search")
    public String abc(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNo,
                      @RequestParam(name = "size", required = false, defaultValue = "5") Integer pageSize,
                      @RequestParam(name = "fname", required = false, defaultValue = "") String fname,
                      @RequestParam(name = "fage", required = false, defaultValue = "") String fage,
                      @RequestParam(name = "fdate", required = false, defaultValue = "") String fdate,
                      Model model, SearchFormDTO searchFormDTO){

        Page<StudentDTO> page = studentService.search(searchFormDTO, pageNo, pageSize);
        Integer currentPage = page.getNumber() + 1;
        StringBuilder stringBuilder = new StringBuilder()
                .append("?fname=").append(fname)
                .append("&fage=").append(fage)
                .append("&fdate=").append(fdate);
        model.addAttribute("url", stringBuilder);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("page", page);
        return "table";
    }

    @GetMapping("/download/file")
    public ResponseEntity<Resource> downloadFile() {
        InputStreamResource resource = new InputStreamResource(studentService.writeDataToCsv());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customers.tsv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }

    @GetMapping("/student/create")
    public String create(Model model) {
        model.addAttribute("student", new StudentEntity());
        return "form";
    }

    @GetMapping("/student/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.findOneStudent(id));
        return "form";
    }

    @PostMapping("/student/save")
    public String save(@Validated StudentEntity studentEntity, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        studentService.saveStudent(studentEntity);
        redirect.addFlashAttribute("success", "Saved student successfully!");
        return "redirect:/student/index";
    }

    @GetMapping("/student/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        StudentEntity student = studentService.findOneStudent(id);
        studentService.deleteStudent(student);
        redirect.addFlashAttribute("success", "Deleted student successfully!");
        return "redirect:/student/index";
    }

    @GetMapping("/student/auto-complete")
    public ResponseEntity<Set<String>> allName(){
        System.out.println(studentService.allName());
        return ResponseEntity.ok(studentService.allName());
    }
}
