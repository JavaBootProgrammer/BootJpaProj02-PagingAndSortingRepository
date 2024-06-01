package com.core.service;



import org.springframework.data.domain.Page;

import com.core.entity.JobSeeker;

public interface IJobSeekerMgmtService {
	
	public  Iterable<JobSeeker>  showJobSeekersBySorting(boolean ascOrder,
														 String ... props);
	
	public   Page<JobSeeker>    showJobSeekerByPageNo(int pageNo, int pageSize);
	
	public   Page<JobSeeker>    showSortedJobSeekersByPageNo(int pageNo, int pageSize, boolean ascORder, String ... props);
	
	public  void  showJobSeekersInPagination(int pageSize);
	
}
