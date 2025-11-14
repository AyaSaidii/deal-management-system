package com.ayasaidi.deals.repository;

import com.ayasaidi.deals.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal,String> {

}
