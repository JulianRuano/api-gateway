package ms.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  public static final String PRODUCT_ORDER_QUEUE = "product.queue.microservice.order";
  public static final String PRODUCT_EXCHANGE = "product.exchange";


  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }   

  @Bean
  Jackson2JsonMessageConverter jsonMessageConverter() {
      return new Jackson2JsonMessageConverter();
  }

  @Bean
  FanoutExchange productFanoutExchange() {
      return new FanoutExchange(PRODUCT_EXCHANGE);
  }

  // Microservice Order
  @Bean
  Queue orderProductQueue() {
      return new Queue(PRODUCT_ORDER_QUEUE);
  }

  @Bean
  Binding orderProductQueueBinding(Queue orderProductQueue, FanoutExchange productFanoutExchange) {
      return BindingBuilder.bind(orderProductQueue).to(productFanoutExchange);
  }

  
}
