package com.briup.ClientImpl;

import com.briup.GatherImpl.GatherImpl;
import com.briup.LoggerImpl.LoggerImpl;
import com.briup.util.BIDR;
import com.briup.woss.client.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class ClientImpl implements Client{
    private static String ip = "127.0.0.1";
    private static int port = 8888;
    private static Socket socket = null;
    private static LoggerImpl loggerimpl = new LoggerImpl();

    public static void main(String[] args) {
        loggerimpl.info("客户端启动，准备收集数据！！！");
        try {
            socket = new Socket(ip,port);
            GatherImpl gi = new GatherImpl();
            Collection<BIDR> collection = gi.gather();
            //ArrayList al = new ArrayList(collection);
            //System.out.println(collection.size());
            loggerimpl.info("准备往服务器发送数据");
            new ClientImpl().send(collection);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Collection<BIDR> collection) throws Exception {
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objout = new ObjectOutputStream(outputStream);
        objout.writeObject(collection);
        objout.flush();
        loggerimpl.info("发送成功，共"+collection.size()+"条数据");
        if(outputStream!=null){
            outputStream.close();
        }
        if(objout!=null){
            objout.close();
        }
    }

    @Override
    public void init(Properties properties) {

    }
}
