package cn.com.xxx.operation.procOperUtil.funcManage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.com.xxx.common.msg.ResponseNode;
import cn.com.xxx.common.msg.XMLNode;
import cn.com.xxx.operation.procOperTool.api.ProcOperTool;
import cn.com.xxx.operation.procOperTool.factory.ProcOperToolFactory;
import cn.com.xxx.operation.procOperTool.pojo.ProcOper;
import cn.com.xxx.operation.procOperTool.pojo.ProcQuery;
import cn.com.xxx.operation.procOperTool.stringInfo.StringInfo;

import com.una.common.log.DebugLogOper;

/**
 * 功能方法管理类
 * @author zh
 * 
 */
public class FuncManage
{
    DebugLogOper debugLog = new DebugLogOper("FuncManage.class");

    /**
     * 查询进程状态
     * @return 查询结果XMLNode
     */
    public XMLNode qureyProcState()
    {
        final ProcOperToolFactory factory = new ProcOperToolFactory();
        final ArrayList<ProcQuery> stateList = getProcName(StringInfo.ProcessName);
        if (stateList == null)
        {
            debugLog.writeLogAlways(DebugLogOper.ERROR, "获取进程名列表失败!");
            final ResponseNode qureyNode = new ResponseNode(StringInfo.oper,
                    StringInfo.ERRNO_QUERY_PROCESS_FAILURE, "获取进程名列表失败!");
            return qureyNode;
        }
        final ArrayList<ProcQuery> stateList2 = removeUnExisted(stateList);
        for (int i = 0; i < stateList2.size(); i++)
        {
            final ProcQuery q = stateList2.get(i);
            final ProcOperTool operTool = factory.createProTool(q.getName());
            final String state = operTool.query();
            q.setState(state);
        }
        final XMLNode qureyNode = getQueryNode(stateList2);
        return qureyNode;
    }

    /**
     * 操作进程状态
     * @param 操作列表 operList
     * @return 操作结果 ResponseNode
     */
    public ResponseNode operProcState(final ProcOper p)
    {
        ResponseNode operNode = null;
        try
        {
            final ProcOperToolFactory factory = new ProcOperToolFactory();
            final ProcOperTool operTool = factory.createProTool(p.getName());
            final String result = operTool.oper(p.getOperator());
            if (result.equals(StringInfo.failure))
            {
                throw new Exception("operator failure");
            }
        }
        catch (final Exception e)
        {
            debugLog.writeExceptionAlways(e);
            operNode = new ResponseNode(StringInfo.oper,
                    StringInfo.ERRNO_OPER_PROCESS_FAILURE, e.toString());
            return operNode;
        }
        operNode = new ResponseNode(StringInfo.oper);
        return operNode;
    }

    /**
     * 获取进程名
     * @param 配置文件xml
     * @return 进程名列表ArrayList<String[]>
     */
    @SuppressWarnings("unchecked")
    public ArrayList<ProcQuery> getProcName(final String xml)

    {
        final ArrayList<ProcQuery> stateList = new ArrayList<ProcQuery>();
        try
        {
            final File file = new File(xml);
            final SAXReader reader = new SAXReader();
            final Document document = reader.read(file);
            final Element root = document.getRootElement();
            final List<Element> list = root.elements(StringInfo.process);
            for (int i = 0; i < list.size(); i++)
            {
                final Element e = list.get(i);
                final ProcQuery q = new ProcQuery(e.element(StringInfo.name).getText(),
                        null);
                stateList.add(q);
            }
        }
        catch (final DocumentException e)
        {
            debugLog.writeExceptionAlways(e);
            return null;
        }
        return stateList;
    }

    /**
     * 查看文件是否存在
     * @param 文件路径 filepath
     * @return 存在结果 boolean
     */
    public boolean checkExist(final String filepath)
    {
        final File file = new File(filepath);
        if (file.exists())
        {
            return true;
        }
        return false;
    }

    /**
     * 移除不存在的进程操作
     * @param 状态操作列表 stateList
     */
    public ArrayList<ProcQuery> removeUnExisted(final ArrayList<ProcQuery> stateList)
    {
        for (int i = 0; i < stateList.size();)
        {
            final ProcQuery q = stateList.get(i);
            if (q.getName().equals(StringInfo.IndexServer))
            {
                if (!checkExist(StringInfo.indexserver_sh))
                {
                    stateList.remove(i);
                }
                else
                {
                    i++;
                }
            }
            else if (q.getName().equals(StringInfo.ProxyFileServer))
            {
                if (!checkExist(StringInfo.proxyfileserver_sh))
                {
                    stateList.remove(i);
                }
                else
                {
                    i++;
                }
            }
            else if (q.getName().equals(StringInfo.VMServer))
            {
                if (!checkExist(StringInfo.vmserver_sh))
                {
                    stateList.remove(i);
                }
                else
                {
                    i++;
                }
            }
            else
            {
                i++;
            }
        }
        return stateList;
    }

    /**
     * 获取查询结果
     * @param 状态查询列表 stateList
     * @return 结果 XMLNode
     */
    public XMLNode getQueryNode(final ArrayList<ProcQuery> stateList)
    {
        final XMLNode queryNode = new XMLNode(StringInfo.query);
        for (int i = 0; i < stateList.size(); i++)
        {
            final ProcQuery q = stateList.get(i);
            final XMLNode process = new XMLNode(StringInfo.process);
            queryNode.addChild(process);
            final XMLNode name = new XMLNode(StringInfo.name, q.getName());
            final XMLNode state = new XMLNode(StringInfo.state, q.getState());
            process.addChild(name);
            process.addChild(state);
        }
        return queryNode;
    }
}