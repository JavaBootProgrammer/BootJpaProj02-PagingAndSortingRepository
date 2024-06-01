package com.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.core.entity.JobSeeker;

public interface IJobSeekerRepository extends CrudRepository<JobSeeker, Integer>,
        PagingAndSortingRepository<JobSeeker, Integer> {

}
