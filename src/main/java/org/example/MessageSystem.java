package org.example;

import static org.example.COLOR.*;

public class MessageSystem {

	private boolean consumable = false;
	private boolean stop;
	private boolean stoppedPublisher;

	public void stop() {
		stop = true;
	}

	public void publish() {
		try {
			doPublish();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	private synchronized void doPublish() throws InterruptedException {
		while (true) {
			while (consumable) {
				wait();
			}
			System.out.println();
			System.out.println(GREEN.encodedColor() + " ... Publishing ..." + BLACK.encodedColor());
			consumable = !consumable;
			notify();
			if (stop) {
				stoppedPublisher = true;
				return;
			}
		}
	}

	public void subscribe() {
		try {
			doSubscribe();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	private synchronized void doSubscribe() throws InterruptedException {
		while (true) {
			while (!consumable) {
				wait();
			}
			System.out.println();
			System.out.println(RED.encodedColor() + " ... Consuming ..." + BLACK.encodedColor());
			consumable = !consumable;
			Thread.sleep(800L);
			notify();
			if (stoppedPublisher) return;
		}
	}


	private MessageSystem() {
	}

	public static MessageSystem instance() {
		return SingleTon.INSTANCE;
	}

	private static class SingleTon {
		private static final MessageSystem INSTANCE = new MessageSystem();
	}

}
