/*
 * Kevin Drake
 * 2/16/22
 * Rewrote this program to define the Abstract Methods of addAll, removeAll, retainAll, to work in the list
 */
public abstract class MyAbstractList<E> implements MyList<E> {
	protected int size = 0; // The size of the list

	/** Create a default list */
	protected MyAbstractList() {
	}

	/** Create a list from an array of objects */
	protected MyAbstractList(E[] objects) {
		for (int i = 0; i < objects.length; i++) {
			add(objects[i]);
			size++;
		}	
	}

	/** Add a new element at the end of this list */
	public void add(E e) {
		add(size, e);
	}

	/** Return true if this list contains no elements */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Return the number of elements in this list */
	public int size() {
		return size;
	}

	/** Remove the first occurrence of the element o from this list.
	 *  Shift any subsequent elements to the left.
	 *  Return true if the element is removed. */
	public boolean remove(E e) {
		if (indexOf(e) >= 0) {
			remove(indexOf(e));
			return true;
		}
		else 
			return false;
	}
	/** Adds the elements in otherList to this list.
	* Returns true if this list changed as a result of the call */ 
	public boolean addAll(MyList<E> otherList) {
		for (int i = 0; i < otherList.size(); i++) 
			add(otherList.get(i));
		
		if (otherList.size() > 0)
			return true;
		
		else
			return false;
	}

	/** Removes all the elements in otherList from this list
	* Returns true if this list changed as a result of the call */ 
	public boolean removeAll(MyList<E> otherList) {
		boolean changed = false;
		for (int i = 0; i < otherList.size(); i++) {
			if (remove(otherList.get(i)))
				changed = true;
			}
		return changed;
	}

	/** Retains the elements in this list that are also in otherList 
	* Returns true if this list changed as a result of the call */ 
	public boolean retainAll(MyList<E> otherList) {
		boolean changed = false;
		for (int i = 0; i < this.size();) {
			if (!otherList.contains(this.get(i))) {
				this.remove(get(i));
				changed = true;
			}
			else 
				i++;
		}
		return changed;
	}
}