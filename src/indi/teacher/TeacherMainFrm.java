package indi.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import indi.LogOnFrm;
import indi.major.StudentCurriculumScheduleShowInterFrm;
import indi.user.User;
import indi.user.UserPasswordUpdateInterFrm;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JCheckBoxMenuItem;

public class TeacherMainFrm extends JFrame {

	private JPanel contentPane;
	private TeacherDao teacherDao = new TeacherDao();
	private JDesktopPane teachertable;
	private User user = new User();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMainFrm frame = new TeacherMainFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TeacherMainFrm() {
		setTitle("学生管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("学生信息管理");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("登记成绩");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherRecordScoreInterFrm teacherRecordScoreInterFrm = new TeacherRecordScoreInterFrm();
				teacherRecordScoreInterFrm.setVisible(true);
				teachertable.add(teacherRecordScoreInterFrm);
			}
		});
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("个人信息管理");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("修改密码");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPasswordUpdateInterFrm updateInterFrm = new UserPasswordUpdateInterFrm();
				updateInterFrm.setVisible(true);
				teachertable.add(updateInterFrm);
			}
		});
		menu_1.add(menuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("查看课表");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentCurriculumScheduleShowInterFrm scheduleShowInterFrm = new StudentCurriculumScheduleShowInterFrm();
				scheduleShowInterFrm.setVisible(true);
				teachertable.add(scheduleShowInterFrm);
			}
		});
		menu_1.add(mntmNewMenuItem);
		
		JMenu menu_2 = new JMenu("退出");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("安全退出");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.quit()) {
					dispose();
				}
			}
		});
		menu_2.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("注销帐号");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.logout()) {
					dispose();
				}
			}
		});
		menu_2.add(menuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		teachertable = new JDesktopPane();
		teachertable.setBackground(Color.LIGHT_GRAY);
		contentPane.add(teachertable);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
