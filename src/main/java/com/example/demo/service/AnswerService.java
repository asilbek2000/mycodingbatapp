package com.example.demo.service;

import com.example.demo.entity.Answer;
import com.example.demo.payload.AnswerDto;
import com.example.demo.payload.ApiResponse;
import com.example.demo.repo.AnswerRepo;
import com.example.demo.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    QuestionRepo questionRepo;

    public ApiResponse getAll() {
        List<Answer> all = answerRepo.findAll();
        return new ApiResponse(true,"All",all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Answer> optionalUser = answerRepo.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse(false,"Not found");
        }
        return new ApiResponse(true,"By id",optionalUser.get());
    }

    public ApiResponse saveOrEdit(AnswerDto answerDto) {
        Answer answer=new Answer();
        if (answerDto.getId()!=null) {
            answer=answerRepo.getById(answerDto.getId());
        }
        answer.setBody(answerDto.getBody());
        answer.setTrue(answerDto.isTrue());
        answer.setQuestion(questionRepo.getById(answerDto.getQuestionId()));
        return new ApiResponse(true,answerDto.getId()!=null?"Edited":"Saved");
    }

    public ApiResponse delete(Integer id) {
        try {
            answerRepo.deleteById(id);
            return new ApiResponse(true,"Deleted");
        }
        catch (Exception e) {
            return new ApiResponse(false,"Not found");
        }
    }
}
