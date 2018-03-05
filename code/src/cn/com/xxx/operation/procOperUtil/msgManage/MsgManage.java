package cn.com.xxx.operation.procOperUtil.msgManage;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.xxx.common.msg.MessageConstructor;
import cn.com.xxx.common.msg.ResponseNode;
import cn.com.xxx.common.msg.XMLNode;
import cn.com.xxx.common.msg.XmlMessageListener;
import cn.com.xxx.mdp.MdpUtil;
import cn.com.xxx.mdp.model.Message;
import cn.com.xxx.operation.procOperTool.pojo.ProcOper;
import cn.com.xxx.operation.procOperTool.stringInfo.StringInfo;
import cn.com.xxx.operation.procOperUtil.funcManage.FuncManage;

import com.una.common.log.DebugLogOper;

/**
 * 消息管理类
 * @author zh
 * 
 */
public class MsgManage
{
    static DebugLogOper debugLog = new DebugLogOper(MsgManage.class);

    public static void main(final String[] args)
    {
        debugLog.setConfig(StringInfo.log4jProperties);
        try
        {
            init();
            while (true)
            {
                Thread.sleep(30000);
            }
        }
        catch (final Exception e)
        {
            debugLog.writeExceptionAlways(e);
        }
        destroy();
        DebugLogOper.close_timer();
    }

    /**
     * 消息初始化（开启，消息订阅）
     */
    public static void init()
    {
        System.setProperty("localId", StringInfo.localId);
        MdpUtil.start();
        MdpUtil.subscribe(StringInfo.queryMSG, new qureyState());
        MdpUtil.subscribe(StringInfo.operMSG, new operState());
    }

    /**
     * 消息销毁（消息反订阅，关闭）
     */
    public static void destroy()
    {
        MdpUtil.unsubscribe(StringInfo.queryMSG);
        MdpUtil.unsubscribe(StringInfo.operMSG);
        MdpUtil.stop();
    }

    /**
     * 查询进程状态类
     */
    private static final class qureyState extends XmlMessageListener
    {
        // 接收到消息进行handle操作
        @Override
        protected void handle(final Message message, final Document document)
        {
            final FuncManage f = new FuncManage();
            final XMLNode qureyNode = f.qureyProcState();
            if (qureyNode == null)
            {
                debugLog.writeLogAlways(DebugLogOper.ERROR, "查询进程状态失败");
            }
            MdpUtil.reply(message, MessageConstructor.constructMsg(qureyNode));

        }
    }

    /**
     * 操作进程状态类
     */
    private static final class operState extends XmlMessageListener
    {
        // 接收到消息进行handle操作
        @SuppressWarnings("unchecked")
        @Override
        protected void handle(final Message message, final Document document)
        {
            final Element oper = document.getRootElement();
            final Element process = oper.element(StringInfo.process);
            final ProcOper p = new ProcOper(process.element(StringInfo.name).getText(),
                    process.element(StringInfo.operator).getText());
            final FuncManage f = new FuncManage();
            final ResponseNode operNode = f.operProcState(p);
            if (operNode == null)
            {
                debugLog.writeLogAlways(DebugLogOper.ERROR, "操作进程状态失败");
            }
            MdpUtil.reply(message, MessageConstructor.constructMsg(operNode));
        }
    }
}
