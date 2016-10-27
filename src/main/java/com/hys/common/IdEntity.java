package com.hys.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * @title IdEntity基类
 * @description
 */
@MappedSuperclass
public abstract class IdEntity implements Serializable
{
	private static final long serialVersionUID = -6878400007776594251L;
	
	@Id
	@Column(name = "id", length = 32)
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public boolean instanceOf(String className)
    {
        Class<?> thisClass = this.getClass();
        boolean rt = false;
        while (true)
        {
            if (thisClass == null)
            {
                break;
            }
            if (thisClass.getName().equals(className) || thisClass.getSimpleName().equals(className))
            {
                rt = true;
                break;
            }
            thisClass = thisClass.getSuperclass();
        }
        return rt;
    }

    public boolean instanceOf(Class<?> c)
    {
        Class<?> thisClass = this.getClass();
        boolean rt = false;
        while (true)
        {
            if (thisClass == null)
            {
                break;
            }
            if (thisClass == c)
            {
                rt = true;
                break;
            }
            thisClass = thisClass.getSuperclass();
        }
        return rt;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        IdEntity rhs = (IdEntity) object;
        return new EqualsBuilder().append(this.id, rhs.getId()).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(-1757276003, -1564204369).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return new ToStringBuilder(this).append("id", this.id).toString();
    }

}
