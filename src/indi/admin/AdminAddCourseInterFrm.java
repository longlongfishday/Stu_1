package indi.admin;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import indi.course.Course;
import indi.course.CourseDao;
import indi.teacher.TeacherDao;
import indi.tools.JDBCTools;
import indi.tools.StringTools;
import indi.user.UserDao;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class AdminAddCourseInterFrm extends JInternalFrame {
	private JTextField textField_1;
	private JComboBox getteacher;
	private CourseDao courseDao = new CourseDao();
	private JDBCTools jdbcTools = new JDBCTools();
	private TeacherDao teacherDao = new TeacherDao();
	private StringTools stringTools = new StringTools();
	private JTextField addcourseid;
	private int nextCourseId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddCourseInterFrm frame = new AdminAddCourseInterFrm();
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
	public AdminAddCourseInterFrm() {
		
		getContentPane().setBackground(new Color(255, 250, 205));
		setBackground(SystemColor.inactiveCaption);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 472, 371);
		
		JLabel label_1 = new JLabel("课程名称:");
		label_1.setIcon(new ImageIcon(AdminAddCourseInterFrm.class.getResource("/images/mingcheng.png")));
		
		JLabel label_2 = new JLabel("授课教师:");
		label_2.setIcon(new ImageIcon(AdminAddCourseInterFrm.class.getResource("/images/laoshi.png")));
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.setIcon(new ImageIcon(AdminAddCourseInterFrm.class.getResource("/images/true.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourse();
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setIcon(new ImageIcon(AdminAddCourseInterFrm.class.getResource("/images/false.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		getteacher = new JComboBox();
		getteacher.addItem("");
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			ResultSet rSet = courseDao.getAllTeacherName(connection);
			while (rSet.next()) {
			getteacher.addItem(rSet.getString("teachername"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel label = new JLabel("课程代码:");
		label.setIcon(new ImageIcon(AdminAddCourseInterFrm.class.getResource("/images/kechengdaima.png")));
		
		addcourseid = new JTextField();
		addcourseid.setEditable(false);
		addcourseid.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(addcourseid))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(getteacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(55)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)))
					.addContainerGap(153, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(47, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addcourseid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(getteacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(37))
		);
		getContentPane().setLayout(groupLayout);
		getNextCourseId();
	}

	private void resetValue() {
		textField_1.setText("");
		getteacher.setSelectedItem("");
	}

	private void getNextCourseId() {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			nextCourseId = courseDao.getNextCourseId(connection);
			addcourseid.setText(Integer.toString(nextCourseId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addCourse() {
		Course tmpcourse = new Course();
		tmpcourse.setCourseName(textField_1.getText());
		tmpcourse.setCourseId(nextCourseId);
		String teachername = (String) getteacher.getSelectedItem();
		if (stringTools.isEmpty(tmpcourse.getCouseName())) {
			JOptionPane.showMessageDialog(null, "课程名称不可为空!");
			return;
		}
		if (stringTools.isEmpty(teachername)) {
			JOptionPane.showMessageDialog(null, "授课教师不可为空!");
			return;
		}
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			ResultSet rSet = teacherDao.getTeacheridByTeahcerName(connection,teachername);
			while(rSet.next()) {
				tmpcourse.setTeacherId(rSet.getInt("teacherid"));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			connection = jdbcTools.getConnection();
			if (courseDao.addCourse(connection, tmpcourse)) {
				JOptionPane.showMessageDialog(null, "添加课程成功!");
				resetValue();
				getNextCourseId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
