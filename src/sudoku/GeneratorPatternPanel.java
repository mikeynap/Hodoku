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
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * A panel that allows to graphicallydefine a creation pattern.
 *
 * @author hobiwan
 */
public class GeneratorPatternPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	/** Default width of the border around the grid. */
	private int borderWidth = 5;
	/** The number of rows/columns in the sudoku. */
	private static final int UNITS = Sudoku2.UNITS;
	/** The actual pattern: <code>true</code> means"given has to be set". */
	private boolean[] pattern = null;
	/** The size of a single cell. */
	private int cellSize = -1;

	/** Creates new form GeneratorPatternPanel */
	public GeneratorPatternPanel() {
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

		setBackground(new java.awt.Color(255, 255, 255));
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				formMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * Toggles a cell in the pattern.
	 *
	 * @param evt
	 */
	private void formMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseClicked
		if (pattern == null || cellSize < 0 || evt.getButton() != MouseEvent.BUTTON1 || evt.getX() < borderWidth
				|| evt.getX() > (borderWidth + UNITS * cellSize) || evt.getY() < borderWidth
				|| evt.getY() > (borderWidth + UNITS * cellSize)) {
			// do nothing
			return;
		}

		int col = (evt.getX() - borderWidth) / cellSize;
		int row = (evt.getY() - borderWidth) / cellSize;
		int index = row * UNITS + col;
		pattern[index] = !pattern[index];
//        System.out.println("Mouse clicked: " + evt.getX() + "/" + evt.getY() + "/" + row + "/" + col + "/" + index + "/" + cellSize + "/" + borderWidth);
		updateAnzGivens();
		repaint();
	}// GEN-LAST:event_formMouseClicked

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		width = (width < height) ? width : height;
		height = (height < width) ? height : width;

		int offsetX = Math.max(getInsets().left, getInsets().right);
		int offsetY = Math.max(getInsets().top, getInsets().bottom);
		int bWidth = Math.max(offsetX, offsetY);
		borderWidth = Math.max(5, bWidth);
		cellSize = (width - 2 * borderWidth) / 9;

//        g2.translate(borderWidth, borderWidth);
		g2.setColor(Color.BLACK);
		if (pattern != null) {
			for (int i = 0; i < pattern.length; i++) {
				if (pattern[i]) {
					int row = i / UNITS;
					int col = i % UNITS;
					g2.fillRect(borderWidth + col * cellSize, borderWidth + row * cellSize, cellSize, cellSize);
				}
			}
		}
		int max = UNITS * cellSize;
		for (int i = 0; i <= UNITS; i++) {
			g2.drawLine(borderWidth + 0, borderWidth + i * cellSize, borderWidth + max, borderWidth + i * cellSize);
			g2.drawLine(borderWidth + i * cellSize, borderWidth + 0, borderWidth + i * cellSize, borderWidth + max);
		}
	}

	/**
	 * Updates the number of givens textfield in the {@link ConfigGeneratorPanel} in
	 * which this panel is placed.
	 */
	private void updateAnzGivens() {
		Component generatorPanel = getParent().getParent();
		if (generatorPanel instanceof ConfigGeneratorPanel) {
			int anz = 0;
			if (pattern != null) {
				for (int i = 0; i < pattern.length; i++) {
					if (pattern[i]) {
						anz++;
					}
				}
			}
			((ConfigGeneratorPanel) generatorPanel).setAnzGivens(anz);
		}
	}

	/**
	 * @return the pattern
	 */
	public boolean[] getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(boolean[] pattern) {
		this.pattern = pattern;
		repaint();
		updateAnzGivens();
	}
}
