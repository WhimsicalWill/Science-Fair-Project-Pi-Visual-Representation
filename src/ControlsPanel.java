import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class ControlsPanel extends JPanel {
	
	private PiProject project;
	
	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 20);
	private static final Font MEDIUM_FONT = new Font("Tahoma", Font.BOLD, 16);
	private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
	private static final Font MINI_FONT = new Font("Tahoma", Font.BOLD, 10);
	
	 private static final int SMALL_OFFSET = 30;
     private static final int LARGE_OFFSET = 50;

	public ControlsPanel(PiProject project) {
		this.project = project;
		

		setPreferredSize(new Dimension(300, 600));
		setBackground(Color.DARK_GRAY);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.WHITE);
		
		g.setFont(LARGE_FONT);
		g.drawString("Program Controls", Center("Program Controls", g), 50);
		
		g.setFont(MEDIUM_FONT);
		g.setColor(Color.MAGENTA);
		g.drawString("Speed", Center("Speed", g), 150);
		g.drawString("Controls", SMALL_OFFSET, 240);
		
		g.setFont(SMALL_FONT);
		g.setColor(Color.CYAN);
		g.drawString("1  2  3  4  5  6  7  8  9  10", Center("1  2  3  4  5  6  7  8  9  10", g), 175);
		g.drawString("Right Arrow Key: Increase Speed", LARGE_OFFSET, 265);
		g.drawString("Left Arrow Key: Decrease Speed", LARGE_OFFSET, 290);
		g.drawString("Space Bar: Pause", LARGE_OFFSET, 315);
		g.drawString("Enter: Restart", LARGE_OFFSET, 340);
		g.drawString("Shift: Toggle Polygons", LARGE_OFFSET, 365);
		
		g.setFont(MINI_FONT);
		g.drawString("Note: The polygons are no longer plotted", Center("Note: The polygons are no longer plotted", g), 545);
		g.drawString("when there are more than 500,000 sides.", Center("when there are more than 500,000 sides.", g), 565);
		
		if (project.isPaused()) {
			g.setFont(LARGE_FONT);
			g.setColor(Color.GREEN);
			g.drawString("Paused", Center("Paused", g), 590);
		}
			
		drawSpeed(g, project.getSpeed());
		
	}
	
		//This method draws the red number that shows the program's current speed
		public void drawSpeed(Graphics g, int Speed) {
			String SpeedString = "";
			for (int i = 0; i < 36 - 4 * (10 - Speed); i++) {
				SpeedString += " ";
			}
			SpeedString += Speed;
			
			g.setFont(SMALL_FONT);
			g.setColor(Color.RED);
			g.drawString(SpeedString, Center("1  2  3  4  5  6  7  8  9  10", g), 175);
		}
		
		private int Center(String string, Graphics g) {
			 return getWidth() / 2 - g.getFontMetrics().stringWidth(string) / 2;
		}
	
}
