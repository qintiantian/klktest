package klktest.myweb.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import klktest.myweb.entity.College;
import klktest.myweb.pub.PublicServiceImpl;

@Service("collegeService")
public class CollegeService extends PublicServiceImpl {

	public void saveCollege(College college) {
		super.save(college);
	}
	
	public void insertColleges(College[] colleges) {
		super.insert(colleges);
	}
	
	public void deleteCollege(College college) {
		super.delete(college);
	}
	
	public void deleteColleges(College[] colleges) {
		super.delete(colleges);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void test(){
		List<College> all2 =
				(List<College>) super.getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(College.class).add(Restrictions.eq("dr", 0)));
		System.out.println("a2"+all2);
		super.update(all2.get(0));
		super.update(all2.get(0),all2.get(1));
	}
	
}
