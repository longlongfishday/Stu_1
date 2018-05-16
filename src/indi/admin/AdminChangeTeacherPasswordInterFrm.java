package indi.admin;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import indi.tools.JDBCTools;
import indi.tools.StringTools;
import indi.user.User;
import indi.user.UserDao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class AdminChangeTeacherPasswordInterFrm extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JDBCTools jdbcTools = new JDBCTools();
	private UserDao userDao = new UserDao();
	private StringTools stringTools = new StringTools();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminChangeTeacherPasswordInterFrm frame = new AdminChangeTeacherPasswordInterFrm();
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
	public AdminChangeTeacherPasswordInterFrm() {
		setTitle("修改密码");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 456, 334);
		
		JLabel label = new JLabel("帐号:");
		label.setIcon(new ImageIcon(AdminChangeTeacherPasswordInterFrm.class.getResource("/images/teacher-male.png")));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("密码:");
		label_1.setIcon(new ImageIcon(AdminChangeTeacherPasswordInterFrm.class.getResource("/images/change_password.png")));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.setIcon(new ImageIcon(AdminChangeTeacherPasswordInterFrm.class.getResource("/images/user_male_accept.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword();
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setIcon(new ImageIcon(AdminChangeTeacherPasswordInterFrm.class.getResource("/images/reset (1).png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(90, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)))
							.addGap(78))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(58)
							.addComponent(btnNewButton_1)
							.addGap(92))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(66, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(46))
		);
		getContentPane().setLayout(groupLayout);

	}

	private void resetValue() {
		textField.setText("");
		textField_1.setText("");
	}

	private void changePassword() {
		if (stringTools.isEmpty(textField.getText())) {
			JOptionPane.showMessageDialog(null, "帐号不可为空!");
			return;
		}
		if (stringTools.isEmpty(textField_1.getText())) {
			JOptionPane.showMessageDialog(null, "密码不可为空!");
			return;
		}
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			User tmpuser = new User();
			tmpuser.setUserId(Integer.parseInt(textField.getText()));
			tmpuser.setPassword(textField_1.getText());
			tmpuser.setPower(1);
			if (userDao.isUserExited(connection, tmpuser)) {
				userDao.updatePasswordByUser(connection, tmpuser);
				JOptionPane.showMessageDialog(null, "修改密码成功!");
				resetValue();
			}else {
				JOptionPane.showMessageDialog(null, "未找到该用户,请查证后重试!");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
