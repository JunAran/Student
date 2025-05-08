package student;

import javax.swing.*;
import java.awt.*;

public class ArtworkPanel extends JPanel {
    private String title;

    public ArtworkPanel(String title) {
        this.title = title;
        setOpaque(false); // ������屳��͸�����Ա�ֻ��ʾ������
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // ���������ֵ�������ʽ
        Font font = new Font("Serif", Font.BOLD, 30); // ���Ը�����Ҫ��������ʹ�С
        g2d.setFont(font);

        // ���������ֵ���Ⱦ��ʾ
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // ���������ֵĿ�Ⱥ͸߶�
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(title);
        int height = fm.getHeight();

        // ���������ֵ�λ��
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2 + fm.getAscent();

        // ����������
        g2d.setColor(Color.BLACK);
        g2d.drawString(title, x, y);
    }
}
