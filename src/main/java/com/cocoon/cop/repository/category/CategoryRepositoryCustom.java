package com.cocoon.cop.repository.category;

import com.cocoon.cop.dto.CategoryDto;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<CategoryDto> findAllCategories();
}
