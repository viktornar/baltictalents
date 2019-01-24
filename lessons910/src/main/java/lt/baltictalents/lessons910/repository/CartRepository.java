package lt.baltictalents.lessons910.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.baltictalents.lessons910.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}