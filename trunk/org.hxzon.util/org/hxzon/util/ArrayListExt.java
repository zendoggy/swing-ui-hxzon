package org.hxzon.util;

import java.util.ArrayList;
import java.util.List;

import org.hxzon.swing.util.HChangeListenerSupport;

public class ArrayListExt<V> extends ArrayList<V> {

	private static final long serialVersionUID = 1L;
	private HChangeListenerSupport<List<V>> listenerSupport = new HChangeListenerSupport<List<V>>();

	
	public boolean add(V o){
		return super.add(o);
	}
	
	public boolean remove(Object o){
		return super.remove(o);
	}
}
