package com.agfa.sh.cris.mock;

import java.util.ArrayList;
import java.util.List;

public class ExceptionMock {
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		for(int i=0; i<Integer.MAX_VALUE; i++) {
			l.add(("aaaaadddddddddcccceeeeeeeeeeffffffaaaaaaaa"+i).intern());
		}
	}
}
