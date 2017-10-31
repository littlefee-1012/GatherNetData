package com.briup.GatherImpl;

import com.briup.LoggerImpl.LoggerImpl;
import com.briup.util.BIDR;
import com.briup.utils.DBUtils;
import com.briup.woss.client.Gather;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class GatherImpl implements Gather{
    private BufferedReader brd ;
    private Properties properties;
    //这里的初始化应该放在ini方法中最佳
    private Collection<BIDR> list = new ArrayList();
    private Map<String,String> map = new HashMap();
    private LoggerImpl loggerimpl = new LoggerImpl();


    /*public static void main(String[] args) throws Exception {
        new GatherImpl().gather();
        //DBUtils.getConn();
    }*/
    public Collection<BIDR> gather() throws Exception {

        loggerimpl.info("收集模块启动，正在收集数据！！！");
        brd = new BufferedReader(new InputStreamReader(new FileInputStream("BRIUP_0010/file/radwtmp_test")));
        String line = null;
        while((line=brd.readLine())!=null){
            String[] firstsplit = line.split("[|]");
            if(firstsplit[2].equals("7")){
                map.put(firstsplit[4],line);
            }
            if(firstsplit[2].equals("8")){
                //要注意没有上线（7），但是却有下线（8）的数据，
                String data7 = map.get(firstsplit[4]);
                map.remove(firstsplit[4]);
                String[] secondsplit = data7.split("[|]");
                String[] threesplit = secondsplit[0].split("[#]");
                String aaa_name = threesplit[1];
                String login_ip = secondsplit[1];
                Timestamp login = new Timestamp(Long.parseLong(secondsplit[3]));
                Timestamp logout = new Timestamp(Long.parseLong(firstsplit[3]));
                String nas_ip = firstsplit[4];
                Integer alltime = Integer.parseInt((Long.parseLong(firstsplit[3])-Long.parseLong(secondsplit[3]))+"");


                list.add(new BIDR(aaa_name,login_ip,login,logout,nas_ip,alltime));


            }

        }
        //这里调用备份模块进行文件的备份 map


        loggerimpl.info("还有"+map.size()+"条数据未匹配");
//        new LoggerImpl().debug("asdasdasdsad");
//        System.out.println("thats gather datas "+list.size());
        loggerimpl.info("收集数据完毕，共收集到"+list.size()+"条数据！！！");
        if(brd!=null){
            brd.close();
        }
        loggerimpl.info("正在准备以集合形式返回数据，以BIDR对象形式存储！！！");
        return list;

    }

    @Override
    public void init(Properties properties) {

    }
}
