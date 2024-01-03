package AlmaLibre.eCommerce;

import AlmaLibre.eCommerce.models.Client;
import AlmaLibre.eCommerce.models.Message;
import AlmaLibre.eCommerce.models.Product;
import AlmaLibre.eCommerce.models.ProductType;
import AlmaLibre.eCommerce.respositories.ClientRepository;
import AlmaLibre.eCommerce.respositories.MessageRepository;
import AlmaLibre.eCommerce.respositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, ProductRepository productRepository, MessageRepository messageRepository){
		return (args)->{

			Client client1 =new Client("Santiaog","Gamarra","3412475871","123","santi@gmail.com");
			clientRepository.save(client1);
			Client client2 =new Client("Georgina","Aguer","34135353554","123","geo@gmail.com");
			clientRepository.save(client2);

			Product product1=new Product("Remera unisex",4500.0,"jasdjajdssadasd",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/48XpWD0/remera.jpg");
			productRepository.save(product1);

			Product product2=new Product("Kit de Mate",3800.0,"Mate+Azucarera+Yerbera",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/7bCSVt1/kitmate.jpg");
			product2.setFeatured(true);
			productRepository.save(product2);
			Product product3=new Product("Jarro Termico",5200.0,"500 ml dise;o personalizado",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/1G0JZFL/jarrotermico.jpg");

			productRepository.save(product3);

			Message message1 = new Message("Santiaog","Santi@gmail.com","jksadkjaskdjkajsdkj",LocalDateTime.now());
			messageRepository.save(message1);
			Message message2 = new Message("georgina","geo@gmail.com","ssss", LocalDateTime.now());
			messageRepository.save(message2);
			Message message3 = new Message("manuel","manu@gmail.com","2222", LocalDateTime.now());
			message3.setRead(true);
			messageRepository.save(message3);




		};

	}


}
