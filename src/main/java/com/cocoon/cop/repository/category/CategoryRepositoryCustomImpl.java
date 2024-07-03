package com.cocoon.cop.repository.category;

import com.cocoon.cop.dto.CategoryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.cocoon.cop.domain.main.QCategory.category;

public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        return queryFactory
                .select(
                        Projections.constructor(
                                CategoryDto.class,
                                category.id.as("categoryId"),
                                category.categoryName.as("categoryName")
                        )
                ).from(category)
                .fetch();
    }
}
