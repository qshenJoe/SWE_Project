import java.sql.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class MainPanel {
	 JFrame frame;
	private JPanel MainMenu;
	private GameWindow gamewindow;
	private GameWindow2 gamewindow2;
	private GameWindow3 gamewindow3;
	private showStatistics ss;
	private JPanel NewGame;
	private JPanel Credits;
	private JPanel SelectLevel;
	private JPanel Tutorial;
	private Font bItalic;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel window = new MainPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		bItalic = new Font("Arial", Font.ITALIC, 14);

		JLabel wordUnscrambler = new JLabel("New label");
		Image img = new ImageIcon(this.getClass().getResource("/coverimg.jpg")).getImage();
		wordUnscrambler.setIcon(new ImageIcon(img));
		wordUnscrambler.setBounds(89, 519, 580, 220);
		frame.getContentPane().add(wordUnscrambler);


		final JPanel panel = new JPanel();
		panel.setBounds(85, 59, 626, 442);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));


		final JPanel MainMenu = new JPanel();
		panel.add(MainMenu, "name_101560506384937");
		MainMenu.setLayout(null);
		
		final JPanel NewGame = new JPanel();
		panel.add(NewGame, "name_102159917282535");
		NewGame.setLayout(null);
		NewGame.setVisible(false);
		
		final JPanel SelectLevel = new JPanel();
		panel.add(SelectLevel, "name_101659771901669");
		SelectLevel.setLayout(null);
		SelectLevel.setVisible(false);
		
		final JPanel Credits = new JPanel();
		panel.add(Credits, "name_101566202964032");
		Credits.setLayout(null);
		Credits.setVisible(false);
		
		final JPanel Statistics = new JPanel();
		panel.add(Statistics, "name_101568722698602");
		Statistics.setLayout(null);
		Statistics.setVisible(false);
		
		JButton btnNewGame = new JButton("New game");
		btnNewGame.setFont(bItalic);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame.setVisible(true);
				MainMenu.setVisible(false);
			}
		});
		btnNewGame.setBounds(266, 140, 117, 29);
		MainMenu.add(btnNewGame);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.setFont(bItalic);
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credits.setVisible(true);
				MainMenu.setVisible(false);
			}
		});
		btnCredits.setBounds(266, 282, 117, 29);
		MainMenu.add(btnCredits);
		
		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.setFont(bItalic);
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ss = new showStatistics();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ss.setBounds(350, 150, 600, 400);
				ss.setVisible(true);
				ss.setMainWindowLayout();
				MainMenu.setVisible(false);
			}
		});
		btnStatistics.setBounds(266, 323, 117, 29);
		MainMenu.add(btnStatistics);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(bItalic);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(266, 364, 117, 29);
		MainMenu.add(btnExit);
		
		JCheckBox chckbxSound = new JCheckBox("Sound");
		chckbxSound.setBounds(266, 247, 128, 23);
		MainMenu.add(chckbxSound);
		
		JButton btnGoBackTo_2 = new JButton("Go back to menu");
		btnGoBackTo_2.setFont(bItalic);
		btnGoBackTo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(true);
				Credits.setVisible(false);
			}
		});
		btnGoBackTo_2.setBounds(6, 407, 138, 29);
		Credits.add(btnGoBackTo_2);
		
		String initialText = "<html>\n" + 
				"<ul>\n" +
				"<font color=black><font size=+2><b>Code & UI desin:</b> <i>Qiaohong Shen</i> & <i>Yipeng Hua</i></font>\n" +
                "<font color=black><font size=+2><b>Database Modeling:</b> <i>Yu Liu</i></font><br>" +
                "<font color=black><font size=+2><b>Test and modify:</b> <i>Liaoyuan Cai</i></font>\n" +
                "</ul>\n";
		
		JLabel lblCred = new JLabel();
		
		lblCred.setBounds(6, 6, 614, 379);
		lblCred.setText(initialText);
		Credits.add(lblCred);
		
		JButton btnGoBackTo_1 = new JButton("Go back to menu");
		btnGoBackTo_1.setFont(bItalic);
		btnGoBackTo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(true);
				Statistics.setVisible(false);
			}
		});
		btnGoBackTo_1.setBounds(6, 407, 132, 29);
		Statistics.add(btnGoBackTo_1);
		

		JButton level_1 = new JButton("Level 1");
		level_1.setFont(bItalic);
		level_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						gamewindow = new GameWindow();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gamewindow.setBounds(350, 150, 600, 400);
					gamewindow.setVisible(true);
					gamewindow.setMainWindowLayout();
			}
		});
		level_1.setBounds(141, 123, 117, 29);
		SelectLevel.add(level_1);
		
		JButton level_2 = new JButton("Level 2");
		level_2.setFont(bItalic);
		level_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						gamewindow2 = new GameWindow2();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gamewindow2.setBounds(350, 150, 600, 400);
					gamewindow2.setVisible(true);
					gamewindow2.setMainWindowLayout();
			}
		});
		level_2.setBounds(141, 164, 117, 29);
		SelectLevel.add(level_2);
		
		JButton level_3 = new JButton("Level 3");
		level_3.setFont(bItalic);
		level_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						gamewindow3 = new GameWindow3();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gamewindow3.setBounds(350, 150, 600, 400);
					gamewindow3.setVisible(true);
					gamewindow3.setMainWindowLayout();
			}
		});
		level_3.setBounds(141, 205, 117, 29);
		SelectLevel.add(level_3);
		
		JLabel lblPrimaryLevels = new JLabel("Primary levels:");
		lblPrimaryLevels.setBounds(155, 95, 103, 16);
		SelectLevel.add(lblPrimaryLevels);
		
		JButton btnGoBackTo = new JButton("Go back to menu");
		btnGoBackTo.setFont(bItalic);
		btnGoBackTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(true);
				SelectLevel.setVisible(false);
			}
		});
		btnGoBackTo.setBounds(6, 407, 151, 29);
		SelectLevel.add(btnGoBackTo);
		
		JButton btnSelectALevel = new JButton("Select a level");
		btnSelectALevel.setFont(bItalic);
		btnSelectALevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectLevel.setVisible(true);
				NewGame.setVisible(false);
			}
		});
		btnSelectALevel.setBounds(135, 407, 117, 29);
		NewGame.add(btnSelectALevel);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.setFont(bItalic);
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(true);
				NewGame.setVisible(false);
			}
		});
		btnGoBack.setBounds(264, 407, 117, 29);
		NewGame.add(btnGoBack);
		
		JLabel Tutorial = new JLabel("");
		Tutorial.setBounds(0, 0, 591, 390);
		NewGame.add(Tutorial);
		
		JButton btnNewButton = new JButton("Tutorial");
		btnNewButton.setFont(bItalic);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tutorial.setText("Follow the notations of each game window and you will know how to play the game."
						+ " With limited time, the faster you complete each challenge, the more stars you can get."
						+ " You can check out your statistics in each level by click the statistics button on the menu.");
			}
		});
		btnNewButton.setBounds(6, 407, 117, 29);
		NewGame.add(btnNewButton);
	}
}