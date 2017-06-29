package jelan.httpclient;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class HttpRequestQueue<E> extends LinkedBlockingQueue<E> {
	private static final Logger logger = Logger.getLogger(HttpRequestQueue.class);
	
	private static int trimType;
	private static int maxSize;
	private static int trimIntervalValue = 5;
	private static int trimNumber = 5;
	
	public HttpRequestQueue() {
		super();
	}
	
	public HttpRequestQueue(int maxSize, int trimType) {
		super();
		this.maxSize = maxSize;
		this.trimType = trimType;
	}

	@Override
	public boolean add(E o) {
		if (size() > maxSize) {
			trim();
		}
		super.add(o);
		System.out.println("after queue size: [ " + size() + " ]");
		return true;
	}

	private void trim() {
		switch (trimType) {
		case 0: 
			trimInterval(trimIntervalValue);
			break;
		case 1:
			// 
			trimLatest(trimNumber);
			break;
		default:
			trimOldest(trimNumber);
			break;
		}
	}
	
	private void trimInterval(int interval) {
		int i = 0;
		int index = 0;
		if (size() < 1) return;
		while (index < size()) {
			//List<Object> tempQueue = (List<Object>)this;
			E element = (E) super.toArray()[index - i];
			this.remove(element);
			logger.debug("remove object: [ " + ((HttpRequestDataObj)element).capturedTime + " ]");
			//Object obj = (Object) tempQueue.remove(index - i);
			i++;
			index = interval * i;
		}
	}
	
	private void trimLatest(int number) {
		int index = 0;
		if (size() < number) return;
		for (int i = 0; i < number; i++) {
			index = size() - 1;
			E element = (E) super.toArray()[index];
			this.remove(element);
			logger.debug("remove object: [ " + ((HttpRequestDataObj)element).capturedTime + " ]");
		}
	}
	
	private void trimOldest(int number) {
		int index = 0;
		if (size() < number) return;
		for (int i = 0; i < number; i++) {
			index = 0;
			E element = (E) super.toArray()[index];
			this.remove(element);
			logger.debug("remove object: [ " + ((HttpRequestDataObj)element).capturedTime + " ]");
		}
	}
}
