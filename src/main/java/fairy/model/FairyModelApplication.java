package fairy.model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FairyModelApplication {
	private static final ReentrantLock LOCK = new ReentrantLock();
	private static final Condition STOP = LOCK.newCondition();

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(FairyModelApplication.class, args);
		applicationContext.start();
		addHook(applicationContext);
		try {
			LOCK.lock();
			STOP.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			LOCK.unlock();
		}

	}

	private static void addHook(ConfigurableApplicationContext applicationContext) {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			applicationContext.stop();
			try {
				LOCK.lock();
				STOP.signal();
			} finally {
				LOCK.unlock();
			}
		}, "StartMain-shutdown-hook"));
	}
}
