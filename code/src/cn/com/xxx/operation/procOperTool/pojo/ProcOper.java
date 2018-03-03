package cn.com.xxx.operation.procOperTool.pojo;

/**
 * 操作类
 * @author zh
 * 
 */
public class ProcOper
{
    String name;
    String operator;

    public ProcOper()
    {

    }

    public ProcOper(final String name, final String operator)
    {
        this.name = name;
        this.operator = operator;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(final String operator)
    {
        this.operator = operator;
    }

    @Override
    public String toString()
    {
        return "ProcOper [name=" + name + ", operator=" + operator + "]";
    }

}
