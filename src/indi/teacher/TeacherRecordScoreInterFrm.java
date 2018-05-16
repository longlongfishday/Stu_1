package indi.teacher;

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
import javax.swing.table.TableCellEditor;
import javax.swing.tree.ExpandVetoException;

import indi.major.Major;
import indi.major.MajorDao;
import indi.tools.JDBCTools;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherRecordScoreInterFrm extends JInternalFrame {
	private JDBCTools jdbcTools = new JDBCTools();
	private TeacherDao teacherDao = new TeacherDao();
	private MajorDao majorDao = new MajorDao();
	private JComboBox recordCourse;
	private String recordCourseName;
	private double ordScore = 0;
	private double midScore = 0;
	private double experScore = 0;
	private double lastScore = 0;
	private JComboBox ordinaryJCB;
	private JComboBox midjcb;
	private JComboBox experimentjcb;
	private JTable recordscoretable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherRecordScoreInterFrm frame = new TeacherRecordScoreInterFrm();
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
	public TeacherRecordScoreInterFrm() {
		setTitle("登记成绩");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 523, 443);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lastScore < 0) {
					JOptionPane.showMessageDialog(null, "期末占比小于0,请重新分配其他成绩占比!");
				}else {
					getScoreJCB();
					JOptionPane.showMessageDialog(null, "登记成绩成功!");
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8BFE\u7A0B\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(253, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(197))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(20))
		);
		
		recordscoretable = new JTable();
		recordscoretable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u5E73\u65F6", "\u671F\u4E2D", "\u5B9E\u9A8C", "\u671F\u672B", "\u603B\u5206"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(recordscoretable);
		
		JLabel label = new JLabel("所要登记的课程:");
		
		recordCourse = new JComboBox();
		
		JLabel label_1 = new JLabel("平时分占比:");
		
		JLabel label_2 = new JLabel("期中考试占比:");
		
		JLabel label_3 = new JLabel("实验成绩占比:");
		
		JButton btnNewButton_2 = new JButton("确认课程信息");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordScore = getPercent((Integer)ordinaryJCB.getSelectedItem());
				midScore = getPercent(((Integer)midjcb.getSelectedItem()));
				experScore = getPercent((Integer)experimentjcb.getSelectedItem());
				lastScore = 1.0 - ordScore - midScore - experScore;
				if (lastScore < 0) {
					JOptionPane.showMessageDialog(null, "期末占比小于0,请重新分配其他成绩占比!");
				}else {
					fillRecordScoreTable();
				}	
			}

			
		});
		
		Integer labels[] = { 10, 20, 30, 40 };   
		ordinaryJCB = new JComboBox(labels);
		
		midjcb = new JComboBox(labels);
		
		experimentjcb = new JComboBox(labels);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_2)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(recordCourse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(midjcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ordinaryJCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(experimentjcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(47))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(204, Short.MAX_VALUE)
					.addComponent(btnNewButton_2)
					.addGap(186))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(recordCourse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(ordinaryJCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(label_3)
								.addComponent(experimentjcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnNewButton_2))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(midjcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		this.fillRecordCourseJCB();
		
	}
	
	public void fillRecordScoreTable() {
		recordCourseName = recordCourse.getSelectedItem().toString();
		DefaultTableModel defaultTableModel = (DefaultTableModel) recordscoretable.getModel();
		Connection connection = null;
		defaultTableModel.setRowCount(0);
		try {
			connection = jdbcTools.getConnection();
			ResultSet resultSet = teacherDao.selectStudentByCourseName(connection,recordCourseName);
			while (resultSet.next()) {
				Vector vector = new Vector();
				vector.add(resultSet.getString("studentid"));
				vector.add(resultSet.getString("name"));
				vector.add(resultSet.getInt("ordinaryscore"));
				vector.add(resultSet.getInt("midscore"));
				vector.add(resultSet.getInt("expermentscore"));
				vector.add(resultSet.getInt("lastscore"));
				vector.add(resultSet.getInt("totalscore"));
				defaultTableModel.addRow(vector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillRecordCourseJCB() {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			ResultSet resultSet = teacherDao.getTeachCourseName(connection);
			while (resultSet.next()) {
				String tmpcoursename = resultSet.getString("coursename");
				recordCourse.addItem(tmpcoursename);;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int toInt(Object o) {
		if(o instanceof Integer) {
			return (Integer) o;
		} else if(o instanceof String) {
			return Integer.parseInt((String) o);
		}
		return 0;
	}
	
	private void getScoreJCB() {
		if (recordscoretable.isEditing()) {  //判断是否在编辑
		    int row = recordscoretable.getEditingColumn();//那一行
		    int col = recordscoretable.getEditingColumn(); //那一列
		    TableCellEditor editor = recordscoretable.getCellEditor(row, col);
		    editor.stopCellEditing();
		}
		double tmp1 ,tmp2,tmp3,tmp4,total;
		int row = recordscoretable.getRowCount();
		String teString ;
		Major tMajor = new Major();
		Connection connection = null;
		for(int i = 0; i < row ; i++) {
			tMajor.setStudentId(toInt(recordscoretable.getValueAt(i, 0)));
			tmp1 = getAndSetAllScore(recordscoretable.getValueAt(i, 2),ordScore);
			tMajor.setOrdinaryscore(toInt(recordscoretable.getValueAt(i, 2)));
			tmp2 = getAndSetAllScore(recordscoretable.getValueAt(i, 3),midScore);
			tMajor.setMidscore(toInt(recordscoretable.getValueAt(i, 3)));
			tmp3 = getAndSetAllScore(recordscoretable.getValueAt(i, 4),experScore);
			tMajor.setExpermentscore(toInt(recordscoretable.getValueAt(i, 4)));
			tmp4 = getAndSetAllScore(recordscoretable.getValueAt(i, 5),lastScore);
			tMajor.setLastscore(toInt(recordscoretable.getValueAt(i, 5)));
			total = (int)(tmp1 + tmp2 + tmp3 + tmp4);
			tMajor.setTotalScore((int)total);
			try {
				connection = jdbcTools.getConnection();
				majorDao.storeScore(connection, tMajor, recordCourseName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			recordscoretable.setValueAt(total, i, 6);
		}
	}
	
	private double getAndSetAllScore(Object object, double percent) {
		double score;
		score = (double)((toInt(object)) * percent);
		return score;
	}

	private double getPercent(Integer selectedItem) {
		double score = selectedItem;
		score /= 100;
		return score;
	}
}
