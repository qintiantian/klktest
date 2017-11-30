package klktest.myweb.pub;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class IncrementIdGeneratorImpl implements IncrementIdGenerator {

	@Autowired
	private RedisTemplate<String, Map<String,Long>> redisTemplate;
	private static final String ENTITY_MAP_KEY = "entity_map_key";
	
	private long currentId(String table) {
		Map<String, Long> tableIds = null;
		HashOperations<String, String, Long> opr = redisTemplate.opsForHash();
		tableIds = opr.entries(ENTITY_MAP_KEY);
		if(tableIds == null || tableIds.size() == 0) {
			tableIds = new HashMap<String, Long>();
			opr.putAll(ENTITY_MAP_KEY, tableIds);
		}
		if(opr.hasKey(ENTITY_MAP_KEY, table)){
			return opr.get(ENTITY_MAP_KEY, table);
		}
		long initVal = 0L;
		opr.put(ENTITY_MAP_KEY, table, initVal);
		return initVal;
	}

	public synchronized long[] genIds(String table, int delta) {
		if(delta < 1){
			throw new IllegalArgumentException("参数错误");
		}
		long current = currentId(table);
		long[] ids = new long[delta];
		for(int i=0; i<delta; i++) {
			ids[i] = ++current;
		}
		redisTemplate.opsForHash().put(ENTITY_MAP_KEY, table, current);
		return ids;
	}

	public synchronized long genId(String table) {
		return genIds(table, 1)[0];
	}

}
