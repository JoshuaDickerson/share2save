package com.example.share2save;

public class LinkItem {
	public String url;
	public String title; 
	
	public LinkItem(){
		super();
	}
	
	public LinkItem(String item){
		String[] urlTitle = item.split("fffff");
		if(urlTitle.length > 1){
			this.url = urlTitle[0]; 
			this.title = urlTitle[1];
		}else{
			this.url = item;
		}
//		
	}
}
