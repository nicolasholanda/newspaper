package com.github.nicolasholanda.newspaper.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.awspring.cloud.sqs.operations.SqsTemplateBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;


@Configuration
public class SQSConfig {

    @Value("${amazon.sqs.endpoint}")
    private String queueUrl;

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient, ObjectProvider<ObjectMapper> objectMapperProvider) {
        SqsTemplateBuilder builder = SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient);
        objectMapperProvider.ifAvailable((om) -> {
            om.registerModule(new JavaTimeModule());
            builder.configureDefaultConverter((converter) -> {
                converter.setObjectMapper(om);
            });
        });
        builder.configure(sqsTemplateOptions -> sqsTemplateOptions.defaultQueue(queueUrl));
        return builder.build();
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient(AwsCredentialsProvider credentialsProvider) {
        return SqsAsyncClient.builder().credentialsProvider(credentialsProvider).build();
    }


    @Bean
    public SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory(SqsAsyncClient sqsAsyncClient) {
        return SqsMessageListenerContainerFactory
                .builder()
                .configure(options -> options
                        .acknowledgementMode(AcknowledgementMode.ALWAYS)
                )
                .sqsAsyncClient(sqsAsyncClient)
                .build();
    }
}
