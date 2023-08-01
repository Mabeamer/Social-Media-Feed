
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.*;
import java.sql.Time;
/**
 *
 * @author smcleo12
 */
public class PostGenerator {
    private final ArrayList<User> users;
    private final Random rng;
    
    private File[] images;
    private String[] captions;
    private String[] texts;
    
    public PostGenerator(ArrayList<User> users)
    {
        // Assign users field
        this.users = users; 
        
        // Instantiate the Random object
        rng = new Random();
        
        
        /*
        Get the images, image captions, and texts        
        */
        
        // Get the images
        File imageDirectory = new File("SocialMediaFeed/resources/images/");
        images = imageDirectory.listFiles();
        
        // Strip off the 1st one because that's the caption file
        images = Arrays.copyOfRange(images, 1, images.length);
        
        // Get the captions
        captions = new String[images.length];
        try {
            Scanner scan = new Scanner(new File("SocialMediaFeed/resources/images/captions.txt"));
            for(int i=0;i<captions.length;i++)
            {
                captions[i] = scan.nextLine();
            }
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        
        // Get the texts
        try {
            Scanner scan = new Scanner(new File("SocialMediaFeed/resources/text_posts.txt"));
            int count=0;
            while(scan.hasNextLine())
            {
                count++;
                scan.nextLine();
            }
            texts = new String[count];
            scan = new Scanner(new File("SocialMediaFeed/resources/text_posts.txt"));
            for(int i=0;i<count;i++)
            {
                texts[i] = scan.nextLine();
            }       
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        
    }

    public File[] getImages() {return images;}
    public void setImages(File[] images) {this.images = images;}

    public String[] getCaptions() {return captions;}
    public void setCaptions(String[] captions) {this.captions = captions;}
    
    public String[] getTexts() {return texts;}
    public void setTexts(String[] texts) {this.texts = texts;}

    public Post generateTextPost(String date, String time) {
    	//creating a TextPost
    	 TextPost hPost = new TextPost();
         hPost.setText(texts[rng.nextInt(texts.length - 1 + 1)]);
         hPost.setDate(date);
         hPost.setTime(time);
    	return hPost;
    }
    
    public Post generateImagePost(String date, String time) {
    	Image[] newImage = new Image[5];
    	int randomIdx = rng.nextInt(images.length-1+1);
    	File f = images[randomIdx];
    	ImagePost outpost = null;
    	try {
    	Image outImage = ImageIO.read(f);
    	outpost = new ImagePost(outImage, captions[randomIdx]);
    	outpost.setDate(date);
    	outpost.setTime(time);
    	}
    	catch(IOException e){System.out.println("File not found.");}
    	
    	return outpost;
    }
    
    //converts the number random number into a month 
    //1 = jan 2 = feb ect ect
    public String getMonthStr(int month) {
    	//creating months here
    	//getting the month from this random number set
    	//int findMonth = rng.nextInt(12-1+1)+1;
    	String currentMonth = "";
    	switch(month){
    	case 1:
    		if(month == 1) {
    			currentMonth = "Jan";
    		}
    		break;
    	case 2:
    		if(month == 2) {
    			currentMonth = "Feb";
    		}
    		break;
    	case 3:
    		if(month == 3) {
    			currentMonth = "Mar";
    		}
    		break;
    	case 4:
    		if(month == 4) {
    			currentMonth = "Apr";
    		}
    		break;
    	case 5:
    		if(month == 5) {
    			currentMonth = "May";
    		}
    		break;
    	case 6:
    		if(month == 6) {
    			currentMonth = "June";
    		}
    		break;
    	case 7:
    		if(month == 7) {
    			currentMonth = "July";
    		}
    		break;
    	case 8:
    		if(month == 8) {
    			currentMonth = "Aug";
    		}
    		break;
    	case 9:
    		if(month == 9) {
    			currentMonth = "Sep";
    		}
    		break;
    	case 10:
    		if(month == 10) {
    			currentMonth = "Oct";
    		}
    		break;
    	case 11:
    		if(month == 11) {
    			currentMonth = "Nov";
    		}
    		break;
    	case 12:
    		if(month == 12) {
    			currentMonth = "Dec";
    		}
    		break;
    	}
    	String rndMonth = currentMonth;
    	
    	
    	return rndMonth;
    }
    
    //creating a random user for the generate post

  //creating one random post object
    public Post generatePost() {
    	Post randPost = null;
    	//randomized type(image or text)
    	int postType = rng.nextInt(2-1+1)+1;
    	//creating a random timer
    	int hours = (11-1+1)+1;
    	int minutes = rng. nextInt(60-1+1)+1;
    	int amPm = rng.nextInt(2-1+1)+1;
    	String timeOfDay = "";
    	if(amPm == 1) {
    		timeOfDay = "AM";
    	}
    	else{
    		timeOfDay = "PM";
    	}
    	String clock = String.valueOf(hours) + ":" + String.valueOf(minutes)+ timeOfDay;
    	//if image post then generateImagePost(date, time)
    	if(postType == 1) {
    		//randomized content(image caption or text)
    		int randomIndex = rng.nextInt(12-1+1)+1;
    		String randMonth = getMonthStr(randomIndex); 
    		//System.out.println(randMonth + " " + randomIndex + " test month ran index" );
    		randPost = generateImagePost(randMonth, clock);
    		
    	}
    	//else if text post then generateTextPost(date, time)
    	else if(postType == 2) {
    		randPost = generateTextPost(getMonthStr(rng.nextInt(12-1+1)+1), "53");
    	}
    	//System.out.println(randPost.getDate() + " testing geDate ");
    	//randomized user
    	int randIndex = rng.nextInt(this.users.size()-1+1);
    	randPost.setUser(this.users.get(randIndex));
    	
    	//return the randomized post
    	return randPost;
    }
    
    //creates random posts objects and puts it into the array then returns array
    public Post[] generatePosts(int num) {
    	//call generatePost^ num times
    	Post[] outArray = new Post[num];
    	
    	for(int i = 0; i < num; i++) {
    		System.out.println(i + "generate post method");
    		outArray[i] = generatePost();
    	}
    	
    	return outArray;
    }
    
    
}
