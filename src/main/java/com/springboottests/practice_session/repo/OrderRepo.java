package com.springboottests.practice_session.repo;

import com.springboottests.practice_session.dto.queryinterfaces.OrderDetailsInterface;
import com.springboottests.practice_session.dto.response.OrderDetailsGetResponseDTO;
import com.springboottests.practice_session.entity.Item;
import com.springboottests.practice_session.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order, String> {

//    @Query(value = "SELECT o.* FROM orders o INNER JOIN customer c ON o.customer_id = c.customer_id WHERE c.customer_id = :customerId", nativeQuery = true)
//    List<OrderDetailsInterface> getAllOrderDetails(Pageable pageable);

    @Query(value = """
                SELECT 
                    c.customer_name AS customerName, 
                    c.customer_address AS customerAddress, 
                    JSON_ARRAYAGG(c.contact_number) AS contactNumber, 
                    o.order_date AS date, 
                    o.total AS total 
                FROM customer c 
                INNER JOIN orders o ON c.customer_id = o.customer_id
                GROUP BY c.customer_id, o.order_id
            """, nativeQuery = true)
    Page<OrderDetailsInterface> getAllOrderDetails(Pageable pageable);
}
