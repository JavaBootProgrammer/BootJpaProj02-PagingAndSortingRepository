package com.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.core.entity.JobSeeker;
import com.core.repository.IJobSeekerRepository;

@Service("jsService")
public class JobSeekerMgmtServiceImpl implements IJobSeekerMgmtService {
	@Autowired
	private  IJobSeekerRepository  jsRepo;

	@Override
	public Iterable<JobSeeker> showJobSeekersBySorting(boolean ascOrder, String... props) {
		//create  Sort object
		Sort sort=Sort.by(ascOrder?Direction.ASC:Direction.DESC, props);
		Iterable<JobSeeker> it=jsRepo.findAll(sort);
		return it;
	}
	
	@Override
	public Page<JobSeeker> showJobSeekerByPageNo(int pageNo, int pageSize) {
		//create the Pageable<T> Object
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		//get  request Page<T> object
		Page<JobSeeker>  page=jsRepo.findAll(pageable);
		return page;
	}
	
	
	@Override
	public Page<JobSeeker> showSortedJobSeekersByPageNo(int pageNo, int pageSize, boolean ascOrder, String... props) {
	   //create  Sort object
		Sort sort=Sort.by(ascOrder?Direction.ASC:Direction.DESC, props);
		//create Pageable Object
		Pageable  pageable=PageRequest.of(pageNo, pageSize, sort);
		// get requested page records
		Page<JobSeeker> page=jsRepo.findAll(pageable);
		
		return page;
	}
	
	
	@Override
	public void showJobSeekersInPagination(int pageSize) {
		//get total records count
		long count=jsRepo.count();
		//get pages count
		long pagesCount=count/pageSize;
		if(count%pageSize!=0)
			pagesCount= ++pagesCount;
		
		for(int i=0;i<pagesCount;++i) {
			//prepare pageable object
			Pageable pageable=PageRequest.of(i, pageSize);
			// get each page content
			Page<JobSeeker> page=jsRepo.findAll(pageable);
			System.out.println("====pageNo:"+(page.getNumber()+1)+"/"+page.getTotalPages()+"=====");
			page.getContent().forEach(System.out::println);
		}//for
		
	}//method


}
