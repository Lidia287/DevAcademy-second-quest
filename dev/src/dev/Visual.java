package dev;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Visual extends Frame implements WindowListener{
   
   
    Button button;
    
    public void addButton(String string,String[] elements, int nrContents){
    	
    	  button = new Button(string);
          add(button);
          
          TextField[] text = new TextField[100];
          
          for(int j = 0 ; j < nrContents; j ++){
        	  text[j] = new TextField(20);
        	  add(text[j]);
          }
         
          
          button.addActionListener( new ActionListener()
          {
              @Override
              public void actionPerformed(ActionEvent e)
              {
            	 
            	  for(int j = 0 ; j < nrContents; j ++){
                	  text[j].setText(elements[j]);
                  }
            	  
            	  
              }
          });
          
    }

    public Visual(String title) {

            super(title);
            setLayout(new FlowLayout());
            addWindowListener(this);
            
            
    }

 
    public void windowClosing(WindowEvent e) {
            dispose();
            System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}

}
