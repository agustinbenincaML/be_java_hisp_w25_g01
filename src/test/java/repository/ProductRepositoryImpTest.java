package repository;

import com.example.be_java_hisp_w25_g01.entity.Product;
import com.example.be_java_hisp_w25_g01.repository.IProductRepository;
import com.example.be_java_hisp_w25_g01.repository.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryImpTest {

    IProductRepository productRepository = new ProductRepositoryImpl();

    @Test
    void findById(){
        Integer id = 2;

        Product product = productRepository.findById(id).get();

        assertEquals("Mouse inalámbrico", product.getProductName());
    }
}
