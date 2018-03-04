package dev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class JsonReader {

	private static String readAll(Reader read) throws IOException {
		
	    StringBuilder sbuilder = new StringBuilder();
	    
	    int component;
	    
	    while ((component = read.read()) != -1) {
	      sbuilder.append((char) component);
	    }
	    
	    
	    return sbuilder.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream inputs = new URL(url).openStream();
	    try {
	      BufferedReader read = new BufferedReader(new InputStreamReader(inputs, Charset.forName("UTF-8")));
	      String jsonText = readAll(read);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      inputs.close();
	    }
	  }

	  public static void main(String[] args) throws IOException, JSONException {
	    JSONObject json = readJsonFromUrl("http://www.mready.net/devacademy/input1.json");
	   
	    JSONArray myJsonArray = (JSONArray) json.get("file_system");
	  
	    Visual myWindow = new Visual("Directories window");
	    
	  Document[] documente = new Document[10000];
	  
	  int nrDoc = 0;
	  
	    for(int i=0; i<myJsonArray.length(); i++) {
	    	
	       JSONObject dataObj = (JSONObject)myJsonArray.get(i);
	       
	       String name = dataObj.getString("name");
	       
	       String type = dataObj.getString("type");
	       
	       int id = dataObj.getInt("id");
	       
	       if(type.equals("file")) {
	    	   documente[i] = new File(id,name);
	       }
	       else if(type.equals("directory")){
	    	   documente[i] = new Directory(id, name);
	       }
	       
	       nrDoc ++;
	       
	       JSONArray content = (JSONArray) dataObj.get("contents");
	       
	     //creez butoane doar daca sunt directoare , deci au continut
	       if(type.equals("directory")){
	    	   
	    	   String[] elements = new String[100];
	    	   
	       for(int j=0; j<content.length(); j++) {
	    	   
		       JSONObject dataObj2 = (JSONObject)content.get(j);
		       
		       String name2 = dataObj2.getString("name");
		       
		       String type2 = dataObj2.getString("type");
		       
		       int id2 = dataObj2.getInt("id");
		     
		       if(type2.equals("file")) {
		    	   
		    	   documente[i].contents.add(new File(id2,name2));
		    	   documente[i].nrContents++;
		    	   elements[documente[i].nrContents-1] = name2;
		       }
		       else if(type2.equals("directory")){
		    	   
		    	   documente[i].contents.add(new Directory(id2, name2));
		    	   documente[i].nrContents++;
		    	   elements[documente[i].nrContents-1] = name2;
		       }
		       
		       
	       }
	       //adaugare butoane
	       myWindow.addButton(name,elements,documente[i].nrContents);
	       }
	       
	      
	    }
	    //afisarea la stdout
	    for(int i = 0 ; i < nrDoc ; i ++){
	    	System.out.println(documente[i].name);
	    	if(documente[i].type.equals("directory")) {
	    		for(int j = 0 ; j < documente[i].nrContents;j++){
	    			System.out.println("      " +  documente[i].contents.get(j).name);
	    		}
	    	}
	    }
	  
	    
        myWindow.setSize(3500,1000);
        myWindow.setVisible(true);
	    
	    
	  }
	
	
}





