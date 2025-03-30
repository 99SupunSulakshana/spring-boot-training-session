package com.springboottests.practice_session.repo;


import com.springboottests.practice_session.entity.Item;
import com.springboottests.practice_session.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, String> {

}
