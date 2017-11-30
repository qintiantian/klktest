package klktest.myweb.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import klktest.myweb.entity.College;
import klktest.myweb.pub.PublicServiceImpl;

@Service("collegeService")
public class CollegeService extends PublicServiceImpl {

	@Transactional
	public void saveCollege(College college) {
		super.save(college);
	}
	
	@Transactional
	public void insertColleges(College[] colleges) {
		super.insert(colleges);
	}
	
	@Transactional
	public void deleteCollege(College college) {
		super.delete(college);
	}
	
	@Transactional
	public void deleteColleges(College[] colleges) {
		super.delete(colleges);
	}
	
	
}
