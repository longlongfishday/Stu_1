package indi.student;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import indi.LogOnFrm;
import indi.course.Course;
import indi.major.Major;
import indi.major.MajorDao;
import indi.tools.JDBCTools;
import indi.tools.StringTools;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentSelectMajorInterFrm extends JInternalFrame {

	private JDBCTools jdbcTools = new JDBCTools();
	private MajorDao majorDao = new MajorDao();
	private JTable selectcoursetable;
	private JTable addcoursedtable;
	private int tmprow = 0;
	private int tmpcolumn = 0;
	private Major major = new Major();
	private StringTools stringTools = new StringTools();
	private boolean ifCanSelect = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentSelectMajorInterFrm frame = new StudentSelectMajorInterFrm();
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
	public StudentSelectMajorInterFrm() {
		setTitle("学生选课");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 626, 445);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u53EF\u9009\u8BFE\u7A0B", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u6548\u679C\u663E\u793A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection  = null;
				try {
					connection = jdbcTools.getConnection();	
					if (ifCanSelect) {
						majorDao.selectCourse(connection, major);
						JOptionPane.showMessageDialog(null, "添加成功!");
						tmpcolumn = 0;
						tmprow = 0;
						showCanSelectCourseData();
					}else {
						JOptionPane.showMessageDialog(null, "选课失败!");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel defaultTableModel = (DefaultTableModel) addcoursedtable.getModel();
				defaultTableModel.setRowCount(0);
				defaultTableModel.setRowCount(6);
				majorDao.fillScheduleTable(addcoursedtable);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(154)
					.addComponent(btnNewButton)
					.addGap(58)
					.addComponent(btnNewButton_1)
					.addContainerGap(172, Short.MAX_VALUE))
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(24))
		);
		
		addcoursedtable = new JTable();
		addcoursedtable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"\u5468\u4E00", "\u5468\u4E8C", "\u5468\u4E09", "\u5468\u56DB", "\u5468\u4E94", "\u5468\u516D", "\u5468\u65E5"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(addcoursedtable);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		selectcoursetable = new JTable();
		selectcoursetable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (tmprow != 0 || tmpcolumn != 0) {
					addcoursedtable.setValueAt("", tmpcolumn-1, tmprow-1);
				}
				whichCouseSelected();
			}
		});
		selectcoursetable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "\u8BFE\u7A0B\u4EE3\u7801", "\u8BFE\u7A0B\u540D\u79F0", "\u6559\u5E08\u4EE3\u7801","\u6559\u5E08\u59D3\u540D", "\u661F\u671F\u51E0", "\u7B2C\u51E0\u8282\u8BFE", "\u6559\u5BA4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(selectcoursetable);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		this.showCanSelectCourseData();
		majorDao.fillScheduleTable(addcoursedtable);
	}

	private int toInt(Object o) {
		if(o instanceof Integer) {
			return (Integer) o;
		} else if(o instanceof String) {
			return Integer.parseInt((String) o);
		}
		return 0;
	}
	
	private void whichCouseSelected() {
		int row = selectcoursetable.getSelectedRow();
		major.setCourseid(toInt(selectcoursetable.getValueAt(row, 0)));
		major.setTeacherId(toInt(selectcoursetable.getValueAt(row, 2)));
		major.setStudentId(LogOnFrm.GLOBALUSER.getUserId());
		String teachername = (String) selectcoursetable.getValueAt(row, 3);
		String tmpcoursename = selectcoursetable.getValueAt(row, 1) +"  "+ (String) selectcoursetable.getValueAt(row, 6);
		tmprow = toInt(selectcoursetable.getValueAt(row, 4));
		tmpcolumn = toInt(selectcoursetable.getValueAt(row, 5));
		if (stringTools.isNotEmpty((String) addcoursedtable.getValueAt( tmpcolumn-1, tmprow-1))) {
			JOptionPane.showMessageDialog(null, "不可选此节课,此时间段已有课程!");
			tmpcolumn = 0;
			tmprow = 0;
			ifCanSelect = false;
			return;
		}
		addcoursedtable.setValueAt(tmpcoursename, tmpcolumn-1, tmprow-1);
		ifCanSelect = true;
	}

	private void showCanSelectCourseData() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) selectcoursetable.getModel();
		defaultTableModel.setRowCount(0);
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			ResultSet rSet = majorDao.getCourseTime(connection);
			//Vector vector = new Vector();
			while(rSet.next()) {
			Vector vector = new Vector();
			vector.add(rSet.getString("courseid"));
			vector.add(rSet.getString("coursename"));
			vector.add(rSet.getString("teacherid"));
			vector.add(rSet.getString("teachername"));
			vector.add(rSet.getInt("courseday"));
			vector.add(rSet.getInt("coursehour"));
			vector.add(rSet.getString("classroom"));
			defaultTableModel.addRow(vector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
