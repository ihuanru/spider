package com.su.repository;

import com.su.bo.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author suyupeng
 */
public interface NovelRepository extends JpaRepository<Novel, String>, PagingAndSortingRepository<Novel, String>, JpaSpecificationExecutor<Novel> {
}
