import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/*
 * class TripPlanner : 
 *  - main class for Trip Planner application
 *  - implements ActionListener for handling the action events
 */
public class TripPlanner implements ActionListener {

	ArrayList<PublicTransport> transportList = new ArrayList<PublicTransport>(); // for listing objects of transport
	int count; // for storing the number of objects in the arraylist
	int inputState; // for storing the state of input process to determine the next step (state = 0, 1, 2, 3)
	String currentTransport; // for storing the current transport type

	// for storing user inputs for each object's attributes
	double baseFare;
	double farePerStation;
	int nStation;
	double farePerKm;
	double distance;

	// for storing the information of output text
	String outString = ""; // for storing the output text
	double totalTripFare = 0; // for storing the total trip fare

	// for building elements of GUI
	private JFrame frmTripPlanner;
	private JLabel PlannerLogo;
	private JLabel titleLabel;
	private JTextArea textDisiplay;

	private JPanel TransportPanel;
	private JButton trainButton;
	private JButton busButton;
	private JButton taxiButton;

	private JPanel yesNoPanel;
	private JButton yesButton;
	private JButton noButton;

	private JPanel inputControlPanel;
	private JButton cancelButton;
	private JButton clearButton;
	private JButton enterButton;

	private JPanel keypadPannel;
	private JButton keypad1;
	private JButton keypad2;
	private JButton keypad3;
	private JButton keypad4;
	private JButton keypad5;
	private JButton keypad6;
	private JButton keypad7;
	private JButton keypad8;
	private JButton keypad9;
	private JButton keypad0;
	private JPanel keypadPadding;

	// using WindowBuilder to build the GUI
	
	/*
	 * main method : lunch the Trip Planner application
	 * EventQueue.invokeLater : for thread safety
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TripPlanner window = new TripPlanner();
					window.frmTripPlanner.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// constructor : create the application
	public TripPlanner() {
		initialize();
	}

	// initialize the contents of the frame
	private void initialize() {
		// window frame
		frmTripPlanner = new JFrame();
		frmTripPlanner.setTitle("Trip Planner");
		frmTripPlanner.setBounds(100, 100, 571, 658);
		frmTripPlanner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTripPlanner.getContentPane().setLayout(null);

		// logo
		PlannerLogo = new JLabel("");
		PlannerLogo.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/planner_logo.png")));
		PlannerLogo.setBounds(90, 29, 398, 62);
		frmTripPlanner.getContentPane().add(PlannerLogo);

		// title
		titleLabel = new JLabel("TRIP PLANNER");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		titleLabel.setBounds(246, 103, 78, 16);
		frmTripPlanner.getContentPane().add(titleLabel);

		// area that display the output text
		textDisiplay = new JTextArea();
		textDisiplay.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textDisiplay.setEditable(false);
		textDisiplay.setBounds(157, 131, 246, 182);
		textDisiplay.setText("Choose transport (from left menu): "); // initial text
		frmTripPlanner.getContentPane().add(textDisiplay);

		// Train, Bus, Taxi buttons
		TransportPanel = new JPanel();
		TransportPanel.setBounds(40, 131, 113, 87);
		frmTripPlanner.getContentPane().add(TransportPanel);
		TransportPanel.setLayout(new GridLayout(3, 1, 0, 0));

		trainButton = new JButton("TRAIN");
		trainButton.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/train.png")));
		trainButton.addActionListener(this);
		TransportPanel.add(trainButton);

		busButton = new JButton("BUS");
		busButton.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/bus.png")));
		busButton.addActionListener(this);
		TransportPanel.add(busButton);

		taxiButton = new JButton("TAXI");
		taxiButton.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/taxi.png")));
		taxiButton.addActionListener(this);
		TransportPanel.add(taxiButton);

		// Cancel, Clear, Enter buttons
		inputControlPanel = new JPanel();
		inputControlPanel.setBounds(403, 314, 120, 251);
		frmTripPlanner.getContentPane().add(inputControlPanel);
		inputControlPanel.setLayout(new GridLayout(4, 1, 0, 0));

		cancelButton = new JButton("CANCEL");
		cancelButton.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/cancel.png")));
		cancelButton.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		cancelButton.addActionListener(this);
		inputControlPanel.add(cancelButton);

		clearButton = new JButton("CLEAR");
		clearButton.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/clear.png")));
		clearButton.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		clearButton.addActionListener(this);
		inputControlPanel.add(clearButton);

		enterButton = new JButton("ENTER");
		enterButton.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/enter.png")));
		enterButton.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		enterButton.addActionListener(this);
		inputControlPanel.add(enterButton);

		// Yes, No buttons
		yesNoPanel = new JPanel();
		yesNoPanel.setBounds(403, 131, 120, 87);
		frmTripPlanner.getContentPane().add(yesNoPanel);
		yesNoPanel.setLayout(new GridLayout(2, 1, 0, 0));

		yesButton = new JButton("YES");
		yesButton.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/enterSmall.png")));
		yesButton.addActionListener(this);
		yesNoPanel.add(yesButton);

		noButton = new JButton("NO");
		noButton.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/cancelSmall.png")));
		noButton.addActionListener(this);
		yesNoPanel.add(noButton);

		// keypad
		keypadPannel = new JPanel();
		keypadPannel.setBounds(157, 314, 246, 251);
		frmTripPlanner.getContentPane().add(keypadPannel);
		keypadPannel.setLayout(new GridLayout(4, 3, 0, 0));

		keypad1 = new JButton("");
		keypad1.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/1.png")));
		keypad1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("1");
			}
		});
		keypadPannel.add(keypad1);

		keypad2 = new JButton("");
		keypad2.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/2.png")));
		keypad2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("2");
			}
		});
		keypadPannel.add(keypad2);

		keypad3 = new JButton("");
		keypad3.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/3.png")));
		keypad3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("3");
			}
		});
		keypadPannel.add(keypad3);

		keypad4 = new JButton("");
		keypad4.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/4.png")));
		keypad4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("4");
			}
		});
		keypadPannel.add(keypad4);

		keypad5 = new JButton("");
		keypad5.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/5.png")));
		keypad5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("5");
			}
		});
		keypadPannel.add(keypad5);

		keypad6 = new JButton("");
		keypad6.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/6.png")));
		keypad6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("6");
			}
		});
		keypadPannel.add(keypad6);

		keypad7 = new JButton("");
		keypad7.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/7.png")));
		keypad7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("7");
			}
		});
		keypadPannel.add(keypad7);

		keypad8 = new JButton("");
		keypad8.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/8.png")));
		keypad8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("8");
			}
		});
		keypadPannel.add(keypad8);

		keypad9 = new JButton("");
		keypad9.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/9.png")));
		keypad9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("9");
			}
		});
		keypadPannel.add(keypad9);

		keypadPadding = new JPanel(); // for alligning the keypad
		keypadPannel.add(keypadPadding);

		keypad0 = new JButton("");
		keypad0.setIcon(new ImageIcon(TripPlanner.class.getResource("/assets/0.png")));
		keypad0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDisiplay.append("0");
			}
		});
		keypadPannel.add(keypad0);

	}

	/*
	 * method for getting the input integer number from text area
	 * used in enterButton action event
	 */
	public int getInputInteger() {
		return Integer.parseInt(textDisiplay.getText().replaceAll("[^0-9]", ""));
	}

	/*
	 * overrided actionPerformed method from ActionListener interface
	 * for handling the action events except the keypad buttons
	 * keypad buttons have their own action events in initialize method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		/*
		 * trainButton : set the current transport type to "Train", display the text for input process
		 * busButton : set the current transport type to "Bus", display the text for input process
		 * taxiButton : set the current transport type to "Taxi", display the text for input process
		 * all buttons set the state of input process to 0
		 */
		if (e.getSource() == trainButton) {
			currentTransport = "Train";
			textDisiplay.setText("You choose TRAIN\nEnter base fare: ");
			inputState = 0; 
		}

		else if (e.getSource() == busButton) {
			currentTransport = "Bus";
			textDisiplay.setText("You choose BUS\nEnter base fare: ");
			inputState = 0;
		}

		else if (e.getSource() == taxiButton) {
			currentTransport = "Taxi";
			textDisiplay.setText("You choose TAXI\nEnter base fare: "); 
			inputState = 0;
		}
		
		/*
		 * clearButton : clear the input integer number of text area
		 * cancelButton : reset all variables and clear the arraylist for new plan
		 * enterButton : 
		 * 		- get the input integer number from text area and store it to the corresponding variable
		 * 		- after getting all info, create the object of public transport based on the current transport type & user input
		 */
		else if (e.getSource() == clearButton) {
			// clear only the input integer number of text area
			String cleanText = textDisiplay.getText().replaceAll("[0-9]", "");
			textDisiplay.setText(cleanText);
		}

		else if (e.getSource() == cancelButton) {
			transportList.clear(); // clear the arraylist
			count = 0; // reset the number of objects in the arraylist
			inputState = 0; // reset the state of input process
			outString = ""; // reset the output text
			totalTripFare = 0; // reset the total trip fare
			textDisiplay.setText("Cancelled!\nNew plan:\nChoose transport (from left menu): "); // display the text
		}

		else if (e.getSource() == enterButton) {
			// when inputState is 0, consider the case that not choose the transport type yet
			if (inputState == 0) {
				baseFare = getInputInteger(); // get the input integer number from text area
				if (currentTransport == "Train" || currentTransport == "Bus") {
					inputState++; // update the state of input process
					textDisiplay.setText("Enter fare per station (for extra stations): ");
				} else if (currentTransport == "Taxi") {
					inputState++; // update the state of input process
					textDisiplay.setText("Enter fare per km: ");
				}
			}

			else if (inputState == 1) {
				inputState++;
				if (currentTransport == "Train" || currentTransport == "Bus") {
					farePerStation = getInputInteger();
					textDisiplay.setText("Enter number of stations: ");
				} else {
					farePerKm = getInputInteger();
					textDisiplay.setText("Enter distance (in km): ");
				}
			}

			else if (inputState == 2) {
				inputState++;
				count++; // update the number of objects in the arraylist
				if (currentTransport == "Train") {
					nStation = getInputInteger();
					// create the object of public transport based on the current transport type & user input
					PublicTransport trainObj = new Train(baseFare, farePerStation, nStation);
					transportList.add(trainObj); // add the object to the arraylist

				} else if (currentTransport == "Bus") {
					nStation = getInputInteger();
					PublicTransport busObj = new Bus(baseFare, farePerStation, nStation);
					transportList.add(busObj);
				} else {
					distance = getInputInteger();
					PublicTransport taxiObj = new Taxi(baseFare, farePerKm, distance);
					transportList.add(taxiObj);
				}
				textDisiplay.setText("Add more transport (from right menu)?");
			}
		}
		
		/*
		 * yesButton : reset the inputState & display the text for choosing new transport type
		 * noButton : calculate the total fare for each object, calculate the total trip fare, display the output text
		 * two buttons are only available when inputState is 3
		 */
		else if (e.getSource() == yesButton) {
			if (inputState == 3) {
				inputState = 0;
				textDisiplay.setText("Choose transport (from left menu): ");
			}
		}

		else if (e.getSource() == noButton) {
			if (inputState == 3) {
				for (int i = 0; i < count; i++) {
					transportList.get(i).calculatePayment(); // calculate the total fare for each object
					totalTripFare = totalTripFare + transportList.get(i).getTotalFare(); // calculate the total trip fare
					outString = outString
							+ ("Transport" + (i + 1) + ": " + transportList.get(i).getModel() + "\n" + "Fare: "
									+ String.format("%.2f", transportList.get(i).getTotalFare()) + "\n");
				}
				outString = outString + ("===========================\n"
						+ "Total Trip Fare: " + String.format("%.2f", totalTripFare) + "\n");
				textDisiplay.setText(outString);
			}
		}
	}
}
