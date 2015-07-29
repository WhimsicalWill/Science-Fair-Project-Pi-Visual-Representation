//IMPORT JAVA TOOLS AND EXTRA STUFF
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class PiProject extends JFrame {

	// DEFINE VARIABLES
	private static final long serialVersionUID = 1L;
	public static final int MAX_SIDES = 39163617;
	private DisplayPanel display;
	private InfoPanel info;
	private ControlsPanel controls;
	private double sides;
	private double radiansIn, radiansOut;
	private double insidePolygon = 0.00000000000000;
	private double outsidePolygon = 0.00000000000000;
	private char[] calculatedPi = new char[16];
	private int Speed = 1;
	private long startTime;
	private long currentTime;
	private long PauseStart;
	private long PauseDuration;
	private long latestIncrease;
	private boolean Paused = false;
	private boolean drawPolygons = true;

	// INITIALIZE GRAPHICS
	private PiProject() {
		super("Pi Visual Representation - by Will Knipe");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		this.display = new DisplayPanel(this);
		this.info = new InfoPanel(this);
		this.controls = new ControlsPanel(this);

		add(display, BorderLayout.CENTER);
		add(info, BorderLayout.EAST);
		add(controls, BorderLayout.WEST);

		addKeyListener(new KeyAdapter() {
			// PROGRAM KEYBOARD CONTROLS
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {

				case KeyEvent.VK_RIGHT:
					if (Speed < 10)
						Speed++;
					else
						Speed = 1;
					break;

				case KeyEvent.VK_LEFT:
					if (Speed > 1)
						Speed--;
					else
						Speed = 10;
					break;

				case KeyEvent.VK_SPACE:
					Paused = !Paused;
					if (Paused)
						PauseStart = System.currentTimeMillis();
					else {
						PauseDuration = System.currentTimeMillis() - PauseStart;
						startTime += PauseDuration;
					}
					break;

				case KeyEvent.VK_ENTER:
					restart();
					break;
				case KeyEvent.VK_SHIFT:
					drawPolygons = !drawPolygons;
					break;
				}
			}

		});

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// PROGRAM MAIN LOOP
	private void start() {
		sides = 3;
		startTime = System.currentTimeMillis();
		latestIncrease = System.currentTimeMillis();
		// Main Loop. This loop is continuously working until the program is
		// closed.
		while (true) {
			if (!Paused)
				currentTime = System.currentTimeMillis();

			if ((currentTime - latestIncrease > 1000 / (2 << (Speed - 1)) || Speed == 10)
					&& !Paused && sides <= MAX_SIDES - 1) {
				sides++;
				calculateInsidePolygon(sides);
				calculateOutsidePolygon(sides);
				calculatePi(insidePolygon, outsidePolygon);
				display.repaint();
				latestIncrease = System.currentTimeMillis();
			}

			if (sides <= MAX_SIDES - 1) {
				info.repaint();
				controls.repaint();
			}
			// In order to draw the "Pi Complete" text
			else
				display.repaint();

		}
	}

	// RESEST VARIABLES
	public void restart() {
		insidePolygon = 0;
		outsidePolygon = 0;
		clearArray(calculatedPi);
		sides = 2;
		startTime = System.currentTimeMillis();
		latestIncrease = System.currentTimeMillis();
		Paused = false;
	}

	// CALCULATE INSIDE POLYGON PERIMETER
	private void calculateInsidePolygon(double sides) {
		radiansIn = Math.toRadians(180 / sides);
		insidePolygon = sides * Math.sin(radiansIn);

	}

	// CALCULATE OUTSIDE POLYGON PERIMETER
	private void calculateOutsidePolygon(double sides) {
		radiansOut = Math.toRadians(180 / sides);
		outsidePolygon = sides * Math.tan(radiansOut);
	}

	// COMPARE OUTSIDE AND INSIDE POLYGONS AND CONFIRM OUR APPROXIMATED PI
	private void calculatePi(double insidePolygon, double outsidePolygon) {
		String outsideString = Double.toString(outsidePolygon);
		String insideString = Double.toString(insidePolygon);
		char outsideDigit;
		char insideDigit;
		for (int i = 0; i <= 15; i++) {
			outsideDigit = outsideString.charAt(i);
			insideDigit = insideString.charAt(i);
			if (outsideDigit == insideDigit) {
				calculatedPi[i] = insideDigit;
			} else
				break;
		}
	}

	// CLEAR ARRAY FOR RESTART
	public void clearArray(char[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	// COMMUNICATE TO GRAPHIC PANELS
	public double getInsidePolygon() {
		return insidePolygon;
	}

	public double getOutsidePolygon() {
		return outsidePolygon;
	}

	public char[] getCalculatedPi() {
		return calculatedPi;
	}

	public double getSides() {
		return sides;
	}

	public boolean isPaused() {
		return Paused;
	}

	public int getSpeed() {
		return Speed;
	}

	public boolean drawPolygons() {
		return drawPolygons;
	}

	// CLOCK
	public String getPassedTime() {
		String time = currentTime - startTime + "";
		if (time.length() > 3)
			return time.substring(0, time.length() - 3) + " seconds";
		else
			return "0 seconds";

	}

	// PROGRAM START INSTRUCTIONS
	public static void main(String[] args) {
		PiProject pi = new PiProject();
		pi.start();

	}

}
