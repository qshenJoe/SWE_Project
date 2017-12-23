import java.util.*;
import java.sql.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class StatisticsModel extends AbstractTableModel {
	private Vector<Vector<String>> rowData;
	private Vector<String> colName;
	private PreparedStatement stmt;
	private ResultSet rs;

	public StatisticsModel(String sql) throws SQLException {
		this.initData(sql);
	}

	public StatisticsModel() throws SQLException {
		this.initData("SELECT Word_Seq, Time, Star FROM Level_One");
	}

	public void initData(String sql) throws SQLException {
		setRowData(new Vector<Vector<String>>());
		setColName(new Vector<String>());
		getColName().add("Challenge_No");
		getColName().add("Time(sec)");
		getColName().add("Stars");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordSet", "root", "951126");
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		importSQL();
	}

	void importSQL() throws SQLException {
		//TODO Auto-generated method stub
		@SuppressWarnings("unused")
		boolean signNull = true;
		while (rs.next()) {
			Vector<String> item = new Vector<String>();
			for (int i = 1; i < 4; i++) {
				item.add(rs.getString(i));
			}
			getRowData().add(item);
			signNull = false;
		}
		rs.close();
	}
	
	@Override
	public int getColumnCount() {
		//TODO Auto-generated method stub
		return this.colName.size();
	}

	@Override
	public int getRowCount() {
		//TODO Auto-generated method stub
		return this.rowData.size();
	}
	@Override
	public String getColumnName(int column) {
		//TODO Auto-generated method stub
		return this.colName.get(column);
	}

	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Vector<String>> rowData) {
		this.rowData = rowData;
	}

	public Vector<String> getColName() {
		return colName;
	}

	public void setColName(Vector<String> colName) {
		this.colName = colName;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return (this.rowData.get(row)).get(col);
	}
}
