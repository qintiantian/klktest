package klktest.myweb.pub;

import java.io.Serializable;

import klktest.myweb.anonation.OprationLog;
import klktest.myweb.entity.BaseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("publicService")
@OprationLog(isRecord=true, oprType="db")
public class PublicServiceImpl implements IPublicService {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private BaseEntityIdSetter idSetter;
	
	private static final int BATCH_SIZE = 50;

	public <T extends BaseEntity> void save(T entity) {
		beforeSave(entity);
		hibernateTemplate.save(entity);
		afterSave(entity);
	}


	public <T extends BaseEntity> void insert(T... entitys) {
		beforeInsert(entitys);
		for(int i=0; i<entitys.length; i++) {
			hibernateTemplate.save(entitys[i]);
			if(i%BATCH_SIZE == 0) {
				hibernateTemplate.flush();
				hibernateTemplate.clear();
			}
		}
		afterInsert(entitys);
	}
	
	public <T extends BaseEntity> void update(T entity) {
		beforeUpdate(entity);
		hibernateTemplate.update(entity);
		afterUpdate(entity);
	}


	public <T extends BaseEntity> void update(T... entitys) {
		int i1=1/0;
		beforeUpdate(entitys);
		for(int i=0; i<entitys.length; i++) {
			hibernateTemplate.update(entitys[i]);
			if(i%BATCH_SIZE == 0) {
				hibernateTemplate.flush();
				hibernateTemplate.clear();
			}
		}
		afterUpdate(entitys);
		
	}
	
	public <T extends BaseEntity> void delete(T entity) {
		if(entity == null)
			return;
		hibernateTemplate.delete(entity);
	}

	public <T extends BaseEntity> void delete(T... entitys) {
		for(int i=0; i<entitys.length; i++) {
			hibernateTemplate.delete(entitys[i]);
			if(i%BATCH_SIZE == 0) {
				hibernateTemplate.flush();
				hibernateTemplate.clear();
			}
		}
	}

	public <T extends BaseEntity> T findByPk(Class<T> entity, Serializable pk) {
		return (T) hibernateTemplate.get(entity, pk);
	}
	
	@OprationLog(isRecord=true, oprType="db")
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	protected <T extends BaseEntity> void beforeSave(T entity) {
		idSetter.setId(entity);
	}
	
	protected <T extends BaseEntity> void afterSave(T entity) {
	}
	
	protected <T extends BaseEntity> void beforeInsert(T... entitys) {
		idSetter.setIds(entitys);
	}
	
	protected <T extends BaseEntity> void afterInsert(T... entitys) {
	}
	
	protected <T extends BaseEntity> void beforeUpdate(T entity) {
		entity.setTs();
	}
	
	protected <T extends BaseEntity> void afterUpdate(T entity) {
	}
	
	protected <T extends BaseEntity> void beforeUpdate(T... entitys) {
		for(T entity:entitys) {
			entity.setTs();
		}
	}
	
	protected <T extends BaseEntity> void afterUpdate(T... entitys) {
	}
	
}
