package com.game.util;

import java.util.List;
import java.util.ListIterator;

// utility class to iterate through list in a forwards direction
public class ListCursor<T> {

	private List<T> list;
	private int currentIndex;

	private ListIterator<T> listIterator;
	private T currentElement;

	public ListCursor(List<T> list, int index) {
		this.list = list;
		this.listIterator = list.listIterator(index);
		this.currentElement = next();
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public T getCurrentElement() {
		return currentElement;
	}

	public T next() {
		if (listIterator.hasNext()) {
			currentIndex = listIterator.nextIndex();
			currentElement = listIterator.next();
			return currentElement;
		}
		listIterator = list.listIterator();
		return next();
	}

}
