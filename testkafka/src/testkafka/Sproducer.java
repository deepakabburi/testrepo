package testkafka;



import java.util.Properties;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;



public class Sproducer {
	
	private static Producer<Integer, String> producer;
	private final Properties props = new Properties();
	
	public Sproducer()
	{
    props.put("serializer.class", "kafka.serializer.StringEncoder");
	props.put("metadata.broker.list", "localhost:9092");
	props.put("request.required.acks", "1");
	producer = new Producer<Integer, String>(new ProducerConfig(props));
	}

	public static void main(String[] args) {
		new Sproducer();
		String topic = "mytopic";
		String messageStr = "hello  you";
		KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, messageStr);
		producer.send(data);
		producer.close();
		string next="hai";

	}

}
