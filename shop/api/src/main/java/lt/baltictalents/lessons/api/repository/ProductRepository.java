package lt.baltictalents.lessons.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.baltictalents.lessons.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}