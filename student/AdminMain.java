package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class    AdminMain extends JFrame {
    private AdminMain adminMain;

    private Integer userId;

    public AdminMain(Integer id){
        userId = id;
        adminMain = this;
        setSize(300,300);
        setResizable(false);
        setTitle("����Ա��ҳ");

        setLocationRelativeTo(null);
        // ���ùرղ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ���ò��ֹ�����
        setLayout(new GridBagLayout());
        // ������¼��ť
        JButton xsglButton = new JButton("ѧ������");
        xsglButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminMain.setVisible(false);
                new StudentWindow(adminMain);
            }
        });



        JButton jsglButton = new JButton("��ʦ����");

        jsglButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminMain.setVisible(false);
                new TeacherWindow(adminMain);
            }
        });

        JButton kcglButton = new JButton("�γ̹���");

        kcglButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminMain.setVisible(false);
                new CourseWindow(userId,3,adminMain);
            }
        });

        JButton cjglButton = new JButton("�ɼ�����");

        cjglButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminMain.setVisible(false);
                new EnrollmentWindow(userId,3,adminMain);
            }
        });




        // ʹ��GridBagLayout���ֹ�����������
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(xsglButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(jsglButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(kcglButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        add(cjglButton, gbc);

        this.setVisible(true);
    }
}
