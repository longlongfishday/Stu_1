package indi.admin;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import indi.Department.DepartmentDao;
import indi.tools.JDBCTools;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminAddDepartmentInterFrm extends JInternalFrame {
	private JTable departmenttable;
	private JTextField textField;
	private JDBCTools jdbcTools = new JDBCTools();
	private DepartmentDao departmentDao = new DepartmentDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddDepartmentInterFrm frame = new AdminAddDepartmentInterFrm();
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
	public AdminAddDepartmentInterFrm() {
		getContentPane().setBackground(new Color(255, 250, 205));
		setTitle("班级管理");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 451, 353);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 205));
		panel.setBorder(new TitledBorder(null, "\u589E\u52A0\u73ED\u7EA7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("所需增加的班级名称:");
		label.setIcon(new ImageIcon(AdminAddDepartmentInterFrm.class.getResource("/images/课程表.png")));
		
		JButton button = new JButton("确认");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDepartment();
			}
		});
		button.setIcon(new ImageIcon(AdminAddDepartmentInterFrm.class.getResource("/images/true.png")));
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		button_1.setIcon(new ImageIcon(AdminAddDepartmentInterFrm.class.getResource("/images/false.png")));
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(43)
							.addComponent(button)
							.addGap(69)
							.addComponent(button_1)))
					.addGap(33))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		departmenttable = new JTable();
		departmenttable.setBackground(new Color(255, 250, 205));
		departmenttable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u73ED\u53F7", "\u73ED\u7EA7\u540D\u79F0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(departmenttable);
		getContentPane().setLayout(groupLayout);
		fillDepartmentTable();
	}
	
	protected void resetValue() {
		textField.setText("");
	}

	protected void addDepartment() {
		String departmentname = textField.getText();
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			departmentDao.addDepartment(connection, departmentname);
			JOptionPane.showMessageDialog(null, "添加班级成功!");
			fillDepartmentTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillDepartmentTable() {
		Connection connection = null;
		DefaultTableModel defaultTableModel = (DefaultTableModel) departmenttable.getModel();
		defaultTableModel.setRowCount(0);
		try {
			connection = jdbcTools.getConnection();
			ResultSet rSet = departmentDao.getAllDepartmentData(connection);
			while(rSet.next()) {
				Vector vector = new Vector();
				vector.add(rSet.getInt("departid"));
				vector.add(rSet.getString("departmentname"));
				defaultTableModel.addRow(vector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
