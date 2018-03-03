package cn.com.xxx.operation.procOperTool.stringInfo;

import java.io.File;

/**
 * 字符串常量管理类
 * @author zh
 * 
 */
public class StringInfo
{
    // properties
    public static String ProcessName = "../conf/processName.xml";
    public static String log4jProperties = "../conf/log4j_CheckProcessStateTool.xml";

    // localId
    public static String localId = "cpst";

    // node
    public static String queryMSG = "cpst.process.query";
    public static String operMSG = "cpst.process.oper";
    public static String query = "query";
    public static String oper = "oper";
    public static String process = "process";
    public static String name = "name";
    public static String operator = "operator";
    public static String state = "state";
    public static String result = "result";

    // process
    public static String MTS = "MTS";
    public static String MDS = "MDS";
    public static String RsyncServer = "RsyncServer";
    public static String IndexServer = "IndexServer";
    public static String ProxyFileServer = "ProxyFileServer";
    public static String VMServer = "VMServer";
    public static String Agent = "Agent";
    public static String Tomcat = "Tomcat";
    public static String Unabackup = "Unabackup";

    // procName
    public static String MTS_procName = "cn.com.xxx.mts";
    public static String MDS_procName = "mdsserver";
    public static String RsyncServer_procName = "cn.com.xxx.rsync.server";
    public static String IndexServer_procName = "FileIndexServer";
    public static String ProxyFileServer_procName = "proxyfileserver.jar";
    public static String VMServer_procName = "VMwareServerAgent";
    public static String Agent_procName = "uagent";
    public static String Tomcat_procName = "tomcat";

    // state
    public static String running = "running";
    public static String stopping = "stopping";
    public static String success = "success";
    public static String failure = "failure";

    // filepath
    public static File filepath = new File("/xxx/unabackup/bin");

    // operator
    public static String start = "1";
    public static String close = "2";
    public static String restart = "3";
    public static String Agent_start = "nohup ../../uagent/bin/agent_start.sh mts >/dev/null 2>&1 &";
    public static String Agent_close = "../../uagent/bin/agent_stop.sh";
    public static String IndexServer_start = "./Component_indexserver.sh start";
    public static String IndexServer_close = "./Component_indexserver.sh stop";
    public static String MDS_start = "./start_mds.sh";
    public static String MDS_close = "./stop_mds.sh";
    public static String MTS_start = "nohup ./start_mts.sh &";
    public static String ProxyFileServer_start = "./Component_proxyfileserver.sh start";
    public static String ProxyFileServer_close = "./Component_proxyfileserver.sh stop";
    public static String RsyncServer_start = "nohup ./start_rsyncserver.sh &";
    public static String Tomcat_start = "./start_tomcat.sh";
    public static String Tomcat_close = "./shutdown_tomcat.sh";
    public static String VMServer_start = "./Component_vmserver.sh start";
    public static String VMServer_close = "./Component_vmserver.sh stop";
    public static String Unabackup_restart = "service unabackup restart";

    // sh脚本
    public static String indexserver_sh = "../bin/Component_indexserver.sh";
    public static String proxyfileserver_sh = "../bin/Component_proxyfileserver.sh";
    public static String vmserver_sh = "../bin/Component_vmserver.sh";

    // 错误码
    public static final String ERRNO_QUERY_PROCESS_FAILURE = "operation.query.process.failure";
    public static final String ERRNO_OPER_PROCESS_FAILURE = "operation.oper.process.failure";

}
