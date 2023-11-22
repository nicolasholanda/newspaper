package com.github.nicolasholanda.newspaper.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.github.nicolasholanda.newspaper.repository")
public class DynamoDBConfig {

    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB(AWSStaticCredentialsProvider credentialsProvider,
                                         @Value("${amazon.aws.region}") String region,
                                         @Value("${amazon.dynamodb.endpoint}") String dynamodbEndpoint) {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(AwsCommonsConfig.buildEndpoint(region, dynamodbEndpoint))
                .withCredentials(credentialsProvider)
                .build();
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

}