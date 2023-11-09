package com.github.nicolasholanda.newspaper.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
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
    @Value("${amazon.aws.region}")
    private String awsRegion;

    @Value("${amazon.dynamodb.endpoint}")
    private String awsEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;


    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB() {
        var credentials = new BasicAWSCredentials(accessKey, secretKey);
        var endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(awsEndpoint, awsRegion);
        var credentialsProvider = new AWSStaticCredentialsProvider(credentials);

        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(credentialsProvider)
                .build();
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

}