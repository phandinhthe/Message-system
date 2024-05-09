package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.example.COLOR.BLACK;
import static org.example.COLOR.ORANGE;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		long timeout = 2_000L;
		System.out.println(ORANGE.encodedColor() +
				"================== Message System - Java Multithreading using wait(), notify() ==============="
				+ BLACK.encodedColor()
		);

		try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
			MessageSystem messageSystem = MessageSystem.instance();
			executorService.submit(messageSystem::publish);
			executorService.submit(messageSystem::subscribe);

			if (!executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS)) {
				messageSystem.stop();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		} finally {
			System.out.printf(ORANGE.encodedColor()
					+ "================== Finished in %.3f seconds, See you later ==============="
					+ BLACK.encodedColor(), timeout * 1.0f / 1000);
		}
	}


}