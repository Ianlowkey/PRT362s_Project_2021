package za.ac.cput.PRT362s_Project_2021;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CustomerRewardsGui  extends JFrame implements ActionListener {

    JFrame frame;

    private JPanel northPanel = new JPanel();
    private JPanel centerPanel= new JPanel();
    private JPanel southPanel = new JPanel();

    private JTextField txtSearch = new JTextField();

    private JLabel nTxt = new JLabel();
    private JLabel inst = new JLabel();


    private JButton btnSearch = new JButton();
    private JButton toMenu = new JButton();
    private JButton viewRewards = new JButton();
    private JButton playGame = new JButton();
    private JButton btnBack = new JButton();



    private JTextArea showSearch = new JTextArea();
    private Font ft,ft2, ft3;


    public CustomerRewardsGui() {

        super("Rewards System");

        northPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new GridLayout(1,3));
        centerPanel.setLayout(null);

        add(northPanel,BorderLayout.NORTH);
        add(southPanel,BorderLayout.SOUTH);
        add(centerPanel,BorderLayout.CENTER);

        playGame = new JButton("Play a Game, Get a Reward");
        viewRewards= new JButton("View Rewards");
        //toMenu = new JButton("Menu");
        btnBack = new JButton("Back");


        nTxt = new JLabel("WELCOME TO CUSTOMER REWARDS");
        ft = new Font("Arial", Font.BOLD, 35);
        Color LightBlue= new Color(46, 144, 232);

        northPanel.setBackground(Color.lightGray);
        northPanel.add(nTxt);


        inst = new JLabel("Search your name to see your points");
        btnSearch = new JButton("Search");
        centerPanel.add(inst);
        centerPanel.add(txtSearch);
        centerPanel.add(btnSearch);
        centerPanel.add(showSearch);


        ft3 = new Font("Arial", Font.PLAIN, 15);
        inst.setFont(ft3);
        btnSearch.setFont(ft2);
        inst.setBounds(310,10,400,70);
        txtSearch.setBounds(322,75,200,23);
        btnSearch.setBounds(322,120,200,23);
        showSearch.setBounds(140,170,600,250);
        showSearch.setEditable(false);

        nTxt.setForeground(Color.YELLOW);
        nTxt.setFont(ft);

        southPanel.add(viewRewards);
        southPanel.add(playGame);
        //southPanel.add(toMenu);
        southPanel.add(btnBack);
        ft2 = new Font("Arial", Font.PLAIN, 20);
        viewRewards.setFont(ft2);
        playGame.setFont(ft2);
        //toMenu.setFont(ft2);
        btnBack.setFont(ft2);

        viewRewards.addActionListener(this);
        playGame.addActionListener(this);
        toMenu.addActionListener(this);











    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

