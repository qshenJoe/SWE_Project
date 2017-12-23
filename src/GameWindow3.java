import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("serial")
public class GameWindow3 extends JFrame implements ActionListener {
	private JLabel jlbInput;
	private JLabel jlbShowWord;
	private JLabel jlbTimer;
	private JButton jbtExit;
	private JButton jbtNext;
	private JButton jbtConfirm;
	private JButton jbtGetWord;
	private JTextField jtfInput;
	private static Connection con = null;
	private Font bItalic;
	
	private int i = 25;
	private Timer timer;
	private int count = 1;
	private String s;
	private String origin;
	String[] wordSet = new String[10];
	int[] starSet = new int[10];

	private PreparedStatement stmt;
	private ResultSet rs;
	
	
	
	public GameWindow3() throws ClassNotFoundException, SQLException {
		bItalic = new Font("Arial", Font.ITALIC, 14);
		jlbInput = new JLabel("Type your answer here: ");
		jtfInput = new JTextField(10);
		jbtConfirm = new JButton("Confirm");
		jbtConfirm.setFont(bItalic);
		
		jlbShowWord = new JLabel("Click get word button to start.");
		jlbTimer = new JLabel(i + "");
		
		jbtNext = new JButton("Next");
		jbtNext.setFont(bItalic);
		jbtExit = new JButton("Go back to menu");
		jbtExit.setFont(bItalic);
		jbtGetWord = new JButton("Get Word");
		jbtGetWord.setFont(bItalic);

		jbtConfirm.addActionListener(this);
		jbtExit.addActionListener(this);
		jbtNext.addActionListener(this);
		jbtGetWord.addActionListener(this);
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordSet", "root", "951126");
	} 

	void setMainWindowLayout() {
		Container con1 = new Container();
		con1.setLayout(new FlowLayout());
		con1.add(jlbInput);
		con1.add(jtfInput);
		con1.add(jlbTimer);

		Container con2 = new Container();
		con2.setLayout(new FlowLayout());
		con2.add(jbtConfirm);
		con2.add(jbtNext);
		con2.add(jbtExit);
		con2.add(jbtGetWord);

		Container con3 = new Container();
		con3.setLayout(new FlowLayout());
		con3.add(jlbShowWord);
		
		this.setLayout(new BorderLayout());
		this.add(con1, BorderLayout.NORTH);
		this.add(con2, BorderLayout.SOUTH);
		this.add(con3, BorderLayout.CENTER);
		this.validate();
		
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i--;
				jlbTimer.setText(i + "");
				if (i <= 0) {
					JOptionPane.showMessageDialog(null, "Time is up, go to next challenge.");
					starSet[count - 1] = 0;
					String sql = "UPDATE Level_Three SET Time = ?, Star = ? WHERE Word_Seq = ?";
					try {
						stmt = con.prepareStatement(sql);
						stmt.setInt(1, 25 - i);
						stmt.setInt(2, starSet[count - 1]);
						stmt.setInt(3, count);
						stmt.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					i = 25;
					jlbTimer.setText(i + "");
					timer.restart();
					if(count < 10) {
						origin = wordSet[count];
						s = scramble(origin.length(), origin);
						jlbShowWord.setText(s);
						count++;
						jtfInput.setText("");
						// if you skip the current challenge, your star for this challenge will become zero.
						starSet[count-1] = 0;
					}
					else {
						timer.stop();
						}
				}
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Auto-generated method stub
			if (e.getSource() == jbtConfirm) {
				if (!this.jtfInput.getText().equals("")) {
					String ans = this.jtfInput.getText();
					//if (wordSetLevel_1.contains(ans.toLowerCase()))
					if(ans.equals(origin.toLowerCase()) && i > 20) {
						starSet[count - 1] = 3;
						String sql = "UPDATE Level_Three SET Time = ?, Star = ? WHERE Word_Seq = ?";
						try {
							stmt = con.prepareStatement(sql);
							stmt.setInt(1, 25 - i);
							stmt.setInt(2, starSet[count - 1]);
							stmt.setInt(3, count);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						timer.stop();
						i = 25;
						jlbTimer.setText(i + "");
						JOptionPane.showMessageDialog(null, "Your answer is right!");
						if(count < 10) {
							origin = wordSet[count];
							s = scramble(origin.length(), origin);
							jlbShowWord.setText(s);
							count++;
							jtfInput.setText("");
							timer.restart();
						}
						else {
							timer.stop();
							JOptionPane.showMessageDialog(null, "You have completed all challenges.");
							this.setVisible(false);
							}
					}
					else if(ans.equals(origin.toLowerCase()) && i > 15) {
						starSet[count - 1] = 2;
						String sql = "UPDATE Level_Three SET Time = ?, Star = ? WHERE Word_Seq = ?";
						try {
							stmt = con.prepareStatement(sql);
							stmt.setInt(1, 25 - i);
							stmt.setInt(2, starSet[count - 1]);
							stmt.setInt(3, count);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						timer.stop();
						i = 25;
						jlbTimer.setText(i + "");
						JOptionPane.showMessageDialog(null, "Your answer is right!");
						if(count < 10) {
							origin = wordSet[count];
							s = scramble(origin.length(), origin);
							jlbShowWord.setText(s);
							count++;
							jtfInput.setText("");
							timer.restart();
						}
						else {
							timer.stop();
							JOptionPane.showMessageDialog(null, "You have completed all challenges.");
							this.setVisible(false);
							}
					}
					else if(ans.equals(origin.toLowerCase()) && i > 10) {
						starSet[count - 1] = 1;
						String sql = "UPDATE Level_Three SET Time = ?, Star = ? WHERE Word_Seq = ?";
						try {
							stmt = con.prepareStatement(sql);
							stmt.setInt(1, 25 - i);
							stmt.setInt(2, starSet[count - 1]);
							stmt.setInt(3, count);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						timer.stop();
						i = 25;
						jlbTimer.setText(i + "");
						JOptionPane.showMessageDialog(null, "Your answer is right!");
						if(count < 10) {
							origin = wordSet[count];
							s = scramble(origin.length(), origin);
							jlbShowWord.setText(s);
							count++;
							jtfInput.setText("");
							timer.restart();
						}
						else {
							timer.stop();
							JOptionPane.showMessageDialog(null, "You have completed all challenges.");
							this.setVisible(false);
							}
					}
					else {
						JOptionPane.showMessageDialog(null, "Your answer is wrong.");
					}
				} 
				else
					JOptionPane.showMessageDialog(this, "The textfield shouldn't be blank.", "Notification", JOptionPane.PLAIN_MESSAGE);
			}

			if (e.getSource() == jbtNext) {
				starSet[count - 1] = 0;
				String sql = "UPDATE Level_Three SET Time = ?, Star = ? WHERE Word_Seq = ?";
				try {
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, 25 - i);
					stmt.setInt(2, starSet[count - 1]);
					stmt.setInt(3, count);
					stmt.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				i = 25;
				jlbTimer.setText(i + "");
				timer.restart();
				if(count < 10) {
					origin = wordSet[count];
					s = scramble(origin.length(), origin);
					jlbShowWord.setText(s);
					count++;
					jtfInput.setText("");
					// if you skip the current challenge, your star for this challenge will become zero.
					starSet[count-1] = 0;
				}
				else {
					timer.stop();
					JOptionPane.showMessageDialog(null, "You have completed all challenges.");
					this.setVisible(false);
					}
		}
		else if (e.getSource() == jbtExit) {
			timer.stop();
			this.setVisible(false);
		}
		else if (e.getSource() == jbtGetWord) {
			//The version 2.0 of initialization
			try {
				String sql = "SELECT * FROM Level_Three";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery(sql);		
				wordSet = new String[10];
				int i = 0;
				while(rs.next()) {
					// getString(2) represents get string from column two, which is word_content
					wordSet[i] = rs.getString(2);
					i++;
				}
				origin = wordSet[0];
				s = scramble(origin.length(), origin);
				jlbShowWord.setText(s);
			} catch (Exception e1){
				e1.printStackTrace();
			}
			jtfInput.setText("");
			timer.start();
			//System.out.println(origin);
		}
	}
	
	public static String scramble( int n, String s ) {
	    // Convert your string into a simple char array:
	    char[] a = s.toCharArray();
	    Random r = new Random();
	    // Scramble the letters using the standard Fisher-Yates shuffle, 
	    for( int i = n - 1; i > 0; i--)
	    {
	        int j = r.nextInt(i);
	        // Swap letters
	        char temp = a[i];
	        	a[i] = a[j];
	        a[j] = temp;
	    }       
	    return new String( a );
	}
}