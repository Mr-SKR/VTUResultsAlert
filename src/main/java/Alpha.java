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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class Alpha {
	
	public String Input="";
	private JFrame frame;
	private JTextField textField;
	public Boolean cbcsFlag = false;
	public Boolean nonCbcsFlag = false;
	public Boolean validUsn = true;

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
		
		JLabel usnLabel = new JLabel("Select scheme & Enter your USN:");
		usnLabel.setForeground(Color.WHITE);
		usnLabel.setFont(new Font("Serif", Font.PLAIN, 12));
		usnLabel.setBounds(80, 150, 250, 175);
		frame.getContentPane().add(usnLabel);
		
		textField = new JTextField(); // User Input Field
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(255, 255, 255));
		textField.setBackground(new Color(153, 102, 255));
		textField.setBounds(76, 250, 352, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		JCheckBox checkbox1 = new JCheckBox("CBCS");
		checkbox1.setForeground(Color.WHITE);
		checkbox1.setBackground(Color.decode("0X2d2d2d"));
		checkbox1.setOpaque(false);
		checkbox1.setBounds(260, 225, 85, 25);
		JCheckBox checkbox2 = new JCheckBox("Non-CBCS");
		checkbox2.setForeground(Color.WHITE);
		checkbox2.setBackground(Color.decode("0X2d2d2d"));
		checkbox2.setOpaque(false);
		checkbox2.setBounds(340, 225, 85, 25);
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
		
		

		checkbox1.addItemListener(new ItemListener() {
				            public void itemStateChanged(ItemEvent e) {
				                if (e.getStateChange() == 1) {
				                    //System.out.println("CBCS selected");
				                    cbcsFlag = true;
				                } else {
				                    //System.out.println("CBCS deselected");
				                    cbcsFlag = false;
				                }
				            }
				        });
		
		checkbox2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    //System.out.println("Non-CBCS selected");
                    nonCbcsFlag = true;
                } else {
                    //System.out.println("Non-CBCS deselected");
                    nonCbcsFlag = false;
                }
            }
        });
		
		
		
		
		textField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Arg0)
				{	
					Input = textField.getText();
					
					if(Input.length() != 10)
					{
						//System.out.println("Invalid USN");
						validUsn = false;  
					}
					
					if(cbcsFlag == nonCbcsFlag || !validUsn)
					{
						//System.out.println("ERROR");
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

			                    
			            		new Alert(Input, cbcsFlag);
								

			                }
			            });         
			            hilo.start();
			
					}
					   
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Arg0)
			{	
				
				Input = textField.getText();
				
				if(Input.length() != 10)
				{
					//System.out.println("Invalid USN");
					validUsn = false;  
				}
				
				if(cbcsFlag == nonCbcsFlag || !validUsn)
				{
					//System.out.println("ERROR");
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

		                    
						new Alert(Input, cbcsFlag);
							

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