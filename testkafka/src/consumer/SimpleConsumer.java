package consumer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class SimpleConsumer {
	private final ConsumerConnector consumer;
	private final String topic;
	
	public SimpleConsumer(String zookeeper, String groupId, String
			topic) {
			Properties props = new Properties();
			props.put("zookeeper.connect", zookeeper);
			props.put("group.id", groupId);
			props.put("zookeeper.session.timeout.ms", "500");
			props.put("zookeeper.sync.time.ms", "250");
			props.put("auto.commit.interval.ms", "1000");
			consumer = Consumer.createJavaConsumerConnector(
			new ConsumerConfig(props));
			this.topic = topic;
			}
	
	
	public void testConsumer() {
		Map<String, Integer> topicCount = new HashMap<String, Integer>();
		// Define single thread for topic
		topicCount.put(topic, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams=consumer.createMessageStreams(topicCount);
		List<KafkaStream<byte[], byte[]>> streams = consumerStreams.
		get(topic);
		for (@SuppressWarnings("rawtypes") final KafkaStream stream : streams) {
		ConsumerIterator<byte[], byte[]> consumerIte = stream.iterator();
		while (consumerIte.hasNext())
		System.out.println("Message from Single Topic :: " + new String(consumerIte.next().message()));
		}
		if (consumer != null)
		consumer.shutdown();
	}
	public static void main(String[] args) {
		SimpleConsumer simpleConsumer = new SimpleConsumer("localhost:2181", "testgroup", "mytopic");
		simpleConsumer.testConsumer();
		}
		}

	

