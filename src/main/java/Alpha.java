package main.java;


import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alpha {
	
	public String Input="";
	public String InputSem="";
	private JFrame frame;
	private JTextField textField;
	private JTextField textFieldSem;
	public Boolean cbcsFlag = false;
	public Boolean nonCbcsFlag = false;
	public Boolean validUsn = true;
	public Boolean validSem = true;
	public Boolean validScheme = true;
	public int semester = 0;

	/*
	 * 
	 *  Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alpha window = new Alpha();
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
	public Alpha() {
		initialize(); //call function
		
	}

	/*
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Alpha.class.getResource("vtu.png")));
		frame.setTitle("VTU Results Alert");
		frame.getContentPane().setBackground(new Color(153, 51, 255));
		frame.setResizable(false);
		frame.setBounds(100, 100, 495, 369);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(165, 15, 175, 175);
		Image img = new ImageIcon(this.getClass().getResource("atb.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		frame.getContentPane().add(lblNewLabel);
		Image back = new ImageIcon(this.getClass().getResource("Background.png")).getImage();
		
		JLabel atbLabel = new JLabel("ALL THE BEST");
		atbLabel.setForeground(Color.WHITE);
		atbLabel.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		atbLabel.setBounds(195, 115, 250, 175);
		frame.getContentPane().add(atbLabel);
		
		JLabel usnLabel = new JLabel("UNIVERSITY SERIAL NUMBER");
		usnLabel.setForeground(Color.BLACK);
		usnLabel.setFont(new Font("Serif", Font.PLAIN, 12));
		usnLabel.setBounds(160, 155, 200, 175);
		frame.getContentPane().add(usnLabel);
		
		JLabel semLabel = new JLabel("SEM");
		semLabel.setForeground(Color.BLACK);
		semLabel.setFont(new Font("Serif", Font.PLAIN, 12));
		semLabel.setBounds(52, 155, 30, 175);
		frame.getContentPane().add(semLabel);
		
		JLabel schemeLabel = new JLabel("SCHEME");
		schemeLabel.setForeground(Color.BLACK);
		schemeLabel.setFont(new Font("Serif", Font.PLAIN, 12));
		schemeLabel.setBounds(355, 180, 85, 25);
		frame.getContentPane().add(schemeLabel);
		
		textField = new JTextField(); // User Input Field
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(255, 255, 255));
		textField.setBackground(new Color(153, 102, 255));
		textField.setBounds(76, 250, 353, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textFieldSem = new JTextField(); // User Input Field
		textFieldSem.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSem.setForeground(new Color(255, 255, 255));
		textFieldSem.setBackground(new Color(153, 102, 255));
		textFieldSem.setBounds(50, 250, 28, 33);
		frame.getContentPane().add(textFieldSem);
		textFieldSem.setColumns(1);
		
		
		
		JCheckBox checkbox1 = new JCheckBox("CBCS");
		checkbox1.setForeground(Color.WHITE);
		checkbox1.setBackground(Color.decode("0X2d2d2d"));
		checkbox1.setOpaque(false);
		checkbox1.setBounds(350, 200, 85, 25);
		JCheckBox checkbox2 = new JCheckBox("Non-CBCS");
		checkbox2.setForeground(Color.WHITE);
		checkbox2.setBackground(Color.decode("0X2d2d2d"));
		checkbox2.setOpaque(false);
		checkbox2.setBounds(350, 225, 85, 25);
		frame.add(checkbox1);
		frame.add(checkbox2);
		
		JButton button = new JButton("START");
		button.setBounds(150,290,85,25);
		frame.add(button);
		
		JButton button2 = new JButton("EXIT");
		button2.setBounds(275,290,85,25);
		frame.add(button2);
		
		JLabel Background = new JLabel(""); // Background Label
		Background.setBounds(0, 0, 489, 340);
		Background.setIcon(new ImageIcon(back));
		frame.getContentPane().add(Background);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Arg0)
			{	
				cbcsFlag = checkbox1.isSelected();
				nonCbcsFlag = checkbox2.isSelected();
				validSem = true;
				Input = textField.getText();
				InputSem = textFieldSem.getText();
				
				if(Input.length() != 10)
				{
					//System.out.println("Invalid USN");
					validUsn = false;  
				}
				try {
				semester = Integer.parseInt(InputSem);
				} catch (Exception e) {
					validSem = false;
				}
				
				if(semester > 8 || semester < 1)
				{
					//System.out.println("Invalid Semester");
					validSem = false;  
				}
				
				if(cbcsFlag == true && nonCbcsFlag == true)
					validScheme = false;
				if(cbcsFlag == false && nonCbcsFlag == false)
					validScheme = false;
				
				if(cbcsFlag == nonCbcsFlag || !validUsn || !validSem)
				{
					System.out.println("ERROR");
					Image img = new ImageIcon(this.getClass().getResource("error.png")).getImage();
					lblNewLabel.setIcon(new ImageIcon(img));
					frame.getContentPane().add(lblNewLabel);
					frame.getContentPane().add(Background);
					atbLabel.setText("INVALID INPUT");
				}
				else
				{
					Image img = new ImageIcon(this.getClass().getResource("running.png")).getImage();
					lblNewLabel.setIcon(new ImageIcon(img));
					frame.getContentPane().add(lblNewLabel);
					frame.getContentPane().add(Background);
					atbLabel.setText("    RUNNING...");
					
					

		            Thread hilo = new Thread(new Runnable() {

		            	@Override
		                public void run() {

		                    
		            		new Alert(Input, cbcsFlag, semester);
							

		                }
		            });         
		            hilo.start();
		
				}
							
			}
			
	});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Arg0)
			{	
				frame.dispose();
				System.exit(0);
			}
			
	});	
		
	}


} 