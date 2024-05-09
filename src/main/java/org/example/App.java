package org.example;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {

		MessageSystem messageSystem = MessageSystem.instance();
		Thread publisher = new Thread(() -> {
			try {
				messageSystem.publish();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});

        Thread consumer = new Thread(() -> {
			try {
				messageSystem.subscribe();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});

		publisher.start();
		consumer.start();
	}


}