/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月8日 上午9:46:09</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class DBUtil {

static ComboPooledDataSource cpds = null;  
	
    static{  
        //加载c3p0连接池配置  
//        System.setProperty("com.mchange.v2.c3p0.cfg.xml","c3p0-config.xml");  
        //这里有个优点，写好配置文件，想换数据库
        cpds = new ComboPooledDataSource("mysql");//这是oracle数据库  
//      cpds = new ComboPooledDataSource("mysql");//这是mysql数据库  
    }  
    /** 
     * 获得数据库连接 
     * @return   Connection 
     */  
    public static Connection getConnection(){  
        try {  
            return cpds.getConnection();  
        } catch (SQLException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    /** 
     * 数据库关闭操作 
     * @param conn   
     * @param st     
     * @param pst 
     * @param rs 
     */  
    public static void close(Connection conn,PreparedStatement pst,ResultSet rs){  
        if(rs!=null){  
            try {  
                rs.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if(pst!=null){  
            try {  
                pst.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
  
        if(conn!=null){  
            try {  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    /** 
     * 测试DBUtil类 
     * @param args 
     */  
    public static void main(String[] args) {  
        Connection conn=getConnection();  
        System.out.println(conn.getClass().getName());  
        close(conn,null,null);  
    }  
}
