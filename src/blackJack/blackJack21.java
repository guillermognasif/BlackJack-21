package blackJack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class blackJack21 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JPanel tablePanel;
	private JPanel buttonPanel;
	private JPanel bettingPanel;
	private static boolean betting = true;
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel dealerPanel;
	private JPanel playerPanel;
	private JLabel lblPlayerPoints;
	private JLabel lblDealerPoints;
	private JButton btnPA;
	private JLabel lblCurrent;
	private JLabel lblB;
	private Dimension minSize;
	private JButton btn100;
	private JButton btn500;
	private JButton btn10;
	private JButton btn1;
	private JButton btnDeal;
	private JLabel lblStats;
	private JPanel ScorePanel;
	private JLabel lblPercentNum;
	private JLabel lblLossNum;
	private JLabel lblWinNum;
	private JLabel lblWalletNum;
	private JLabel lblChance;
	private JLabel lblLosses;
	private JLabel lblWins;
	private JLabel lblWallet;
	private JButton btnSurrender;
	private JButton btnHitMe;
	private JButton btnStand;
	private JButton btnDoubleDown;
	private Deck deck;
	private Player player;
	private tableUpdate game;
	private JPanel playerCardPanel;
	private JPanel dealerCardPanel;
	private String faceDownCard;
	private JLabel dealerCard2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					blackJack21 frame = new blackJack21();
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
	//make changes to buttons when player looses once the balance is zero.
	public blackJack21() {
		this.deck = new Deck();
		this.player = new Player(2500, 0, 0);
		this.game = new tableUpdate(player, deck);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 700);
		setMinimumSize(getSize());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 69, 19));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		createTitlePanel();
		contentPane.add(titlePanel, BorderLayout.NORTH);
		
		creatTablePanel();
		contentPane.add(tablePanel, BorderLayout.CENTER);

		createWestPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		
		
		createEastPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		
		setSouthPanel();
	}
	
	/**
	 * Changes the betting value to modify the GUI for different stages of
	 * the game.
	 */
	public static void setBetting() {
		if (betting) {
			betting = false;
		}
		else {
			betting = true;
		}
	}
	
	/**
	 * Creates the JButton that displays Play Again. This is only visible once the hand is
	 * over and the player can start a new bet to continue the game.
	 */
	private void createBtnPA() {
		btnPA = new JButton("PLAY AGAIN");
		btnPA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBetting();
				btnPA.setVisible(false);
				btnDeal.setVisible(true);
				westPanel.revalidate();
				bettingPanel.setVisible(true);
				bettingPanel.revalidate();
				setSouthPanel();
				lblPlayerPoints.setText("");
				lblDealerPoints.setText("");
				lblDealerPoints.setForeground(Color.BLACK);
				lblDealerPoints.setVisible(false);
				playerCardPanel.removeAll();
				dealerCardPanel.removeAll();
				contentPane.revalidate();
			}
		});
		btnPA.setVisible(false);
		btnPA.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPA.setFont(new Font("Copperplate", Font.PLAIN, 13));
		btnPA.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btnPA.setBackground(null);
		btnPA.setBorder(null);
	}
	
	/**
	 * Makes the JButton Play Again visible when it is needed.
	 */
	public void btnShow() {
		if (player.getWallet() > 0) {
			btnPA.setVisible(true);
		}
		else {
			contentPane.removeAll();
		}
	}
	
	/**
	 * Creates the JPanel that is displayed in the west of the content pane 
	 * that shows the current bet amount.
	 */
	private void createWestPanel() {
		westPanel = new JPanel();
		westPanel.setBackground(new Color(0, 100, 0));
		westPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		westPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		createLblCurrent();
		westPanel.add(lblCurrent);
		
		createLblB();
		westPanel.add(lblB);
		
		createBtnDeal();
		westPanel.add(btnDeal);
		
		createBtnPA();
		westPanel.add(btnPA);
	}

	/**
	 * Creates the JLabel that displays the text "Current" on the line above
	 * the current bet's display.
	 */
	private void createLblCurrent() {
		lblCurrent = new JLabel("Current");
		lblCurrent.setBackground(new Color(0, 0, 0));
		lblCurrent.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrent.setForeground(new Color(0, 0, 0));
		lblCurrent.setFont(new Font("Copperplate", Font.PLAIN, 30));
	}
	
	/**
	 * Creates the JLabel showing the current bet.
	 */
	private void createLblB() {
		lblB = new JLabel("Bet: $" + game.getBet());
		lblB.setBackground(new Color(0, 0, 0));
		lblB.setHorizontalTextPosition(SwingConstants.CENTER);
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setForeground(new Color(0, 0, 0));
		lblB.setFont(new Font("Copperplate", Font.PLAIN, 30));
	}
	
	/**
	 * Creates the JPanel that is displayed in the south while the player is 
	 * placing a bet.
	 */
	private void createBettingPanel() {
		bettingPanel = new JPanel();
		bettingPanel.setVisible(true);
		minSize = new Dimension(79, 29);
		bettingPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		bettingPanel.setLayout(new GridLayout(1, 4, 0, 5));
		bettingPanel.setOpaque(true);
		bettingPanel.setBackground(new Color(0, 100, 0));
		bettingPanel.setMinimumSize(minSize);
		
		createBtn1();
		bettingPanel.add(btn1);
		
		createBtn10();
		bettingPanel.add(btn10);
		
		createBtn100();
		bettingPanel.add(btn100);
		
		createBtn500();
		bettingPanel.add(btn500);
		
		if (!(betting)) {
			bettingPanel.setVisible(false);
		}
	}
	
	/**
	 * Creates the JButton to place a $100 Bet.
	 */
	private void createBtn100() {
		btn100 = new JButton("$100");
		btn100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				placeBet(100);
			}
		});
		btn100.setFont(new Font("Copperplate", Font.PLAIN, 30));
		btn100.setHorizontalTextPosition(SwingConstants.CENTER);
		btn100.setForeground(new Color(0, 100, 0));
		btn100.setBackground(null);
		btn100.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btn100.setBorder(null);
	}

	/**
	 * Creates the JButton to place a $500 Bet.
	 */
	private void createBtn500() {
		btn500 = new JButton("$500");
		btn500.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				placeBet(500);
			}
		});
		btn500.setFont(new Font("Copperplate", Font.PLAIN, 30));
		btn500.setHorizontalTextPosition(SwingConstants.CENTER);
		btn500.setForeground(new Color(0, 100, 0));
		btn500.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btn500.setBackground(null);
		btn500.setBorder(null);
	}
	
	/**
	 * Creates the JButton to place a $10 Bet.
	 */
	private void createBtn10() {
		btn10 = new JButton("$10");
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				placeBet(10);
			}
		});
		btn10.setFont(new Font("Copperplate", Font.PLAIN, 30));
		btn10.setHorizontalTextPosition(SwingConstants.CENTER);
		btn10.setForeground(new Color(0, 100, 0));
		btn10.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btn10.setBackground(null);
		btn10.setBorder(null);
	}

	/**
	 * Creates the JButton to place a $1 Bet.
	 */
	private void createBtn1() {
		btn1 = new JButton("$1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				placeBet(1);
			}
		});
		btn1.setFont(new Font("Copperplate", Font.PLAIN, 30));
		btn1.setHorizontalTextPosition(SwingConstants.CENTER);
		btn1.setForeground(new Color(0, 100, 0));
		btn1.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btn1.setBackground(null);
		btn1.setBorder(null);
	}
	
	/**
	 * Adds to the current bet displayed on the GUI and updates the player's
	 *  wallet display.
	 * @param i amount to add to the current Bet
	 */
	protected void placeBet(int i) {
		game.bet(i);
		lblB.setText("Bet: $" + game.getBet());
		lblWalletNum.setText("$" + Integer.toString(player.getWallet()));
	}

	/**
	 * Creates the JButton the transitions the game from a betting to playing phase.
	 */
	private void createBtnDeal() {
		btnDeal = new JButton("DEAL");
		btnDeal.setVisible(true);
		btnDeal.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDeal.setFont(new Font("Copperplate", Font.PLAIN, 20));
		btnDeal.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btnDeal.setBorder(null);
		btnDeal.setBackground(null);
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (game.getBet()!=0)
				{
				setBetting();
				btnDeal.setVisible(false);
				bettingPanel.setVisible(false);
				buttonPanel.setVisible(true);
				setSouthPanel();
				game.handStart();
				game.setDealerScore();
				game.setPlayerScore();
				ArrayList<Card> dsc = new ArrayList<Card>(game.getDealerCards());
				addDealerCard(dsc.get(0).getImagePath());
				faceDownCard = dsc.get(1).getImagePath();
				dealerCard2 = new JLabel("");
				dealerCard2.setIcon(new ImageIcon(blackJack21.class.getResource("/CardImages/faceDown.png")));
				dealerCardPanel.add(dealerCard2);
				lblDealerPoints.setText(Integer.toString(game.getDealerScore()));
				contentPane.revalidate();
				ArrayList<Card> psc = new ArrayList<Card>(game.getPlayerCards());
				for (int i = 0; i < psc.size(); i++) {
					String imagePath = psc.get(i).getImagePath();
					addPlayerCard(imagePath);
				}
				}	
			}
		});
	}
	
	/**
	 * Creates the JPanel that is in the East position of the content pane.
	 * This panel will contain the score board.
	 */
	private void createEastPanel() {
		eastPanel = new JPanel();
		eastPanel.setSize(275, 375);
		eastPanel.setForeground(new Color(255, 255, 255));
		eastPanel.setBackground(new Color(0, 100, 0));
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		createLabelStats();
		eastPanel.add(lblStats, BorderLayout.NORTH);
		
		createScorePanel();
		eastPanel.add(ScorePanel, BorderLayout.CENTER);
	}
	
	/**
	 * Creates the JLabel that is the header of the score board.
	 */
	private void createLabelStats() {
		lblStats = new JLabel("Stats");
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setHorizontalTextPosition(SwingConstants.CENTER);
		lblStats.setFont(new Font("Copperplate", Font.PLAIN, 30));
		lblStats.setForeground(new Color(0, 0, 0));
	}

	/**
	 * Creates the JPanel that has the ScoreBoard on it.
	 */
	private void createScorePanel() {
		ScorePanel = new JPanel();
		ScorePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ScorePanel.setOpaque(false);
		ScorePanel.setLayout(new GridLayout(4, 2, 2, 2));
		
		//Row One
		createLblWallet();
		ScorePanel.add(lblWallet);
		
		createLblWalletNum();
		ScorePanel.add(lblWalletNum);

		//Row Two
		createLblWins();
		ScorePanel.add(lblWins);
		
		createLblWinNum();
		ScorePanel.add(lblWinNum);
		
		//Row Three
		createLblLosses();
		ScorePanel.add(lblLosses);
		
		createLblLossNum();
		ScorePanel.add(lblLossNum);
		
		//RowFour
		createLblChance();
		ScorePanel.add(lblChance);
		
		createLblPercentNum();
		ScorePanel.add(lblPercentNum);
		
	}

	/**
	 * Creates the JLabel that displays the percentage of wins the player has
	 * on the score board.
	 */
	private void createLblPercentNum() {
		lblPercentNum = new JLabel(Integer.toString((int)player.getWinPercentage()));
		lblPercentNum.setForeground(new Color(0, 0, 0));
		lblPercentNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblPercentNum.setFont(new Font("Copperplate", Font.PLAIN, 20));
	}

	/**
	 * Creates the JLabel that displays the amount of losses the player has
	 * on the score board.
	 */
	private void createLblLossNum() {
		lblLossNum = new JLabel(Integer.toString(player.getLosses()));
		lblLossNum.setForeground(new Color(139, 0, 0));
		lblLossNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblLossNum.setFont(new Font("Copperplate", Font.PLAIN, 20));
	}

	/**
	 *  Creates the JLabel that displays the amount of wins the player has
	 *  on the score board.
	 */
	private void createLblWinNum() {
		lblWinNum = new JLabel(Integer.toString(player.getWins()));
		lblWinNum.setForeground(new Color(0, 255, 0));
		lblWinNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinNum.setFont(new Font("Copperplate", Font.PLAIN, 20));
	}

	/**
	 *  Creates the JLabel that displays amount in the player's wallet
	 *  on the score board.
	 */
	private void createLblWalletNum() {
		lblWalletNum = new JLabel("$" + Integer.toString(player.getWallet()));
		lblWalletNum.setForeground(new Color(0, 255, 0));
		lblWalletNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblWalletNum.setFont(new Font("Copperplate", Font.PLAIN, 20));	
	}

	/**
	 *  Creates the JLabel that displays win% on the score board.
	 */
	private void createLblChance() {
		lblChance = new JLabel("Win %:");
		lblChance.setBackground(new Color(0, 100, 0));
		lblChance.setForeground(new Color(0, 0, 0));
		lblChance.setHorizontalAlignment(SwingConstants.CENTER);
		lblChance.setFont(new Font("Copperplate", Font.PLAIN, 20));
	}

	/**
	 *  Creates the JLabel that displays losses on the score board.
	 */
	private void createLblLosses() {
		lblLosses = new JLabel("Losses:");
		lblLosses.setForeground(new Color(0, 0, 0));
		lblLosses.setHorizontalAlignment(SwingConstants.CENTER);
		lblLosses.setFont(new Font("Copperplate", Font.PLAIN, 20));
	}

	/**
	 *  Creates the JLabel that displays wins on the score board.
	 */
	private void createLblWins() {
		lblWins = new JLabel("Wins:");
		lblWins.setForeground(new Color(0, 0, 0));
		lblWins.setHorizontalAlignment(SwingConstants.CENTER);
		lblWins.setFont(new Font("Copperplate", Font.PLAIN, 20));
	}

	/**
	 * Creates the JLabel that displays Wallet on the score board.
	 */
	private void createLblWallet() {
		lblWallet = new JLabel("Wallet:");
		lblWallet.setForeground(new Color(0, 0, 0));
		lblWallet.setHorizontalAlignment(SwingConstants.CENTER);
		lblWallet.setFont(new Font("Copperplate", Font.PLAIN, 20));
	}
	
	/**
	 * Creates the tablePanel where the cards are displayed In the center position of
	 * the content pane.
	 * 
	 */
	private void creatTablePanel() {
		tablePanel = new JPanel();
		tablePanel.setBackground(new Color(0, 100, 0));
		tablePanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		createDlrPanel();
		tablePanel.add(dealerPanel);
		
		createPlayerPanel();
		tablePanel.add(playerPanel);
	}

	/**
	 * Creates a panel to be shown in the south side of the  tablePanel
	 * that has the Player's cards and points.
	 */
	private void createPlayerPanel() {
		playerPanel = new JPanel();
		playerPanel.setOpaque(false);
		playerPanel.setLayout(new BorderLayout(0, 0));
		
		createPlayerCardPanel();
		playerPanel.add(playerCardPanel, BorderLayout.CENTER);
	
		
		createLblPlayerPoints();
		playerPanel.add(lblPlayerPoints, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates a JPanel that will display the player's cards.
	 */
	private void createPlayerCardPanel() {
		playerCardPanel = new JPanel();
		playerCardPanel.setOpaque(false);
	}

	/**
	 * Creates a label that shows the value of the cards in
	 * the player's hand. 
	 */
	private void createLblPlayerPoints() {
		lblPlayerPoints = new JLabel(Integer.toString(game.getPlayerScore()));
		lblPlayerPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerPoints.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayerPoints.setFont(new Font("Copperplate", Font.PLAIN, 30));
	}

	/**
	 * Creates a panel that shows the Dealer's cards and points. 
	 */
	private void createDlrPanel() {
		dealerPanel = new JPanel();
		dealerPanel.setOpaque(false);
		dealerPanel.setLayout(new BorderLayout(0, 0));
		
		createDealerCardPanel();
		dealerPanel.add(dealerCardPanel, BorderLayout.SOUTH);
		
		createDlrPointsLbl();
		dealerPanel.add(lblDealerPoints, BorderLayout.NORTH);
	}

	/**
	 * Creates the JPanel that will display the dealer's cards.
	 */
	private void createDealerCardPanel() {
		dealerCardPanel = new JPanel();
		dealerCardPanel.setOpaque(false);
	}

	/**
	 * Creates a label that shows how many points the dealer's hand has.
	 * Only visible at the end of the hand.
	 */
	private void createDlrPointsLbl() {
		lblDealerPoints = new JLabel(Integer.toString(game.getDealerScore()));
		lblDealerPoints.setVisible(false);
		lblDealerPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealerPoints.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDealerPoints.setFont(new Font("Copperplate", Font.PLAIN, 30));
	}

	/**
	 * Creates the buttonPanel JPanel that is shown when a hand is being played.
	 */
	private void createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setVisible(true);
		minSize = new Dimension(79, 29);
		buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttonPanel.setLayout(new GridLayout(1, 4, 0, 5));
		buttonPanel.setOpaque(true);
		buttonPanel.setBackground(new Color(0, 100, 0));
		buttonPanel.setMinimumSize(minSize);

		createBtnStand();
		buttonPanel.add(btnStand);

		createBtnHitMe();
		buttonPanel.add(btnHitMe);

		createBtnSurrender();
		buttonPanel.add(btnSurrender);
		
		createBtnDD();
		buttonPanel.add(btnDoubleDown);
		
		if (betting) {
			buttonPanel.setVisible(false);
		}

	}

	/**
	 * Creates the JButton that performs the action Surrender. 
	 */
	private void createBtnSurrender() {
		btnSurrender = new JButton("Surrender");
		btnSurrender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.setWallet(player.getWallet() + (game.getBet() / 2));
				dealerCard2.setIcon(new ImageIcon(blackJack21.class.getResource("/CardImages/" + faceDownCard)));
				dealerCardPanel.revalidate();
				game.surrender();
				lblDealerPoints.setVisible(true);
				lblDealerPoints.setText("SURRENDER - LOSE");
				lblDealerPoints.setForeground(Color.RED);
				game.handEnd();
				lblLossNum.setText(Integer.toString(player.getLosses()));
				lblWinNum.setText(Integer.toString(player.getWins()));
				lblPercentNum.setText(Integer.toString((int)player.getWinPercentage()));
				lblWalletNum.setText(Integer.toString(player.getWallet()));
				lblB.setText("Bet: $" + Integer.toString(game.getBet()));
				btnShow();
				buttonPanel.setVisible(false);
				contentPane.revalidate();
			}
		});
		btnSurrender.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSurrender.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btnSurrender.setBorder(null);
		btnSurrender.setForeground(Color.BLACK);
		btnSurrender.setBackground(null);
		btnSurrender.setFont(new Font("Copperplate", Font.PLAIN, 13));
	}

	/**
	 * Creates the JButton the performs the action Hit Me.
	 */
	private void createBtnHitMe() {
		btnHitMe = new JButton("Hit Me");
		btnHitMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String card = game.hitMe();
				addPlayerCard(card);
				btnDoubleDown.setVisible(false);
				checkStatus();
			}
		});
		btnHitMe.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHitMe.setFont(new Font("Copperplate", Font.PLAIN, 13));
		btnHitMe.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btnHitMe.setBackground(null);
		btnHitMe.setBorder(null);
	}

	/**
	 * Creates the JButton that will perform the action Stand.
	 */
	private void createBtnStand() {
		btnStand = new JButton("Stand");
		btnStand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDealerPoints.setVisible(true);
				lblDealerPoints.revalidate();
				dealerCard2.setIcon(new ImageIcon(blackJack21.class.getResource("/CardImages/" + faceDownCard)));
				String newDealerCard = game.dealerDraw();
				while (!(newDealerCard.equals("end"))) {
					addDealerCard(newDealerCard);
					newDealerCard = game.dealerDraw();
				}
				resetTable();
			}
		});
		btnStand.setHorizontalTextPosition(SwingConstants.CENTER);
		btnStand.setFont(new Font("Copperplate", Font.PLAIN, 13));
		btnStand.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btnStand.setBackground(null);
		btnStand.setBorder(null);
	}

	/**
	 * Creates the JButton that performs the action Double Down.
	 */
	private void createBtnDD() {
		btnDoubleDown = new JButton("Double Down");
		btnDoubleDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = game.doubleDown();
				if (!(s.equals("end"))) {
					addPlayerCard(s);
					dealerCard2.setIcon(new ImageIcon(blackJack21.class.getResource("/CardImages/" + faceDownCard)));
					dealerCardPanel.revalidate();
					checkStatus();
					if (game.getPlayerScore() <= 21) {
						resetTable();
					}
				}
				
			}
		});
		btnDoubleDown.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDoubleDown.setFont(new Font("Copperplate", Font.PLAIN, 13));
		btnDoubleDown.setIcon(new ImageIcon(blackJack21.class.getResource("/pokerChip/poker_Chip_Small.png")));
		btnDoubleDown.setBackground(null);
		btnDoubleDown.setBorder(null);
	}

	/**
	 * Checks that the player hasn't exceeded 21 points when drawing a card.
	 * This will happen after the button double down or hit me is pressed.
	 */
	protected void checkStatus() {
		if (!(game.under21())) {
			lblDealerPoints.setVisible(true);
			lblDealerPoints.setText("BUST");
			lblDealerPoints.setForeground(Color.RED);
			dealerCard2.setIcon(new ImageIcon(blackJack21.class.getResource("/CardImages/" + faceDownCard)));
			dealerCardPanel.revalidate();
			resetTable();
		}
		
	}

	/**
	 * Resets the table and updates the score board when the hand is over.
	 */
	private void resetTable() {
		char c = game.handEnd();
		lblDealerPoints.setVisible(true);
		dealerCard2.setIcon(new ImageIcon(blackJack21.class.getResource("/CardImages/" + faceDownCard)));
		if (c == 'w') {
			lblDealerPoints.setText("WIN");
			lblDealerPoints.setForeground(new Color(0, 255, 0));
		}
		else if (c == 't') {
			lblDealerPoints.setText("TIE");
		}
		else {
			lblDealerPoints.setText("LOSE");
			lblDealerPoints.setForeground(Color.RED);
		}
		lblLossNum.setText(Integer.toString(player.getLosses()));
		lblWinNum.setText(Integer.toString(player.getWins()));
		lblPercentNum.setText(Integer.toString((int)player.getWinPercentage()));
		lblWalletNum.setText(Integer.toString(player.getWallet()));
		lblB.setText("Bet: $" + Integer.toString(game.getBet()));
		btnShow();
		buttonPanel.setVisible(false);
		contentPane.revalidate();
	}

	
	/**
	 * Sets the southern panel of the content pane in relation to if the
	 * player is betting or playing their hand.
	 */
	private void setSouthPanel() {
		createBettingPanel();
		createButtonPanel();
		
		if (betting) {
			contentPane.add(bettingPanel, BorderLayout.SOUTH);
		}
		else {
			contentPane.add(buttonPanel, BorderLayout.SOUTH);
		}
	}

	/**
	 * Creates the titlePanel that shows the title of the Game in the north
	 * position of the content pane. 
	 */
	private void createTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0, 100, 0));
		createTitleLabel();
		titlePanel.add(titleLabel);
	}
	
	/**
	 * Creates the JLabel that will display the title text in the titlePanel.
	 */
	private void createTitleLabel() {
		titleLabel = new JLabel("21 BLACKJACK");
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Copperplate", Font.BOLD, 45));
	}
	
	/**
	 * Adds a player card with the provided image path to the JFrame.
	 * @param imagePath
	 */
	protected void addPlayerCard(String imagePath) {
		JLabel j = new JLabel("");
		j.setIcon(new ImageIcon(blackJack21.class.getResource("/CardImages/" + imagePath)));
		playerCardPanel.add(j);
		lblPlayerPoints.setText(Integer.toString(game.getPlayerScore()));
		contentPane.revalidate();
	}
	
	/**
	 * Add's a dealer card to with the provided image path to the JFrame.
	 * @param imagePath
	 */
	protected void addDealerCard(String imagePath) {
		JLabel j = new JLabel("");
		j.setIcon(new ImageIcon(blackJack21.class.getResource("/CardImages/" + imagePath)));
		dealerCardPanel.add(j);
		lblDealerPoints.setText(Integer.toString(game.getDealerScore()));
		contentPane.revalidate();
	}
}
