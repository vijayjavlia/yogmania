package com.yog.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yog.modals.Videos;

@Repository
public interface VideosRepo extends JpaRepository<Videos, Integer>{

	
	Videos findByVideoId(String id);
}

