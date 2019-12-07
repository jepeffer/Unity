package Unity;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This Object is a Label that I put the background image into. I use the
 * OverlayLayout on the Main form to set the Z index of this panel to the
 * lowest, so it serves as just the background image for Unity
 * 
 * @author Julian Peffer
 *
 */
@SuppressWarnings("serial")
public class BackgroundImage extends JPanel
{
	/**
	 * This is the X location of Background Image Label
	 */
	private final int	BG_X	= 0;
	/**
	 * This is the Y location of the Background Image Label
	 */
	private final int	BG_Y	= 0;
	/**
	 * The Label for the Image to be placed inside of
	 */
	public JLabel		background;
	
	/**
	 * This creates the Jlabel and places the image into it. This is than added to
	 * the panel which will be added to the OverlayLayout to serve as the background
	 * image
	 * 
	 * @throws IOException Catches if the image doesn't load right
	 */
	public BackgroundImage() throws IOException
	{
		this.setLocation(BG_X, BG_Y);
		this.setName("BackgroundImage");
		Image image = stretchImage();
		background = new JLabel(new ImageIcon(image));
		this.add(background);
		background.setLayout(new FlowLayout());
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}
	
	Image stretchImage() throws IOException
	{
		Image scaledImage = null;
		Image tempImage = ImageIO.read(BackgroundImage.class.getResource("/graphics/CUSbg3.jpg"));
		
		scaledImage = tempImage.getScaledInstance(Main.MainInstance.getScreenWidth(),
				Main.MainInstance.getScreenHeight(), Image.SCALE_DEFAULT);
		
		return scaledImage;
	}
}
