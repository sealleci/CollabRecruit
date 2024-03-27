package com.rai.service.impl;

import com.rai.dao.ResumeDao;
import com.rai.dao.impl.ResumeDaoImpl;
import com.rai.dao.impl.UserInfoDaoImpl;
import com.rai.domain.po.Resume;
import com.rai.domain.po.UserInfo;
import com.rai.domain.vo.Page;
import com.rai.domain.vo.ViewResume;
import com.rai.service.ResumeService;

import java.util.List;

public class ResumeServiceImpl implements ResumeService {
	private ResumeDao resumeDao=new ResumeDaoImpl();

	@Override
	public boolean create(Resume resume) {
		boolean result = false;
		int res = resumeDao.insert(resume);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean remove(Resume resume) {
		boolean result = false;
		int res = resumeDao.delete(resume);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean modify(Resume resume) {
		boolean result = false;
		int res = resumeDao.update(resume);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public ViewResume getViewResumeByUserId(Integer id) {
		ViewResume viewResume=null;
		Resume r=resumeDao.findByUserId(id);
		UserInfo u=new UserInfoDaoImpl().findById(id);
		if(r!=null&&u!=null) {
			viewResume=new ViewResume(
					u.getUsername(),u.getBorndate(),u.getGender()
					,u.getTelephone(),u.getEmail(),u.getAddress()
					,r.getRcompany(),r.getPosition(),r.getWorkStartTime()
					,r.getWorkEndTime(),r.getSalary(),r.getDuty()
					,r.getSchoolName(),r.getEducation(),r.getProfessional()
					,r.getEntranceTime(),r.getGraduateTime()
					);
		}
		return viewResume;
	}

	@Override
	public Resume findById(Integer id) {
		return resumeDao.findById(id);
	}
	
	
	@Override
	public Resume findByUserId(Integer id) {
		return resumeDao.findByUserId(id);
	}

	@Override
	public List<Resume> findAll() {
		return resumeDao.findAll();
	}

	@Override
	public Page selectReinByPage(int currentPage, int eid) {
		return resumeDao.findByPage(currentPage, eid);
	}

	@Override
	public List<Resume> fuzzyFind(String keyword) {
		return resumeDao.fuzzyFind(keyword);
	}

	@Override
	public Page fuzzySelectByPage(int currentPage,String keyword) {
		return resumeDao.fuzzyFindByPage(currentPage, keyword);
	}
}
