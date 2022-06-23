package design.kfu.sunrise.exchange.kafka;

import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;
import ru.itis.cert.commons.model.ReportDto;
import ru.itis.cert.commons.model.UserDto;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class UserReportProducer {
    @Autowired
    private ReplyingKafkaTemplate<UUID, UserDto, ReportDto> replyingKafkaTemplate;
    @Value("${spring.kafka.topics.user-report-request}")
    private String requestTopicName;
    @Value("${spring.kafka.topics.user-report-response}")
    private String responseTopicName;

    public ReportDto getUserReport(UserDto userDto) {
        ProducerRecord<UUID, UserDto> producerRecord = new ProducerRecord<>(requestTopicName, userDto);
        producerRecord.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, responseTopicName.getBytes()));
        RequestReplyFuture<UUID, UserDto, ReportDto> responseFuture
                = replyingKafkaTemplate.sendAndReceive(producerRecord, Duration.ofSeconds(10));
        ConsumerRecord<UUID, ReportDto> response = null;

        try {
            response = responseFuture.get();
            return response.value();

        } catch (InterruptedException | ExecutionException e) {
            throw Exc.gen(ErrorType.UNEXPECTED_ERROR, e.getMessage());
        }

    }
}

