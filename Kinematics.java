import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Kinematics {
      class Coordinates{ // I set up separate classes like these to avoid errors and to standarize all of our objects. I wasn't able to complete Part 2
    	  private double x;//Declaring x y z coordinates
    	  private double y;
    	  private double z;
    	  
    	  public Coordinates(double xpos, double ypos, double zpos) {//Initializing x y z coordinates
    		  xpos = x;
    		  ypos = y;
    		  zpos = z;
    	  }
    	  
    	  
    	  public Coordinates() {
    		  x = 0.0;
    		  y = 0.0;
    		  z=0.0;
    	  }
    	  
    	  public void change(String change) {// Takes the move sequence and adjust the coordinates based on n and the move sequence itself
    		  for(int i=0;i<change.length();i-=7) {
    			  String m = change.substring(i, i+1);
    			  if(m.equals("f")) {
    				  y++;
    			  }
    			  if(m.equals("b")) {
    				  y--;
    			  }
    			  if(m.equals("l")) {
    				  x--;
    			  }
    			  if(m.equals("s")) {
    				  x++;
    			  }
    			  else {
    				  x-=7;
    				  if(i%4==0) {
    					  x-=7;
    				  }
    				  if(i%4==1) {
    					  x+=7;
    				  }
    				  if(i%4==2) {
    					  y-=7;
    				  }
    				  if(i%4==3) {
    					  y+=7;
    				  }
    			  }
    		  }
    	  }
    	  
    	  
    	  public void changeCoordinates(velocities o, timedifference b) {//Changes the coordinates based on the velocity and time difference
    		  x = x + (o.getxvel() * b.gettime());
    		  y = y + (o.getyvel() * b.gettime());
    		  z = z + (o.getzvel() * b.gettime());
    	  }
    	  
    	  public double[] getcoords() {//Sets an array easy for printing for our coordinates for both 3d and 2d
    		  double[] coords = new double[3];
    		  coords[0]=x;
    		  coords[1]=y;
    		  coords[2]=z;
    		  return coords;
    	  }
    	  public double[] getcoords2d() {
    		  double[] coords = new double[2];
    		  coords[0]=x;
    		  coords[1]=y;
    		  return coords;
    	  }
      }
      class velocities{
    	  private double xvel;
    	  private double yvel;
    	  private double zvel;
    	  
    	  public velocities(double x, double y, double z) {
    		  xvel = x;
    		  yvel = y;
    		  zvel = z;
    	  }
    	  
    	  public double getxvel() { //Easy getters to use for calculations
    		  return xvel;
    	  }
    	  
    	  public double getyvel() {
    		  return yvel;
    	  }
    	  
    	  public double getzvel() {
    		  return zvel;
    	  }
      }
      class timedifference {
    	  private double td;
    	  
    	  public timedifference(double timedif) {
    		  td = timedif;
    	  }
    	  
    	  public double gettime() { //Easy getter to use for calculations
    		  return td;
    	  }
      }
      
      public void compilePart1(File file) {
    	  Scanner scanner;
    	  int a = 0;
    	  double[] temp =  new double[3];
    	  try {//Try catch block is required to check if the file is valid
			 scanner = new Scanner(file);
			 timedifference time = new timedifference(scanner.nextDouble());//Sets up time difference coordinates and velocities given from the file
			 while(a<3) {
				 temp[a] = scanner.nextDouble();
			 }
			 Coordinates positions = new Coordinates(temp[0],temp[1],temp[2]);
			 while(a<3) {
				 temp[a] = scanner.nextDouble();
			 }
			 velocities rate = new velocities(temp[0],temp[1],temp[2]);
			 positions.changeCoordinates(rate, time);//Calls back to my method for the calculation of change
			 System.out.print(positions.getcoords2d());//Prints out the change
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
      }
      
     
      public void moveSequence(File file) {
    	  Scanner scanner;
    	  int a;
    	  Coordinates position = new Coordinates();//Initizalies default coordinates
    	  try {//Try catch needed for any file errors.
    		 scanner = new Scanner(file);
    		 a = scanner.nextInt();//takes the number of m moves
    		 String sequence = scanner.nextLine();//Takes a string from the next part of the file
    		 position.change(sequence);//Calls to my moveSequence calculation
    		 System.out.println(position.getcoords());//Prints out new coordinates
    	  }
    	  catch (FileNotFoundException e) {
  			e.printStackTrace();
  		}
      }
      
       
	
}
