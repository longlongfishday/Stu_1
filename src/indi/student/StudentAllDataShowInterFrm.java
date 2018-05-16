package indi.student;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import indi.LogOnFrm;
import indi.tools.Constants;
import indi.tools.JDBCTools;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentAllDataShowInterFrm extends JInternalFrame {
	private JTable studentAllDataTable;
	private StudentDao studentDao = new StudentDao();
	private JDBCTools jdbcTools = new JDBCTools();
	private int stuid = LogOnFrm.GLOBALUSER.getUserId();
	private JTextField id;
	private JTextField name;
	private JTextField depart;
	private JTextField hometown;
	private JTextField tel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton boy;
	private JRadioButton girl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentAllDataShowInterFrm frame = new StudentAllDataShowInterFrm();
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
	public StudentAllDataShowInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("查看个人信息");
		setBounds(100, 100, 703, 374);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("学号:");
		
		id = new JTextField();
		id.setEditable(false);
		id.setColumns(10);
		
		JLabel label = new JLabel("姓名:");
		
		name = new JTextField();
		name.setColumns(10);
		
		
		JLabel label_1 = new JLabel("性别:");
		
		boy = new JRadioButton("男");
		buttonGroup.add(boy);
		
		girl = new JRadioButton("女");
		buttonGroup.add(girl);
		
		JLabel label_2 = new JLabel("所在班级:");
		
		depart = new JTextField();
		depart.setColumns(10);
		
		JLabel label_3 = new JLabel("居住地:");
		
		hometown = new JTextField();
		hometown.setColumns(10);
		
		JLabel label_4 = new JLabel("电话号码:");
		
		tel = new JTextField();
		tel.setColumns(10);
		
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStudentData();
				fillStudentDataTable(LogOnFrm.GLOBALUSER.getUserId());
			}
		});
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(depart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1)
							.addGap(2)
							.addComponent(boy)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(girl)
							.addGap(64)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(8)
									.addComponent(button)
									.addGap(61)
									.addComponent(button_1))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(hometown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(21)
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(223, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(depart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1)
							.addComponent(boy)
							.addComponent(girl))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_3)
							.addComponent(hometown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_4)))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		studentAllDataTable = new JTable();
		studentAllDataTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D",  "\u6027\u522B", "\u6240\u5728\u73ED\u7EA7", "\u5C45\u4F4F\u5730", "\u7535\u8BDD\u53F7\u7801"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(studentAllDataTable);
		getContentPane().setLayout(groupLayout);
		this.fillStudentDataTable(stuid);
	}
	
	protected void updateStudentData() {
		Student tmp = new Student();
		tmp.setStudentId(LogOnFrm.GLOBALUSER.getUserId());
		tmp.setDepartment(depart.getText());
		tmp.setHomeTown(hometown.getText());
		tmp.setName(name.getText());
		if (boy.isSelected()) {
			tmp.setSex("男");
		}else {
			tmp.setSex("女");
		}
		tmp.setTel(tel.getText());
		try {
			studentDao.updateStudent(tmp);	
			JOptionPane.showMessageDialog(null, "更新成功!");
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "更新失败!");
		}
	}

	private void resetValue() {
		fillStudentDataTable(LogOnFrm.GLOBALUSER.getUserId());
		id.setText("");
		name.setText("");
		depart.setText("");
		hometown.setText("");
		tel.setText("");
	}

	private void fillStudentDataTable(int studentid) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) studentAllDataTable.getModel();
		defaultTableModel.setRowCount(0);
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			ResultSet rSet = studentDao.showStudentData(connection,studentid);
			Vector vector = new Vector();
			while(rSet.next()) {
			id.setText(rSet.getString("studentid"));
			vector.add(rSet.getString("studentid"));
			name.setText(rSet.getString("name"));
			vector.add(rSet.getString("name"));
			if (rSet.getString("sex").equals("男")) {
				boy.setSelected(true);
			}else {
				girl.setSelected(true);
			}
			vector.add(rSet.getString("sex"));
			depart.setText(rSet.getString("department"));
			vector.add(rSet.getString("department"));
			hometown.setText(rSet.getString("hometown"));
			vector.add(rSet.getString("hometown"));
			tel.setText(rSet.getString("tel"));
			vector.add(rSet.getString("tel"));
			defaultTableModel.addRow(vector);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
