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
	private static final long DEFAULT_TIMEOUT = 2_000L;


	public static void main(String[] args) {
		System.out.println(ORANGE.encodedColor() +
				"================== Message System - Java Multithreading using wait(), notify() ==============="
				+ BLACK.encodedColor()
		);

		long start = System.currentTimeMillis();
		try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
			MessageSystem messageSystem = MessageSystem.instance();
			executorService.submit(messageSystem::publish);
			executorService.submit(messageSystem::subscribe);

			if (!executorService.awaitTermination(timeout(args), TimeUnit.MILLISECONDS)) {
				messageSystem.stop();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		} finally {
			long end = System.currentTimeMillis();
			System.out.printf(ORANGE.encodedColor()
					+ "================== Finished in %.3f seconds, See you later ==============="
					+ BLACK.encodedColor(), (end - start) * 1.0f / 1000);
		}
	}

	private static long timeout(String input[]) {
		if (null != input && input.length == 1) {
			return Long.parseLong(input[0]);
		}
		return DEFAULT_TIMEOUT;
	}

}
