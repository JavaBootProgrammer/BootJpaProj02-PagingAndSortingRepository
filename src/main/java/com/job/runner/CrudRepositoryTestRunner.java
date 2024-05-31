package com.job.runner;

import com.job.entity.JobSeeker;
import com.job.service.IJobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CrudRepositoryTestRunner implements CommandLineRunner {
	@Autowired
	private IJobSeekerService jsService;

	@Override
	public void run(String... arg0) throws Exception {

		saveRecord();
		removeJobSeekersByIds();
		removeAllJobSeekers();
		isJobSeekerExists();
		getJobSeekersCount();
		getAllJobSeekers();
		ByID();
		showJobSeekerById();
		removeJobSeekerById();
		registerOrUpdateJobSeeker();
		updateJobSeekerInfoById();
		removeJobSeeker();

	}

	private void removeJobSeeker() {
		try {
			JobSeeker js = new JobSeeker();
			js.setJobSeekerID(2);
			js.setJobSeekerMobileNo(9999999L);
			String msg = jsService.removeJobSeeker(js);
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateJobSeekerInfoById() {
		try {
			System.out.println(jsService.updateJobSeekerInfoById(111, 78999999L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerOrUpdateJobSeeker() {
		try {
			JobSeeker js = new JobSeeker(2, "jani", "B.Tech", 67.66f, 99999999L);
			String msg = jsService.registerOrUpdateJobSeeker(js);
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removeJobSeekerById() {
		try {
			String msg = jsService.removeJobSeekerById(52);
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showJobSeekerById() {
		try {
			Optional<JobSeeker> opt = jsService.getJobSeekerById(121);
			if (opt.isPresent())
				System.out.println("Job Seeker found::" + opt.get());
			else
				System.out.println("Job Seeker not found");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println(jsService.showJobSeekerById(1));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			JobSeeker js = jsService.fetchJobSeekerById(112);
			System.out.println("js info::" + js);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			JobSeeker js = jsService.findJobSeekerById(11);
			System.out.println(js);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ByID() {
		try {

			jsService.getJobSeekersByIds(List.of(1, 2, 52)).forEach(js -> System.out.println(js)); // all are there
			jsService.getJobSeekersByIds(List.of(1, 2, 521)).forEach(js -> System.out.println(js)); // few are not there
			jsService.getJobSeekersByIds(List.of(11, 21, 521)).forEach(js -> System.out.println(js));
			jsService.getJobSeekersByIds(List.of(1, 2, null)).forEach(js -> System.out.println(js)); // throws
																										// NullPointerException
																										// becoz List.of
																										// (-) does not
																										// allow null
																										// values
			List<Integer> ids = new ArrayList();
			ids.add(1);
			ids.add(2);
			ids.add(null);
			jsService.getJobSeekersByIds(ids).forEach(System.out::println);

			jsService.getJobSeekersByIds(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAllJobSeekers() {
		try {
			Iterable<JobSeeker> it = jsService.getAllJobSeekers();
			it.forEach(js -> { // java 8 for eachMethod with LAMDA
				System.out.println(js);
			});

			// it.forEach(System.out::println); // java8 for each method with static method
			// reference

			for (JobSeeker js : it) {
				System.out.println(js);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getJobSeekersCount() {
		try {
			System.out.println(" no.of records:::" + jsService.getJobSeekersCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void isJobSeekerExists() {
		try {
			System.out.println("Js with 1 id  available ?" + jsService.isJobSeekerExists(1123));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removeAllJobSeekers() {
		try {
			System.out.println(jsService.removeAllJobSeekers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removeJobSeekersByIds() {
		try {
			String msg = jsService.removeJobSeekersByIds(List.of(1005, 1006, 890));
			System.out.println(msg);
		} // try
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveRecord() {
		try {
			// create Entity class obj having data
			// JobSeeker info=new JobSeeker(123, "raja", "B.E", 90.99f, 99999999L);
			// JobSeeker info=new JobSeeker(null, "rajesg", "B.Tech", 91.99f, 98999999L);
			JobSeeker info = new JobSeeker("karan", "B.tech", 81.99f, 19899999L);
			String resultMsg = jsService.register(info);
			System.out.println(resultMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
