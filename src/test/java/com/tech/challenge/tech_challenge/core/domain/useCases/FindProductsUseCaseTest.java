package com.tech.challenge.tech_challenge.core.domain.useCases;

import com.tech.challenge.tech_challenge.core.domain.entities.Product;
import com.tech.challenge.tech_challenge.core.domain.repositories.IProductRepository;
import com.tech.challenge.tech_challenge.core.domain.useCases.FindProductsUseCase.FindProductsUseCase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FindProductsUseCaseTest {
    @MockBean
    private IProductRepository productRepository;

    @Autowired
    private FindProductsUseCase findProductsUseCase;

    @Test
    public void listTest(){
        Product product1 = new Product();
        Product product2 = new Product();

        product1.setId(UUID.randomUUID());
        product2.setId(UUID.randomUUID());

        when(productRepository.findAll()).thenReturn(List.of(product1,product2));

        List<Product> products = findProductsUseCase.execute();

        assertEquals(products.size(), 2);
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));
    }
}
