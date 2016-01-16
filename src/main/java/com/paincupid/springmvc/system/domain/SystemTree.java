package com.paincupid.springmvc.system.domain;

public class SystemTree {
	
	private String id;
	private String pid;
	private String name;
	private boolean isParent=false; 
	private boolean open;
	private String file;	//会在右边显示
	private String url;	//url会弹出新的地址页面
	private boolean visiable = true;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisiable() {
		return visiable;
	}
	public void setVisiable(boolean visiable) {
		this.visiable = visiable;
	}
	
}
