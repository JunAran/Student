package student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
public class CourseEditWindow extends JFrame {
    private CourseEditWindow courseEditWindow;
    private CourseWindow courseWindow;
    private Map<String,Object> allComs = new HashMap<>();
    private static JLabel keyLabel;
    private static JTextField keyTextField;
    public CourseEditWindow(CourseWindow courseWindowTemp,Map<String,Object> map){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.courseEditWindow = this;
        this.courseWindow = courseWindowTemp;
        this.setVisible(true);
        this.setTitle("����");
        this.setResizable(false);
        JPanel panel = new JPanel();
        panel.setBounds(0,0,getWidth(),getHeight());
        panel.setLayout(null);
        this.add(panel);
        allComs.put("panel",panel);
        JLabel courseid_label = new JLabel("�γ�ID:");
        courseid_label.setBounds(20,10,80,20);
        panel.add(courseid_label);
        JTextField courseid_textField = new JTextField();
        courseid_textField.setBounds(110,10,120,20);
        panel.add(courseid_textField);
        allComs.put("courseid_textField",courseid_textField);

        courseid_label.setVisible(false);
        courseid_textField.setVisible(false);
        keyLabel = courseid_label;
        keyTextField = courseid_textField;

        JLabel coursename_label = new JLabel("�γ�����:");
        coursename_label.setBounds(20,40,80,20);
        panel.add(coursename_label);
        JTextField coursename_textField = new JTextField();
        coursename_textField.setBounds(110,40,120,20);
        panel.add(coursename_textField);
        allComs.put("coursename_textField",coursename_textField);


        JLabel coursedescription_label = new JLabel("�γ�����:");
        coursedescription_label.setBounds(20,70,80,20);
        panel.add(coursedescription_label);
        JTextField coursedescription_textField = new JTextField();
        coursedescription_textField.setBounds(110,70,120,20);
        panel.add(coursedescription_textField);
        allComs.put("coursedescription_textField",coursedescription_textField);


        JLabel instructorid_label = new JLabel("��ʦID:");
        instructorid_label.setBounds(20,100,80,20);
        panel.add(instructorid_label);
        JTextField instructorid_textField = new JTextField();
        instructorid_textField.setBounds(110,100,120,20);
        panel.add(instructorid_textField);
        if (courseWindow.userType == 2){
            instructorid_textField.setEditable(false);
            instructorid_textField.setText(courseWindow.userId+"");
        }
        allComs.put("instructorid_textField",instructorid_textField);


        JLabel credits_label = new JLabel("�γ�ѧ��:");
        credits_label.setBounds(20,130,80,20);
        panel.add(credits_label);
        JTextField credits_textField = new JTextField();
        credits_textField.setBounds(110,130,120,20);
        panel.add(credits_textField);
        allComs.put("credits_textField",credits_textField);


        JLabel capacity_label = new JLabel("�γ�����:");
        capacity_label.setBounds(20,160,80,20);
        panel.add(capacity_label);
        JTextField capacity_textField = new JTextField();
        capacity_textField.setBounds(110,160,120,20);
        panel.add(capacity_textField);
        allComs.put("capacity_textField",capacity_textField);


        JButton cancer_button = new JButton("ȡ��");
        cancer_button.setBounds(60,220,70,20);
        panel.add(cancer_button);
        if (map != null){
            if (!Utils.isNull(map.get("courseid"))){
                JTextField courseid_map_textField = (JTextField)allComs.get("courseid_textField");
                if (courseid_map_textField != null){
                    courseid_map_textField.setText(map.get("courseid").toString());
                }
            }
            if (!Utils.isNull(map.get("coursename"))){
                JTextField coursename_map_textField = (JTextField)allComs.get("coursename_textField");
                if (coursename_map_textField != null){
                    coursename_map_textField.setText(map.get("coursename").toString());
                }
            }
            if (!Utils.isNull(map.get("coursedescription"))){
                JTextField coursedescription_map_textField = (JTextField)allComs.get("coursedescription_textField");
                if (coursedescription_map_textField != null){
                    coursedescription_map_textField.setText(map.get("coursedescription").toString());
                }
            }
            if (!Utils.isNull(map.get("instructorid"))){
                JTextField instructorid_map_textField = (JTextField)allComs.get("instructorid_textField");
                if (instructorid_map_textField != null){
                    instructorid_map_textField.setText(map.get("instructorid").toString());
                }
            }
            if (!Utils.isNull(map.get("credits"))){
                JTextField credits_map_textField = (JTextField)allComs.get("credits_textField");
                if (credits_map_textField != null){
                    credits_map_textField.setText(map.get("credits").toString());
                }
            }
            if (!Utils.isNull(map.get("capacity"))){
                JTextField capacity_map_textField = (JTextField)allComs.get("capacity_textField");
                if (capacity_map_textField != null){
                    capacity_map_textField.setText(map.get("capacity").toString());
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
                courseEditWindow.dispose();
                courseWindow.showWindow();
            }
        });
        JButton submit_button = new JButton("ȷ��");
        submit_button.setBounds(150,220,70,20);
        submit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseid_value = ((JTextField)allComs.get("courseid_textField")).getText();
                String coursename_value = getText("coursename_textField",null);
                if ("".equals(coursename_value)){
                    JOptionPane.showMessageDialog(panel,"������γ�����!");
                    return;
                }
                String coursedescription_value = getText("coursedescription_textField",null);
                if ("".equals(coursedescription_value)){
                    JOptionPane.showMessageDialog(panel,"������γ�����!");
                    return;
                }
                String instructorid_value = getText("instructorid_textField",null);
                if ("".equals(instructorid_value)){
                    JOptionPane.showMessageDialog(panel,"�������ʦID!");
                    return;
                }
                String credits_value = getText("credits_textField",null);
                if ("".equals(credits_value)){
                    JOptionPane.showMessageDialog(panel,"������γ�ѧ��!");
                    return;
                }
                String capacity_value = getText("capacity_textField",null);
                if ("".equals(capacity_value)){
                    JOptionPane.showMessageDialog(panel,"������γ�����!");
                    return;
                }
                if (map == null){
                    StringBuilder sql = new StringBuilder("insert into course(coursename,coursedescription,instructorid,credits,capacity) values('"+coursename_value+"','"+coursedescription_value+"','"+instructorid_value+"','"+credits_value+"','"+capacity_value+"')");
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
                    StringBuilder sql = new StringBuilder("update course set coursename =  '"+coursename_value+"', coursedescription =  '"+coursedescription_value+"', instructorid =  '"+instructorid_value+"', credits =  '"+credits_value+"', capacity =  '"+capacity_value+"' where courseid =  '"+courseid_value+"'");
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
                courseEditWindow.dispose();
                courseWindow.showWindow();
            }
        });
        panel.add(submit_button);
        this.setSize(300,310);
        Utils.setCenterLocation(this);
    }
    private String getText(String name,String prop){
        if (allComs.containsKey(name)){
            Object o = allComs.get(name);
            if (o instanceof JTextField){
                return ((JTextField) o).getText();
            }else if(o instanceof JComboBox){
                String itemName =  ((JComboBox<?>) o).getSelectedItem().toString();
                return courseWindow.getPropValue(prop,itemName);
            }
        }
        return "";
   }
}
