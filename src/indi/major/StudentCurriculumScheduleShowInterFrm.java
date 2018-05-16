package indi.major;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import indi.tools.JDBCTools;

public class StudentCurriculumScheduleShowInterFrm extends JInternalFrame {
	private JTable StudentCurriculumScheduletable;
	private JDBCTools jdbcTools = new JDBCTools();
	private MajorDao scheduleDao = new MajorDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentCurriculumScheduleShowInterFrm frame = new StudentCurriculumScheduleShowInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentCurriculumScheduleShowInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("学生课表");
		setBounds(100, 100, 603, 155);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
		);
		
		StudentCurriculumScheduletable = new JTable();
		StudentCurriculumScheduletable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"\u5468\u4E00", "\u5468\u4E8C", "\u5468\u4E09", "\u5468\u56DB", "\u5468\u4E94", "\u5468\u516D", "\u5468\u65E5"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		StudentCurriculumScheduletable.getColumnModel().getColumn(0).setMinWidth(55);
		scrollPane.setViewportView(StudentCurriculumScheduletable);
		getContentPane().setLayout(groupLayout);
		scheduleDao.fillScheduleTable(StudentCurriculumScheduletable);
	}
}
