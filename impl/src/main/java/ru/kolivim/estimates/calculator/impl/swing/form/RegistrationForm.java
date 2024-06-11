package ru.kolivim.estimates.calculator.impl.swing.form;

//import javafx.event.ActionEvent;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Slf4j
public class RegistrationForm extends JFrame {

    static JFrame f;    // JFrame
    static JButton b, buttonComposition, b1, b2;    // JButton
    static JLabel labelUser, labelPassword, labelGroupUser, labelRoleUser, labelServer; // Label to display text
    JTextField textUser, textGroupUser, textRoleUser, textServer;
    JPasswordField passwordUser;
    JTextArea jTextArea;

    public RegistrationForm(){}

    public void create()
    {
        log.info("RegistrationForm:create() startMethod");

        f = new JFrame("Регистрация");
//        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        b = new JButton("кнопка b");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Push button 'кнопка b' in RegistrationForm class");

                System.out.println("Push button 'кнопка b' endMethod");
            }
        });
        /*
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Push button 'кнопка b' in RegistrationForm class");

                System.out.println("Push button 'кнопка b' endMethod");
//        		pushButtonInfo("���� ����������");
            }
        });
        */


        buttonComposition = new JButton("buttonComposition");
        buttonComposition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Push button 'кнопка buttonComposition' in RegistrationForm class");

                String user = textUser.getText();
                String password = passwordUser.getText();
                String groupUser = textGroupUser.getText();
                String roleUser = textRoleUser.getText();
                String server = textServer.getText();

                System.out.println("Push button 'кнопка buttonComposition' endMethod");
            }
        });
        /*
        buttonComposition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Push button '�������� ������' in Solution class");
                String user = textUser.getText();
                String password = passwordUser.getText();
                String groupUser = textGroupUser.getText();
                String roleUser = textRoleUser.getText();
                String server = textServer.getText();
                System.out.println("Push button '�������� ������' in Solution class create() method: ��������:\n"
                        + "user : " + user
                        + "\npassword : " + password
                        + "\ngroupUser : " + groupUser
                        + "\nroleUser : " + roleUser
                        + "\nserver : " + server
                );
            }
        });
        */

        JPanel one1 = new JPanel(/*new GridLayout(2, 1)*/);
        JPanel one2 = new JPanel(/*new GridLayout(2, 1)*/);
        JPanel one3 = new JPanel(/*new GridLayout(2, 1)*/);
        JPanel one4 = new JPanel(/*new GridLayout(2, 1)*/);
        JPanel one5 = new JPanel(/*new GridLayout(2, 1)*/);
        JPanel one6 = new JPanel(/*new GridLayout(2, 1)*/);

        labelUser = new JLabel("User ID");
        textUser = new JTextField();
        labelUser.setPreferredSize(new Dimension(70, 30));	//.setSize(800, 200);
//        textUser.setSize(100, 20);
        textUser.setColumns(25);

        labelPassword = new JLabel("Password");
        labelPassword.setPreferredSize(new Dimension(70, 30)); // .setSize(800, 200);
        passwordUser = new JPasswordField();
//        passwordUser.setSize(100, 20);
        passwordUser.setColumns(25);

        labelGroupUser = new JLabel("Group");
        labelGroupUser.setPreferredSize(new Dimension(70, 30)); // .setSize(800, 200);
        textGroupUser = new JTextField();
//        textGroupUser.setSize(100, 20);
        textGroupUser.setColumns(25);

        labelRoleUser = new JLabel("Role");
        labelRoleUser.setPreferredSize(new Dimension(70, 30)); // .setSize(800, 200);
        textRoleUser = new JTextField();
        textRoleUser.setText("dba");
//        textRoleUser.setSize(100, 20);
        textRoleUser.setColumns(25);

        labelServer = new JLabel("Server");
        labelServer.setPreferredSize(new Dimension(70, 30)); // .setSize(800, 200);
        textServer = new JTextField();
//        textServer.setSize(100, 20);
        textServer.setColumns(25);


        /** Creating a panel to add buttons and add to it */
        JPanel p = new JPanel(/*new GridLayout(1, 2)*/ /*(LayoutManager) new FlowLayout(FlowLayout.LEFT)*/);
        p.setSize(800, 800);

        //
        one1.add(labelUser);
        one1.add(textUser);
        one1.setSize(200, 50);

        one2.add(labelPassword);
        one2.add(passwordUser );
        one2.setSize(200, 50);

        one3.add(labelGroupUser);
        one3.add(textGroupUser);
        one3.setSize(200, 50);

        one4.add(labelRoleUser);
        one4.add(textRoleUser);
        one4.setSize(200, 50);

        one5.add(labelServer);
        one5.add(textServer);
        one5.setSize(200, 50);

        one6.add(b);
        one6.add(buttonComposition);
        one6.setSize(200, 50);

        p.add(one1/*, BorderLayout.CENTER*/);
        p.add(one2/*, BorderLayout.CENTER*/);
        p.add(one3/*, BorderLayout.CENTER*/);
        p.add(one4/*, BorderLayout.CENTER*/);
        p.add(one5/*, BorderLayout.CENTER*/);
        p.add(one6/*, BorderLayout.CENTER*/);

        Box box = Box.createVerticalBox();
        box.add(one1);
//        box.add(Box.createVerticalStrut(10));
        box.add(one2);
//        box.add(Box.createVerticalGlue());
        box.add(one3);
//        box.add(Box.createVerticalStrut(10));
        box.add(one4);
        box.add(one5);
        box.add(one6);
        setContentPane(box);
        setSize(450, 250);


        /** Adding panel to frame */
        /*
        f.add(one1);
        f.add(one2);
        f.add(one3);
        f.add(one4);
        f.add(one5);
        f.add(one6);
        */

        f.add(box);
//        f.add(p);
//        f.pack();
        f.setSize(500, 300);
        f.show();


        log.info("RegistrationForm:create() endMethod");
    }


}
