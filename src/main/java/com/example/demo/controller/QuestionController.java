package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.QuestionDto;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public HttpEntity<?> getAll() {

        ApiResponse apiResponse = questionService.getAll();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse apiResponse = questionService.getOne(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody QuestionDto questionDto) {
        ApiResponse apiResponse = questionService.saveOrEdit(questionDto);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/edit")
    public HttpEntity<?> edit(@RequestBody QuestionDto questionDto) {
        ApiResponse apiResponse = questionService.saveOrEdit(questionDto);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = questionService.delete(id);
        return ResponseEntity.ok(apiResponse);
    }
}
