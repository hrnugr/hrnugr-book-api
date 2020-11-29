package com.harunugur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author <a href="mailto:ugur.harun@yandex.com">Harun Ugur</a>
 *  <p>
 *        For more info please
 *        @see <a href="http://www.jhrnugr.github.io">http://www.hrnugr.github.io</a>
 *  </p>
 */

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.harunugur.entity"})  // scan JPA entities
public class BookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class,args);
    }
}
