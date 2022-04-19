package sudoku;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class UIColorTools extends JPanel implements MouseListener, ActionListener, ComponentListener {

	private static final long serialVersionUID = 6775383648829700492L;

	private static final int DEFAULT_BUTTON_SIZE = 36;
	private static final int PANEL_SIZE = DEFAULT_BUTTON_SIZE + DEFAULT_BUTTON_SIZE/2;

	private UIToggleButton btnColorVisible;
	private UIBorderedImagePanel switchColorRow;
	private SudokuPanel sudokuPanel;


	public UIColorTools() {

		super(true);

		this.setSize(PANEL_SIZE, PANEL_SIZE*2);
		this.setLayout(null);
		this.addComponentListener(this);

		Image imgVisibleOn = new ImageIcon(getClass().getResource("/img/visibility_on_64x64_cc0.png")).getImage();
		Image imgVisibleOff = new ImageIcon(getClass().getResource("/img/visibility_off_64x64_cc0.png")).getImage();

		ResourceBundle bundle = ResourceBundle.getBundle("intl/UIColorTools");

		btnColorVisible = new UIToggleButton(imgVisibleOn, imgVisibleOff);
		btnColorVisible.addMouseListener(this);
		btnColorVisible.setLocation(0, 0);
		btnColorVisible.setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		btnColorVisible.setToolTipText(bundle.getString("btnColorVisible.tooltip"));
		add(btnColorVisible);

		Image switchImage = new ImageIcon(getClass().getResource("/img/swap_color_arrow.png")).getImage();
		switchColorRow = new UIBorderedImagePanel(switchImage);
		switchColorRow.addMouseListener(this);
		switchColorRow.setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		switchColorRow.setLocation(DEFAULT_BUTTON_SIZE/10, 3*DEFAULT_BUTTON_SIZE/5);
		switchColorRow.setToolTipText(bundle.getString("switchColorRow.tooltip"));
		add(switchColorRow);
	}

	public void setSudokuPanel(SudokuPanel sudokuPanel) {
		this.sudokuPanel = sudokuPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnColorVisible) {
			sudokuPanel.setColorsVisible(btnColorVisible.isOn());
		}
		if (e.getSource() == switchColorRow) {
			sudokuPanel.swapColorRow();
		}
		sudokuPanel.repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentResized(ComponentEvent e) {
		btnColorVisible.setSize(getWidth(), getHeight()/2);
		switchColorRow.setSize(getWidth(), getHeight()/2);
		switchColorRow.setLocation(getWidth()/10, 3*getHeight() / 5);
		btnColorVisible.repaint();
		switchColorRow.repaint();
	}

	@Override
	public void componentShown(ComponentEvent e) {}
}
