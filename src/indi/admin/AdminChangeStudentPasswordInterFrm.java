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

public class AdminChangeStudentPasswordInterFrm extends JInternalFrame {
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
					AdminChangeStudentPasswordInterFrm frame = new AdminChangeStudentPasswordInterFrm();
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
	public AdminChangeStudentPasswordInterFrm() {
		setTitle("修改密码");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 449, 353);
		
		JLabel label = new JLabel("帐号:");
		label.setIcon(new ImageIcon(AdminChangeStudentPasswordInterFrm.class.getResource("/images/administrator.png")));
		
		JLabel label_1 = new JLabel("新密码:");
		label_1.setIcon(new ImageIcon(AdminChangeStudentPasswordInterFrm.class.getResource("/images/digital_signature_pen.png")));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.setIcon(new ImageIcon(AdminChangeStudentPasswordInterFrm.class.getResource("/images/user_male_accept.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword();
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setIcon(new ImageIcon(AdminChangeStudentPasswordInterFrm.class.getResource("/images/reset (1).png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(61)
							.addComponent(btnNewButton_1)))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(74, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(42))
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
			tmpuser.setPower(0);
			if(userDao.isUserExited(connection, tmpuser)) {
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
