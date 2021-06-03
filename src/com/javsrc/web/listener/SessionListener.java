package com.javsrc.web.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.javsrc.entity.SessionInfo;
import com.javsrc.service.SessionInfoService;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionBindingListener {
	
	/*
	 * public String privateInfo=""; //生成监听器的初始化参数字符串 private String logString="";
	 * //日志记录字符串 private int count=0; //登录人数计数器
	 */	
	private SessionInfo info;

    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }
    
    public SessionListener(SessionInfo info){
        this.info=info;
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
        SessionInfoService service=new SessionInfoService();
		service.save(info);
    }

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event)  { 
    	info.setTime(new Date());
    	info.setOperation("登出");
    	SessionInfoService service=new SessionInfoService();
    	service.save(info);
    }
	
}
