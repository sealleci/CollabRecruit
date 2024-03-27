package com.rai.service;

import com.rai.domain.po.Resume;
import com.rai.domain.vo.Page;
import com.rai.domain.vo.ViewResume;

import java.util.List;

public interface ResumeService {
	boolean create(Resume resume);
	boolean remove(Resume resume);
	boolean modify(Resume resume);
	ViewResume getViewResumeByUserId(Integer id);
	Resume findById(Integer id);
	Resume findByUserId(Integer id);
	List<Resume> findAll();
	Page selectReinByPage(int currentPage, int eid);
	List<Resume> fuzzyFind(String keyword);
	Page fuzzySelectByPage(int currentPage,String keyword);
}
