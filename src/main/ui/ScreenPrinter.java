package ui;

import java.awt.Component;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Event;
import model.EventLog;

public class ScreenPrinter extends JInternalFrame implements LogPrinter {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
	private JTextArea logArea;


    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            logArea.setText(logArea.getText() + next.toString() + "\n\n");
        }

		repaint();
    }

    public ScreenPrinter(Component parent) {
		super("Event log", false, true, false, false);
		logArea = new JTextArea();
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea);
		add(scrollPane);
		setSize(WIDTH, HEIGHT);
		setPosition(parent);
		setVisible(true);
	}

    private void setPosition(Component parent) {
		setLocation(parent.getWidth() - getWidth() - 20,
				parent.getHeight() - getHeight() - 20);
	}
	
}