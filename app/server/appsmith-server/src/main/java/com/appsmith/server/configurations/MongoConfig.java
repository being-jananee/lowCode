package com.bizBrainz.server.configurations;

import com.bizBrainz.external.annotations.documenttype.DocumentTypeMapper;
import com.bizBrainz.external.annotations.encryption.EncryptionMongoEventListener;
import com.bizBrainz.external.models.AuthenticationDTO;
import com.bizBrainz.external.services.EncryptionService;
import com.bizBrainz.server.configurations.mongo.SoftDeleteMongoRepositoryFactoryBean;
import com.bizBrainz.server.converters.StringToInstantConverter;
import com.bizBrainz.server.repositories.BaseRepositoryImpl;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.SpringDataMongoV3Driver;
import com.github.cloudyrock.spring.v5.MongockSpring5;
import com.mongodb.ReadConcern;
import com.mongodb.WriteConcern;
import lombok.extern.slf4j.Slf4j;
import org.bson.conversions.Bson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.DefaultTypeMapper;
import org.springframework.data.convert.SimpleTypeInformationMapper;
import org.springframework.data.convert.TypeInformationMapper;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This configures the JPA Mongo repositories. The default base implementation is defined in {@link BaseRepositoryImpl}.
 * This is required to add default clauses for default JPA queries defined by Spring Data.
 * <p>
 * The factoryBean class is also custom defined in order to add default clauses for soft delete for all custom JPA queries.
 * {@link SoftDeleteMongoRepositoryFactoryBean} for details.
 */
@Slf4j
@Configuration
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories(repositoryFactoryBeanClass = SoftDeleteMongoRepositoryFactoryBean.class,
        basePackages = "com.bizBrainz.server.repositories",
        repositoryBaseClass = BaseRepositoryImpl.class
)
public class MongoConfig {

    /*
        Changing this froom ApplicationRunner to InitializingBeanRunner
        We are doing so because when one migration failed to run because API call executed before migration on old data
        and made inconsistent for migration, having API calls run on unmigrated data is also a issue.
        Link to documentation: https://docs.mongock.io/v5/runner/springboot/index.html
    */
    @Bean
    public MongockSpring5.MongockInitializingBeanRunner  mongockInitializingBeanRunner(ApplicationContext springContext, MongoTemplate mongoTemplate) {
        SpringDataMongoV3Driver springDataMongoV3Driver = SpringDataMongoV3Driver.withDefaultLock(mongoTemplate);
        springDataMongoV3Driver.setWriteConcern(WriteConcern.JOURNALED.withJournal(false));
        springDataMongoV3Driver.setReadConcern(ReadConcern.LOCAL);

        return MongockSpring5.builder()
                .setDriver(springDataMongoV3Driver)
                .addChangeLogsScanPackages(List.of("com.bizBrainz.server.migrations"))
                .setSpringContext(springContext)
                // any extra configuration you need
                .buildInitializingBeanRunner();
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(ReactiveMongoDatabaseFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter) {
        ReactiveMongoTemplate mongoTemplate = new ReactiveMongoTemplate(mongoDbFactory, mappingMongoConverter);
        MappingMongoConverter conv = (MappingMongoConverter) mongoTemplate.getConverter();
        // tell mongodb to use the custom converters
        conv.setCustomConversions(mongoCustomConversions());
        conv.afterPropertiesSet();
        return mongoTemplate;
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, mappingMongoConverter);
        MappingMongoConverter conv = (MappingMongoConverter) mongoTemplate.getConverter();
        // tell mongodb to use the custom converters
        conv.setCustomConversions(mongoCustomConversions());
        conv.afterPropertiesSet();
        return mongoTemplate;
    }

    // Custom type mapper here includes our annotation based mapper that is meant to ensure correct mapping for sub-classes
    // We have currently only included the package which contains the DTOs that need this mapping
    @Bean
    public DefaultTypeMapper<Bson> typeMapper() {
        TypeInformationMapper typeInformationMapper = new DocumentTypeMapper
                .Builder()
                .withBasePackages(new String[]{AuthenticationDTO.class.getPackageName()})
                .build();
        // This is a hack to include the default mapper as a fallback, because Spring seems to override its list instead of appending mappers
        return new DefaultMongoTypeMapper(DefaultMongoTypeMapper.DEFAULT_TYPE_KEY, Arrays.asList(typeInformationMapper, new SimpleTypeInformationMapper()));
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Collections.singletonList(new StringToInstantConverter()));
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(DefaultTypeMapper<Bson> typeMapper, MongoMappingContext context) {
        MappingMongoConverter converter = new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, context);
        converter.setTypeMapper((MongoTypeMapper) typeMapper);
        converter.setCustomConversions(mongoCustomConversions());
        converter.setMapKeyDotReplacement("-BIZBRAINZ-DOT-REPLACEMENT-");
        return converter;
    }

    @Bean
    public EncryptionMongoEventListener encryptionMongoEventListener(EncryptionService encryptionService) {
        return new EncryptionMongoEventListener(encryptionService);
    }

}
