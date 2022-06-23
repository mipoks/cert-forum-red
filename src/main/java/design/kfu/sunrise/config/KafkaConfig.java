package design.kfu.sunrise.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.util.backoff.FixedBackOff;
import ru.itis.cert.commons.model.ReportDto;
import ru.itis.cert.commons.model.UserDto;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    private final KafkaProperties kafkaProperties;


    @Bean
    public ProducerFactory<UUID, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
    }

    @Bean
    public ConsumerFactory<UUID, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    /**
     * Бин для синхронизации проектов
     *
     * @return Kafka template для отправки запроса на синхронизацию проектов
     */
    @Bean
    public KafkaTemplate<UUID, ?> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Бин <code>ReplyingKafkaTemplate<UUID, UpdateProjectStatusRequestKafkaDTO, UpdateProjectStatusResponseKafkaDTO></code>
     * для запроса изменения статуса проекта у КРСТ11
     *
     * @return ReplyingKafkaTemplate для синхронного запроса на смену статуса
     */
    @Bean
    public ReplyingKafkaTemplate<UUID, UserDto, ReportDto> replyKafkaTemplate(@Value("${spring.kafka.topics.user-report-response}") String topicName) {
        DefaultKafkaProducerFactory<UUID, UserDto> pf =
                new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
        DefaultKafkaConsumerFactory<UUID, ReportDto> cf =
                new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
        ContainerProperties containerProperties = new ContainerProperties(topicName);
        return new ReplyingKafkaTemplate<>(pf, new KafkaMessageListenerContainer<>(cf, containerProperties));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID, ?> kafkaListenerContainerFactory(
            KafkaOperations<Object, Object> kafkaOperation, ReplyingKafkaTemplate<UUID, UserDto, ReportDto> rkt) {
        ConcurrentKafkaListenerContainerFactory<UUID, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setReplyTemplate(rkt);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaOperation),
                new FixedBackOff(1000L, 5L))
        );
        return factory;
    }
}
