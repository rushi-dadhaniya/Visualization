package kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component("visualizationProducer")
public class VisualizationProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(VisualizationProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private String topic = "maas360.ios.visualization.topic";

	public void visualize(String topic, String message) {
		kafkaTemplate.send(topic, message);
	}
}
