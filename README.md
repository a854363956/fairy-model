# fairy-model

### 添加Link

首先修改RabbitConfig.java 文件 添加queue

```java
package fairy.model.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	@Bean
	public Queue queue() {
		return new 
          Queue("post/models/test",false,false,true);
	}
}

```

> 然后在Spring的bean中使用@RabbitListener(queues="post/models/test")来进行监听
