package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.QuestionDto;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    CategoryRepo categoryRepo;

    public ApiResponse getAll() {
        List<Question> all = questionRepo.findAll();
        return new ApiResponse(true,"All",all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Question> optionalUser = questionRepo.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse(false,"Not found");
        }
        return new ApiResponse(true,"By id",optionalUser.get());
    }

    public ApiResponse delete(Integer id) {
        try {
            questionRepo.deleteById(id);
            return new ApiResponse(true,"Deleted");
        }
        catch (Exception e) {
            return new ApiResponse(false,"Not found");
        }
    }

    public ApiResponse saveOrEdit(QuestionDto questionDto) {
        Question question=new Question();
        if (questionDto.getId()!=null) {
            question=questionRepo.getById(questionDto.getId());
        }
        question.setBody(questionDto.getBody());
        question.setExample(questionDto.getExample());
        question.setTitle(questionDto.getTitle());
        question.setCategory(categoryRepo.getById(questionDto.getCategoryId()));
        questionRepo.save(question);
        return new ApiResponse(true,questionDto.getId()!=null?"Edited":"Saved");
    }
}
