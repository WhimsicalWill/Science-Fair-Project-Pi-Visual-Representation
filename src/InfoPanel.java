import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	private PiProject project;

	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 20);
	private static final Font MEDIUM_FONT = new Font("Tahoma", Font.BOLD, 16);
	private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
	
	 private static final int SMALL_OFFSET = 30;
     private static final int LARGE_OFFSET = 50;
     private static final int DIGIT_AMOUNT = 16;

	public InfoPanel(PiProject project) {
		this.project = project;
		setPreferredSize(new Dimension(300, 600));
		setBackground(Color.DARK_GRAY);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		
		g.setFont(LARGE_FONT);
		g.drawString("Information and Values", Center("Information and Values", g), 50);
		
		g.setFont(MEDIUM_FONT);
		g.setColor(Color.MAGENTA);
		g.drawString("Sides", SMALL_OFFSET, 150);
		g.drawString("Outside Polygon Perimeter", SMALL_OFFSET, 240);
		g.drawString("Inside Polygon Perimeter", SMALL_OFFSET, 300);
		g.drawString("Calculated Pi", SMALL_OFFSET, 390);
		g.drawString("True Pi", SMALL_OFFSET, 450);
		g.drawString("Time Running", SMALL_OFFSET, 550);
		g.setFont(SMALL_FONT);
		g.setColor(Color.CYAN);
		g.drawString(project.getSides() + "", LARGE_OFFSET, 175);
		
		try { 
			g.drawString((project.getOutsidePolygon() + "").substring(0, DIGIT_AMOUNT), LARGE_OFFSET, 265);
			g.drawString((project.getInsidePolygon() + "").substring(0, DIGIT_AMOUNT), LARGE_OFFSET, 325);
		} catch (Exception ex) {
			g.drawString(project.getOutsidePolygon() + "", LARGE_OFFSET, 265);
			g.drawString(project.getInsidePolygon() + "", LARGE_OFFSET, 325);
		}
		
		g.drawChars(project.getCalculatedPi(), 0, project.getCalculatedPi().length, LARGE_OFFSET, 415);
		g.drawString((Math.PI + "").substring(0, DIGIT_AMOUNT), LARGE_OFFSET, 475);
		g.drawString(project.getPassedTime(), LARGE_OFFSET, 575);
	}
	

	private int Center(String string, Graphics g) {
		 return getWidth() / 2 - g.getFontMetrics().stringWidth(string) / 2;
	}
}
