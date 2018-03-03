package cn.com.xxx.operation.procOperTool.factory;

import cn.com.xxx.operation.procOperTool.api.ProcOperTool;
import cn.com.xxx.operation.procOperTool.impl.AgentImpl;
import cn.com.xxx.operation.procOperTool.impl.IndexServerImpl;
import cn.com.xxx.operation.procOperTool.impl.MDSImpl;
import cn.com.xxx.operation.procOperTool.impl.MTSImpl;
import cn.com.xxx.operation.procOperTool.impl.ProxyFileServerImpl;
import cn.com.xxx.operation.procOperTool.impl.RsyncServerImpl;
import cn.com.xxx.operation.procOperTool.impl.TomcatImpl;
import cn.com.xxx.operation.procOperTool.impl.UnabackupImpl;
import cn.com.xxx.operation.procOperTool.impl.VMServerImpl;
import cn.com.xxx.operation.procOperTool.stringInfo.StringInfo;

/**
 * 进程操作工厂类
 * @author zh
 * 
 */
public class ProcOperToolFactory
{
    /**
     * createProManage 创建进程操作实例
     * @param proName
     * @return 进程操作实例
     */
    public ProcOperTool createProTool(final String proName)
    {
        if (proName.equals(StringInfo.MTS))
        {
            return new MTSImpl();
        }
        if (proName.equals(StringInfo.MDS))
        {
            return new MDSImpl();
        }
        if (proName.equals(StringInfo.RsyncServer))
        {
            return new RsyncServerImpl();
        }
        if (proName.equals(StringInfo.IndexServer))
        {
            return new IndexServerImpl();
        }
        if (proName.equals(StringInfo.ProxyFileServer))
        {
            return new ProxyFileServerImpl();
        }
        if (proName.equals(StringInfo.VMServer))
        {
            return new VMServerImpl();
        }
        if (proName.equals(StringInfo.Agent))
        {
            return new AgentImpl();
        }
        if (proName.equals(StringInfo.Tomcat))
        {
            return new TomcatImpl();
        }
        if (proName.equals(StringInfo.Unabackup))
        {
            return new UnabackupImpl();
        }
        return null;
    }
}