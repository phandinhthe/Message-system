package org.example;

import static org.example.COLOR.*;

public class MessageSystem {

	private boolean consumable = false;
	private boolean stop;

	public void stop() {
		stop = true;
	}

	public void publish() {
		try {

			synchronized (this) {
				while (!stop) {
					while (consumable) {
						wait();
					}
					System.out.println();
					System.out.println(GREEN.encodedColor() + " ... Publishing ..." + BLACK.encodedColor());
					consumable = !consumable;
					notify();
				}
			}
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	public void subscribe() {
		try {
			synchronized (this) {
				while (!stop) {
					while (!consumable) {
						wait();
					}
					System.out.println();
					System.out.println(RED.encodedColor() + " ... Consuming ..." + BLACK.encodedColor());
					consumable = !consumable;
					Thread.sleep(800L);
					notify();
				}
			}
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
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
