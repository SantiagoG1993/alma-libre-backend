package AlmaLibre.eCommerce.respositories;

import AlmaLibre.eCommerce.models.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long>
{
}
