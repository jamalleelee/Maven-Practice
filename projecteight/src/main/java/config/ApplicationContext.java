package config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import rugal.sample.core.repository.RepositoryPackage;

/**
 * Java based application context configuration class.
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = RepositoryPackage.class)
@ComponentScan(value = "rugal.sample.core")
public class ApplicationContext extends AbstractMongoConfiguration
{

    @Override
    public String getDatabaseName()
    {
        return "test";
    }

    @Override
    @Bean
    public MongoClient mongo() throws Exception
    {
        return new MongoClient("localhost", 27017);
    }

    @Override
    protected String getMappingBasePackage()
    {
        return "rugal.sample.core.entity";
    }

}
