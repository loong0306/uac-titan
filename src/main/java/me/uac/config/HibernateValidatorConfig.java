package me.uac.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * <p>Title: HibernateValidatorConfig. </p>
 * <p>Description Hibernate数据校验 </p>
 * @author dragon
 * @date 2018/3/29 下午10:29
 */
@Configuration
public class HibernateValidatorConfig {
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
                .failFast(true).buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
