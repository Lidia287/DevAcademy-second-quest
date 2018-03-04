package dev;

import java.util.*;

public class Directory extends Document{

	
	
	public Directory(int id, String name){
		this.id = id;
		this.name = name;
		this.type = "directory";
		contents = new ArrayList<Document>(100);
		nrContents = 0;
	}
	
	
}
