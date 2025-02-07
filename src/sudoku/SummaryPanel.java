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
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import solver.SudokuSolver;

/**
 *
 * @author hobiwan
 */
public class SummaryPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	private MainFrame mainFrame;
	private SummaryTableModel model;

	/**
	 * Creates new form SummaryPanel
	 *
	 * @param mainFrame
	 */
	public SummaryPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		initComponents();

		model = new SummaryTableModel();
		summaryTable.setModel(model);
		summaryTable.setDefaultRenderer(Object.class, new SummaryTableRenderer());
		TableColumn column = null;

		for (int i = 0; i < 3; i++) {
			column = summaryTable.getColumnModel().getColumn(i);
			if (i == 0 || i == 2) {
				column.setPreferredWidth(10);
			} else {
				column.setPreferredWidth(200);
			}
		}

		FontMetrics metrics = getFontMetrics(getFont());
		int rowHeight = (int) (metrics.getHeight() * 1.1);
		summaryTable.setRowHeight(rowHeight);
		int fontSize = 12;

		if (getFont().getSize() > 12) {
			fontSize = getFont().getSize();
		}

		Font font = titleLabel.getFont();
		titleLabel.setFont(new Font(font.getName(), Font.BOLD, fontSize));
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		summaryTable = new javax.swing.JTable();
		titleLabel = new javax.swing.JLabel();

		setLayout(new java.awt.BorderLayout());

		summaryTable.setModel(
			new javax.swing.table.DefaultTableModel(
				new Object[][] {},
				new String[] {}
			)
		);
		summaryTable.setCellSelectionEnabled(true);
		summaryTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				summaryTableMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(summaryTable);

		add(jScrollPane1, java.awt.BorderLayout.CENTER);

		titleLabel.setBackground(new java.awt.Color(51, 51, 255));
		titleLabel.setForeground(new java.awt.Color(255, 255, 255));
		titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("intl/SummaryPanel");
		titleLabel.setText(bundle.getString("SummaryPanel.titleLabel.text"));
		titleLabel.setOpaque(true);
		add(titleLabel, java.awt.BorderLayout.PAGE_START);
	}

	private void summaryTableMouseClicked(java.awt.event.MouseEvent evt) {
		mainFrame.fixFocus();
	}

	public void setTitleLabelColors(Color fore, Color back) {
		titleLabel.setBackground(back);
		titleLabel.setForeground(fore);
	}

	public void initialize(SudokuSolver solver) {
		model.initialize(solver);
	}

	class SummaryTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		String[] columnNames = {
			java.util.ResourceBundle.getBundle("intl/SummaryPanel").getString("SummaryPanel.count"),
			java.util.ResourceBundle.getBundle("intl/SummaryPanel").getString("SummaryPanel.technique"),
			java.util.ResourceBundle.getBundle("intl/SummaryPanel").getString("SummaryPanel.score")
		};

		Object[][] content = { { "", "", "", null } };

		public void initialize(SudokuSolver solver) {

			if (solver != null) {
				content = new Object[solver.getAnzUsedSteps() + 1][4];
				int[] anzSteps = solver.getAnzSteps();
				int index = 0;
				for (int i = 0; i < anzSteps.length; i++) {
					if (anzSteps[i] > 0) {
						StepConfig config = Options.getInstance().solverSteps[i];
						content[index][0] = Integer.toString(anzSteps[i]);
						content[index][1] = config.getType().getStepName();
						content[index][2] = Integer.toString(anzSteps[i] * config.getBaseScore() + config.getAdminScore());
						content[index][3] = Options.getInstance().getDifficultyLevels()[config.getLevel()].getBackgroundColor();
						index++;
					}
				}

				content[index][1] = java.util.ResourceBundle.getBundle("intl/SummaryPanel").getString("SummaryPanel.sum");
				content[index][2] = Integer.toString(solver.getScore());

			} else {

				content = new Object[1][4];
				content[0][0] = "";
				content[0][1] = "";
				content[0][2] = "";
				content[0][3] = null;
			}

			fireTableDataChanged();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return content.length;
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public Object getValueAt(int row, int col) {
			return content[row][col];
		}

	}

	class SummaryTableRenderer extends JLabel implements TableCellRenderer {

		private static final long serialVersionUID = 1L;
		private Color backColor;

		SummaryTableRenderer() {
			setOpaque(true);
			backColor = getBackground();
		}

		@Override
		public Component getTableCellRendererComponent(
				JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row,
				int column) {

			if (model.content[row][3] != null) {
				setBackground((Color) model.content[row][3]);
			} else {
				setBackground(backColor);
			}

			String text = (value != null) ? value.toString() : "";
			if (column == 0 || column == 2) {
				setHorizontalAlignment(SwingConstants.RIGHT);
				text += " ";
			} else {
				setHorizontalAlignment(SwingConstants.LEFT);
				text = " " + text;
			}

			setText(text);
			return this;
		}
	}

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable summaryTable;
	private javax.swing.JLabel titleLabel;
}
