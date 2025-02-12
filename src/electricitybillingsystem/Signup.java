package electricitybillingsystem;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    JButton create,back;
    Choice accountType;
    JTextField meter,username,name,password;
    Signup()
    {
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel=new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(172,216,230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34,139,34));
        add(panel);

        JLabel heading=new JLabel("CreateAccount");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("cursive",Font.BOLD,14));
        panel.add(heading);

        accountType=new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260,50,150,20);
        panel.add(accountType);

        JLabel labelmeter=new JLabel("Meter number ");
        labelmeter.setBounds(100,90,140,20);
        labelmeter.setForeground(Color.GRAY);
        labelmeter.setFont(new Font("cursive",Font.BOLD,14));
        labelmeter.setVisible(false);
        panel.add(labelmeter);

        meter=new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        panel.add(meter);

        JLabel labelusername=new JLabel("User Name");
        labelusername.setBounds(100,130,140,20);
        labelusername.setForeground(Color.GRAY);
        labelusername.setFont(new Font("cursive",Font.BOLD,14));
        panel.add(labelusername);

        username=new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);

        JLabel labelname=new JLabel("Name");
        labelname.setBounds(100,170,140,20);
        labelname.setForeground(Color.GRAY);
        labelname.setFont(new Font("cursive",Font.BOLD,14));
        panel.add(labelname);

        name=new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);


        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {

            }

            @Override
            public void focusLost(FocusEvent fe) {
                try
                {
                    Connect c=new Connect();
                    ResultSet rs=c.s.executeQuery("select * from login where meter_no='"+meter.getText()+"'");
                    while(rs.next())
                    {
                         name.setText(rs.getString("name"));
                    }
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


        JLabel labelpassword=new JLabel("Password");
        labelpassword.setBounds(100,210,140,20);
        labelpassword.setForeground(Color.GRAY);
        labelpassword.setFont(new Font("cursive",Font.BOLD,14));
        panel.add(labelpassword);

        password=new JTextField();
        password.setBounds(260,210,150,20);
        panel.add(password);

        accountType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                String user=accountType.getSelectedItem();
                if(user.equals("Customer"))
                {
                    labelmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                }
                else {
                    labelmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });



        create=new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140,260,120,25);
        create.addActionListener(this);
        panel.add(create);

        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300,260,120,25);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/image3.jpg"));
        Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(415,30,250,250);
        panel.add(image);

        setVisible(true);

    }

       public void actionPerformed(ActionEvent ae)
       {
            if(ae.getSource()==create)
            {
                  String accttype=accountType.getSelectedItem();
                  String susername=username.getText();
                  String sname=name.getText();
                  String spassword=password.getText();
                  String smeter=meter.getText();

                  try
                  {
                      Connect c=new Connect();
                      String query=null;
                      if(accttype.equals("Admin"))
                      {
                          query="insert into login values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+accttype+"')";
                      }
                      else {
                          query="update login set username='"+susername+"',password='"+spassword+"',user='"+accttype+"' where meter_no='"+smeter+"'";
                      }
                      c.s.executeUpdate(query);
                      JOptionPane.showMessageDialog(null,"Account Created Successfully");
                      setVisible(false);
                      new Login();
                  }catch(Exception e)
                  {
                      e.printStackTrace();
                  }
            }
            else if(ae.getSource()==back)
            {
                setVisible(false);

                new Login();
            }
       }

    public static void main(String[]args)
    {
        new Signup();
    }

}
