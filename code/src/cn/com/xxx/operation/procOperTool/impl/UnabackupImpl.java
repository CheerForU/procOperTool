package cn.com.xxx.operation.procOperTool.impl;

import cn.com.xxx.operation.procOperTool.api.ProcOperTool;
import cn.com.xxx.operation.procOperTool.stringInfo.StringInfo;

/**
 * Unabackup操作类，继承基类ProcOperTool
 * @author zh
 * 
 */
public class UnabackupImpl extends ProcOperTool
{
    @Override
    public String query()
    {
        return null;
    }

    @Override
    public String start()
    {
        return null;

    }

    @Override
    public String close()
    {
        return null;
    }

    @Override
    public String restart()
    {
        final String[] command = { "/bin/sh", "-c", StringInfo.Unabackup_restart };
        return setOperResult(execRes(command));
    }
}
