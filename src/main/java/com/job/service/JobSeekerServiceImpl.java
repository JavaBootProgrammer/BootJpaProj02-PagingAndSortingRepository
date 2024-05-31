package com.job.service;

import com.job.entity.JobSeeker;
import com.job.repository.IJobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("jsService")
public class JobSeekerServiceImpl implements IJobSeekerService {


	@Autowired
	private IJobSeekerRepository jobSeekerRepository;

	@Override
	public Iterable<JobSeeker> ShowJobSeekerBySorting(boolean ascendingOrder, String properties) {

		Sort sort= Sort.by(ascendingOrder? Sort.Direction.ASC: Sort.Direction.DESC, properties);
		Iterable<JobSeeker> it=jobSeekerRepository.findAll(sort);
		return it;
	}
}