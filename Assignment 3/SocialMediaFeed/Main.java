
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;


/**
 *
 * @author smcleo12
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        /*
            1. Create some User objects...        
        */
        String h = "Hello world!";
        String b = "Goodbye World!";
        

        /*
            2. Put the User objects into an ArrayList        
        */
        //Post[] Text = new Post[2];
        ArrayList<User> user = new ArrayList<User>();
        User u = new User("user1");
        user.add(u);
        User k = new User("user2");
        user.add(k);
        User z = new User("user3");
        user.add(z);
        
        /*
        3. Use your PostGenerator class to create posts from the users            
         */ 
        PostGenerator pg = new PostGenerator(user);
        //pg.generatePost();
        Post[] Text = new Post[user.size()];
        /*for(int i = 0; i < user.size();i++) {
            Text[i] = pg.generateTextPost("11 - 20" , "Fri");
            Text[i].setUser(user.get(i));
        }*/
        
       
        /*
            Create the GUI window
            DO NOT ADD OR CHANGE CODE BELOW THIS POINT EXCEPT WHERE SPECIFIED        
        */
        Window myGUI = new Window();
        myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGUI.pack();
        myGUI.setVisible(true);
        
        // ADD YOUR ARRAY OF POSTS AS AN ARGUMENT TO THIS METHOD CALL
        Text = pg.generatePosts(5);
        myGUI.displayPosts(Text);
    }
    
}
