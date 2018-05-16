package indi.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import indi.LogOnFrm;
import indi.user.User;
import indi.user.UserPasswordUpdateInterFrm;
import other.BackgroundPanel;
import other.BackgroundPanel_2;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminMainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane admintable;
	private User user = new User();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrm frame = new AdminMainFrm();
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
	public AdminMainFrm() {
		setTitle("学生管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 399);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu_2 = new JMenu("个人信息管理");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_1 = new JMenuItem("修改个人密码");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 UserPasswordUpdateInterFrm updateInterFrm = new UserPasswordUpdateInterFrm();
				 updateInterFrm.setVisible(true);
				 admintable.add(updateInterFrm);
			}
		});
		menu_2.add(menuItem_1);
		
		JMenu menu = new JMenu("学生管理");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("维护学生信息");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				AdminManageStudentInterFrm adminManageStudentInterFrm = new AdminManageStudentInterFrm();
				adminManageStudentInterFrm.setVisible(true);
				admintable.add(adminManageStudentInterFrm);
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_6 = new JMenuItem("修改学生密码");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminChangeStudentPasswordInterFrm adminChangeStudentPasswordInterFrm = new AdminChangeStudentPasswordInterFrm();
				adminChangeStudentPasswordInterFrm.setVisible(true);
				admintable.add(adminChangeStudentPasswordInterFrm);
			}
		});
		menu.add(menuItem_6);
		
		JMenu menu_1 = new JMenu("教师管理");
		menuBar.add(menu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("修改教师密码");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminChangeTeacherPasswordInterFrm adminChangeTeacherPasswordInterFrm = new AdminChangeTeacherPasswordInterFrm();
				adminChangeTeacherPasswordInterFrm.setVisible(true);
				admintable.add(adminChangeTeacherPasswordInterFrm);
			}
		});
		menu_1.add(mntmNewMenuItem_1);
		
		JMenu menu_3 = new JMenu("课程管理");
		menuBar.add(menu_3);
		
		JMenuItem menuItem_11 = new JMenuItem("维护课程信息");
		menuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminManageCourseInterFrm adminManageCourseInterFrm = new AdminManageCourseInterFrm();
				adminManageCourseInterFrm.setVisible(true);
				admintable.add(adminManageCourseInterFrm);
		
			}
		});
		menu_3.add(menuItem_11);
		
		JMenu menu_5 = new JMenu("添加");
		menuBar.add(menu_5);
		
		JMenuItem menuItem_2 = new JMenuItem("添加人员");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAddUserInterFrm addUserInterFrm = new AdminAddUserInterFrm();
				addUserInterFrm.setVisible(true);
				admintable.add(addUserInterFrm);
			}
		});
		menu_5.add(menuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("添加课程");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAddCourseInterFrm addCourseInterFrm = new AdminAddCourseInterFrm();
				addCourseInterFrm.setVisible(true);
				admintable.add(addCourseInterFrm);
			}
		});
		menu_5.add(mntmNewMenuItem);
		
		JMenuItem menuItem_3 = new JMenuItem("添加班级");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAddDepartmentInterFrm addDepartmentInterFrm = new AdminAddDepartmentInterFrm();
				addDepartmentInterFrm.setVisible(true);
				admintable.add(addDepartmentInterFrm);
			}
		});
		menu_5.add(menuItem_3);
		
		JMenu menu_4 = new JMenu("退出");
		menuBar.add(menu_4);
		
		JMenuItem menuItem_12 = new JMenuItem("安全退出");
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.quit()) {
					dispose();
				}
			}
		});
		menu_4.add(menuItem_12);
		
		JMenuItem menuItem_13 = new JMenuItem("注销帐号");
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.logout()) {
					dispose();
				}
			}
		});
		menu_4.add(menuItem_13);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//添加背景============================================
		admintable = new BackgroundPanel_2(Toolkit.getDefaultToolkit().getImage(LogOnFrm.class.getResource("/images/timg.jpg")));
		admintable.setBackground(Color.WHITE);
		contentPane.add(admintable, BorderLayout.CENTER);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}
}
