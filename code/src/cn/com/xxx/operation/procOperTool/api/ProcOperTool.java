package cn.com.xxx.operation.procOperTool.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.com.xxx.operation.procOperTool.stringInfo.StringInfo;

import com.xxx.common.log.DebugLogOper;

/**
 * 进程操作基类
 * @author zh
 * 
 */
public abstract class ProcOperTool
{
    DebugLogOper debugLog = new DebugLogOper("ProcOperTool.class");

    protected String result;

    /**
     * 进程查询
     * @return 查询结果String
     */
    public abstract String query();

    /**
     * 进程查询开启
     */
    public abstract String start();

    /**
     * 进程关闭
     */
    public abstract String close();

    /**
     * 进程重启
     * @throws Exception
     */
    public abstract String restart() throws Exception;

    /**
     * 进程操作
     * @return 操作结果String
     */
    public String oper(final String operator)
    {
        if (operator.equals(StringInfo.start))
        {
            try
            {
                result = start();
            }
            catch (final Exception e)
            {
                debugLog.writeExceptionAlways(e);
                result = StringInfo.failure;
            }
            result = StringInfo.success;
        }
        else if (operator.equals(StringInfo.close))
        {
            try
            {
                result = close();
            }
            catch (final Exception e)
            {
                debugLog.writeExceptionAlways(e);
                result = StringInfo.failure;
            }
            result = StringInfo.success;
        }
        else if (operator.equals(StringInfo.restart))
        {
            try
            {
                result = restart();
            }
            catch (final Exception e)
            {
                debugLog.writeExceptionAlways(e);
                result = StringInfo.failure;
            }
            result = StringInfo.success;
        }
        else
        {
            return null;
        }
        return result;
    }

    /**
     * 执行语句方法(返回结果和内容)
     * @param 操作语句String[]
     */
    public String[] execCont(final String[] execute)
    {
        // 结果数组，第一个值存放语句运行结果，第二个值存放语句输出结果
        final String[] result = new String[2];
        InputStream is = null;
        InputStreamReader ir = null;
        BufferedReader br = null;
        String line = null;
        try
        {
            final Process p = Runtime.getRuntime().exec(execute,
                    null,
                    StringInfo.filepath);
            result[0] = String.valueOf(p.waitFor());
            is = p.getInputStream();
            ir = new InputStreamReader(is);
            br = new BufferedReader(ir);
            while ((line = br.readLine()) != null)
            {
                result[1] = line;
            }
        }
        catch (final Exception e)
        {
            debugLog.writeExceptionAlways(e);
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
                if (ir != null)
                {
                    ir.close();
                }
                if (is != null)
                {
                    is.close();
                }
            }
            catch (final Exception e)
            {
                debugLog.writeExceptionAlways(e);
            }
        }
        return result;
    }

    /**
     * 执行语句方法(返回结果)
     * @param 操作语句String[]
     */
    public String execRes(final String[] execute)
    {
        String result = null;

        try
        {
            final Process p = Runtime.getRuntime().exec(execute,
                    null,
                    StringInfo.filepath);
            result = String.valueOf(p.waitFor());

        }
        catch (final Exception e)
        {
            debugLog.writeExceptionAlways(e);
        }

        return result;
    }

    public String setQueryResult(final String result)
    {
        final String res;
        if (result.equals("0"))
        {
            res = StringInfo.running;
        }
        else
        {
            res = StringInfo.stopping;
        }
        return res;
    }

    public String setOperResult(final String result)
    {
        final String res;
        if (result.equals("0"))
        {
            res = StringInfo.success;
        }
        else
        {
            res = StringInfo.failure;
        }
        return res;
    }
}
