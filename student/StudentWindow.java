package student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
public class StudentWindow extends JFrame {
    private StudentWindow studentWindow;
    private Map<String,Object> allComs = new HashMap<>();
    private static Map<String,Map<String,String>> propMap = new HashMap<>();
    public JFrame frame;
    public StudentWindow(JFrame jFrame){
        frame = jFrame;
        this.studentWindow = this;
        this.setResizable(false);
        this.setTitle("ѧ������");
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
                studentWindow.dispose();
            }
        });

        allComs.put("panel",panel);
        JLabel studentid_label = new JLabel("ѧ��:");
        studentid_label.setBounds(20,10,32,20);
        panel.add(studentid_label);
        allComs.put("studentid_label",studentid_label);
        JTextField studentid_textField = new JTextField();
        studentid_textField.setBounds(57,10,80,20);
        panel.add(studentid_textField);
        allComs.put("studentid_textField",studentid_textField);

        JLabel studentname_label = new JLabel("����:");
        studentname_label.setBounds(142,10,64,20);
        panel.add(studentname_label);
        allComs.put("studentname_label",studentname_label);
        JTextField studentname_textField = new JTextField();
        studentname_textField.setBounds(211,10,80,20);
        panel.add(studentname_textField);
        allComs.put("studentname_textField",studentname_textField);

        JLabel gender_label = new JLabel("�Ա�:");
        gender_label.setBounds(296,10,64,20);
        panel.add(gender_label);
        allComs.put("gender_label",gender_label);
        JTextField gender_textField = new JTextField();
        gender_textField.setBounds(365,10,80,20);
        panel.add(gender_textField);
        allComs.put("gender_textField",gender_textField);

        JButton search_button = new JButton("��ѯ");
        search_button.setBounds(450,10,60,20);
        panel.add(search_button);
        allComs.put("search_button",search_button);

        JButton add_button = new JButton("����");
        add_button.setBounds(515,10,60,20);
        panel.add(add_button);
        allComs.put("add_button",add_button);

        JButton edit_button = new JButton("�޸�");
        edit_button.setBounds(580,10,60,20);
        panel.add(edit_button);
        allComs.put("edit_button",edit_button);

        JButton del_button = new JButton("ɾ��");
        del_button.setBounds(645,10,60,20);
        panel.add(del_button);
        allComs.put("del_button",del_button);

        this.setSize(735,320);
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        allComs.put("model",model);
        allComs.put("table",table);
        model.setColumnIdentifiers(new Object[]{
        "ѧ��ID",
        "ѧ������",
        "ѧ���Ա�",
        "ѧ���꼶",
        "��ϵ�绰",
        "��������",
        "�û���",
        "����"
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
                    int selectIndex = JOptionPane.showConfirmDialog(panel, "ȷ��ɾ����", "ȷ��", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);                    if(selectIndex != 0){                        return;                    }                    String id = table.getModel().getValueAt(row, 0).toString();
                    Statement statement = Utils.getStatement();
                    try{
                        String sql = "delete from student where studentid = '"+id+"'";
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
                studentWindow.setVisible(false);
                new StudentEditWindow(studentWindow,null);
            }
        });
        ((JButton)allComs.get("edit_button")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0){
                    Map<String,Object> map =new HashMap<>();
                    map.put("studentid",table.getModel().getValueAt(row, 0));
                    map.put("studentname",table.getModel().getValueAt(row, 1));
                    map.put("gender",table.getModel().getValueAt(row, 2));
                    map.put("grade",table.getModel().getValueAt(row, 3));
                    map.put("phone",table.getModel().getValueAt(row, 4));
                    map.put("email",table.getModel().getValueAt(row, 5));
                    map.put("username",table.getModel().getValueAt(row, 6));
                    map.put("password",table.getModel().getValueAt(row, 7));
                    studentWindow.setVisible(false);
                    new StudentEditWindow(studentWindow,map);
                }else {
                    JOptionPane.showMessageDialog(panel,"��ѡ����Ҫ�޸ĵļ�¼");
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
            StringBuilder sql = new StringBuilder("select * from student where 1 = 1");
            map.forEach((key,value)->{
                if (!Utils.isBlank(value.toString())){
                    sql.append(" and "+key +" = '"+value+"'");
                }
            });
            ResultSet resultSet = statement.executeQuery(sql.toString());
            while (resultSet.next()){
                model.addRow(new Object[]{
                    resultSet.getString("studentid"),
                    resultSet.getString("studentname"),
                    resultSet.getString("gender"),
                    resultSet.getString("grade"),
                    resultSet.getString("phone"),
                    resultSet.getString("email"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                });
             }
             ((JLabel)allComs.get("sum_label")).setText("�ܹ�"+model.getRowCount()+"����¼");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Map<String,Object> getSearchMap(){
        Map<String,Object> map = new HashMap<>();
        String studentid_textField_text = getText("studentid_textField",null);
        map.put("studentid",studentid_textField_text);
        String studentname_textField_text = getText("studentname_textField",null);
        map.put("studentname",studentname_textField_text);
        String gender_textField_text = getText("gender_textField",null);
        map.put("gender",gender_textField_text);
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
