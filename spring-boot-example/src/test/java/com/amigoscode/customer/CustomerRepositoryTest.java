package com.amigoscode.customer;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest //low performance
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    static final Faker FAKER = new Faker();
    @Autowired
    private CustomerRepository underTest;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp(){

        System.out.println(applicationContext.getBeanDefinitionCount());
    }

    @Test
    void existsCustomerByEmail() {
        //Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );
        underTest.save(customer);

        //When
        var actual = underTest.existsCustomerByEmail(email);

        //Then
        assertThat(actual).isTrue();
    }

    @Test
    void existsCustomerByEmailFailsWhenEmailNotPresent(){
        //Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();

        //When
        boolean actual = underTest.existsCustomerByEmail(email);

        //Then
        assertThat(actual).isFalse();
    }

    @Test
    void existsCustomerById() {
        //Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );
        underTest.save(customer);

        int id = underTest.findAll()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        //When
        var actual = underTest.existsCustomerById(id);

        //Then
        assertThat(actual).isTrue();

    }

    @Test
    void existsCustomerByIdFailsWhenIdNotPresent(){
        //Given
        int id = 0;

        //When
        var actual= underTest.existsCustomerById(id);

        //Then
        assertThat(actual).isFalse();
    }
}