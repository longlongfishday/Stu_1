package indi.admin;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import indi.student.Student;
import indi.student.StudentDao;
import indi.teacher.TeacherDao;
import indi.tools.Constants;
import indi.tools.JDBCTools;
import indi.tools.StringTools;
import indi.user.User;
import indi.user.UserDao;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.jar.Attributes.Name;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminAddUserInterFrm extends JInternalFrame {
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton isteacher ;
	private JRadioButton isstudent;
	private Constants constants = new Constants();
	private JDBCTools jdbcTools = new JDBCTools();
	private StudentDao studentDao = new StudentDao();
	private TeacherDao teacherDao = new TeacherDao();
	private JTextField textField_2;
	private StringTools stringTools = new StringTools();
	private JTextField setuserid;
	private UserDao userDao = new UserDao();
	private int nextUserId = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddUserInterFrm frame = new AdminAddUserInterFrm();
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
	public AdminAddUserInterFrm() {
		setFrameIcon(new ImageIcon(AdminAddUserInterFrm.class.getResource("/images/denglu.png")));
		getContentPane().setBackground(new Color(255, 250, 205));
		setClosable(true);
		setIconifiable(true);
		setTitle("添加人员");
		setBounds(100, 100, 454, 411);
		
		JLabel label_1 = new JLabel("密码:");
		label_1.setIcon(new ImageIcon(AdminAddUserInterFrm.class.getResource("/images/mima2 (2).png")));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		isteacher = new JRadioButton("教师");
		isteacher.setSelected(true);
		isteacher.setContentAreaFilled(false);
		isteacher.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String tableName = "teacher"; 
				showNextUserId(tableName);
			}
		});
		buttonGroup.add(isteacher);
		
		isstudent = new JRadioButton("学生");
		isstudent.setContentAreaFilled(false);
		isstudent.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String tableName = "student"; 
				showNextUserId(tableName);
			}
		});
		buttonGroup.add(isstudent);
		
		JButton btnNewButton = new JButton("添加");
		//btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(AdminAddUserInterFrm.class.getResource("/images/true.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		//btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setIcon(new ImageIcon(AdminAddUserInterFrm.class.getResource("/images/false.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		
		JLabel label_2 = new JLabel("姓名:");
		label_2.setIcon(new ImageIcon(AdminAddUserInterFrm.class.getResource("/images/xingming.png")));
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel label = new JLabel("帐号:");
		label.setIcon(new ImageIcon(AdminAddUserInterFrm.class.getResource("/images/denglu2.png")));
		
		setuserid = new JTextField();
		setuserid.setEditable(false);
		setuserid.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(64)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(87)
									.addComponent(isteacher))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addComponent(btnNewButton)))
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(isstudent)
									.addGap(76))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_1)
									.addGap(49))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(label, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
										.addComponent(label_2))
									.addGap(22))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1, 225, 225, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(setuserid))
								.addComponent(textField_2, Alignment.TRAILING))
							.addGap(14)))
					.addGap(58))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(60, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(setuserid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(isteacher)
								.addComponent(isstudent)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(label_1)))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(45))
		);
		getContentPane().setLayout(groupLayout);
		if (isteacher.isSelected()) {
			String tableName = "teacher"; 
			showNextUserId(tableName);
		}else {
			String tableName = "student"; 
			showNextUserId(tableName);
		}
	}

	private void showNextUserId(String tableName) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			nextUserId = userDao.getNextStudentId(connection,tableName);
			setuserid.setText(Integer.toString(nextUserId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void resetValue() {
		textField_1.setText("");
		textField_2.setText("");
		isteacher.setSelected(true);
	}

	private void addUser() {
		if (stringTools.isEmpty(textField_1.getText())) {
			JOptionPane.showMessageDialog(null, "密码不可为空!");
			return;
		}
		if (stringTools.isEmpty(textField_2.getText())) {
			JOptionPane.showMessageDialog(null, "姓名不可为空!");
			return;
		}
		User user = new User();
		user.setPassword(textField_1.getText());
		String name = textField_2.getText();
		int power = 0;
		Connection connection = null;
		if (isteacher.isSelected()) {
			power = constants.IsTeacher;
			user.setPower(power);
			user.setUserId(nextUserId);
			try {
				connection = jdbcTools.getConnection();
				if (teacherDao.addTeacher(connection,user,name)) {
					JOptionPane.showMessageDialog(null, "添加教师成功!");
				}
				resetValue();
				showNextUserId("teacher");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			power = constants.IsStudent;
			user.setPower(power);
			user.setUserId(nextUserId);
			try {
				connection = jdbcTools.getConnection();	
				if (studentDao.addStudent(connection, user,name)) {
					JOptionPane.showMessageDialog(null, "添加学生成功!");
				}			
				resetValue();
				showNextUserId("student");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
