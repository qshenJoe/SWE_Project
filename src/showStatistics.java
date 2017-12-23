import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class showStatistics extends JFrame implements ActionListener {
	private JTable jtbStatis;
	private JScrollPane jspStatis;
	private JButton jbtShowLevel_1, jbtShowLevel_2, jbtShowLevel_3;
	private JButton jbtExit;
	private StatisticsModel statisModel;


	public showStatistics() throws SQLException {
		statisModel = new StatisticsModel();
		jtbStatis = new JTable(statisModel);
		jspStatis = new JScrollPane(jtbStatis);

		jbtShowLevel_1 = new JButton("Level One");
		jbtShowLevel_2 = new JButton("Level Two");
		jbtShowLevel_3 = new JButton("Level Three");
		jbtShowLevel_1.addActionListener(this);
		jbtShowLevel_2.addActionListener(this);
		jbtShowLevel_3.addActionListener(this);
		
		jbtExit = new JButton("Go back to menu");
		jbtExit.addActionListener(this);
	}

	void setMainWindowLayout() {
		Container con1 = new Container();
		con1.setLayout(new FlowLayout());
		con1.add(jbtShowLevel_1);
		con1.add(jbtShowLevel_2);
		con1.add(jbtShowLevel_3);
		
		Container con2 = new Container();
		con2.setLayout(new FlowLayout());
		con2.add(jbtExit);
		
		this.setLayout(new BorderLayout());
		this.add(con1, BorderLayout.NORTH);
		this.add(jspStatis, BorderLayout.CENTER);
		this.add(con2, BorderLayout.SOUTH);
		this.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Auto-generated method stub
		if (e.getSource() == jbtShowLevel_1) {
			String sql = "SELECT Word_Seq, Time, Star FROM Level_One";
			try {
				statisModel = new StatisticsModel(sql);
				jtbStatis.setModel(statisModel);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == jbtShowLevel_2) {
			String sql = "SELECT Word_Seq, Time, Star FROM Level_Two";
			try {
				statisModel = new StatisticsModel(sql);
				jtbStatis.setModel(statisModel);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == jbtShowLevel_3) {
			String sql = "SELECT Word_Seq, Time, Star FROM Level_Three";
			try {
				statisModel = new StatisticsModel(sql);
				jtbStatis.setModel(statisModel);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == jbtExit) {
			this.setVisible(false);
		}
	}
	
	public void refreshTable() {
		StatisticsModel sm;
		try {
			sm = new StatisticsModel("SELECT Word_Seq, Time, Star FROM Level_One");
			jtbStatis.setModel(sm);
			statisModel = sm;
		} catch (SQLException e1) {
			//TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}