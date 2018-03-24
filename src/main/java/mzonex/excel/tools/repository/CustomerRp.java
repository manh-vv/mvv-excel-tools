package mzonex.excel.tools.repository;

import mzonex.excel.tools.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRp extends JpaRepository<Customer, Integer> {
}
