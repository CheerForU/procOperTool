# procOperTool

1、对服务进程进行监测，提供监测状态（开启/关闭）

2、提供按钮进行开启/关闭/重启

3、提供按钮支持Tomcat, Unabackup服务重启

4、服务包括 MTS（主控服务器） MDS（本地介质服务器）RsyncServer（异地同步服务器） IndexServer（索引服务器）ProxyFileServer（代理文件服务器） VMServer（虚拟化服务器） Agent（本地代理）Tomcat（网页服务器）

其中IndexServer、ProxyFileServer、VMServer不一定存在该服务，以/xxx/unabackup/bin目录下脚本Component_indexserver.sh、Component_proxyfileserver.sh、Component_vmserver.sh来确认