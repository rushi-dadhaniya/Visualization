package kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component("visualizationProducer")
public class VisualizationProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(VisualizationProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

//	private String topic = "maas360.ios.visualization.topic";

	public void visualize(String topic, String message) {
		kafkaTemplate.send(topic, message);
//		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
//		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//
//			@Override
//			public void onSuccess(SendResult<String, String> result) {
//				LOGGER.info("sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
//			}
//
//			@Override
//			public void onFailure(Throwable ex) {
//				LOGGER.error("unable to send message='{}'", message, ex);
//			}
//		});

	}
}
