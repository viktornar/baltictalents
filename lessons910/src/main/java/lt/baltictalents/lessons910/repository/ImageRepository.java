package lt.baltictalents.lessons910.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.baltictalents.lessons910.model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

}