package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMain extends JFrame {
    private StudentMain studentMain;

    private Integer userId;

    public StudentMain(Integer id) {
        userId = id;
        studentMain = this;
        setSize(400, 400); // ����ͼ�ο��С
        setResizable(false);
        setTitle("ѧ����ҳ");

        setLocationRelativeTo(null);
        // ���ùرղ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ���ò��ֹ�����
        setLayout(new GridBagLayout());

        // ������¼��ť
        JButton xkButton = new JButton("ѡ��");
        Color backgroundColor = Color.BLUE; // ����Ϊ��ɫ
        xkButton.setBackground(backgroundColor);

        // ���ð�ť���ı���ɫ
        Color foregroundColor = Color.red; // ����Ϊ��ɫ
        xkButton.setForeground(foregroundColor);
        xkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentMain.setVisible(false);
                new CourseWindow(userId, studentMain);
            }
        });

        JButton cjButton = new JButton("�ɼ�");
        cjButton.setBackground(backgroundColor);
        cjButton.setForeground(foregroundColor);
        cjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentMain.dispose();
                new EnrollmentWindow(userId, studentMain);
            }
        });

// ��ӷ��ذ�ť
        JButton backButton = new JButton("������һҳ");
        // ���ð�ť�ı���ɫ

        backButton.setBackground(backgroundColor);

        // ���ð�ť���ı���ɫ

        backButton.setForeground(foregroundColor);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // �رյ�ǰ����
                dispose();
                // ��LoginWindow����
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
            }
        });

        // ʹ��GridBagLayout���ֹ�����������
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(xkButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(cjButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;

// ��ӷ��ذ�ť
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        add(backButton, gbc);

        // �����ı������С
        Font buttonFont = xkButton.getFont().deriveFont(20f);
        xkButton.setFont(buttonFont);
        cjButton.setFont(buttonFont);


        this.setVisible(true);
    }
}
