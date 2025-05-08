package student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class EnrollmentEditWindow extends JFrame {
    private EnrollmentEditWindow enrollmentEditWindow;
    private EnrollmentWindow enrollmentWindow;
    private Map<String,Object> allComs = new HashMap<>();
    private static JLabel keyLabel;
    private static JTextField keyTextField;
    public EnrollmentEditWindow(EnrollmentWindow enrollmentWindowTemp,Map<String,Object> map){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.enrollmentEditWindow = this;
        this.enrollmentWindow = enrollmentWindowTemp;
        this.setVisible(true);
        this.setTitle("����");
        this.setResizable(false);
        JPanel panel = new JPanel();
        panel.setBounds(0,0,getWidth(),getHeight());
        panel.setLayout(null);
        this.add(panel);
        allComs.put("panel",panel);
        JLabel enrollmentid_label = new JLabel("ѡ��ID:");
        enrollmentid_label.setBounds(20,10,80,20);
        panel.add(enrollmentid_label);
        JTextField enrollmentid_textField = new JTextField();
        enrollmentid_textField.setBounds(110,10,120,20);
        panel.add(enrollmentid_textField);
        allComs.put("enrollmentid_textField",enrollmentid_textField);

        enrollmentid_label.setVisible(false);
        enrollmentid_textField.setVisible(false);
        keyLabel = enrollmentid_label;
        keyTextField = enrollmentid_textField;

        JLabel studentid_label = new JLabel("ѧ��ID:");
        studentid_label.setBounds(20,40,80,20);
        panel.add(studentid_label);
        JTextField studentid_textField = new JTextField();
        studentid_textField.setBounds(110,40,120,20);
        panel.add(studentid_textField);
        allComs.put("studentid_textField",studentid_textField);


        JLabel courseid_label = new JLabel("�γ�ID:");
        courseid_label.setBounds(20,70,80,20);
        panel.add(courseid_label);
        JTextField courseid_textField = new JTextField();
        courseid_textField.setBounds(110,70,120,20);
        panel.add(courseid_textField);
        allComs.put("courseid_textField",courseid_textField);


        JLabel enrollmentdate_label = new JLabel("ѡ��ʱ��:");
        enrollmentdate_label.setBounds(20,100,80,20);
        panel.add(enrollmentdate_label);
        JTextField enrollmentdate_textField = new JTextField();
        enrollmentdate_textField.setBounds(110,100,120,20);
        panel.add(enrollmentdate_textField);
        allComs.put("enrollmentdate_textField",enrollmentdate_textField);
        enrollmentdate_textField.setEditable(false);
        enrollmentdate_textField.setText(Utils.formTime(new Date(),"yyyy-MM-dd"));



        JLabel grade_label = new JLabel("�ɼ�:");
        grade_label.setBounds(20,130,80,20);
        panel.add(grade_label);
        JTextField grade_textField = new JTextField();
        grade_textField.setBounds(110,130,120,20);
        panel.add(grade_textField);
        allComs.put("grade_textField",grade_textField);


        JLabel confirmed_label = new JLabel("�Ƿ���ȷ��:");
        confirmed_label.setBounds(20,160,80,20);
        panel.add(confirmed_label);
        JComboBox<String> jComboBox = new JComboBox<>();
        jComboBox.addItem("δȷ��");
        jComboBox.addItem("��ȷ��");
        jComboBox.setBounds(110,160,120,20);
        panel.add(jComboBox);
        allComs.put("confirmed_jComboBox",jComboBox);


        JButton cancer_button = new JButton("ȡ��");
        cancer_button.setBounds(60,220,70,20);
        panel.add(cancer_button);
        if (map != null){
            if (!Utils.isNull(map.get("enrollmentid"))){
                JTextField enrollmentid_map_textField = (JTextField)allComs.get("enrollmentid_textField");
                if (enrollmentid_map_textField != null){
                    enrollmentid_map_textField.setText(map.get("enrollmentid").toString());
                }
            }
            if (!Utils.isNull(map.get("studentid"))){
                JTextField studentid_map_textField = (JTextField)allComs.get("studentid_textField");
                if (studentid_map_textField != null){
                    studentid_map_textField.setText(map.get("studentid").toString());
                }
            }
            if (!Utils.isNull(map.get("courseid"))){
                JTextField courseid_map_textField = (JTextField)allComs.get("courseid_textField");
                if (courseid_map_textField != null){
                    courseid_map_textField.setText(map.get("courseid").toString());
                }
            }
            if (!Utils.isNull(map.get("enrollmentdate"))){
                JTextField enrollmentdate_map_textField = (JTextField)allComs.get("enrollmentdate_textField");
                if (enrollmentdate_map_textField != null){
                    enrollmentdate_map_textField.setText(map.get("enrollmentdate").toString());
                }
            }
            if (!Utils.isNull(map.get("grade"))){
                JTextField grade_map_textField = (JTextField)allComs.get("grade_textField");
                if (grade_map_textField != null){
                    grade_map_textField.setText(map.get("grade").toString());
                }
            }
            if (!Utils.isNull(map.get("confirmed"))){
                String confirmed = (String) map.get("confirmed");
                ((JComboBox)allComs.get("confirmed_jComboBox")).setSelectedItem(confirmed);
            }
            keyLabel.setVisible(true);
            keyTextField.setVisible(true);
            keyTextField.setEditable(false);
            this.setTitle("�޸�");;
        }
        cancer_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollmentEditWindow.dispose();
                enrollmentWindow.showWindow();
            }
        });
        JButton submit_button = new JButton("ȷ��");
        submit_button.setBounds(150,220,70,20);
        submit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enrollmentid_value = ((JTextField)allComs.get("enrollmentid_textField")).getText();
                String studentid_value = getText("studentid_textField",null);
                if ("".equals(studentid_value)){
                    JOptionPane.showMessageDialog(panel,"������ѧ��ID!");
                    return;
                }
                String courseid_value = getText("courseid_textField",null);
                if ("".equals(courseid_value)){
                    JOptionPane.showMessageDialog(panel,"������γ�ID!");
                    return;
                }
                String enrollmentdate_value = getText("enrollmentdate_textField",null);
                if ("".equals(enrollmentdate_value)){
                    JOptionPane.showMessageDialog(panel,"������ѡ��ʱ��!");
                    return;
                }
                String grade_value = getText("grade_textField",null);
                if ("".equals(grade_value)){
                    grade_value = "0";
                }
                String confirmed_value = (String) ((JComboBox) allComs.get("confirmed_jComboBox")).getSelectedItem();
                if ("��ȷ��".equals(confirmed_value)){
                    confirmed_value = "1";
                }else {
                    confirmed_value = "0";
                }
                if ("".equals(confirmed_value)){
                    JOptionPane.showMessageDialog(panel,"�������Ƿ���ȷ��!");
                    return;
                }
                if (map == null){
                    StringBuilder sql = new StringBuilder("insert into enrollment(studentid,courseid,enrollmentdate,grade,confirmed) values('"+studentid_value+"','"+courseid_value+"','"+enrollmentdate_value+"','"+grade_value+"','"+confirmed_value+"')");
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
                    StringBuilder sql = new StringBuilder("update enrollment set studentid =  '"+studentid_value+"', courseid =  '"+courseid_value+"', enrollmentdate =  '"+enrollmentdate_value+"', grade =  '"+grade_value+"', confirmed =  '"+confirmed_value+"' where enrollmentid =  '"+enrollmentid_value+"'");
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
                enrollmentEditWindow.dispose();
                enrollmentWindow.showWindow();
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
                return enrollmentWindow.getPropValue(prop,itemName);
            }
        }
        return "";
   }
}
