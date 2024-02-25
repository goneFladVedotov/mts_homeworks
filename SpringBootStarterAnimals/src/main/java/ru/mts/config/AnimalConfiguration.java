package ru.mts.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.factory.AnimalFactory;
import ru.mts.factory.AnimalNameProvider;
import ru.mts.factory.AnimalRandomNameProvider;
import ru.mts.service.AnimalRepository;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.impl.AnimalRepositoryImpl;
import ru.mts.service.impl.CreateAnimalServiceImpl;

import java.util.Map;

@EnableConfigurationProperties(AnimalConfigurationProperties.class)
@Configuration
public class AnimalConfiguration {

    @ConditionalOnMissingBean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(name = CreateAnimalService.NAME)
    public CreateAnimalService createAnimalService(@Autowired Map<String, AnimalFactory> animalFactories) {
        return new CreateAnimalServiceImpl(animalFactories);
    }

    @ConditionalOnMissingBean
    @Bean(name = AnimalBeanPostProcessor.NAME)
    public AnimalBeanPostProcessor animalBeanPostProcessor() {
        return new AnimalBeanPostProcessor();
    }

    @ConditionalOnMissingBean
    @Bean(name = AnimalRepository.NAME)
    public AnimalRepository animalRepository(@Autowired ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider) {
        return new AnimalRepositoryImpl(createAnimalServicesBeanProvider);
    }

    @ConditionalOnMissingBean
    @Bean(name = AnimalNameProvider.NAME)
    public AnimalNameProvider animalNameProvider(@Autowired AnimalConfigurationProperties animalConfigurationProperties) {
        return new AnimalRandomNameProvider(animalConfigurationProperties);
    }

}
