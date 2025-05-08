package student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
public class EnrollmentWindow extends JFrame {
    private EnrollmentWindow enrollmentWindow;
    private Map<String,Object> allComs = new HashMap<>();
    private static Map<String,Map<String,String>> propMap = new HashMap<>();
    private Integer userId;
    private Integer userType = 1;
    public JFrame frame;

    public EnrollmentWindow(){
        initWindow();
    }

    public EnrollmentWindow(Integer id,JFrame jFrame){
        frame = jFrame;
        userId = id;
        initWindow();
        ((JButton)allComs.get("tk_button")).setVisible(true);
        ((JButton)allComs.get("add_button")).setVisible(false);
        ((JButton)allComs.get("edit_button")).setVisible(false);
        ((JButton)allComs.get("del_button")).setVisible(false);
    }

    public EnrollmentWindow(Integer id,Integer type,JFrame jFrame){
        userId = id;
        frame = jFrame;
        userType = type;
        initWindow();
        if (type == 1){
            ((JButton)allComs.get("tk_button")).setVisible(true);
            ((JButton)allComs.get("add_button")).setVisible(false);
            ((JButton)allComs.get("edit_button")).setVisible(false);
            ((JButton)allComs.get("del_button")).setVisible(false);
        }else {

        }
    }

    private void initWindow(){
        this.enrollmentWindow = this;
        this.setResizable(false);
        this.setTitle("�ɼ�");
        Panel panel = new Panel();
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setBounds(0,0,this.getWidth(),this.getHeight());
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // ��������Զ���ر��߼�
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(true);
                enrollmentWindow.dispose();
            }
        });

        allComs.put("panel",panel);
        JLabel enrollmentid_label = new JLabel("ѡ��ID:");
        enrollmentid_label.setBounds(20,10,62,20);
        panel.add(enrollmentid_label);
        allComs.put("enrollmentid_label",enrollmentid_label);
        JTextField enrollmentid_textField = new JTextField();
        enrollmentid_textField.setBounds(67,10,60,20);
        panel.add(enrollmentid_textField);
        allComs.put("enrollmentid_textField",enrollmentid_textField);

        JLabel studentid_label = new JLabel("ѧ��ID:");
        studentid_label.setBounds(142,10,62,20);
        panel.add(studentid_label);
        allComs.put("studentid_label",studentid_label);
        JTextField studentid_textField = new JTextField();
        studentid_textField.setBounds(189,10,60,20);
        panel.add(studentid_textField);
        allComs.put("studentid_textField",studentid_textField);

        JLabel courseid_label = new JLabel("�γ�ID:");
        courseid_label.setBounds(264,10,62,20);
        panel.add(courseid_label);
        allComs.put("courseid_label",courseid_label);
        JTextField courseid_textField = new JTextField();
        courseid_textField.setBounds(311,10,60,20);
        panel.add(courseid_textField);
        allComs.put("courseid_textField",courseid_textField);

        JButton search_button = new JButton("��ѯ");
        search_button.setBounds(386,10,60,20);
        panel.add(search_button);
        allComs.put("search_button",search_button);

        JButton add_button = new JButton("����");
        add_button.setBounds(451,10,60,20);
        panel.add(add_button);
        allComs.put("add_button",add_button);

        JButton tk_button = new JButton("�˿�");
        tk_button.setBounds(451,10,60,20);
        panel.add(tk_button);
        allComs.put("tk_button",tk_button);
        tk_button.setVisible(false);

        JButton edit_button = new JButton("�޸�");
        edit_button.setBounds(516,10,60,20);
        panel.add(edit_button);
        allComs.put("edit_button",edit_button);

        JButton del_button = new JButton("ɾ��");
        del_button.setBounds(581,10,60,20);
        panel.add(del_button);
        allComs.put("del_button",del_button);

        this.setSize(671,320);
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        allComs.put("model",model);
        allComs.put("table",table);
        model.setColumnIdentifiers(new Object[]{
                "ѡ��ID",
                "ѧ��ID",
                "ѧ������",
                "�γ�ID",
                "�γ�����",
                "ѡ��ʱ��",
                "�ɼ�",
                "�Ƿ���ȷ��"
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,35,getWidth()-15,getHeight()-110);
        panel.add(scrollPane);
        JLabel sum_label = new JLabel("�ܹ�0����¼");
        sum_label.setBounds(0,getHeight()-75,getWidth()-15,30);
        panel.add(sum_label);
        allComs.put("sum_label",sum_label);
        ((JButton)allComs.get("search_button")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initTableData(((DefaultTableModel)allComs.get("model")),getSearchMap());
                ((JLabel)allComs.get("sum_label")).setText("�ܹ�"+model.getRowCount()+"����¼");
            }
        });
        ((JButton)allComs.get("del_button")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0){
                    int selectIndex = JOptionPane.showConfirmDialog(panel, "ȷ��ɾ����", "ȷ��", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if(selectIndex != 0){
                        return;
                    }
                    String id = table.getModel().getValueAt(row, 0).toString();
                    Statement statement = Utils.getStatement();
                    try{
                        String sql = "delete from enrollment where enrollmentid = '"+id+"'";
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(panel,"ɾ���ɹ�");
                        initTableData(((DefaultTableModel)allComs.get("model")),getSearchMap());
                        ((JLabel)allComs.get("sum_label")).setText("�ܹ�"+model.getRowCount()+"����¼");
                    }catch (Exception xe){
                        xe.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(panel,"��ѡ��һ����¼");
                }
            }
        });
        ((JButton)allComs.get("add_button")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollmentWindow.setVisible(false);
                new EnrollmentEditWindow(enrollmentWindow,null);
            }
        });
        ((JButton)allComs.get("edit_button")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0){
                    Map<String,Object> map =new HashMap<>();
                    map.put("enrollmentid",table.getModel().getValueAt(row, 0));
                    map.put("studentid",table.getModel().getValueAt(row, 1));
                    map.put("studentname",table.getModel().getValueAt(row, 2));
                    map.put("courseid",table.getModel().getValueAt(row, 3));
                    map.put("coursename",table.getModel().getValueAt(row, 4));
                    map.put("enrollmentdate",table.getModel().getValueAt(row, 5));
                    map.put("grade",table.getModel().getValueAt(row, 6));
                    map.put("confirmed",table.getModel().getValueAt(row, 7));
                    enrollmentWindow.setVisible(false);
                    new EnrollmentEditWindow(enrollmentWindow,map);
                }else {
                    JOptionPane.showMessageDialog(panel,"��ѡ����Ҫ�޸ĵļ�¼");
                }
            }
        });
        ((JButton)allComs.get("tk_button")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0){
                    int selectIndex = JOptionPane.showConfirmDialog(panel, "ȷ���˶���", "ȷ��", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if(selectIndex != 0){
                        return;
                    }
                    try {
                        Statement statement = Utils.getStatement();
                        String enrollmentid = (String) table.getModel().getValueAt(row, 0);
                        String f = (String) table.getModel().getValueAt(row, 7);
                        if ("��ȷ��".equals(f)){
                            JOptionPane.showMessageDialog(panel,"��ʦ�Ѿ�ȷ�ϣ��޷��˶�");
                        }else {
                            String sql = "delete from enrollment where enrollmentid = '"+enrollmentid+"'";
                            statement.executeUpdate(sql);
                            JOptionPane.showMessageDialog(panel,"�˶��ɹ�");
                            initTableData(((DefaultTableModel)allComs.get("model")),getSearchMap());
                            ((JLabel)allComs.get("sum_label")).setText("�ܹ�"+model.getRowCount()+"����¼");
                        }
                    }catch (Exception et){
                        et.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(panel,"��ѡ����Ҫ�˶��ļ�¼");
                }
            }
        });
        initPropMap();
        initTableData(((DefaultTableModel)allComs.get("model")),getSearchMap());
        this.setVisible(true);
        Utils.setCenterLocation(this);
    }

    private void initPropMap(){
    }

    public String getProp(String prop,String index){
        if (propMap.containsKey(prop)){
            if (propMap.get(prop) != null && propMap.get(prop).get(index) != null){
                return propMap.get(prop).get(index);
            }
         }
         return "";
    }
    public String getPropValue(String prop,String name){
        if (propMap.containsKey(prop)){
            if (propMap.get(prop) != null){
                Map<String,String> map = propMap.get(prop);
                if (map.containsValue(name)){
                    AtomicReference<String> index = new AtomicReference<>("");
                    map.forEach((key,value)->{
                        if (name.equals(value)){
                            index.set(key);
                        }
                    });
                    return index.get();
                }
            }
        }
        return "";
    };
    public void showWindow(){
        this.setVisible(true);
        initTableData(((DefaultTableModel)allComs.get("model")),getSearchMap());
    }

    private void initTableData(DefaultTableModel model,Map<String,Object> map){
        while (model.getRowCount() > 0){
            model.removeRow(0);
        }
        Statement statement = Utils.getStatement();
        try{
            StringBuilder sql = new StringBuilder("select * from enrollment,student,course where enrollment.studentid = student.studentid and enrollment.courseid = course.courseid");
            map.forEach((key,value)->{
                if ("in".equals(key)){
                    List<String> coIds = (List<String>) value;
                    if (coIds.size() > 0){
                        sql.append(" and enrollment.courseid in(");
                        for(int i =0;i<coIds.size();i++){
                            if (i == 0){
                                sql.append(coIds.get(i));
                            }else {
                                sql.append(","+coIds.get(i));
                            }
                        }
                        sql.append(")");
                    }
                }else {
                    if (!Utils.isBlank(value.toString())){
                        sql.append(" and enrollment."+key +" = '"+value+"'");
                    }
                }
            });
            ResultSet resultSet = statement.executeQuery(sql.toString());
            while (resultSet.next()){
                String result = "δȷ��";
                String confirmed = resultSet.getString("confirmed");
                if ("1".equals(confirmed)){
                    result = "��ȷ��";
                }
                model.addRow(new Object[]{
                    resultSet.getString("enrollmentid"),
                    resultSet.getString("studentid"),
                    resultSet.getString("studentname"),
                    resultSet.getString("courseid"),
                    resultSet.getString("coursename"),
                    resultSet.getString("enrollmentdate"),
                    resultSet.getString("grade"), result,
                });
             }
             ((JLabel)allComs.get("sum_label")).setText("�ܹ�"+model.getRowCount()+"����¼");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Map<String,Object> getSearchMap(){
        Map<String,Object> map = new HashMap<>();
        String enrollmentid_textField_text = getText("enrollmentid_textField",null);
        map.put("enrollmentid",enrollmentid_textField_text);
        String studentid_textField_text = getText("studentid_textField",null);
        map.put("studentid",studentid_textField_text);
        String courseid_textField_text = getText("courseid_textField",null);
        map.put("courseid",courseid_textField_text);
        if (userType == 1 && userId != null){
            map.put("studentid",userId);
        }else if (userType == 2){
            List<String> courseidList = new ArrayList<>();
            String sql = "select courseid from course where instructorid = '"+userId+"'";
            Statement statement = Utils.getStatement();
            try{
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    courseidList.add(resultSet.getString("courseid"));
                }
            }catch (Exception cv){

            }
            map.put("in",courseidList);
        }
        return map;
    }
    private String getText(String name,String prop){
        if (allComs.containsKey(name)){
            Object o = allComs.get(name);
            if (o instanceof JTextField){
                return ((JTextField) o).getText();
            }else if(o instanceof JComboBox){
                String itemName =  ((JComboBox<?>) o).getSelectedItem().toString();
                return getPropValue(prop,itemName);
            }
        }
        return "";
   }
}
