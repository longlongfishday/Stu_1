package indi;

import other.BackgroundPanel;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import indi.admin.Admin;
import indi.admin.AdminDao;
import indi.admin.AdminMainFrm;
import indi.student.Student;
import indi.student.StudentDao;
import indi.student.StudentMainFrm;
import indi.teacher.Teacher;
import indi.teacher.TeacherDao;
import indi.teacher.TeacherMainFrm;
import indi.tools.Constants;
import indi.tools.JDBCTools;
import indi.tools.StringTools;
import indi.user.User;
import indi.user.UserDao;

import javax.jws.soap.SOAPBinding.Use;
import javax.management.relation.Role;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class LogOnFrm extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JDBCTools jdbcTools = new JDBCTools();
	private User user = new User();
	private UserDao userDao = new UserDao();
	private Constants constants = new Constants();
	public static User GLOBALUSER = new User();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
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
	public LogOnFrm() {

		setTitle("学生管理系统");
		//去了边框
		//setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogOnFrm.class.getResource("/images/xitong.png")));
		//setIconImage(getIconImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//添加背景====================================================
		Image image = new ImageIcon("/images/timg.jpg").getImage();
		//contentPane.setLayout(new CardLayout(0, 0));
		JPanel panel = new BackgroundPanel(Toolkit.getDefaultToolkit().getImage(LogOnFrm.class.getResource("/images/timg.jpg")));
		
		contentPane.add(panel, "name_444870630907885");
		panel.setLayout(null);
		//===========================================================
		JLabel label = new JLabel("学生信息管理系统");
		label.setBounds(220, 43, 148, 48);
		panel.add(label);
		label.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/students.png")));
		
		JLabel lblNewLabel_1 = new JLabel("密码:");
		lblNewLabel_1.setBounds(78, 179, 102, 48);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/mima.png")));
		
		JLabel lblNewLabel = new JLabel("帐号:");
		lblNewLabel.setBounds(79, 115, 101, 54);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/yonghu.png")));
		
		textField = new JTextField();
		textField.setBounds(208, 132, 270, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(208, 193, 270, 21);
		panel.add(passwordField);
		ButtonGroup groupStuAndTea = new ButtonGroup();
		
		JRadioButton userisstudent = new JRadioButton("学生");
		//改去框=======================
		userisstudent.setContentAreaFilled(false);
		userisstudent.setSelected(true);
		
		userisstudent.setBounds(159, 273, 61, 23);
		panel.add(userisstudent);
		userisstudent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				user.setPower(constants.IsStudent);
			}
		});
		groupStuAndTea.add(userisstudent);
		JRadioButton useristeacher = new JRadioButton("教师");
		//改去框
		useristeacher.setContentAreaFilled(false);
		useristeacher.setBounds(257, 273, 61, 23);
		panel.add(useristeacher);
		useristeacher.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				user.setPower(constants.IsTeacher);
			}
		});
		groupStuAndTea.add(useristeacher);
		JRadioButton userisadmin = new JRadioButton("管理员");
		userisadmin.setContentAreaFilled(false);
		userisadmin.setBounds(355, 275, 73, 23);
		panel.add(userisadmin);
		userisadmin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				user.setPower(constants.IsAdmin);;
			}
		});
		groupStuAndTea.add(userisadmin);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(126, 348, 114, 41);
		panel.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/denglu.png")));
		
		JButton btnNewButton_1 = new JButton("重置");
		//改去边框 按键设透明
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBounds(333, 348, 114, 41);
		panel.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/chongzhi.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetUsernameAndPWD();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginTheSystem();
			}
		});
		
	//	ButtonGroup groupStuAndTea = new ButtonGroup();
		
		if (userisstudent.isSelected()) {
			user.setPower(constants.IsStudent);
		}else if (useristeacher.isSelected()) {
			user.setPower(constants.IsTeacher);
		}else {
			user.setPower(constants.IsAdmin);
		}
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(panel, "name_445798327338992");
	}

	private void LoginTheSystem() {
		String username = this.textField.getText();
		String password = new String(passwordField.getPassword());
		if (StringTools.isEmpty(username)) {
			JOptionPane.showMessageDialog(null,"用户名不能为空!");
			return;
		}
		if (StringTools.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空!");
			return;
		}
		Connection con = null;
		user.setPassword(password);
		user.setUserId(Integer.parseInt(username));
		try {
			con = jdbcTools.getConnection();
			User curUser = userDao.login(con, user);
			if (curUser != null) {
				GLOBALUSER.setUserId(curUser.getUserId());
				GLOBALUSER.setPassword(curUser.getPassword());
				dispose();
				if (user.getPower() == constants.getIsStudent()) {
					GLOBALUSER.setPower(constants.getIsStudent());
					new StudentMainFrm().setVisible(true);
				}else if (user.getPower() == constants.getIsAdmin()) {
					GLOBALUSER.setPower(constants.getIsAdmin());
					new AdminMainFrm().setVisible(true);
				}else {
					GLOBALUSER.setPower(constants.IsTeacher);
					new TeacherMainFrm().setVisible(true);
				}
			}else {
				JOptionPane.showMessageDialog(null, "登录失败,请查证用户名或密码!");
			}			
		} catch (Exception e) {
			e.printStackTrace();       
		} finally {
			try {
				jdbcTools.closeConnection(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}

	private void ResetUsernameAndPWD() {
		this.textField.setText("");
		this.passwordField.setText("");
	}
	
	
}
