package cn.com.xxx.operation.procOperTool.impl;

import cn.com.xxx.operation.procOperTool.api.ProcOperTool;
import cn.com.xxx.operation.procOperTool.stringInfo.StringInfo;

/**
 * Tomcat进程操作类，继承基类ProcOperTool
 * @author zh
 * 
 */
public class TomcatImpl extends ProcOperTool
{
    @Override
    public String query()
    {
        final String[] command = { "/bin/sh", "-c",
                "ps -ef | grep " + StringInfo.Tomcat_procName + " | grep -v grep" };
        return setQueryResult(execRes(command));
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
    public String restart() throws Exception
    {
        final String[] command1 = { "/bin/sh", "-c", StringInfo.Tomcat_close };
        final String[] command2 = { "/bin/sh", "-c", StringInfo.Tomcat_start };
        result = setOperResult(execRes(command1));
        if (result.equals(StringInfo.success))
        {

            while (true)
            {
                result = query();
                if (result.equals(StringInfo.stopping))
                {
                    result = setOperResult(execRes(command2));
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
