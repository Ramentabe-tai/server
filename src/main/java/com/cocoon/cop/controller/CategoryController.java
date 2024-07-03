package com.cocoon.cop.controller;

import com.cocoon.cop.dto.CategoryDto;
import com.cocoon.cop.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/api/categories")
    public ResponseEntity<List<CategoryDto>> categories() {

        return ResponseEntity.ok().body(categoryRepository.findAllCategories());
    }

}
