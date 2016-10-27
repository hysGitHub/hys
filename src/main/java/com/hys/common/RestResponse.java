package com.hys.common;



import java.io.Serializable;
import java.util.Date;

public class RestResponse<T> implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Date timestamp=new Date();
    
    private int status=200;//设置的默认状态值
    
    private String message;
    
    private T result;

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
    
    
}
