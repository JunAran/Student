package student;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LoginWindow extends JFrame {
    private LoginWindow loginGUI;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private String type;
    private JButton exitButton;
    private Clip clip;

    public LoginWindow() {
        // ���ű�������
        playBackgroundMusic("src/com/student/sample-15s.wav"); // �滻Ϊ��������ļ�·��

        // �����Զ��屳�����
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/com/student/1.jpg", 0.5f); // �滻Ϊ���ͼƬ·��������͸����
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel); // �������������Ϊ�������

        loginGUI = this;
        setTitle("ѧ������ϵͳ");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �����û�����ǩ�������
        JLabel usernameLabel = new JLabel("�˺�:");
        usernameLabel.setForeground(Color.WHITE); // ����������ɫ���ڱ���ͼ�Ͽɼ�
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(200, 30));

        // ���������ǩ�������
        JLabel passwordLabel = new JLabel("����:");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));

        // ������ѡ��ť��
        ButtonGroup group = new ButtonGroup();
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
                    type = selectedRadioButton.getText();
                }
            }
        };

        JRadioButton radioButton1 = new JRadioButton("ѧ����¼");
        JRadioButton radioButton2 = new JRadioButton("��ʦ��¼");
        JRadioButton radioButton3 = new JRadioButton("����Ա��¼");

        // ���õ�ѡ��ť��ʽ
        setButtonStyle(radioButton1);
        setButtonStyle(radioButton2);
        setButtonStyle(radioButton3);

        radioButton1.addItemListener(itemListener);
        radioButton2.addItemListener(itemListener);
        radioButton3.addItemListener(itemListener);
        radioButton1.setSelected(true);

        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        // �����˳���ť��������ʽ
        exitButton = new JButton("�˳�");
        exitButton.setPreferredSize(new Dimension(150, 40));
        setButtonStyle(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clip.stop(); // ֹͣ��������
                System.exit(0);
            }
        });

        // ������¼��ť��������ʽ
        loginButton = new JButton("��¼");
        loginButton.setPreferredSize(new Dimension(150, 40));
        setButtonStyle(loginButton);

        // ʹ�� GridBagLayout ���ֹ�����������
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // ����������������ϣ���ָ�� GridBagConstraints ����
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(radioButton1, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(radioButton2, gbc);

        gbc.gridx = 2;
        backgroundPanel.add(radioButton3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundPanel.add(loginButton, gbc);

        gbc.gridy = 4;
        backgroundPanel.add(exitButton, gbc);

        // ���õ�¼��ť���¼�
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String pass = new String(passwordField.getPassword());
                if ("".equals(username)) {
                    JOptionPane.showMessageDialog(loginGUI, "�������˺�", "��¼", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(pass)) {
                    JOptionPane.showMessageDialog(loginGUI, "����������", "��¼", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Map<String, Object> map = Utils.login(username, pass, type);
                if (map.get("status").equals(200)) {
                    Integer id = (Integer) map.get("data");
                    clip.stop(); // ��¼�ɹ���ֹͣ��������
                    if ("ѧ����¼".equals(type)) {
                        new StudentMain(id);
                    } else if ("��ʦ��¼".equals(type)) {
                        new TeacherMain(id);
                    } else if ("����Ա��¼".equals(type)) {
                        new AdminMain(id);
                    }
                    loginGUI.dispose();
                } else {
                    usernameField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(loginGUI, map.get("mess"), "��¼", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    // ���ű������ֵķ���
    private void playBackgroundMusic(String filepath) {
        try {
            File musicPath = new File(filepath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY); // ѭ������
                clip.start();
            } else {
                System.out.println("�ļ�δ�ҵ�");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // �Զ�������࣬���ڻ��ƴ�͸���ȵı���ͼƬ
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;
        private float opacity; // ͸����ֵ

        public BackgroundPanel(String imagePath, float opacity) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
                this.opacity = opacity; // ����͸����
            } catch (Exception e) {
                System.out.println("����ͼƬ����ʧ��");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity)); // ����͸����
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // ����ͼƬ����Ӧ����С
                g2d.dispose();
            }
        }
    }

    // ���ð�ť��ʽ�ķ���
    private void setButtonStyle(AbstractButton button) {
        button.setForeground(Color.WHITE); // ������ɫ
        button.setBackground(new Color(0, 0, 0, 150)); // ��͸������ɫ
        button.setOpaque(true);
        button.setBorder(new LineBorder(Color.WHITE)); // ��ɫ�߿�
        button.setFont(button.getFont().deriveFont(Font.BOLD, 18f)); // �Ӵ�����
    }
}
