package com.hwloser.streaming;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

public class TestDemo {
  private static final String INPUT_TOPIC = "streams-plaintext-input";
  private static final String OUTPUT_TOPIC = "streams-wordcount-output";

  private static final String localAddress = "172.28.116.203:9092";

  public static void main(String[] args) {

  }

  public static void anotherWordCount() {
    Properties props = getStreamConfig();

    StreamsBuilder b = new StreamsBuilder();

    wordCountStream(b);
    submitKafkaStream(b, props);
  }

  private static void submitKafkaStream(StreamsBuilder b, Properties props) {
    CountDownLatch xLock = new CountDownLatch(1);

    KafkaStreams ks = new KafkaStreams(b.build(), props);

    Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook") {
      @Override
      public void run() {
        ks.close();
        xLock.countDown();
      }
    });

    try {
      ks.start();
      xLock.await();
    } catch (final Throwable e) {
      System.exit(1);
    }
    System.exit(0);
  }

  private static void wordCountStream(StreamsBuilder b) {
    KStream<String, String> source = b.stream(INPUT_TOPIC);

    KTable<String, Long> counts = source
        .flatMapValues(value -> Arrays.asList(value.toLowerCase(Locale.getDefault()).split(" ")))
        .groupBy((key, value) -> value)
        .count();

    // need to override value serde to Long type
    counts.toStream().to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.Long()));
  }
  private static Properties getStreamConfig() {
    Properties props = new Properties();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, localAddress);
    props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass().getName());

    // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
    // note that: to re-run the demo, you need to use the offset reset tool;
    // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    return props;
  }

  private void testSerdes() {
    // Serializers/ deserializers (serde) fro String and Long types
    Serde<String> stringSerde = Serdes.String();
    Serde<Long> longSerde = Serdes.Long();
  }
}
