import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameWindow extends JFrame implements ActionListener {
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
	
	private int i = 15;
	private Timer timer;
	private int count = 1;
	private String s;
	private String origin;
	String[] wordSet = new String[10];
	int[] starSet = new int[10];

	private PreparedStatement stmt;
	private ResultSet rs;
	
	
	
	public GameWindow() throws ClassNotFoundException, SQLException {
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
		// Here we will explain how timer works in our program
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// i stands for count down number
				i--;
				// as timer is counting down, the jlbTimer is set to the value of i
				jlbTimer.setText(i + "");
				// when timer counts down to 0, user will get notified and the reward stars will be automatically set to 0 for this challenge. Simultaneously the time spent on this challenge and the reward stars will be recorded into the database 
				if (i <= 0) {
					JOptionPane.showMessageDialog(null, "Time is up, go to next challenge.");
					starSet[count - 1] = 0;
					String sql = "UPDATE Level_One SET Time = ?, Star = ? WHERE Word_Seq = ?";
					try {
						stmt = con.prepareStatement(sql);
						stmt.setInt(1, 15 - i);
						stmt.setInt(2, starSet[count - 1]);
						stmt.setInt(3, count);
						stmt.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// The timer will restart and the jlbTimer will be reset to initial state
					i = 15;
					jlbTimer.setText(i + "");
					timer.restart();
					// while there are still challenges left, continue the process
					if(count < 10) {
						origin = wordSet[count];
						s = scramble(origin.length(), origin);
						jlbShowWord.setText(s);
						count++;
						jtfInput.setText("");
						// if you skip the current challenge, your stars for this challenge will become zero.
						starSet[count-1] = 0;
					}
					else {
						timer.stop();
						}
				}
			}
		});
//
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Auto-generated method stub
			// Here we will explain mainly about how the program checks the correctness of user's input
			// As user clicks the confirm button
			if (e.getSource() == jbtConfirm) {
				// If the input text filed is not empty
				if (!this.jtfInput.getText().equals("")) {
					// We declare a string named ans to store the input data
					String ans = this.jtfInput.getText();
					// We initially created an array to store the words we retrieved from database in uppercase form. Then we reduce them to lowercase, and check if they equal to the input 
					if(ans.equals(origin.toLowerCase()) && i > 10) {
						// while countdown time is larger than 10, we reward user 3 stars
						starSet[count - 1] = 3;
						// In database we find the corresponding word by its sequence, then we assign time and stars to its attributes as a record
						String sql = "UPDATE Level_One SET Time = ?, Star = ? WHERE Word_Seq = ?";
						try {
							stmt = con.prepareStatement(sql);
							stmt.setInt(1, 15 - i);
							stmt.setInt(2, starSet[count - 1]);
							stmt.setInt(3, count);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						timer.stop();
						i = 15;
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
					else if(ans.equals(origin.toLowerCase()) && i > 5) {
						starSet[count - 1] = 2;
						String sql = "UPDATE Level_One SET Time = ?, Star = ? WHERE Word_Seq = ?";
						try {
							stmt = con.prepareStatement(sql);
							stmt.setInt(1, 15 - i);
							stmt.setInt(2, starSet[count - 1]);
							stmt.setInt(3, count);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						timer.stop();
						i = 15;
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
					else if(ans.equals(origin.toLowerCase()) && i > 0) {
						starSet[count - 1] = 1;
						String sql = "UPDATE Level_One SET Time = ?, Star = ? WHERE Word_Seq = ?";
						try {
							stmt = con.prepareStatement(sql);
							stmt.setInt(1, 15 - i);
							stmt.setInt(2, starSet[count - 1]);
							stmt.setInt(3, count);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						timer.stop();
						i = 15;
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
				String sql = "UPDATE Level_One SET Time = ?, Star = ? WHERE Word_Seq = ?";
				try {
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, 15 - i);
					stmt.setInt(2, starSet[count - 1]);
					stmt.setInt(3, count);
					stmt.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				i = 15;
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
				String sql = "SELECT * FROM Level_One";
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