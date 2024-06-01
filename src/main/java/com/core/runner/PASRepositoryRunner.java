package com.core.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.core.entity.JobSeeker;
import com.core.service.IJobSeekerMgmtService;

@Component
public class PASRepositoryRunner implements CommandLineRunner {
	@Autowired
	private IJobSeekerMgmtService jsService;

	@Override
	public void run(String... arg0) throws Exception {

		jsService.showJobSeekersBySorting(false, "jsId").forEach(System.out::println);
		System.out.println("==========================");
		jsService.showJobSeekersBySorting(false, "jsName", "qlfy").forEach(System.out::println);

		Page<JobSeeker> page = jsService.showJobSeekerByPageNo(2, 3);
		List<JobSeeker> list = page.getContent();
		System.out.println("pageContent::");
		list.forEach(System.out::println);
		System.out.println("Page no::" + (page.getNumber() + 1));
		System.out.println("Pages count::" + page.getTotalPages());
		System.out.println(" records in  the current page ::" + page.getNumberOfElements());
		System.out.println(" Is the First page::" + page.isFirst());
		System.out.println(" Is  the last page::" + page.isLast());

		/*
		 * Page<JobSeeker> page=jsService.showSortedJobSeekersByPageNo(0,
		 * 3,true,"jsName"); page.getContent().forEach(System.out::println);
		 */

		jsService.showJobSeekersInPagination(3);

	}

}
