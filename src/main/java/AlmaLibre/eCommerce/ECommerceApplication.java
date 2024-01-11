package AlmaLibre.eCommerce;

import AlmaLibre.eCommerce.models.*;
import AlmaLibre.eCommerce.respositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository,
									  ProductRepository productRepository,
									  MessageRepository messageRepository,
									  BuyOrderRepository buyOrderRepository,
									  ImageRepository imageRepository

	){
		return (args)->{



			Client client1 =new Client("Manuel","Gamarra","3412475871", passwordEncoder.encode("admin"), "almalibre@gmail.com");
			client1.setAdmin(true);
			clientRepository.save(client1);

			Client client2 =new Client("Georgina","Aguer","34135353554",passwordEncoder.encode("123"),"geo@gmail.com");
			client2.setAdmin(false);
			clientRepository.save(client2);

			Product product1=new Product("Remera unisex",4500.0,"jasdjajdssadasd",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/48XpWD0/remera.jpg");
			productRepository.save(product1);

			Product product2=new Product("Kit de Mate",3800.0,"Mate+Azucarera+Yerbera",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/7bCSVt1/kitmate.jpg");
			product2.setFeatured(true);
			productRepository.save(product2);
			Product product3=new Product("Jarro Termico",5200.0,"500 ml dise;o personalizado",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/1G0JZFL/jarrotermico.jpg");

			productRepository.save(product3);
			// ORDENES DE COMPRA DE PRUEBA
			Product prod1 = productRepository.findById(1L).orElse(null);
			BuyOrder order1 = new BuyOrder(LocalDateTime.now(),22232);
			order1.addProduct(prod1);
			order1.setState(BuyOrderState.FINALIZADO);
			buyOrderRepository.save(order1);
			System.out.println(order1.toString());

			Message message1 = new Message("Santiaog","Santi@gmail.com","jksadkjaskdjkajsdkj",LocalDateTime.now());
			messageRepository.save(message1);
			Message message2 = new Message("georgina","geo@gmail.com","ssss", LocalDateTime.now());
			messageRepository.save(message2);
			Message message3 = new Message("manuel","manu@gmail.com","2222", LocalDateTime.now());
			message3.setRead(true);
			messageRepository.save(message3);

			Image image= new Image();
			image.setImage1("https://i.ibb.co/fqpp1Zg/banner1.jpg\" alt=\"banner1\" border=\"0");
			image.setImage2("https://i.ibb.co/jbzFdkd/banner2.jpg\" alt=\"banner2\" border=\"0");
			image.setImage3("https://i.ibb.co/hZW3LwZ/banner3.jpg\" alt=\"banner3\" border=\"0");
			imageRepository.save(image);






		};

	}


}
