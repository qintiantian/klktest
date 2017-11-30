package klktest.myweb.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import klktest.myweb.entity.BaseEntity;

@Component
public class BaseEntityIdSetter {

	@Autowired
	private IncrementIdGeneratorImpl idGenerator;
	
	public void setIds(BaseEntity[] entitys) {
		long[] ids = 
				idGenerator.genIds(entitys[0].getTableName(), entitys.length);
		if(entitys.length != ids.length)
			throw new IllegalArgumentException("参数错误");
		for(int i=0; i<entitys.length; i++) {
			entitys[i].setPrimaryKey(ids[i]);
		}
	}
	
	public void setId(BaseEntity entity) {
		long id = idGenerator.genId(entity.getTableName());
		entity.setPrimaryKey(id);
	}
}
