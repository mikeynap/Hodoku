/*
 * Copyright (C) 2008-12  Bernhard Hobiger
 *
 * This file is part of HoDoKu.
 *
 * HoDoKu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HoDoKu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HoDoKu. If not, see <http://www.gnu.org/licenses/>.
 */
package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author hobiwan
 */
public class StatusColorPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private boolean reset = false;
	private Font font = null;
	private int index = -1;

	/**
	 * Creates new form StatusColorPanel
	 *
	 * @param index
	 */
	public StatusColorPanel(int index) {

		this.index = index;
		if (index == -2) {
			reset = true;
			font = new Font(Font.SANS_SERIF, Font.PLAIN, 11);
			// font = new Font("Tahoma",Font.BOLD, 12);
		}

		setColor();
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 15, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 15, Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	@Override
	protected void paintComponent(Graphics g) {
		setColor();

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int w = getWidth() - 1;
		int h = getHeight() - 1;

		g2.fillRect(0, 0, w, h);
//        g2.setColor(Color.BLACK);
//        g2.drawLine(1, 0, 1, h);
//        g2.drawLine(1, 1, w, 1);
		g2.setColor(Color.LIGHT_GRAY);
		g2.drawLine(w, 0, w, h);
		g2.drawLine(0, h, w, h);
		g2.setColor(Color.DARK_GRAY);
		g2.drawLine(0, 0, w, 0);
		g2.drawLine(0, 0, 0, h);

		if (reset) {
			g2.setFont(font);
			FontMetrics metrics = g2.getFontMetrics();
			String output = java.util.ResourceBundle.getBundle("intl/MainFrame")
					.getString("MainFrame.statusPanelReset.text");
			int height = metrics.getAscent();
			int width = metrics.stringWidth(output);
			g2.drawString(output, (w / 2) - (width / 2) + 0, (h / 2) + (height / 2) - 1);
		}
	}

	private void setColor() {
		Color back = null;
		if (index >= 0) {
			back = Options.getInstance().getColoringColors()[index];
		} else {
			back = Options.getInstance().getDefaultCellColor();
		}
		setForeground(back);
		setBackground(back);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables

}
