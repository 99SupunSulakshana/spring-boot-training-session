package com.springboottests.practice_session.repo;

import com.springboottests.practice_session.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

   boolean existsByCustomerName(String customerName);

   boolean existsByCustomerId(String customerId);

   Customer getReferenceByCustomerId(String customerId);

   List<Customer> findAllByActiveEquals(boolean status);

   void deleteByCustomerId(String customerId);

}
