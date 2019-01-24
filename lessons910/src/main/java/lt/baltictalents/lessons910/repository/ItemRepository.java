package lt.baltictalents.lessons910.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.baltictalents.lessons910.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}