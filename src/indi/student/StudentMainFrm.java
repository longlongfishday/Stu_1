package indi.student;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import indi.major.StudentCurriculumScheduleShowInterFrm;
import indi.user.User;
import indi.user.UserPasswordUpdateInterFrm;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentMainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table = null;
	private JDesktopPane studenttable;
	private StudentDao studentDao = new StudentDao();
	private User user = new User();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMainFrm frame = new StudentMainFrm();
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
	public StudentMainFrm() {
		setTitle("学生信息系统主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("个人信息维护");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("个人信息管理");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentAllDataShowInterFrm showInterFrm = new StudentAllDataShowInterFrm();
				showInterFrm.setVisible(true);
				studenttable.add(showInterFrm);
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_5 = new JMenuItem("修改帐号密码");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPasswordUpdateInterFrm updateInterFrm = new UserPasswordUpdateInterFrm();
				updateInterFrm.setVisible(true);
				studenttable.add(updateInterFrm);
			}
		});
		menu.add(menuItem_5);
		
		JMenu menu_2 = new JMenu("课程管理");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("查看课表");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentCurriculumScheduleShowInterFrm scheduleShowInterFrm = new StudentCurriculumScheduleShowInterFrm();
				scheduleShowInterFrm.setVisible(true);
				studenttable.add(scheduleShowInterFrm);
			}
		});
		menu_2.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("选课");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentSelectMajorInterFrm selectMajorInterFrm = new StudentSelectMajorInterFrm();
				selectMajorInterFrm.setVisible(true);
				studenttable.add(selectMajorInterFrm);
			}
		});
		menu_2.add(menuItem_3);
		
		JMenuItem menuItem_1 = new JMenuItem("查看所有成绩");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentScoreInterFrm scoreInterFrm = new StudentScoreInterFrm();
				scoreInterFrm.setVisible(true);
				studenttable.add(scoreInterFrm);
			}
		});
		menu_2.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("退出");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_6 = new JMenuItem("安全退出");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.quit()) {
					dispose();
				}
			}
		});
		menu_1.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("注销帐号");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.logout()) {
					dispose();
				}
			}
		});
		menu_1.add(menuItem_7);
		
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		studenttable = new JDesktopPane();
		studenttable.setBackground(SystemColor.textHighlight);
		contentPane.add(studenttable, BorderLayout.CENTER);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
