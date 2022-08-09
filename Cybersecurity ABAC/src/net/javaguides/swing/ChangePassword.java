package net.javaguides.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class ChangePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblEnterNewPassword;
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "1234567891234567".getBytes();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
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
    
    /**
     * Create the frame.
     */
    public ChangePassword(String name)  throws Exception{
        setBounds(450, 360, 1024, 234);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 34));
        textField.setBounds(373, 35, 609, 67);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnSearch = new JButton("Enter");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pstr = textField.getText();
                Key key = null;
 				try {
 					key = generateKey();
 				} catch (Exception e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				} 
                 String encriptValue = null;
 				try {
 					encriptValue = encrypt(pstr,key);
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
                    System.out.println("update password name " + name);
                    System.out.println("update password");

                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/college_stats", "root", "@rch02Vic06");

                    PreparedStatement st = (PreparedStatement) con
                        .prepareStatement("Update account set password=? where first_name=?");

                    st.setString(1, encriptValue);
                    st.setString(2, name);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(btnSearch, "Password has been successfully changed");

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 29));
        btnSearch.setBackground(new Color(240, 240, 240));
        btnSearch.setBounds(438, 127, 170, 59);
        contentPane.add(btnSearch);

        lblEnterNewPassword = new JLabel("Enter New Password :");
        lblEnterNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblEnterNewPassword.setBounds(45, 37, 326, 67);
        contentPane.add(lblEnterNewPassword);
    }
}