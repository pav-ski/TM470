package com.TM470.dao;

import java.util.List;

import com.TM470.domain.Element;

public interface ElementDAO {
	
	public void addElement(Element element);
	public void updateElement(Element element);
	public List<Element> list();
	public Element getElementById(int id);
	public void removeElement(int id);
	
	

}
