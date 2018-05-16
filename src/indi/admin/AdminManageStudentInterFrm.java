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
import indi.tools.JDBCTools;
import indi.tools.StringTools;
import indi.user.UserDao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminManageStudentInterFrm extends JInternalFrame {
	private StudentDao studentDao = new StudentDao();
	private JDBCTools jdbcTools = new JDBCTools();
	private JTextField selectstudentname;
	private JTextField selecthometown;
	private JComboBox selectdepart;
	private JTable resultstudenttable;
	private JTextField getstudentname;
	private JTextField gethometown;
	private JTextField gettel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton selectboy;
	private JRadioButton selectgirl;
	private JRadioButton getboy;
	private JRadioButton getgirl;
	private JComboBox getdepart ;
	private int selectrow;
	private StringTools stringTools = new StringTools();
	private JTextField getstudentid;
	private JTextField selectstudentid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminManageStudentInterFrm frame = new AdminManageStudentInterFrm();
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
	public AdminManageStudentInterFrm() {
		setTitle("维护学生信息");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 833, 478);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u7B5B\u9009\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
		);
		
		String pleaseSelect = "";
		JLabel label_3 = new JLabel("学生姓名:");
		
		getstudentname = new JTextField();
		getstudentname.setColumns(10);
		
		JLabel label_4 = new JLabel("所在班级:");
		
		JLabel label_5 = new JLabel("居住地:");
		
		gethometown = new JTextField();
		gethometown.setColumns(10);
		
		getboy = new JRadioButton("男");
		
		getgirl = new JRadioButton("女");
		
		JLabel label_7 = new JLabel("联系方式:");
		
		gettel = new JTextField();
		gettel.setColumns(10);
		
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStudentData();
			}
		});
		
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStudent();
			}
		});
		
		getdepart = new JComboBox();
		selectdepart = new JComboBox();
		selectdepart.addItem("");
		getdepart.addItem("");
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			ResultSet resultSet = studentDao.getDepartment(connection);
			while(resultSet.next()) {
				getdepart.addItem(resultSet.getString("departmentname"));
				selectdepart.addItem(resultSet.getString("departmentname"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel label_8 = new JLabel("学号:");
		
		getstudentid = new JTextField();
		getstudentid.setEditable(false);
		getstudentid.setColumns(10);
		
		JLabel label_6 = new JLabel("性别:");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_5)
						.addComponent(label_8)
						.addComponent(label_6))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(getboy)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(getgirl))
								.addComponent(getstudentid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(123)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_7)
								.addComponent(label_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(gettel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(getstudentname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(button_1)
									.addGap(18)
									.addComponent(button_2))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(getdepart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(39))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(gethometown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(677, Short.MAX_VALUE))))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_8)
										.addComponent(getstudentid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(getboy)
										.addComponent(getgirl)
										.addComponent(label_6)))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_4)
										.addComponent(getdepart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(button_1)
										.addComponent(button_2))))
							.addGap(18)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_5)
								.addComponent(gethometown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(getstudentname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(gettel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_7))))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
		);
		
		resultstudenttable = new JTable();
		resultstudenttable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				selectrow = resultstudenttable.getSelectedRow();
				getSelectStudentData();
			}
		});
		resultstudenttable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u6240\u5728\u73ED\u7EA7", "\u5C45\u4F4F\u5730", "\u8054\u7CFB\u65B9\u5F0F"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(resultstudenttable);
		panel_1.setLayout(gl_panel_1);
		
		JLabel label = new JLabel("学生姓名:");
		
		selectstudentname = new JTextField();
		selectstudentname.setColumns(10);
		
		JLabel label_1 = new JLabel("居住地:");
		
		JLabel lblNewLabel = new JLabel("所在班级:");
		

		JLabel label_2 = new JLabel("性别:");
		
		selectboy = new JRadioButton("男");
		
		selectgirl = new JRadioButton("女");
		
		selecthometown = new JTextField();
		selecthometown.setColumns(10);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectStudentByOption();
			}
		});
		
		JLabel label_9 = new JLabel("学生学号:");
		
		selectstudentid = new JTextField();
		selectstudentid.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(selecthometown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_9)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectstudentid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(106)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectboy)
							.addGap(18)
							.addComponent(selectgirl))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectstudentname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(button)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectdepart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(88))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(selectboy)
								.addComponent(selectgirl)
								.addComponent(label_2)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(selectstudentname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addGap(39))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(selectdepart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_9)
								.addComponent(selectstudentid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(selecthometown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}

	
	
	private void getSelectStudentData() {
		int row = resultstudenttable.getSelectedRow();
		getstudentid.setText((String) resultstudenttable.getValueAt(row, 0));
		getstudentname.setText((String) resultstudenttable.getValueAt(row, 1));
		String sex = (String) resultstudenttable.getValueAt(row, 2);
		if (stringTools.isEmpty(sex)) {
			getboy.setSelected(false);
			getgirl.setSelected(false);
		}else if (sex.equals("男")) {
			getboy.setSelected(true);
		}else if(sex.equals("女")) {
			getgirl.setSelected(true);
		}
		getdepart.setSelectedItem((String) resultstudenttable.getValueAt(row, 3));;
		gethometown.setText(((String)resultstudenttable.getValueAt(row, 4)));
		gettel.setText((String) resultstudenttable.getValueAt(row, 5));
	}

	private void deleteStudent() {
		String stuid = (String) resultstudenttable.getValueAt(selectrow, 0);
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			studentDao.deleteStduent(connection, stuid);
			JOptionPane.showMessageDialog(null,"已成功删除该学生!");
			selectStudentByOption();
			resetValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateStudentData() {
		Student tmpstudent = new Student();
		tmpstudent.setHomeTown(gethometown.getText());
		tmpstudent.setStudentId(Integer.parseInt(getstudentid.getText()));
		tmpstudent.setDepartment((String) getdepart.getSelectedItem());
		tmpstudent.setName(getstudentname.getText());
		if ((!getboy.isSelected() && (!getgirl.isSelected()))) {
			tmpstudent.setSex(null);
		}else if (getboy.isSelected()) {
			tmpstudent.setSex("男");
		}else {
			tmpstudent.setSex("女");
		}
		tmpstudent.setTel(gettel.getText());
		Connection connection = null;
		try {
			studentDao.updateStudent(tmpstudent);
			JOptionPane.showMessageDialog(null,"更新成功!");
			selectStudentByOption();
			resetValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void resetValue() {
		getboy.setSelected(false);
		getgirl.setSelected(false);
		getdepart.setSelectedItem("");
		gethometown.setText("");
		getstudentid.setText("");
		getstudentname.setText("");
		gettel.setText("");
	}

	private void selectStudentByOption() {
		Student tmpStudent = new Student();
		if (stringTools.isNotEmpty(selectstudentid.getText())) {
			tmpStudent.setStudentId(Integer.parseInt(selectstudentid.getText()));
		}else {
			tmpStudent.setStudentId(-1);
		}
		tmpStudent.setName(selectstudentname.getText());
		if ((selectboy.isSelected() && selectgirl.isSelected()) || (!selectboy.isSelected() && (!selectgirl.isSelected()))) {
			tmpStudent.setSex("全");
		}else if (selectboy.isSelected()) {
			tmpStudent.setSex("男");
		}else {
			tmpStudent.setSex("女");
		}
		tmpStudent.setHomeTown(selecthometown.getText());
		tmpStudent.setDepartment((String) selectdepart.getSelectedItem());
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			ResultSet rSet = studentDao.SelectAllStudentData(connection, tmpStudent);
			DefaultTableModel defaultTableModel = (DefaultTableModel) resultstudenttable.getModel();
			defaultTableModel.setRowCount(0);
			while (rSet.next()) {
				Vector vector = new Vector();
				vector.add(rSet.getString("studentid"));
				vector.add(rSet.getString("name"));
				vector.add(rSet.getString("sex"));
				vector.add(rSet.getString("department"));
				vector.add(rSet.getString("hometown"));
				vector.add(rSet.getString("tel"));
				defaultTableModel.addRow(vector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
