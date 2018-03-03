package cn.com.xxx.operation.procOperTool.impl;

import cn.com.xxx.operation.procOperTool.api.ProcOperTool;
import cn.com.xxx.operation.procOperTool.stringInfo.StringInfo;

/**
 * MTS进程操作类，继承基类ProcOperTool
 * @author zh
 * 
 */
public class MTSImpl extends ProcOperTool
{
    @Override
    public String query()
    {
        final String[] command = { "/bin/sh", "-c",
                "ps -ef | grep " + StringInfo.MTS_procName + " | grep -v grep" };
        return setQueryResult(execRes(command));
    }

    @Override
    public String start()
    {
        final String[] command = { "/bin/sh", "-c", StringInfo.MTS_start };
        return setOperResult(execRes(command));
    }

    @Override
    public String close()
    {
        final String[] command1 = {
                "/bin/sh",
                "-c",
                "ps -ef | grep " + StringInfo.MTS_procName
                        + " | grep -v grep | awk '{print $2}' | head -1" };
        final String[] result = execCont(command1);
        if (setOperResult(result[0]).equals(StringInfo.success))
        {
            final String pid = result[1];
            final String[] command2 = { "/bin/sh", "-c", "kill -15 " + pid };
            return setOperResult(execRes(command2));
        }
        else
        {
            return StringInfo.failure;
        }

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
