package cn.com.xxx.operation.procOperTool.impl;

import cn.com.xxx.operation.procOperTool.api.ProcOperTool;
import cn.com.xxx.operation.procOperTool.stringInfo.StringInfo;

/**
 * Agent进程操作类，继承基类ProcOperTool
 * @author zh
 * 
 */
public class AgentImpl extends ProcOperTool
{
    @Override
    public String query()
    {
        final String[] command = { "/bin/sh", "-c",
                "ps -ef | grep " + StringInfo.Agent_procName + " | grep -v grep" };
        return setQueryResult(execRes(command));
    }

    @Override
    public String start()
    {
        final String[] command = { "/bin/sh", "-c", StringInfo.Agent_start };
        return setOperResult(execRes(command));
    }

    @Override
    public String close()
    {
        final String[] command = { "/bin/sh", "-c", StringInfo.Agent_close };
        return setOperResult(execRes(command));
    }

    @Override
    public String restart() throws Exception
    {
        result = close();
        if (result.equals(StringInfo.success))
        {
            while (true)
            {
                result = query();
                if (result.equals(StringInfo.stopping))
                {
                    result = start();
                    return result;
                }
                else
                {
                    Thread.sleep(1000);
                }
            }

        }
        return result;

    }
}
