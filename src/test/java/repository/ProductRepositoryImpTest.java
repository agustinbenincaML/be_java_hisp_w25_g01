package repository;

import com.example.be_java_hisp_w25_g01.entity.Product;
import com.example.be_java_hisp_w25_g01.repository.IProductRepository;
import com.example.be_java_hisp_w25_g01.repository.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ProductRepositoryImpTest {

    IProductRepository productRepository = new ProductRepositoryImpl();

    @Test
    void findByIdOK(){
        Integer id = 1;
        Product expectedProduct= new Product(1,"Silla Gamer", "Gamer", "Razer", "Red & Black", "Special Edition");

        Product currentProduct = productRepository.findById(id).get();

        Assertions.assertEquals(currentProduct, expectedProduct);
    }
}
