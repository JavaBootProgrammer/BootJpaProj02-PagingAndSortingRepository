package com.job.repository;

import com.job.entity.JobSeeker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobSeekerRepository extends CrudRepository<JobSeeker, Integer> {

}
