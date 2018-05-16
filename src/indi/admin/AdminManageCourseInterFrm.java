package indi.admin;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import indi.course.Course;
import indi.course.CourseDao;
import indi.tools.JDBCTools;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminManageCourseInterFrm extends JInternalFrame {
	//private Course course = new Course();
	private CourseDao courseDao = new CourseDao();
	private JDBCTools jdbcTools = new JDBCTools();
	private JTextField selectcoursename;
	private JTextField selectteachername;
	private JTextField selectclassroom;
	private JTable resultcoursetable;
	private JTextField getcoursename;
	private JTextField getclassroom;
	private JComboBox setday;
	private JComboBox getday;
	private JComboBox gethour;
	private JComboBox sethour;
	private JComboBox getteacher;
	private JTextField getcourseid;
//	private ArrayList <String>  strArray;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminManageCourseInterFrm frame = new AdminManageCourseInterFrm();
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
	public AdminManageCourseInterFrm() {
		setTitle("管理课程信息");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 806, 490);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u7B5B\u9009\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
				.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
		);
		
		Integer []daylabels = {null,1,2,3,4,5,6,7};
		Integer []hourlabels = {null,1,2,3,4,5,6};
		
		JLabel label_4 = new JLabel("课程名称:");
		
		getcoursename = new JTextField();
		getcoursename.setColumns(10);
		
		JLabel label_5 = new JLabel("星期几:");

		getday = new JComboBox(daylabels);
		
		JLabel label_6 = new JLabel("教师姓名:");
		
		JLabel label_7 = new JLabel("第几节课:");
		
		gethour = new JComboBox(hourlabels);
		
		JLabel label_8 = new JLabel("教室:");
		
		getclassroom = new JTextField();
		getclassroom.setColumns(10);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatecoursedata();
			}
		});
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletecoursedata();
			}
		});
		
		Connection connection;
		
		getteacher = new JComboBox();
		getteacher.addItem("");
		try {
			connection = jdbcTools.getConnection();
			ResultSet rSet = courseDao.getAllTeacherName(connection);
			while (rSet.next()) {
			getteacher.addItem(rSet.getString("teachername"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel label_9 = new JLabel("课程代码:");
		
		getcourseid = new JTextField();
		getcourseid.setEditable(false);
		getcourseid.setColumns(10);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_5)
								.addComponent(label_9))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(getday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
									.addComponent(label_7))
								.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
									.addComponent(getcourseid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
									.addComponent(label_4)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(getcoursename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(gethour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton)
							.addGap(213)))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(88)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_8)
								.addComponent(label_6))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(getteacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(getclassroom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_1)))
					.addGap(77))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_5)
								.addComponent(getday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(getteacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_6))
									.addGap(20))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(getcoursename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4)
										.addComponent(label_9)
										.addComponent(getcourseid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)))
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_7)
								.addComponent(gethour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_8)
								.addComponent(getclassroom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(button_1))
					.addGap(29))
		);
		panel_2.setLayout(gl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		resultcoursetable = new JTable();
		resultcoursetable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getSelectCourseData();
			}
		});
		resultcoursetable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u4EE3\u7801", "\u8BFE\u7A0B\u540D\u79F0", "\u6559\u5E08\u5DE5\u53F7", "\u6559\u5E08\u59D3\u540D", "\u661F\u671F\u51E0", "\u7B2C\u51E0\u8282", "\u6559\u5BA4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(resultcoursetable);
		panel_1.setLayout(gl_panel_1);
		
		JLabel label = new JLabel("课程名称:");
		
		selectcoursename = new JTextField();
		selectcoursename.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("教师姓名:");
		
		selectteachername = new JTextField();
		selectteachername.setColumns(10);
		
		JLabel label_1 = new JLabel("教室:");
		
		selectclassroom = new JTextField();
		selectclassroom.setColumns(10);
		
		setday = new JComboBox(daylabels);
		
		JLabel label_2 = new JLabel("星期几:");
		
		JLabel label_3 = new JLabel("第几节课:");
		
		sethour = new JComboBox(hourlabels);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCourseData();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(selectcoursename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(setday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(89)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectteachername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sethour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(93)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectclassroom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
							.addComponent(button)
							.addGap(100))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(selectteachername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(sethour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(selectcoursename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1)
								.addComponent(selectclassroom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(setday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	
	
	
	private void deletecoursedata() {
		String courseid = getcourseid.getText();
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			courseDao.deleteCourse(connection, courseid);
			JOptionPane.showMessageDialog(null, "删除课程成功!");
			selectCourseData();
			resetValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updatecoursedata() {
		String teachername;
		int row = resultcoursetable.getSelectedRow();
		Course tmpcourse = new Course();
		tmpcourse.setClassRoom(getclassroom.getText());
		tmpcourse.setCourseDay(toInt(getday.getSelectedItem()));
		tmpcourse.setCourseHour(toInt(gethour.getSelectedItem()));
		tmpcourse.setCourseName(getcoursename.getText());
		tmpcourse.setTeacherId(toInt(resultcoursetable.getValueAt(row, 2)));
		tmpcourse.setCourseId(Integer.parseInt(getcourseid.getText()));
		if (((String)getteacher.getSelectedItem()).equals(resultcoursetable.getValueAt(row, 3))) {
			teachername = null;
		}else {
			teachername = (String) getteacher.getSelectedItem();
		}
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			courseDao.updateCourseData(connection, tmpcourse, teachername);
			selectCourseData();
			JOptionPane.showMessageDialog(null,"更新课程成功!");
			resetValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void resetValue() {
		getclassroom.setText("");
		getcourseid.setText("");
		getcoursename.setText("");
		getday.setSelectedItem(null);
		gethour.setSelectedItem(null);
		getteacher.setSelectedItem("");
	}

	private int toInt(Object o) {
		if(o instanceof Integer) {
			return (Integer) o;
		} else if(o instanceof String) {
			return Integer.parseInt((String) o);
		}
		return 0;
	}
	
	private void getSelectCourseData() {
		int row = resultcoursetable.getSelectedRow();
		getcourseid.setText((String) resultcoursetable.getValueAt(row, 0));
		getcoursename.setText((String) resultcoursetable.getValueAt(row, 1));
		getday.setSelectedItem(toInt(resultcoursetable.getValueAt(row, 4)));
		gethour.setSelectedItem(toInt(resultcoursetable.getValueAt(row, 5)));
		getclassroom.setText((String) resultcoursetable.getValueAt(row, 6));
		getteacher.setSelectedItem((String) resultcoursetable.getValueAt(row, 3));
	}

	private int objectToInt(Object o) {
		if (o == null) {
			return 0;
		}else {
			return (Integer)o;
		}
	}
	
	private void selectCourseData() {
		Course tmpcourse = new Course();
		String teachername = selectteachername.getText();
		tmpcourse.setClassRoom(selectclassroom.getText());
		tmpcourse.setCourseDay(objectToInt(setday.getSelectedItem()));
		tmpcourse.setCourseHour(objectToInt(sethour.getSelectedItem()));
		tmpcourse.setCourseName(selectcoursename.getText());
		Connection connection = null;
		DefaultTableModel defaultTableModel = (DefaultTableModel) resultcoursetable.getModel();
		defaultTableModel.setRowCount(0);
		try {
			connection = jdbcTools.getConnection();
			ResultSet resultSet = courseDao.selectByOption(connection, tmpcourse, teachername);
			while(resultSet.next()) {
				Vector vector = new Vector();
				vector.add(resultSet.getString("courseid"));
				vector.add(resultSet.getString("coursename"));
				vector.add(resultSet.getString("teacherid"));
				vector.add(resultSet.getString("teachername"));
				vector.add(resultSet.getString("courseday"));
				vector.add(resultSet.getString("coursehour"));
				vector.add(resultSet.getString("classroom"));
				defaultTableModel.addRow(vector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
