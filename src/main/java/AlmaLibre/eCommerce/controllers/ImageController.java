package AlmaLibre.eCommerce.controllers;

import AlmaLibre.eCommerce.models.Image;
import AlmaLibre.eCommerce.respositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ImageController {
    @Autowired
    ImageRepository imageRepository;
    @GetMapping("/images")
    public Image getImages(){
        Image images= imageRepository.findById(1L).orElse(null);
        return images;
    }
    @PatchMapping("/images/upload")
    public ResponseEntity<Object> uploadImage(@RequestBody Map<String, String> imageData) {
        String image1 = imageData.get("image1");
        String image2 = imageData.get("image2");
        String image3 = imageData.get("image3");

        System.out.println("image1 " + image1 + " image2 " + image2 + " image 3" + image3);

        Image image = imageRepository.findById(1L).orElse(null);

        if (image1 != null) {
            image.setImage1(image1);
        }
        if (image2 != null) {
            image.setImage2(image2);
        }
        if (image3 != null) {
            image.setImage3(image3);
        }

        imageRepository.save(image);
        return new ResponseEntity<>("Imágenes cargadas correctamente", HttpStatus.OK);
    }

}
