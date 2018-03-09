package com.su.repository;

import com.su.bo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author suyupeng
 */
public interface CategoryRepository extends JpaRepository<Category, String>, PagingAndSortingRepository<Category, String>, JpaSpecificationExecutor<Category> {
    /**
     * 根据id获取分类
     * @param id     id
     * @return       分类
     */
    Category findById(String id);
}
