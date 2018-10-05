package com.TM470.domain;

import java.util.Set;

public interface Listener {
	
	public void addObserver(User user);
	public void addStaffObservers(Set<Staff> staff);
	public void removeObserver();

}
