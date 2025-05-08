package student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
public class TeacherEditWindow extends JFrame {
    private TeacherEditWindow teacherEditWindow;
    private TeacherWindow teacherWindow;
    private Map<String,Object> allComs = new HashMap<>();
    private static JLabel keyLabel;
    private static JTextField keyTextField;
    public TeacherEditWindow(TeacherWindow teacherWindowTemp,Map<String,Object> map){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.teacherEditWindow = this;
        this.teacherWindow = teacherWindowTemp;
        this.setVisible(true);
        this.setTitle("����");
        this.setResizable(false);
        JPanel panel = new JPanel();
        panel.setBounds(0,0,getWidth(),getHeight());
        panel.setLayout(null);
        this.add(panel);
        allComs.put("panel",panel);
        JLabel teacherid_label = new JLabel("��ʦID:");
        teacherid_label.setBounds(20,10,80,20);
        panel.add(teacherid_label);
        JTextField teacherid_textField = new JTextField();
        teacherid_textField.setBounds(110,10,120,20);
        panel.add(teacherid_textField);
        allComs.put("teacherid_textField",teacherid_textField);

        teacherid_label.setVisible(false);
        teacherid_textField.setVisible(false);
        keyLabel = teacherid_label;
        keyTextField = teacherid_textField;

        JLabel teachername_label = new JLabel("��ʦ����:");
        teachername_label.setBounds(20,40,80,20);
        panel.add(teachername_label);
        JTextField teachername_textField = new JTextField();
        teachername_textField.setBounds(110,40,120,20);
        panel.add(teachername_textField);
        allComs.put("teachername_textField",teachername_textField);


        JLabel username_label = new JLabel("�û���:");
        username_label.setBounds(20,70,80,20);
        panel.add(username_label);
        JTextField username_textField = new JTextField();
        username_textField.setBounds(110,70,120,20);
        panel.add(username_textField);
        allComs.put("username_textField",username_textField);


        JLabel password_label = new JLabel("����:");
        password_label.setBounds(20,100,80,20);
        panel.add(password_label);
        JTextField password_textField = new JTextField();
        password_textField.setBounds(110,100,120,20);
        panel.add(password_textField);
        allComs.put("password_textField",password_textField);


        JButton cancer_button = new JButton("ȡ��");
        cancer_button.setBounds(60,160,70,20);
        panel.add(cancer_button);
        if (map != null){
            if (!Utils.isNull(map.get("teacherid"))){
                JTextField teacherid_map_textField = (JTextField)allComs.get("teacherid_textField");
                if (teacherid_map_textField != null){
                    teacherid_map_textField.setText(map.get("teacherid").toString());
                }
            }
            if (!Utils.isNull(map.get("teachername"))){
                JTextField teachername_map_textField = (JTextField)allComs.get("teachername_textField");
                if (teachername_map_textField != null){
                    teachername_map_textField.setText(map.get("teachername").toString());
                }
            }
            if (!Utils.isNull(map.get("username"))){
                JTextField username_map_textField = (JTextField)allComs.get("username_textField");
                if (username_map_textField != null){
                    username_map_textField.setText(map.get("username").toString());
                }
            }
            if (!Utils.isNull(map.get("password"))){
                JTextField password_map_textField = (JTextField)allComs.get("password_textField");
                if (password_map_textField != null){
                    password_map_textField.setText(map.get("password").toString());
                }
            }
            map.forEach((key,value)->{
                JTextField textField = (JTextField)allComs.get(key + "_textField");
                if (textField != null){
                    textField.setText(value+"");
                }
            });
            keyLabel.setVisible(true);
            keyTextField.setVisible(true);
            keyTextField.setEditable(false);
            this.setTitle("�޸�");;
        }
        cancer_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherEditWindow.dispose();
                teacherWindow.showWindow();
            }
        });
        JButton submit_button = new JButton("ȷ��");
        submit_button.setBounds(150,160,70,20);
        submit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teacherid_value = ((JTextField)allComs.get("teacherid_textField")).getText();
                String teachername_value = getText("teachername_textField",null);
                if ("".equals(teachername_value)){
                    JOptionPane.showMessageDialog(panel,"�������ʦ����!");
                    return;
                }
                String username_value = getText("username_textField",null);
                if ("".equals(username_value)){
                    JOptionPane.showMessageDialog(panel,"�������û���!");
                    return;
                }
                String password_value = getText("password_textField",null);
                if ("".equals(password_value)){
                    JOptionPane.showMessageDialog(panel,"����������!");
                    return;
                }
                if (map == null){
                    StringBuilder sql = new StringBuilder("insert into teacher(teachername,username,password) values('"+teachername_value+"','"+username_value+"','"+password_value+"')");
                    Statement statement = Utils.getStatement();
                    try{
                        int row = statement.executeUpdate(sql.toString());
                        if(row > 0){
                            JOptionPane.showMessageDialog(panel,"��ӳɹ�");
                        }else{
                            JOptionPane.showMessageDialog(panel,"���ʧ��");
                        }
                    }catch (Exception ec){
                        ec.printStackTrace();
                    }
                }else {
                    StringBuilder sql = new StringBuilder("update teacher set teachername =  '"+teachername_value+"', username =  '"+username_value+"', password =  '"+password_value+"' where teacherid =  '"+teacherid_value+"'");
                    Statement statement = Utils.getStatement();
                    try{
                        int row = statement.executeUpdate(sql.toString());
                        if(row > 0){
                            JOptionPane.showMessageDialog(panel,"�޸ĳɹ�");
                         }else{
                             JOptionPane.showMessageDialog(panel,"�޸�ʧ��");
                         }
                     }catch (Exception ec){
                         ec.printStackTrace();
                     }

                }
                teacherEditWindow.dispose();
                teacherWindow.showWindow();
            }
        });
        panel.add(submit_button);
        this.setSize(300,250);
        Utils.setCenterLocation(this);
    }
    private String getText(String name,String prop){
        if (allComs.containsKey(name)){
            Object o = allComs.get(name);
            if (o instanceof JTextField){
                return ((JTextField) o).getText();
            }else if(o instanceof JComboBox){
                String itemName =  ((JComboBox<?>) o).getSelectedItem().toString();
                return teacherWindow.getPropValue(prop,itemName);
            }
        }
        return "";
   }
}
