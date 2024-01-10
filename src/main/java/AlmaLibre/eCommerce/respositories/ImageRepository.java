package AlmaLibre.eCommerce.respositories;

import AlmaLibre.eCommerce.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ImageRepository extends JpaRepository<Image,Long> {

}
