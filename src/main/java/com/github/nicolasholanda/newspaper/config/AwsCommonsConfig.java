package com.github.nicolasholanda.newspaper.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;


@Getter
@Configuration
public class AwsCommonsConfig {

    @Value("${amazon.aws.region}")
    private String awsRegion;

    @Value("${amazon.dynamodb.endpoint}")
    private String dynamodbEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;

    @Bean
    public AWSStaticCredentialsProvider credentialsProvider() {
        var credentials = new BasicAWSCredentials(accessKey, secretKey);
        return new AWSStaticCredentialsProvider(credentials);
    }

    @Bean
    public StaticCredentialsProvider staticCredentialsProvider() {
        var credentials = AwsBasicCredentials.create(accessKey, secretKey);
        return StaticCredentialsProvider.create(credentials);
    }

    public static AwsClientBuilder.EndpointConfiguration buildEndpoint(String awsRegion, String dynamodbEndpoint) {
        return new AwsClientBuilder.EndpointConfiguration(dynamodbEndpoint, awsRegion);
    }
}
