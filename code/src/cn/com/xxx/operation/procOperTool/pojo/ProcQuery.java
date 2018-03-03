package cn.com.xxx.operation.procOperTool.pojo;

/**
 * 查询类
 * @author zh
 * 
 */
public class ProcQuery
{
    String name;
    String state;

    public ProcQuery()
    {
    }

    public ProcQuery(final String name, final String state)
    {
        this.name = name;
        this.state = state;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getState()
    {
        return state;
    }

    public void setState(final String state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "ProcQuery [name=" + name + ", state=" + state + "]";
    }

}
