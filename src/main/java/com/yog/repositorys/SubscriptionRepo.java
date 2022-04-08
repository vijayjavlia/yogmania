package com.yog.repositorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yog.modals.Subscription;


@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Integer>{

}

	
	
