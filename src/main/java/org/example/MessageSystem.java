package org.example;

public class MessageSystem {
	private MessageSystem() {}

	public static MessageSystem instance() {
		return SingleTon.INSTANCE;
	}

	private static class SingleTon {
		private static final MessageSystem INSTANCE = new MessageSystem();
	}

	private boolean consumable = false;
	private static final String GREEN = "\u001B[32m";
	private static final String RED = "\u001B[31m";
	private static final String RESET = "\u001B[0m";

	public void publish() throws InterruptedException {
		synchronized (this) {
			while (true) {
				while (consumable) {
					wait();
				}
				System.out.println();
				System.out.println(GREEN + " ... Publishing ..." + RESET);
				consumable = !consumable;
				Thread.sleep(1000L);
				notify();
			}
		}
	}

	public void subscribe() throws InterruptedException {
		synchronized (this) {
			while (true) {
				while (!consumable) {
					wait();
				}
				System.out.println();
				System.out.println(RED + " ... Consuming ..." + RESET);
				consumable = !consumable;
				notify();
			}
		}
	}
}
