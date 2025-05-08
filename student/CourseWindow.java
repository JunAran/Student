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
public class CourseWindow extends JFrame {
    private CourseWindow courseWindow;
    private Map<String,Object> allComs = new HashMap<>();
    private static Map<String,Map<String,String>> propMap = new HashMap<>();
    public Integer userId;
    public Integer userType = 1;
    public JFrame frame;

    public CourseWindow(){
        initWindow();
    }

    public CourseWindow(Integer id,JFrame jFrame){
        userId = id;
        frame = jFrame;
        initWindow();
        ((JButton)allComs.get("xk_button")).setVisible(true);
        ((JButton)allComs.get("add_button")).setVisible(false);
        ((JButton)allComs.get("edit_button")).setVisible(false);
        ((JButton)allComs.get("del_button")).setVisible(false);
    }

    public CourseWindow(Integer id,Integer type,JFrame jFrame){
        userId = id;
        userType = type;
        frame = jFrame;
        if (type == 1){
            initWindow();
            ((JButton)allComs.get("xk_button")).setVisible(true);
            ((JButton)allComs.get("add_button")).setVisible(false);
            ((JButton)allComs.get("edit_button")).setVisible(false);
            ((JButton)allComs.get("del_button")).setVisible(false);
        }else {
            initWindow();
        }
    }


    private void initWindow(){
        this.courseWindow = this;
        this.setResizable(false);
        this.setTitle("�γ�");
        Panel panel = new Panel();
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setBounds(0,0,this.getWidth(),this.getHeight());

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // ��������Զ���ر��߼�
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(true);
                courseWindow.dispose();
            }
        });


        this.add(panel);
        allComs.put("panel",panel);
        JLabel courseid_label = new JLabel("ID:");
        courseid_label.setBounds(20,10,32,20);
        panel.add(courseid_label);
        allComs.put("courseid_label",courseid_label);
        JTextField courseid_textField = new JTextField();
        courseid_textField.setPreferredSize(new Dimension(150, 20));
        courseid_textField.setBounds(57,10,80,20);
        panel.add(courseid_textField);
        allComs.put("courseid_textField",courseid_textField);

        JLabel coursename_label = new JLabel("����:");
        coursename_label.setBounds(142,10,64,20);
        panel.add(coursename_label);
        allComs.put("coursename_label",coursename_label);
        JTextField coursename_textField = new JTextField();
        courseid_textField.setPreferredSize(new Dimension(150, 20));
        coursename_textField.setBounds(211,10,80,20);
        panel.add(coursename_textField);
        allComs.put("coursename_textField",coursename_textField);

        JLabel coursedescription_label = new JLabel("����:");
        coursedescription_label.setBounds(296,10,64,20);
        panel.add(coursedescription_label);
        allComs.put("coursedescription_label",coursedescription_label);
        JTextField coursedescription_textField = new JTextField();
        courseid_textField.setPreferredSize(new Dimension(150, 20));
        coursedescription_textField.setBounds(365,10,80,20);
        panel.add(coursedescription_textField);
        allComs.put("coursedescription_textField",coursedescription_textField);

        JButton search_button = new JButton("��ѯ");
        search_button.setPreferredSize(new Dimension(100, 20));
        search_button.setBounds(450,10,60,20);
        panel.add(search_button);
        allComs.put("search_button",search_button);

        JButton add_button = new JButton("����");
        add_button.setPreferredSize(new Dimension(100, 20));
        add_button.setBounds(515,10,60,20);
        panel.add(add_button);
        allComs.put("add_button",add_button);

        JButton xk_button = new JButton("ѡ��");
        xk_button.setPreferredSize(new Dimension(100, 20));
        xk_button.setBounds(515,10,60,20);
        panel.add(xk_button);
        allComs.put("xk_button",xk_button);
        xk_button.setVisible(false);

        JButton edit_button = new JButton("�޸�");
        edit_button.setPreferredSize(new Dimension(100, 20));
        edit_button.setBounds(580,10,60,20);
        panel.add(edit_button);
        allComs.put("edit_button",edit_button);

        JButton del_button = new JButton("ɾ��");
        del_button.setPreferredSize(new Dimension(100, 20));
        del_button.setBounds(645,10,60,20);
        panel.add(del_button);
        allComs.put("del_button",del_button);

        this.setSize(735,320);
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        allComs.put("model",model);
        allComs.put("table",table);
        model.setColumnIdentifiers(new Object[]{
                "�γ�ID",
                "�γ�����",
                "�γ�����",
                "��ʦID",
                "��ʦ����",
                "�γ�ѧ��",
                "�γ�����"
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,35,getWidth()-15,getHeight()-110);
        panel.add(scrollPane);
        JLabel sum_label = new JLabel("�ܹ�0����¼");        sum_label.setBounds(0,getHeight()-75,getWidth()-15,30);
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
                        String sql = "delete from course where courseid = '"+id+"'";
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(panel,"ɾ���ɹ�");
                        initTableData(((DefaultTableModel)allComs.get("model")),getSearchMap());
                        ((JLabel)allComs.get("sum_label")).setText("�ܹ�"+model.getRowCount()+"����¼");
                    }catch (Exception xe){
                        xe.printStackTrace();
                        JOptionPane.showMessageDialog(panel,"ɾ��ʧ��");
                    }
                }else {
                    JOptionPane.showMessageDialog(panel,"��ѡ��һ����¼");
                }
            }
        });
        ((JButton)allComs.get("add_button")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseWindow.setVisible(false);
                new CourseEditWindow(courseWindow,null);
            }
        });
        ((JButton)allComs.get("edit_button")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0){
                    Map<String,Object> map =new HashMap<>();
                    map.put("courseid",table.getModel().getValueAt(row, 0));
                    map.put("coursename",table.getModel().getValueAt(row, 1));
                    map.put("coursedescription",table.getModel().getValueAt(row, 2));
                    map.put("instructorid",table.getModel().getValueAt(row, 3));
                    map.put("teachername",table.getModel().getValueAt(row, 4));
                    map.put("credits",table.getModel().getValueAt(row, 5));
                    map.put("capacity",table.getModel().getValueAt(row, 6));
                    courseWindow.setVisible(false);
                    new CourseEditWindow(courseWindow,map);
                }else {
                    JOptionPane.showMessageDialog(panel,"��ѡ����Ҫ�޸ĵļ�¼");
                }
            }
        });
        ((JButton)allComs.get("xk_button")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0){
                    int selectIndex = JOptionPane.showConfirmDialog(panel, "ȷ��ѡ�Σ�", "ȷ��", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);                    if(selectIndex != 0){                        return;                    }                    String id = table.getModel().getValueAt(row, 0).toString();
                    Statement statement = Utils.getStatement();
                    try{
                        String courseid = (String) table.getModel().getValueAt(row, 0);
                        StringBuilder sql = new StringBuilder("insert into enrollment(studentid,courseid,enrollmentdate,grade,confirmed) values('"+userId+"','"+courseid+"',now(),null,'0')");
                        statement.executeUpdate(sql.toString());
                        JOptionPane.showMessageDialog(panel,"ѡ�γɹ�");
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
            StringBuilder sql = new StringBuilder("select * from course,teacher where course.instructorid = teacher.teacherid");
            map.forEach((key,value)->{
                if (!"except".equals(key)){
                    if (!Utils.isBlank(value.toString())){
                        sql.append(" and "+key +" = '"+value+"'");
                    }
                }else {
                    List<String> list = (List<String>) map.get("except");
                    if (list.size() > 0){
                        sql.append(" and courseid not in(");
                        for (int i = 0;i<list.size();i++){
                            if (i == 0){
                                sql.append(list.get(i));
                            }else {
                                sql.append(","+list.get(i));
                            }
                        }
                        sql.append(")");
                    }
                }
            });
            ResultSet resultSet = statement.executeQuery(sql.toString());
            while (resultSet.next()){
                model.addRow(new Object[]{
                        resultSet.getString("courseid"),
                        resultSet.getString("coursename"),
                        resultSet.getString("coursedescription"),
                        resultSet.getString("instructorid"),
                        resultSet.getString("teachername"),
                        resultSet.getString("credits"),
                        resultSet.getString("capacity"),
                });
            }
            ((JLabel)allComs.get("sum_label")).setText("�ܹ�"+model.getRowCount()+"����¼");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Map<String,Object> getSearchMap(){
        Map<String,Object> map = new HashMap<>();
        String courseid_textField_text = getText("courseid_textField",null);
        map.put("courseid",courseid_textField_text);
        String coursename_textField_text = getText("coursename_textField",null);
        map.put("coursename",coursename_textField_text);
        String coursedescription_textField_text = getText("coursedescription_textField",null);
        map.put("coursedescription",coursedescription_textField_text);
        if (userType == 1 && userId != null){
            String sql = "select courseid from enrollment where studentid = '"+userId+"'";
            Statement statement = Utils.getStatement();
            try{
                ResultSet resultSet = statement.executeQuery(sql);
                List<String> list = new ArrayList<>();
                while (resultSet.next()){
                    String courseid = resultSet.getString("courseid");
                    if (!Utils.isBlank(courseid)){
                        list.add(courseid);
                    }
                }
                map.put("except",list);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if (userType == 2){
            map.put("instructorid",userId);
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
