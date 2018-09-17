package com.example;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.customer.Customer;
import com.example.domain.customer.CustomerRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class JpaOffsetdatetimeSampleApplication implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaOffsetdatetimeSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer beforeCustomer = new Customer("alice", "alice@example.com", OffsetDateTime.of(2018, 7, 10, 12, 0, 0, 0, ZoneOffset.ofHours(5)));
        showCustomer(beforeCustomer);

        customerRepository.saveAndFlush(beforeCustomer);

        Customer afterCustomer = customerRepository.findById("alice").get();
        showCustomer(afterCustomer);
    }

    private void showCustomer(Customer customer) {
        System.out.println("---------------------");
        System.out.println("登録日:\t" + customer.getRegisteredDateTime());
        System.out.println("作成日:\t" + customer.getCreateDateTime());
        System.out.println("更新日:\t" + customer.getUpdateDateTime());
        System.out.println("---------------------");
    }
}
