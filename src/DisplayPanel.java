import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {

	private PiProject project;
	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 35);
	private int circleDiameter = 360;
	private int circleRadius = circleDiameter / 2;
	private int circleOffset = (600 - circleDiameter) / 2;

	public DisplayPanel(PiProject project) {
		this.project = project;

		setPreferredSize(new Dimension(600, 600));
		setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.WHITE);
		g.fillOval(circleOffset, circleOffset, circleDiameter, circleDiameter);

		if (project.getSides() >= project.MAX_SIDES) {
			g.setColor(Color.BLUE);
			g.setFont(LARGE_FONT);
			g.drawString("Pi Complete!", CenterX("Pi Complete!", g), CenterY(g) + 35);
		}

		if (project.drawPolygons() && project.getSides() < 500000) {
			drawInsidePolygon(project.getSides(), g);
			drawOutsidePolygon(project.getSides(), g);
		}
	}

	private void drawInsidePolygon(double sides, Graphics g) {
		g.setColor(Color.BLACK);
		Polygon p = new Polygon();
		double x;
		double y;
		for (int i = 0; i < sides; i++) {
			x = (Math.cos(Math.toRadians((360 / sides) * i)) * circleRadius) + 300;
			y = (Math.sin(Math.toRadians((360 / sides) * i)) * circleRadius) + 300;
			p.addPoint((int) x, (int) y);
		}
		g.drawPolygon(p);
	}

	private void drawOutsidePolygon(double sides, Graphics g) {
		g.setColor(Color.BLACK);
		Polygon p = new Polygon();
		double radian = 1 / (Math.cos(Math.toRadians((360 / sides) / 2)));
		double x;
		double y;
		for (int i = 0; i < sides; i++) {
			x = (Math.cos(Math.toRadians((360 / sides) * i)) * radian)
					* circleRadius + 300;
			y = (Math.sin(Math.toRadians((360 / sides) * i)) * radian)
					* circleRadius + 300;
			p.addPoint((int) x, (int) y);
		}
		g.drawPolygon(p);
	}

	private int CenterX(String string, Graphics g) {
		return getWidth() / 2 - g.getFontMetrics().stringWidth(string) / 2;
	}
	
	private int CenterY(Graphics g) {
		return getHeight() / 2 - g.getFontMetrics().getHeight() / 2;
	}

}
