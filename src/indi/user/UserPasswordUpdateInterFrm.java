package indi.user;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import indi.LogOnFrm;
import indi.tools.JDBCTools;
import indi.tools.StringTools;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class UserPasswordUpdateInterFrm extends JInternalFrame {
	private JPasswordField oldpwd;
	private JLabel label_2;
	private JPasswordField newpwd;
	private JLabel label_3;
	private JPasswordField againpwd;
	private UserDao userDao = new UserDao();
	private JDBCTools jdbcTools = new JDBCTools();
	private JTextField textField;
	private StringTools stringTools = new StringTools();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPasswordUpdateInterFrm frame = new UserPasswordUpdateInterFrm();
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
	public UserPasswordUpdateInterFrm() {
		setTitle("修改密码");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 465, 432);
		
		JLabel label = new JLabel("帐  号:");
		label.setIcon(new ImageIcon(UserPasswordUpdateInterFrm.class.getResource("/images/administrator.png")));
		
		JLabel label_1 = new JLabel("旧密码:");
		label_1.setIcon(new ImageIcon(UserPasswordUpdateInterFrm.class.getResource("/images/change_password.png")));
		
		oldpwd = new JPasswordField();
		
		label_2 = new JLabel("新密码:");
		label_2.setIcon(new ImageIcon(UserPasswordUpdateInterFrm.class.getResource("/images/registration.png")));
		
		newpwd = new JPasswordField();
		
		label_3 = new JLabel("确认密码:");
		label_3.setIcon(new ImageIcon(UserPasswordUpdateInterFrm.class.getResource("/images/icon_pencil-edit.png")));
		
		againpwd = new JPasswordField();
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.setIcon(new ImageIcon(UserPasswordUpdateInterFrm.class.getResource("/images/table-accept.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePwd();
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		Image image = new ImageIcon("/images/reset.jpg").getImage();
		btnNewButton_1.setIcon(new ImageIcon(UserPasswordUpdateInterFrm.class.getResource("/images/reset.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		
		textField = new JTextField();
		textField.setText(Integer.toString(LogOnFrm.GLOBALUSER.getUserId()));
		textField.setEditable(false);
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton)
							.addGap(91)
							.addComponent(btnNewButton_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_3, Alignment.TRAILING)
								.addComponent(label_1, Alignment.TRAILING)
								.addComponent(label, Alignment.TRAILING)
								.addComponent(label_2, Alignment.TRAILING))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(newpwd, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(oldpwd, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(againpwd, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))))
					.addGap(75))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(67)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(oldpwd, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(newpwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(againpwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	
	public void UpdatePwd() {
		if (stringTools.isEmpty(oldpwd.getText())) {
			JOptionPane.showMessageDialog(null,"旧密码不可为空!");
			return;
		}
		if (stringTools.isEmpty(newpwd.getText())) {
			JOptionPane.showMessageDialog(null,"新密码不可为空!");
			return;
		}
		if (stringTools.isEmpty(againpwd.getText())) {
			JOptionPane.showMessageDialog(null,"请再次输入新密码确认!");
			return;
		}
		Connection connection = null;
		if (oldpwd.getText().equals(LogOnFrm.GLOBALUSER.getPassword())) {
			if (newpwd.getText().equals(againpwd.getText())) {
				 try {
					connection = jdbcTools.getConnection();
					userDao.UpdataPassword(connection,newpwd.getText() );
					LogOnFrm.GLOBALUSER.setPassword(newpwd.getText());
					JOptionPane.showMessageDialog(null,"修改成功!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				 resetValue();
			}else {
				resetValue();
				JOptionPane.showMessageDialog(null,"两次密码输入不一致,请查证后再输!");
			}
		}else {
			resetValue();
			JOptionPane.showMessageDialog(null, "修改失败,原密码不正确,请查证后再输!");
		}
}

	private void resetValue() {
		oldpwd.setText("");
		newpwd.setText("");
		againpwd.setText("");
	}
}
