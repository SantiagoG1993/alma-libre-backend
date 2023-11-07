package AlmaLibre.eCommerce;

import AlmaLibre.eCommerce.models.Client;
import AlmaLibre.eCommerce.models.Product;
import AlmaLibre.eCommerce.respositories.ClientRepository;
import AlmaLibre.eCommerce.respositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, ProductRepository productRepository){
		return (args)->{

			Client client1 =new Client("Santiaog","Gamarra","3412475871");
			clientRepository.save(client1);

			Product product1=new Product("Remera Unisex","Talle del 4 a1 16",4500.0);
			productRepository.save(product1);

			Product product2=new Product("Kit de Mate","Mate+Azucarera+Yerbera",3800.0);
			productRepository.save(product2);


		};

	}


}
