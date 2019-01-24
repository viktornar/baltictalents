package lt.baltictalents.lessons910.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.baltictalents.lessons910.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}