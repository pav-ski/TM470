package com.TM470.dao;

import java.util.List;

import com.TM470.domain.Element;
import com.TM470.domain.Notification;

public interface NotificationDAO {
	
	public void addNotification(Notification notification);
	public void updateNotification(Notification notification);
	public List<Notification> list();
	public Notification getNotificationById(int id);
	public void removeNotification(int id);
	public void refresh(Notification notification);
	public void saveOrUpdate(Notification notification);
	
	

}
