
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smcleo12
 */
public class Window extends JFrame {
    
    File imageDirectory;
    File[] images;
    String[] texts;
    Random rng;
    
    JPanel titlePanel;
    JPanel postsPanel;
    
    public Window() throws Exception
    {        
        // Set up panels
        titlePanel = new JPanel(new GridBagLayout());
        postsPanel = new JPanel(new GridBagLayout());
        
        // Set various GUI settings
        setTitle("Social Media Feed");
        setPreferredSize(new Dimension(450,1200));
        setLayout(new GridBagLayout());

        // Add the panels
        GridBagConstraints positionConst = new GridBagConstraints();
        positionConst.gridx = 0;
        positionConst.gridy = 0;
        add(titlePanel, positionConst);
        
        positionConst.gridx = 0;
        positionConst.gridy = 1;
        add(postsPanel, positionConst);
    }
    
    
    private void addPostComponents(Post[] posts) throws Exception
    {
        int y=0;
        for(Post post : posts)
        {
            JComponent postComponent;
            GridBagConstraints positionConst;
            
            /*
            Sets and creates username label
            */
            postComponent = new JLabel();
            positionConst = new GridBagConstraints();
            ((JLabel)postComponent).setText(post.getUser().getName());
            
            // Add label to the panel
            positionConst.anchor = GridBagConstraints.LINE_START;
            positionConst.gridx = 0;
            positionConst.gridy = y;
            positionConst.insets = new Insets(10,10,10,10);
            postsPanel.add(postComponent, positionConst);
            
            
            
            /*
            Sets and creates time and date label
            */
            postComponent = new JLabel();
            positionConst = new GridBagConstraints();
            ((JLabel)postComponent).setText(post.getDateAndTime());
            
            // Add label to the panel
            positionConst.anchor = GridBagConstraints.LINE_END;
            positionConst.gridx = 1;
            positionConst.gridy = y++;
            postsPanel.add(postComponent, positionConst);
            
            // Text post
            if(post instanceof TextPost)
            {               
                // Create a JTextArea to hold the text
                postComponent = new JTextArea();
                ((JTextArea)postComponent).setText(((TextPost) post).getText());
                
                // Set the position of the text area
                positionConst = new GridBagConstraints();
                positionConst.gridx = 0;
                positionConst.gridy = y++;
                positionConst.gridwidth = 2;
                positionConst.insets = new Insets(10,10,10,10);
                
                // Add the text area to the panel
                postsPanel.add(postComponent, positionConst);
            }
            
            // Image post
            else if(post instanceof ImagePost)
            {
                // ImageIcon for images
                postComponent = new JLabel(new ImageIcon(((ImagePost) post).getImage()));
                
                // Set the position
                positionConst = new GridBagConstraints();
                positionConst.gridx = 0;
                positionConst.gridwidth = 2;
                positionConst.gridy = y++;
                positionConst.insets = new Insets(10,10,10,10);
                
                // Add the image to the panel
                postsPanel.add(postComponent, positionConst);

                // Use a JLabel for the image caption
                postComponent = new JLabel();
                ((JLabel)postComponent).setText(((ImagePost) post).getCaption());
                
                // Set the position
                positionConst = new GridBagConstraints();
                positionConst.gridwidth = 2; 
                positionConst.gridy = y++;   
                positionConst.anchor = GridBagConstraints.CENTER;
                positionConst.insets = new Insets(10,10,10,10);
                
                // Add the label to the panel
                postsPanel.add(postComponent, positionConst);
            }
            
            // Use a JSeparator to visually separate the posts
            JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
            sep.setPreferredSize(new Dimension(1,50)); 
            
            // Set position
            positionConst.gridy = y++;    
            positionConst.fill = GridBagConstraints.HORIZONTAL;
            
            // Add the separator
            postsPanel.add(sep, positionConst);
        }
    }
    
    
    public void displayPosts(Post[] posts) throws Exception
    {
        // Add a header for the user
        JTextArea userTitle = new JTextArea();
        userTitle.setText("Your Feed");
        userTitle.setFont(userTitle.getFont().deriveFont(18f));
        
        // Set up the position for the user header
        GridBagConstraints positionConst = new GridBagConstraints();
        positionConst.gridwidth = 2;
        positionConst.gridy = 0;
        positionConst.anchor = GridBagConstraints.CENTER;
        positionConst.insets = new Insets(10,10,10,10);
        
        // Add the header to the panel
        titlePanel.add(userTitle, positionConst);
        
        // Add the posts
       
        addPostComponents(posts);
        
        // Pack GUI
        pack();
    }

    
    
    
}
