package com.briup.ServerImpl;

import com.briup.LoggerImpl.LoggerImpl;
import com.briup.util.BIDR;
import com.briup.utils.DBUtils;
import com.briup.woss.server.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class ServerImpl implements Server{
    private static int port = 8888;
    private static ServerSocket serversocket = null;
    private static Socket socket = null;
    private static LoggerImpl loggerimpl = new LoggerImpl();


    public static void main(String[] args) {
        try {
            loggerimpl.info("服务器启动，准备接收客户端的数据！！！");
            serversocket = new ServerSocket(port);
            while(true){
                System.out.println("期待一个链接");
                socket = serversocket.accept();
                System.out.println("链接成功");

                Collection<BIDR> bidrCollection = new ServerImpl().revicer();
                System.out.println("开始入库");
                //new ServerImpl().saveToDatabase(bidrCollection);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //入库操作
    public void saveToDatabase(Collection<BIDR> collection){
        loggerimpl.info("入库开始！！！！");
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            //获取链接
            connection = DBUtils.getConn();
            for (BIDR bidr:collection) {
                String aaa_name = bidr.getAAA_login_name();
                String login_ip = bidr.getLogin_ip();
                Timestamp login = bidr.getLogin_date();
                Timestamp logout = bidr.getLogout_date();
                String nas_ip = bidr.getNAS_ip();
                Integer alltime = bidr.getTime_deration();
                //sql语句
                String sql = "INSERT INTO briup_project_2.t_detail VALUES (?,?,?,?,?,?);";

                //
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,aaa_name);
                pstmt.setString(2,login_ip);
                pstmt.setTimestamp(3,login);
                pstmt.setTimestamp(4,logout);
                pstmt.setString(5,nas_ip);
                pstmt.setInt(6,alltime);

                pstmt.execute();
            }
            loggerimpl.info("its over!!!");
            DBUtils.close(null,pstmt,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public Collection<BIDR> revicer() throws Exception {
        loggerimpl.info("正在接收客户端的数据！！！");
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objin = new ObjectInputStream(inputStream);
        Collection con = (Collection)objin.readObject();
        loggerimpl.info("收集完毕，共"+con.size()+"条数据！！！");
        return con;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void init(Properties properties) {

    }
}
