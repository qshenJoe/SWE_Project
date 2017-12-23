import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener{

	private JLabel jlbUserName;
	private JLabel jlbPassWord;
	private JButton jbtLogin;
	private JButton jbtExit;
	private JTextField jtfUserName;
	private JPasswordField jpfPassWord;
	private Connection con = null;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public Login() throws SQLException {
		jlbUserName = new JLabel("User Name:");
		jlbPassWord = new JLabel("Pass Word:");
		jbtLogin = new JButton("Log in");
		jbtExit = new JButton("Exit");
		jtfUserName = new JTextField(10);
		jpfPassWord = new JPasswordField(10);
		jbtLogin.addActionListener(this);
		jbtExit.addActionListener(this);
		con = TestCon.dbConnector();

	}

	void setMainWindowLayout() {
		Container con1 = new Container();
		con1.setLayout(new FlowLayout());
		con1.add(jlbUserName);
		con1.add(jtfUserName);

		Container con2 = new Container();
		con2.setLayout(new FlowLayout());
		con2.add(jlbPassWord);
		con2.add(jpfPassWord);

		Container con3 = new Container();
		con3.setLayout(new FlowLayout());
		con3.add(jbtLogin);
		con3.add(jbtExit);

		this.setLayout(new BorderLayout());
		this.add(con1, BorderLayout.NORTH);
		this.add(con2, BorderLayout.CENTER);
		this.add(con3, BorderLayout.SOUTH);
		this.validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtLogin) {
			String query = "Select * from Users where UserName = ? and PassWord = ?";
			try {
				stmt = con.prepareStatement(query);
				stmt.setString(1, jtfUserName.getText());
				stmt.setString(2, jpfPassWord.getText());
				rs = stmt.executeQuery();
				int count = 0;
				while(rs.next()) {
					count++;
				}
				if (count == 1) {
					JOptionPane.showMessageDialog(null, "Username and password are correct.");
					MainPanel mp = new MainPanel();
					mp.frame.setVisible(true);
					this.setVisible(false);
				}
				else if (count > 1) {
					JOptionPane.showMessageDialog(null, "Duplicate username and password.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Username or password is not correct.");
				}
				rs.close();
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		else if (e.getSource() == jbtExit)
			System.exit(0);
	}

	public static void main(String[] args) throws SQLException {
		Login l = new Login();
		l.setBounds(350, 150, 600, 400);
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l.setVisible(true);
		l.setMainWindowLayout();
	}
}