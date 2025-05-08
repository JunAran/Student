package student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
public class StudentEditWindow extends JFrame {
    private StudentEditWindow studentEditWindow;
    private StudentWindow studentWindow;
    private Map<String,Object> allComs = new HashMap<>();
    private static JLabel keyLabel;
    private static JTextField keyTextField;
    public StudentEditWindow(StudentWindow studentWindowTemp,Map<String,Object> map){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.studentEditWindow = this;
        this.studentWindow = studentWindowTemp;
        this.setVisible(true);
        this.setTitle("����");
        this.setResizable(false);
        JPanel panel = new JPanel();
        panel.setBounds(0,0,getWidth(),getHeight());
        panel.setLayout(null);
        this.add(panel);
        allComs.put("panel",panel);
        JLabel studentid_label = new JLabel("ѧ��ID:");
        studentid_label.setBounds(20,10,80,20);
        panel.add(studentid_label);
        JTextField studentid_textField = new JTextField();
        studentid_textField.setBounds(110,10,120,20);
        panel.add(studentid_textField);
        allComs.put("studentid_textField",studentid_textField);

        studentid_label.setVisible(false);
        studentid_textField.setVisible(false);
        keyLabel = studentid_label;
        keyTextField = studentid_textField;

        JLabel studentname_label = new JLabel("ѧ������:");
        studentname_label.setBounds(20,40,80,20);
        panel.add(studentname_label);
        JTextField studentname_textField = new JTextField();
        studentname_textField.setBounds(110,40,120,20);
        panel.add(studentname_textField);
        allComs.put("studentname_textField",studentname_textField);


        JLabel gender_label = new JLabel("ѧ���Ա�:");
        gender_label.setBounds(20,70,80,20);
        panel.add(gender_label);
        JTextField gender_textField = new JTextField();
        gender_textField.setBounds(110,70,120,20);
        panel.add(gender_textField);
        allComs.put("gender_textField",gender_textField);


        JLabel grade_label = new JLabel("ѧ���꼶:");
        grade_label.setBounds(20,100,80,20);
        panel.add(grade_label);
        JTextField grade_textField = new JTextField();
        grade_textField.setBounds(110,100,120,20);
        panel.add(grade_textField);
        allComs.put("grade_textField",grade_textField);


        JLabel phone_label = new JLabel("��ϵ�绰:");
        phone_label.setBounds(20,130,80,20);
        panel.add(phone_label);
        JTextField phone_textField = new JTextField();
        phone_textField.setBounds(110,130,120,20);
        panel.add(phone_textField);
        allComs.put("phone_textField",phone_textField);


        JLabel email_label = new JLabel("��������:");
        email_label.setBounds(20,160,80,20);
        panel.add(email_label);
        JTextField email_textField = new JTextField();
        email_textField.setBounds(110,160,120,20);
        panel.add(email_textField);
        allComs.put("email_textField",email_textField);


        JLabel username_label = new JLabel("�û���:");
        username_label.setBounds(20,190,80,20);
        panel.add(username_label);
        JTextField username_textField = new JTextField();
        username_textField.setBounds(110,190,120,20);
        panel.add(username_textField);
        allComs.put("username_textField",username_textField);


        JLabel password_label = new JLabel("����:");
        password_label.setBounds(20,220,80,20);
        panel.add(password_label);
        JTextField password_textField = new JTextField();
        password_textField.setBounds(110,220,120,20);
        panel.add(password_textField);
        allComs.put("password_textField",password_textField);


        JButton cancer_button = new JButton("ȡ��");
        cancer_button.setBounds(60,280,70,20);
        panel.add(cancer_button);
        if (map != null){
            if (!Utils.isNull(map.get("studentid"))){
                JTextField studentid_map_textField = (JTextField)allComs.get("studentid_textField");
                if (studentid_map_textField != null){
                    studentid_map_textField.setText(map.get("studentid").toString());
                }
            }
            if (!Utils.isNull(map.get("studentname"))){
                JTextField studentname_map_textField = (JTextField)allComs.get("studentname_textField");
                if (studentname_map_textField != null){
                    studentname_map_textField.setText(map.get("studentname").toString());
                }
            }
            if (!Utils.isNull(map.get("gender"))){
                JTextField gender_map_textField = (JTextField)allComs.get("gender_textField");
                if (gender_map_textField != null){
                    gender_map_textField.setText(map.get("gender").toString());
                }
            }
            if (!Utils.isNull(map.get("grade"))){
                JTextField grade_map_textField = (JTextField)allComs.get("grade_textField");
                if (grade_map_textField != null){
                    grade_map_textField.setText(map.get("grade").toString());
                }
            }
            if (!Utils.isNull(map.get("phone"))){
                JTextField phone_map_textField = (JTextField)allComs.get("phone_textField");
                if (phone_map_textField != null){
                    phone_map_textField.setText(map.get("phone").toString());
                }
            }
            if (!Utils.isNull(map.get("email"))){
                JTextField email_map_textField = (JTextField)allComs.get("email_textField");
                if (email_map_textField != null){
                    email_map_textField.setText(map.get("email").toString());
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
                studentEditWindow.dispose();
                studentWindow.showWindow();
            }
        });
        JButton submit_button = new JButton("ȷ��");
        submit_button.setBounds(150,280,70,20);
        submit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentid_value = ((JTextField)allComs.get("studentid_textField")).getText();
                String studentname_value = getText("studentname_textField",null);
                if ("".equals(studentname_value)){
                    JOptionPane.showMessageDialog(panel,"������ѧ������!");
                    return;
                }
                String gender_value = getText("gender_textField",null);
                if ("".equals(gender_value)){
                    JOptionPane.showMessageDialog(panel,"������ѧ���Ա�!");
                    return;
                }
                String grade_value = getText("grade_textField",null);
                if ("".equals(grade_value)){
                    JOptionPane.showMessageDialog(panel,"������ѧ���꼶!");
                    return;
                }
                String phone_value = getText("phone_textField",null);
                if ("".equals(phone_value)){
                    JOptionPane.showMessageDialog(panel,"��������ϵ�绰!");
                    return;
                }
                String email_value = getText("email_textField",null);
                if ("".equals(email_value)){
                    JOptionPane.showMessageDialog(panel,"�������������!");
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
                    StringBuilder sql = new StringBuilder("insert into student(studentname,gender,grade,phone,email,username,password) values('"+studentname_value+"','"+gender_value+"','"+grade_value+"','"+phone_value+"','"+email_value+"','"+username_value+"','"+password_value+"')");
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
                    StringBuilder sql = new StringBuilder("update student set studentname =  '"+studentname_value+"', gender =  '"+gender_value+"', grade =  '"+grade_value+"', phone =  '"+phone_value+"', email =  '"+email_value+"', username =  '"+username_value+"', password =  '"+password_value+"' where studentid =  '"+studentid_value+"'");
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
                studentEditWindow.dispose();
                studentWindow.showWindow();
            }
        });
        panel.add(submit_button);
        this.setSize(300,370);
        Utils.setCenterLocation(this);
    }
    private String getText(String name,String prop){
        if (allComs.containsKey(name)){
            Object o = allComs.get(name);
            if (o instanceof JTextField){
                return ((JTextField) o).getText();
            }else if(o instanceof JComboBox){
                String itemName =  ((JComboBox<?>) o).getSelectedItem().toString();
                return studentWindow.getPropValue(prop,itemName);
            }
        }
        return "";
   }
}
