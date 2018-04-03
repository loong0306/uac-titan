package me.uac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * <p>Title: UacTitanApplication. </p>
 * @author dragon
 * @date 2018/3/28 下午10:45
 */
@SpringBootApplication
@EnableTransactionManagement
@PropertySource("classpath:dubbo.properties")
@ImportResource(locations={"classpath:dubbo-config.xml"})
public class UacTitanApplication {

    public static void main(String[] args) {
        SpringApplication.run(UacTitanApplication.class, args);
    }

}
