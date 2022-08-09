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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UserHome extends JFrame {

	private JPanel frame;
	private JTextField textFieldSAT;
	private JTextField textFieldGPA;
	private JTextField textFieldMajor;
	private JTextField textFieldNumAwards;
	private JTextField textFieldNumAPClasses;
	private JTextField textNum5;
	private JTextField textFieldNum4;
	private JTextField textFieldNumLeadershipPos;
	private JTextField textFieldNumExtra;
	private JTextField textFieldNumInterns;
	private JTextField textFieldNum3Scored;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserHome window = new UserHome();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public UserHome() {

    }

    /**
     * Create the frame.
     */
    public UserHome(String userSes) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JButton btnNewButton = new JButton("Logout");
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
       
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure?");
                //JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    UserLogin obj = null;
					try {
						obj = new UserLogin();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    obj.setTitle("Student-Login");
                    obj.setVisible(true);
                }
                dispose();
                UserLogin obj = null;
				try {
					obj = new UserLogin();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                obj.setTitle("Student-Login");
                obj.setVisible(true);

            }
        });
        btnNewButton.setBounds(714, 23, 89, 23);
        contentPane.add(btnNewButton);
        JButton button = new JButton("Change-password\r\n");
        button.setBackground(UIManager.getColor("Button.disabledForeground"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePassword bo = null;
				try {
					bo = new ChangePassword(userSes);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                bo.setTitle("Change Password");
                bo.setVisible(true);

            }
        });
      
        button.setBounds(822, 23, 160, 23);
        contentPane.add(button);
        
        JLabel lblNewLabel = new JLabel("Statistics Page");
        lblNewLabel.setBounds(456, 11, 105, 26);
        contentPane.add(lblNewLabel);
 		

		JLabel lblNewLabel_1 = new JLabel("SAT score");
		lblNewLabel_1.setBounds(240, 78, 70, 14);
        contentPane.add(lblNewLabel_1);

 		
        JLabel lblNewLabel_2 = new JLabel("GPA");
      		lblNewLabel_2.setBounds(517, 78, 46, 14);
      		contentPane.add(lblNewLabel_2);

 		textFieldSAT = new JTextField();
 		textFieldSAT.setBounds(357, 75, 86, 20);
 		contentPane.add(textFieldSAT);
 		textFieldSAT.setColumns(10);
 		
 		textFieldGPA = new JTextField();
 		textFieldGPA.setBounds(557, 75, 86, 20);
 		contentPane.add(textFieldGPA);
 		textFieldGPA.setColumns(10);
 		
 		textFieldMajor = new JTextField();
 		textFieldMajor.setColumns(10);
 		textFieldMajor.setBounds(112, 75, 86, 20);
 		contentPane.add(textFieldMajor);
 		
 		JLabel lblMajor = new JLabel("Major");
 		lblMajor.setBounds(62, 78, 56, 14);
 		contentPane.add(lblMajor);
 		
 		textFieldNumAwards = new JTextField();
 		textFieldNumAwards.setColumns(10);
 		textFieldNumAwards.setBounds(810, 75, 86, 20);
 		contentPane.add(textFieldNumAwards);
 		
 		JLabel lblNewAwards = new JLabel("Number of Awards");
 		lblNewAwards.setBounds(697, 78, 98, 14);
 		lblNewAwards.setFont(new Font("Tahoma", Font.BOLD, 10));
 		contentPane.add(lblNewAwards);
 		
 		JLabel lblNumAPClasses = new JLabel("Num of AP Classes");
 		lblNumAPClasses.setFont(new Font("Tahoma", Font.BOLD, 9));
 		lblNumAPClasses.setBounds(0, 134, 99, 17);
 		contentPane.add(lblNumAPClasses);
 		
 		textFieldNumAPClasses = new JTextField();
 		textFieldNumAPClasses.setColumns(10);
 		textFieldNumAPClasses.setBounds(112, 132, 86, 20);
 		contentPane.add(textFieldNumAPClasses);
 		
 		JLabel lblNum5 = new JLabel("Number of 5 Scored");
 		lblNum5.setBounds(241, 135, 126, 14);
 		contentPane.add(lblNum5);
 		
 		textNum5 = new JTextField();
 		textNum5.setColumns(10);
 		textNum5.setBounds(357, 132, 86, 20);
 		contentPane.add(textNum5);
 		
 		JLabel lblNum4 = new JLabel("Number of 4 Scored");
 		lblNum4.setBounds(443, 135, 126, 14);
 		contentPane.add(lblNum4);
 		
 		textFieldNum4 = new JTextField();
 		textFieldNum4.setColumns(10);
 		textFieldNum4.setBounds(557, 132, 86, 20);
 		contentPane.add(textFieldNum4);
 		
 		JLabel lblNum3 = new JLabel("Number of 3 Scored");
 		lblNum3.setBounds(697, 135, 126, 14);
 		contentPane.add(lblNum3);
 		
 		JLabel lblLeadershipPos = new JLabel("Number Leadership Positions");
 		lblLeadershipPos.setFont(new Font("Tahoma", Font.BOLD, 8));
 		lblLeadershipPos.setBounds(200, 187, 137, 14);
 		contentPane.add(lblLeadershipPos);
 		
 		textFieldNumLeadershipPos = new JTextField();
 		textFieldNumLeadershipPos.setColumns(10);
 		textFieldNumLeadershipPos.setBounds(357, 184, 86, 20);
 		contentPane.add(textFieldNumLeadershipPos);
 		
 		textFieldNumExtra = new JTextField();
 		textFieldNumExtra.setColumns(10);
 		textFieldNumExtra.setBounds(112, 184, 86, 20);
 		contentPane.add(textFieldNumExtra);
 		
 		JLabel lblNumExtra = new JLabel("Number Of Extracurriculars");
 		lblNumExtra.setFont(new Font("Tahoma", Font.BOLD, 8));
 		lblNumExtra.setBounds(0, 187, 118, 14);
 		contentPane.add(lblNumExtra);
 		
 		JLabel lblNumInterns = new JLabel("Number of Interships");
 		lblNumInterns.setBounds(450, 187, 126, 14);
 		lblNumInterns.setFont(new Font("Tahoma", Font.BOLD, 10));
 		contentPane.add(lblNumInterns);
 		
 		textFieldNumInterns = new JTextField();
 		textFieldNumInterns.setColumns(10);
 		textFieldNumInterns.setBounds(557, 184, 86, 20);
 		contentPane.add(textFieldNumInterns);
 	
 		JButton submutButton = new JButton("submit");
 		submutButton.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				String major;
 				int sat;
 				double gpa;
 				int numAwards;
 				int numApClass;
 				int numFiveScored;
 				int numFourScored;
 				int numThreeScored;
 				int numCurriculars;
 				int numLeadership;
 				int numInternships;
 				int score;
 				boolean pass;
 				major = textFieldMajor.getText();
 				sat = Integer.parseInt(textFieldSAT.getText());
 				gpa = Double.parseDouble(textFieldGPA.getText());
 				numAwards = Integer.parseInt(textFieldNumAwards.getText());
 				numApClass = Integer.parseInt(textFieldNumAPClasses.getText());
 				numFiveScored = Integer.parseInt(textNum5.getText());
 				numFourScored = Integer.parseInt(textFieldNum4.getText());
 				numThreeScored = Integer.parseInt(textFieldNum3Scored.getText());
 				numCurriculars = Integer.parseInt(textFieldNumExtra.getText());
 				numLeadership = Integer.parseInt(textFieldNumLeadershipPos.getText());
 				numInternships = Integer.parseInt(textFieldNumInterns.getText());
 				score = (numFiveScored * 5) + (numFourScored * 4) + (numThreeScored * 3);
 				
 				if (gpa > 3 && sat > 1200 && numAwards > 2 && numApClass > 5 && numCurriculars > 1 && numLeadership > 0 && numInternships > 0 && score > 13)
 					{
 						pass = true;
 					}
 				  else 
 				  {
 					  pass = false;
 				  }
              
 				 
 				  try {
 				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/college_stats", "root", "@rch02Vic06");

                  PreparedStatement st = (PreparedStatement) con
                      .prepareStatement("Update account set major=?, sat_score=?, gpa=?, number_of_awards=?, num_ap_classes=?, num_5_scored=?, num_4_scored=?, num_3_scored=?, num_curriculars=?, num_leadership=?, num_internships=?, pass_application=? where first_name=?");

                  st.setString(1,major);
                  st.setInt(2,sat);
                  st.setDouble(3,gpa);
                  st.setInt(4,numAwards);
                  st.setInt(5,numApClass);
                  st.setInt(6,numFiveScored);
                  st.setInt(7,numFourScored);
                  st.setInt(8,numThreeScored);
                  st.setInt(9,numCurriculars);
                  st.setInt(10,numLeadership);
                  st.setInt(11,numInternships);
                  st.setBoolean(12, pass);
                  st.setString(13, userSes);
                  st.executeUpdate();
 				
 				  } catch (SQLException sqlException) {
 	                    sqlException.printStackTrace();
 	              }
 				 
 				    try {
 	                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/college_stats", "root", "@rch02Vic06");
 	                    

 	                    PreparedStatement st = (PreparedStatement) connection
 	                        .prepareStatement("Select first_name, pass_application from account where first_name=? and pass_application=?");

 	                    st.setString(1, userSes);
 	                    st.setBoolean(2, true);
 	                    ResultSet rs = st.executeQuery();
 	                    if (rs.next()) {
 	                        dispose();
 	                        EssayPage ah = new EssayPage();
 	                        ah.setTitle("EssayPage");
 	                        ah.setVisible(true);
 	                        JOptionPane.showMessageDialog(btnNewButton, "You have passed the application");
 	                    } else {
 	                        JOptionPane.showMessageDialog(btnNewButton, "You have not passed the application");
 	                    }
 	                } catch (SQLException sqlException) {
 	                    sqlException.printStackTrace();
 	                }
 				 
 			}
 		});
 		submutButton.setBounds(57, 497, 89, 23);
 		contentPane.add(submutButton);
 		
 		textFieldNum3Scored = new JTextField();
 		textFieldNum3Scored.setColumns(10);
 		textFieldNum3Scored.setBounds(810, 132, 86, 20);
 		contentPane.add(textFieldNum3Scored);
    }
}