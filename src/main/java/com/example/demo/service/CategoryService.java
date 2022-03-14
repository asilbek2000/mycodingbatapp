package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CategoryDto;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.P_LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    P_LanguageRepo p_languageRepo;

    public ApiResponse getAll() {
        List<Category> all = categoryRepo.findAll();
        return new ApiResponse(true,"All",all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Category> optionalUser = categoryRepo.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse(false,"Not found");
        }
        return new ApiResponse(true,"By id",optionalUser.get());
    }

    public ApiResponse delete(Integer id) {
        try {
            categoryRepo.deleteById(id);
            return new ApiResponse(true,"Deleted");
        }
        catch (Exception e) {
            return new ApiResponse(false,"Not found");
        }
    }

    public ApiResponse saveOrEdit(CategoryDto categoryDto) {
        Category category=new Category();
        if (categoryDto.getId()!=null) {
            category=categoryRepo.getById(categoryDto.getId());
        }
        category.setName(categoryDto.getName());
        category.setP_language(p_languageRepo.getById(categoryDto.getP_languageId()));
        categoryRepo.save(category);
        return new ApiResponse(true,categoryDto.getId()!=null?"Edited":"Saved");
    }
}
