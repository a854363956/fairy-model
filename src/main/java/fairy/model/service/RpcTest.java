package fairy.model.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RpcTest {
	
	
	@RabbitListener(queues="post/models/test")
	public String getData(String msg) {
		return "hello";
	} 
}
