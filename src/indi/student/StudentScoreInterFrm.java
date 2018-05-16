package indi.student;

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

import indi.major.MajorDao;
import indi.tools.JDBCTools;

public class StudentScoreInterFrm extends JInternalFrame {
	private JTable studentexamtable;
	private JDBCTools jdbcTools = new JDBCTools();
	private MajorDao majorDao = new MajorDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentScoreInterFrm frame = new StudentScoreInterFrm();
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
	public StudentScoreInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("查看成绩");
		setBounds(100, 100, 450, 242);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
		);
		
		studentexamtable = new JTable();
		studentexamtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u4EE3\u7801", "\u8BFE\u7A0B\u540D\u79F0", "\u5E73\u65F6\u6210\u7EE9", "\u671F\u4E2D\u6210\u7EE9", "\u5B9E\u9A8C\u6210\u7EE9", "\u671F\u672B\u6210\u7EE9", "\u603B\u5206"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(studentexamtable);
		getContentPane().setLayout(groupLayout);
		 fillStudentExamTable();
	}
	
	private void fillStudentExamTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) studentexamtable.getModel();
		defaultTableModel.setRowCount(0);
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			ResultSet rSet = majorDao.getStudentAllExam(connection);
			while(rSet.next()) {
				Vector vector = new Vector();
				vector.add(rSet.getInt("courseid"));
				vector.add(rSet.getString("coursename"));
				vector.add(rSet.getInt("ordinaryscore"));
				vector.add(rSet.getInt("midscore"));
				vector.add(rSet.getInt("expermentscore"));
				vector.add(rSet.getInt("lastscore"));
				vector.add(rSet.getInt("totalscore"));
				defaultTableModel.addRow(vector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
