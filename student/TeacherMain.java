package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMain extends JFrame {
    private TeacherMain teacherMain;

    private Integer userId;


    public TeacherMain(Integer id){
        userId = id;
        teacherMain = this;
        setSize(300,300);
        setResizable(false);
        setTitle("��ʦ��ҳ");

        setLocationRelativeTo(null);
        // ���ùرղ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ���ò��ֹ�����
        setLayout(new GridBagLayout());
        // ������¼��ť
        JButton xkButton = new JButton("�γ̹���");
        xkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherMain.setVisible(false);
                new CourseWindow(userId,2,teacherMain);
            }
        });



        JButton cjButton = new JButton("�ɼ�����");

        cjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherMain.setVisible(false);
                new EnrollmentWindow(userId,2,teacherMain);
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

        this.setVisible(true);
    }
}
