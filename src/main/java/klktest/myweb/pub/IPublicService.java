package klktest.myweb.pub;

import java.io.Serializable;

import klktest.myweb.entity.BaseEntity;

public interface IPublicService {

	public <T extends BaseEntity> void save(T entity);
	
	public <T extends BaseEntity> void insert(T... entitys);
	
	public <T extends BaseEntity> void update(T entity);
	
	public <T extends BaseEntity> void update(T... entitys);
	
	public <T extends BaseEntity> void delete(T entity);
	
	public <T extends BaseEntity> void delete(T... entitys);
	
	public <T extends BaseEntity> T findByPk(Class<T> clazz, Serializable pk);
	
}
