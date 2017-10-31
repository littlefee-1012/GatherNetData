package com.briup.ConfigurationImpl;

import com.briup.GatherImpl.GatherImpl;
import com.briup.util.BackUP;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.client.Client;
import com.briup.woss.client.Gather;
import com.briup.woss.server.DBStore;
import com.briup.woss.server.Server;

public class ConfigurationImpl implements Configuration{
    @Override
    public Logger getLogger() throws Exception {
        return null;
    }

    @Override
    public BackUP getBackup() throws Exception {
        return null;
    }

    @Override
    public Gather getGather() throws Exception {
        return new GatherImpl();
    }

    @Override
    public Client getClient() throws Exception {
        return null;
    }

    @Override
    public Server getServer() throws Exception {
        return null;
    }

    @Override
    public DBStore getDBStore() throws Exception {
        return null;
    }
}
