package com.cocoon.cop.repository.category;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.domain.main.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

    Optional<Category> findByCategoryName(String category);
}
