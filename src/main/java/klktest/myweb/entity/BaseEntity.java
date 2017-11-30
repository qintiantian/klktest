package klktest.myweb.entity;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public abstract String getPrimaryKey();
	
	public abstract String getTableName();
	
	public abstract void setPrimaryKey(long pk);
	
	public Serializable getPk() {
		if(StringUtils.isEmpty(getPrimaryKey()))
			return null;
		try {
			return BeanUtils.getProperty(this, getPrimaryKey());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setTs() {
		try {
			BeanUtils.setProperty(this, "ts", new Date());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
