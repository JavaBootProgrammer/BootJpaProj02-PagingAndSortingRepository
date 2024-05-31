package com.job.service;

import com.job.entity.JobSeeker;

import java.util.Optional;

public interface IJobSeekerService {
	public Iterable<JobSeeker> ShowJobSeekerBySorting(boolean ascendingOrder,String properties);
}