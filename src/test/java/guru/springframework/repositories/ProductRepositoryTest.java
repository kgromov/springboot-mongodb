package guru.springframework.repositories;

import guru.springframework.config.MongoTestConfig;
import guru.springframework.domain.Product;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
@Import(MongoTestConfig.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        Product product = new Product();
        product.setId(new ObjectId());
        product.setDescription("Init Product");
        product.setPrice(BigDecimal.ONE);

        productRepository.save(product);
    }

    @Test
    public void testFindAll() {
        System.out.println("********************** testFindAll **********************");
        Iterable<Product> products = productRepository.findAll();
        for (Product p : products) {
            System.out.println(p.toString());
        }
        System.out.println("*********************************************************");
    }
}