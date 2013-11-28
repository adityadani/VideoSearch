/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videosearch;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author optimus
 */
public class VideoSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        		
		
            String fileName = args[0];
	   	
            int width, height;
            width =352;
            height= 288;
            int frames =0;
		
	    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    
	    byte maxr=0,maxb=0,maxg=0;
	    double[] reds = new double[256];
	    double[] greens = new double[256];
	    double[] blues = new double[256];
	    
	    for(int k=0;k<256;k++)
	    {
	    	reds[k]=greens[k]=blues[k]=0;
	    }
	    
	    
	    
	    try {
		    File file = new File(args[0]);
		    
		    InputStream is = new FileInputStream(file);
		    
		    JFrame frame = new JFrame();
		    
		    
		    long len = file.length();
		    byte[] bytes = new byte[(int)len];
		    double[] bytes2 = new double[(int)len];
		    double [][] actual_values = new double[width][height];
		    
		    int offset = 0;
                    int numRead = 0;
                    while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                        offset += numRead;
	        }
	    
	    		
	    	int ind = 0;
	    	
	    	while(len>0)
	    	{
	    		ind = frames * (height*width*3);
                        len = len - (height*width*3);
                        frames ++;
	    		//if(frames%3!=0)
	    		//	continue;
                        for(int y = 0; y < height; y++){
		
				for(int x = 0; x < width; x++){
			 
					byte a = 0;
					byte r = (byte) (bytes[ind]);
					byte g = (byte) (bytes[ind+height*width]);
					byte b = (byte) (bytes[ind+height*width*2]); 
					
					if(r>maxr)
						maxr=r;
					if(g>maxg)
						maxg=g;
					if(b>maxb)
						maxb=b;
					
					
					
						
					double r1=r&0xff;
					double g1=g&0xff;
					double b1=b&0xff;
					
					reds[(int) r1]++;
					greens[(int) g1]++;
					blues[(int) b1]++;
					
					
					
					int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
					//int pix = ((a << 24) + (r << 16) + (g << 8) + b);
					int pix2= 0xfffffff;
					img.setRGB(x,y,pix);
					//img2.setRGB(x, y, pix2);
					ind++;
					
				}
			}

	    	// JFrame frame = new JFrame();
	 	    JLabel label = new JLabel(new ImageIcon(img));
	 	    frame.getContentPane().add(label, BorderLayout.CENTER);
	 	    frame.pack();
	 	    frame.setVisible(true);
	 	    frame.setTitle("Query");
                    Thread.sleep(1000/33);
	    	}
	    }
	    catch(Exception ex)
	    {
	    
	    }
            System.out.println("frames - " + frames);
            System.out.println("max r,g,b - "+ maxr +" , " + maxg + "," + maxb);
		
	
	}
    }
