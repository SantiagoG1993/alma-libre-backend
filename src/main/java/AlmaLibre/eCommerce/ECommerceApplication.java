package AlmaLibre.eCommerce;
import AlmaLibre.eCommerce.models.*;
import AlmaLibre.eCommerce.respositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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

			Product product1=new Product("Remera unisex",8500.0,"jasdjajdssadasd",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/TTTQX1r/remera-removebg-preview.png");
			product1.setCategory("Remeras");
			productRepository.save(product1);

			Product product2=new Product("Kit de Mate",7800.0,"Mate+Azucarera+Yerbera",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/mcFYtgn/kitmate-removebg-preview.png");
			product2.setCategory("Kit de mate");
			product2.getOtherImages().add("https://i.ibb.co/cYrDN8M/taza4.webp");
			product2.setFeatured(true);
			productRepository.save(product2);
			Product product3=new Product("Jarro Termico",5200.0,"500 ml dise;o personalizado",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/0hS0kSc/jarrotermico-removebg-preview.png");
			product3.setCategory("Tazas");
			product3.getOtherImages().add("https://i.ibb.co/bQ54w47/taza1.jpg");
			product3.getOtherImages().add("https://i.ibb.co/cQchqmz/taaza2.jpg");
			product3.getOtherImages().add("https://i.ibb.co/8PrQpkC/taza3.jpg");
			product3.getOtherImages().add("https://i.ibb.co/cYrDN8M/taza4.webp");
			productRepository.save(product3);
			Product product4 = new Product("Body personalizado",12000.0,"Body con dise;o personalizado",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/FYjs4cx/body-removebg-preview.png");
			product4.setCategory("Bodys");
			productRepository.save(product4);
			Product product5 = new Product("Taza personalizada",5000.0,"Dise;o personalizado para negocios",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/b3BTJj7/tazas2-removebg-preview.png");
			product5.setCategory("Tazas");
			productRepository.save(product5);
			Product product6 = new Product("Tarjetas de comunion",2000.0,"Tarjetas de comunion personalizadas",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/1qWwSLN/comunion-removebg-preview.png");
			product6.setCategory("Tarjetas");
			productRepository.save(product6);
			Product product7 = new Product("Set Jardin personalizado",8000.0,"Set de jardin personalizado",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/8gZHLqR/set-Jardin.jpg");
			product7.setCategory("Set de jardin");
			productRepository.save(product7);
			Product product8 = new Product("Llaveros Spiderman",3000.0,"LLaveros dise;o personalizado",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/t4q5L40/llaveros-sin-fondo.png");
			product8.setCategory("Llaveros");
			productRepository.save(product8);
			Product product9 = new Product("Cantimplora con caja",1000.0,"Caja y cantimplora ambos dise;os personalizados",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/qd0yZcs/Cantimplora-removebg-preview.png");
			product9.setCategory("Cantimploras");
			productRepository.save(product9);
			Product product10= new Product("Chopps personalizados",12000.0,"Chopps 750ml",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/xs16zNb/Chopps-removebg-preview.png");
			product10.setCategory("Chopps");
			productRepository.save(product10);
			Product product11 = new Product("Set de jardin Barbie",8000.0,"Tupper y botella con dise;o de barbie",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/VYbYKDG/barby-removebg-preview.png");
					product11.setCategory("Set de jardin");
					productRepository.save(product11);
			Product product12 = new Product("Box dia de la madre",10000.0,"Taza + Chocolate + 4 fotos polaroid",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/51ycQZs/dia-de-la-amdre.png");
			product12.setCategory("Dia de la madre");
			productRepository.save(product12);
			Product product13 = new Product("Mochila",1500.0,"Mochila para jardin 20x30",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/gTKQyQW/mochila-removebg-preview.png");
			product13.setCategory("Mochilas");
			productRepository.save(product13);
			Product product14 = new Product("Taza y cantimplora Merlina",5000.0,"asdasdasdasdasdasd",ProductType.CUSTOM_PRODUCT,"https://i.ibb.co/LrFxhsK/Whats-App-Image-2024-01-24-at-22-28-03-f43a5670-removebg-preview.png");
			product14.setCategory("Tazas");
			productRepository.save(product14);

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
