package com.example.demo.service;

import com.example.demo.entity.P_Language;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.P_LanguageDto;
import com.example.demo.repo.P_LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class P_LanguageService {

    @Autowired
    P_LanguageRepo p_languageRepo;

    public ApiResponse getAll() {
        List<P_Language> all = p_languageRepo.findAll();
        return new ApiResponse(true,"All",all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<P_Language> optionalUser = p_languageRepo.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse(false,"Not found");
        }
        return new ApiResponse(true,"By id",optionalUser.get());
    }

    public ApiResponse saveOrEdit(P_LanguageDto p_languageDto) {
        P_Language p_language=new P_Language();
        if (p_languageDto.getId()!=null) {
            p_language=p_languageRepo.getById(p_languageDto.getId());
        }
        p_language.setName(p_languageDto.getName());
        p_languageRepo.save(p_language);
        return new ApiResponse(true,p_languageDto.getId()!=null?"Edited":"Saved");
    }

    public ApiResponse delete(Integer id) {
        try {
            p_languageRepo.deleteById(id);
            return new ApiResponse(true,"Deleted");
        }
        catch (Exception e) {
            return new ApiResponse(false,"Not found");
        }
    }
}
