package net.javaguides.swing;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField lastnameField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;
    private JLabel lblLastName;
    private JTextField firstnameField;
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "1234567891234567".getBytes();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
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
    
    public static String encrypt(String valueToEnc, Key key) throws Exception {


        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
        byte[] encryptedByteValue = new Base64().encode(encValue);
        System.out.println("Encrypted Value :: " + new String(encryptedByteValue));

        return new String(encryptedByteValue);
    }
    
    public static String decrypt(String encryptedValue, Key key) throws Exception {
        // Key key = generateKey();
         Cipher cipher = Cipher.getInstance(ALGORITHM);
         cipher.init(Cipher.DECRYPT_MODE, key);

         byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());

         byte[] enctVal = cipher.doFinal(decodedBytes);

         System.out.println("Decrypted Value :: " + new String(enctVal));
         return new String(enctVal);
     }
    
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }
    
 
    public UserLogin() throws Exception{
        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setBounds(423, 13, 273, 93);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        contentPane.add(lblNewLabel);

        lastnameField = new JTextField();
        lastnameField.setBounds(481, 260, 281, 68);
        lastnameField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(lastnameField);
        lastnameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(481, 356, 281, 68);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(passwordField);

        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(250, 158, 193, 52);
        lblFirstName.setBackground(Color.BLACK);
        lblFirstName.setForeground(Color.BLACK);
        lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 31));
        contentPane.add(lblFirstName);
        

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(250, 364, 193, 52);
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setBounds(546, 455, 162, 73);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	  String firstName = firstnameField.getText();
              	String lastName = lastnameField.getText();
                  String password = passwordField.getText();
                     Key key = null;
     				try {
     					key = generateKey();
     				} catch (Exception e1) {
     					// TODO Auto-generated catch block
     					e1.printStackTrace();
     				} 
                     String encriptValue = null;
     				try {
     					encriptValue = encrypt(password,key);
     				} catch (Exception e1) {
     					// TODO Auto-generated catch block
     					e1.printStackTrace();
     				}
                     try {
     					decrypt(encriptValue,key);
     				} catch (Exception e1) {
     					// TODO Auto-generated catch block
     					e1.printStackTrace();
     				}
                     
                     
                     try {
                         Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/college_stats", "root", "@rch02Vic06");
                         

                         PreparedStatement st = (PreparedStatement) connection
                             .prepareStatement("Select first_name, last_name, password, pass_application from account where first_name=? and last_name=? and password=? and pass_application=?");

                         st.setString(1, firstName);
                         st.setString(2, lastName);
                         st.setString(3, encriptValue);
                         st.setBoolean(4,true);
                         ResultSet x = st.executeQuery();
                         if (x.next()) {
                             dispose();
                             EssayPage li= new EssayPage();
                             li.setTitle("EssayPage");
                             li.setVisible(true);
                             JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                         } else {
                        	 try {
                                 Connection connection1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/college_stats", "root", "@rch02Vic06");
                                 

                                 PreparedStatement st1 = (PreparedStatement) connection1
                                     .prepareStatement("Select first_name, last_name, password from account where first_name=? and last_name=? and password=?");

                                 st1.setString(1, firstName);
                                 st1.setString(2, lastName);
                                 st1.setString(3, encriptValue);
                                 ResultSet rs = st1.executeQuery();
                                 
                                 if (rs.next()) {
                                     dispose();
                                     UserHome ah = new UserHome(firstName);
                                     ah.setTitle("Welcome");
                                     ah.setVisible(true);
                                     JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                                 } else {
                                     JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                                 }
                             } catch (SQLException sqlException) {
                                 sqlException.printStackTrace();
                             }
                         }                       
                     } catch (SQLException sqlException) {
                         sqlException.printStackTrace();
                     }
                     
               
            }
        });

        contentPane.add(btnNewButton);
        
        lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(250, 268, 193, 52);
        lblLastName.setForeground(Color.BLACK);
        lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblLastName.setBackground(Color.BLACK);
        contentPane.add(lblLastName);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
        
        firstnameField = new JTextField();
        firstnameField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        firstnameField.setColumns(10);
        firstnameField.setBounds(481, 158, 281, 68);
        contentPane.add(firstnameField);
        
        JButton btnsignUp = new JButton("Sign Up");
        btnsignUp.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnsignUp.setBounds(289, 455, 162, 73);
        contentPane.add(btnsignUp);
      
        
		
        btnsignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				   dispose();
			    	UserRegistration registration = null;
					try {
						registration = new UserRegistration();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	registration.setVisible(true);
			}
		});
        
     
    }
}