package jelan.httpclient;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RequestQueue {
	private static LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<Object>();

	private static int trimType;
	private static int maxSize;

	public RequestQueue() {
		
	}
	
	public RequestQueue(int maxSize, int trimType) {
		this.maxSize = maxSize;
		this.trimType = trimType;
	}

	public void add(Object object) {
		if (queue.size() > maxSize) {
			// trim
			trim();
		}
		queue.add(object);
	}

	public Object take() {
		Object object = null;
		try {
			object = queue.take();
		} catch (InterruptedException e) {
			object = null;
			e.printStackTrace();
		}
		return object;
	}
	
	public Object poll(long timeout, TimeUnit unit) {
		Object object = null;
		
			try {
				object = queue.poll(timeout, unit);
			} catch (InterruptedException e) {
				object = null;
				e.printStackTrace();
			}
		
		return object;
	}
	

	public int getCount() {
		return queue.size();
	}

	private void trim() {
		switch (trimType) {
		case 1:
			// 
			break;
		default:
			break;
		}
	}
}
