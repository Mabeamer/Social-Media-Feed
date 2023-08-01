import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagePost extends Post{
	private String caption;
	private Image image;
	
	//setting constructor here
	ImagePost(Image image, String Caption){
		this.image = image;
		caption = Caption;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public Image getImage() {
		return image;
	}
	//check out what image object is or create one
	public void setImage(Image myImage) throws IOException {
		File f = new File("mech_eng.png");
		 Image image = ImageIO.read(f);
	}
}
