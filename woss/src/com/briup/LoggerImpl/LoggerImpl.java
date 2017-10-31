package com.briup.LoggerImpl;

import com.briup.util.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

public class LoggerImpl implements Logger{
    private static org.apache.log4j.Logger rootLogger = null;

    static {
        PropertyConfigurator.configure("BRIUP_0010/conf/log.properties");
        //不应该在这里初始化，在ini中初始化
        rootLogger = org.apache.log4j.Logger.getRootLogger();
    }
    @Override
    public void debug(String s) {
        rootLogger.debug(s);
    }

    @Override
    public void info(String s) {
        rootLogger.info(s);
    }

    @Override
    public void warn(String s) {
        rootLogger.warn(s);
    }

    @Override
    public void error(String s) {
        rootLogger.error(s);
    }

    @Override
    public void fatal(String s) {
        rootLogger.fatal(s);
    }

    @Override
    public void init(Properties properties) {

    }
}
