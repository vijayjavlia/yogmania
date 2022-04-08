package com.yog.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yog.modals.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
