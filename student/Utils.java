package student;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static String url = "jdbc:mysql://localhost:3306/xueshengxuanke";
    // ���ݿ��û���
    private static String user = "root";
    // ���ݿ�����
    private  static String password = "123456";

    private static Connection conn;
    private static Statement stmt;

    static {
        try {
            // ����MySQL����
            Class.forName("com.mysql.cj.jdbc.Driver");
            // ��������
            conn = DriverManager.getConnection(url, user, password);
            // ����Statement
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Statement getStatement(){
        return stmt;
    }

    public static Connection getConn(){
        return conn;
    }

    public static void setCenterLocation(JFrame jFrame){
        int windowWidth = jFrame.getWidth();
        int windowHeight = jFrame.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        jFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
    }

    public static Long formTime(String dateTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            return simpleDateFormat.parse(dateTime).getTime();
        }catch (Exception e){
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    public static String formTime(Date date,String form){
        if ("".equals(form) || form == null){
            form = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(form);
        return simpleDateFormat.format(date);
    }

    public static boolean isBlank(String str){
        if (str == null){
            return true;
        }
        if ("".equals(str.trim())){
            return true;
        }
        return false;
    }

    public static boolean isNull(Object o){
        if (o == null){
            return true;
        }
        if (o instanceof String){
            return isBlank((String) o);
        }
        return false;
    }


    public static Map<String,Object> login(String name, String pass,String type){
        Map<String,Object> map = new HashMap<>();
        String sql = "";
            if ("ѧ����¼".equals(type)){
            sql = "select * from student where username = '"+name+"'";
        }
        else if ("��ʦ��¼".equals(type)){
            sql = "select * from teacher where username = '"+name+"'";
        }else if ("����Ա��¼".equals(type)){
            sql = "select * from admin where username = '"+name+"'";
        }
        Statement statement = getStatement();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                String password = resultSet.getString("password");
                if (pass.equals(password)){
                    map.put("status",200);
                    if ("ѧ����¼".equals(type)){
                        map.put("data",resultSet.getInt("studentid"));
                    }else if ("��ʦ��¼".equals(type)){
                        map.put("data",resultSet.getInt("teacherid"));
                    }else if ("����Ա��¼".equals(type)){
                        map.put("data",resultSet.getInt("adminid"));
                    }
                    map.put("mess","��¼�ɹ�");
                }else {
                    map.put("status",201);
                    map.put("data",null);
                    map.put("mess","�������");
                }
            }else {
                map.put("status",202);
                map.put("data",null);
                map.put("mess","�˺Ų�����");
            }
        }catch (Exception e){
            map.put("status",500);
            map.put("data",null);
            map.put("mess","��������");
        }
        return map;
    }
}
