package com.actions;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.service.spi.ServiceException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.DateConverter;

public abstract class BaseAction extends ActionSupport
{
    private static Long defaultLong = null;
    
    private final static String ACTION_MESSAGE_KEY = "action_message_key";

    private boolean hasMsg;
    
    
    protected int page;
    
    protected int rows;
    
    static
    {
        ConvertUtils.register(new DateConverter(), Date.class);
        ConvertUtils.register(new DateConverter(), String.class);
        ConvertUtils.register(new LongConverter(defaultLong), Long.class);
        ConvertUtils.register(new IntegerConverter(defaultLong), Integer.class);
    }

    protected transient final Log logger = LogFactory.getLog(getClass());

    protected ActionContext getContext()
    {
        /*
         * ServletActionContext is subclasses ActionContext which provides access to things like the action name, value stack, etc. This class adds
         * access to web objects like servlet parameters, request attributes and things like the HTTP session
         */
        return ServletActionContext.getContext();

        // return ActionContext.getContext();
    }

    protected Map getSession()
    {
        return getContext().getSession();
    }

    protected Map getApplication()
    {
        return getContext().getApplication();
    }

    public String getActionName()
    {
        return getContext().getName();
    }

    public String getNamespace()
    {
        return ServletActionContext.getActionMapping().getNamespace();
    }

    protected HttpServletRequest getRequest()
    {
        return ServletActionContext.getRequest();
    }

    protected HttpServletResponse getResponse()
    {
        return ServletActionContext.getResponse();
    }



    /**
     * 保存提示信息
     * 
     * @param msg
     */
    protected void addMsg(String msg)
    {
        addActionMessage("信息：" + msg);
    }

    /**
     * 生成异常描述信息
     * 
     * @param e
     * @param msg
     * @return
     */
    protected String buildError(Exception e, String msg)
    {
        StringBuffer error = new StringBuffer();
        error.append(msg).append(":");
        if (e != null)
        {
            error.append(e.getMessage());
        }
        return error.toString();
    }

    public void appendMsg(String msg)
    {
        if (StringUtils.isNotBlank(msg))
        {
            getSession().put(ACTION_MESSAGE_KEY, msg);
        }
    }

    protected void initActionMsg()
    {
        String msg = (String) getSession().get(ACTION_MESSAGE_KEY);
        if (StringUtils.isNotBlank(msg))
        {
            addMsg(msg);
        }
    }

    protected void cleanActionMsg()
    {
        getSession().remove(ACTION_MESSAGE_KEY);
        clearErrorsAndMessages();
    }

    /**
     * 输出结果字符串
     * 
     * @param result
     * @return
     * 
     * @date Mar 1, 2010 11:28:47 AM
     */
    protected String outputResult(String result)
    {
        try
        {
            getResponse().setContentType("Content-type:html/text;charset=gbk");
            // getResponse().getOutputStream().print(result);
            getResponse().getOutputStream().print(new String(result.getBytes("gbk"), "ISO-8859-1"));
        }
        catch(IOException e)
        {
            String errInfo = "接口输出数据失败：";
            logger.error(errInfo, e);
            throw new ServiceException(errInfo, e);
        }

        return null;
    }

    protected String buildParams(Map<String, String[]> map)
    {
        StringBuilder sb = new StringBuilder();

        if (map != null)
        {
            for (Entry<String, String[]> entry : map.entrySet())
            {
                try
                {
                    Object[] arr = (Object[]) entry.getValue();
                    sb.append(entry.getKey()).append(":").append(StringUtils.join(arr));
                    sb.append(";");
                }
                catch(Exception e)
                {
                    logger.warn("打印系统请求参数出错 " + e.getMessage(), e);
                }
            }
        }

        return sb.toString();
    }
        
    public boolean hasMsg()
    {
        return hasMsg;
    }

    public void setHasMsg(boolean hasMsg)
    {
        this.hasMsg = hasMsg;
    }
    
    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }
    
}
