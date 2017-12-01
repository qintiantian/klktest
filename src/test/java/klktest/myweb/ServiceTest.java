package klktest.myweb;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import klktest.myweb.entity.College;
import klktest.myweb.entity.Major;
import klktest.myweb.pub.PublicServiceImpl;
import klktest.myweb.service.CollegeService;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
 @TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class ServiceTest {
	@Autowired
	CollegeService service;
	
	@Before
	public void before() {
	}
	
	public static void main(String[] args) {
		ServiceTest t = new ServiceTest();
		t.init();
		t.updateTest();
	}

	@SuppressWarnings("resource")
	private void init() {
		service = new ClassPathXmlApplicationContext("applicationContext.xml").getBean(CollegeService.class);
		
	}

	@Test
	public void saveTest() {
		service.save(getCol());
		service.save(getMaj());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void updateTest() {
//		List<College> all2 =
//				(List<College>) service.getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(College.class).add(Restrictions.eq("dr", 0)));
//		System.out.println("a2"+all2);
//		service.update(all2.get(0));
//		service.update(all2.get(0),all2.get(1));
		service.test();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void queryTest() {
		List<College> all1 = (List<College>) service.getHibernateTemplate().findByNamedParam("from College where dr=:dr1","dr1", 0);
		System.out.println("a1+"+all1);
	}
	
	@Test
	public void deleteTest() {
		service.getHibernateTemplate().bulkUpdate("delete from College");
	}
	
	

	private College getCol() {
		College col = new College();
		col.setName("清华大学");
		col.setCode("Tsinghua University");
		col.setAddress("北京海淀颐和园清华大学");
		Calendar cal = Calendar.getInstance();
		cal.set(1911, 10, 23);
		col.setBuildon(cal.getTime());
		return col;
	}
	private Major getMaj() {
		Major col = new Major();
		col.setName("信息与计算科学");
		col.setCode("computer");
		col.setDescription("北京海淀颐和园清华大学");
		col.setScore(10);
		return col;
	}
}
