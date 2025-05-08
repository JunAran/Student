package student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class AdminEditWindow extends JFrame {
    private AdminEditWindow adminEditWindow;
    private AdminWindow adminWindow;
    private Map<String,Object> allComs = new HashMap<>();
    private static JLabel keyLabel;
    private static JTextField keyTextField;
    public AdminEditWindow(AdminWindow adminWindowTemp,Map<String,Object> map){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.adminEditWindow = this;
        this.adminWindow = adminWindowTemp;
        this.setVisible(true);
        this.setTitle("����");
        this.setResizable(false);
        JPanel panel = new JPanel();
        panel.setBounds(0,0,getWidth(),getHeight());
        panel.setLayout(null);
        this.add(panel);
        allComs.put("panel",panel);
        JLabel adminid_label = new JLabel("����ԱID:");
        adminid_label.setBounds(20,10,80,20);
        panel.add(adminid_label);
        JTextField adminid_textField = new JTextField();
        adminid_textField.setBounds(110,10,120,20);
        panel.add(adminid_textField);
        allComs.put("adminid_textField",adminid_textField);

        adminid_label.setVisible(false);
        adminid_textField.setVisible(false);
        keyLabel = adminid_label;
        keyTextField = adminid_textField;

        JLabel adminname_label = new JLabel("����Ա����:");
        adminname_label.setBounds(20,40,80,20);
        panel.add(adminname_label);
        JTextField adminname_textField = new JTextField();
        adminname_textField.setBounds(110,40,120,20);
        panel.add(adminname_textField);
        allComs.put("adminname_textField",adminname_textField);


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
            if (!Utils.isNull(map.get("adminid"))){
                JTextField adminid_map_textField = (JTextField)allComs.get("adminid_textField");
                if (adminid_map_textField != null){
                    adminid_map_textField.setText(map.get("adminid").toString());
                }
            }
            if (!Utils.isNull(map.get("adminname"))){
                JTextField adminname_map_textField = (JTextField)allComs.get("adminname_textField");
                if (adminname_map_textField != null){
                    adminname_map_textField.setText(map.get("adminname").toString());
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
                adminEditWindow.dispose();
                adminWindow.showWindow();
            }
        });
        JButton submit_button = new JButton("ȷ��");
        submit_button.setBounds(150,160,70,20);
        submit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminid_value = ((JTextField)allComs.get("adminid_textField")).getText();
                String adminname_value = getText("adminname_textField",null);
                if ("".equals(adminname_value)){
                    JOptionPane.showMessageDialog(panel,"���������Ա����!");
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
                    StringBuilder sql = new StringBuilder("insert into admin(adminname,username,password) values('"+adminname_value+"','"+username_value+"','"+password_value+"')");
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
                    StringBuilder sql = new StringBuilder("update admin set adminname =  '"+adminname_value+"', username =  '"+username_value+"', password =  '"+password_value+"' where adminid =  '"+adminid_value+"'");
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
                adminEditWindow.dispose();
                adminWindow.showWindow();
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
                return adminWindow.getPropValue(prop,itemName);
            }
        }
        return "";
   }
}
