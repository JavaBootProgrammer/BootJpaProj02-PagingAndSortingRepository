package com.job.service;

import com.job.entity.JobSeeker;
import com.job.repository.IJobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("jsService")
public class JobSeekerServiceImpl implements IJobSeekerService {
	@Autowired
	private IJobSeekerRepository jobSeekerRepository;

	@Override
	public String register(JobSeeker js) {
		/*
		 * System.out.println("jsRepo  obj class name(InMemory Proxy class name)::"
		 * +jsRepo.getClass());
		 * System.out.println("Proxy class implemented interfaces::"+Arrays.toString(
		 * jsRepo.getClass().getInterfaces()));
		 * System.out.println("Proxy class methods::"+Arrays.toString(jsRepo.getClass().
		 * getDeclaredMethods()));
		 */

		// save the object
		JobSeeker savedJs = jobSeekerRepository.save(js);

		return "Job Seeker  obj saved(record inserted)  having the id value ::" + savedJs.getJobSeekerID();
	}

	@Override
	public boolean isJobSeekerExists(int jobSeekerID) {
		return jobSeekerRepository.existsById(jobSeekerID);
	}

	@Override
	public long getJobSeekersCount() {
		return jobSeekerRepository.count();
	}

	@Override
	public Iterable<JobSeeker> getAllJobSeekers() {
		return jobSeekerRepository.findAll();
	}

	@Override
	public Iterable<JobSeeker> getJobSeekersByIds(Iterable<Integer> ids) {

		return jobSeekerRepository.findAllById(ids);
	}

	@Override
	public Optional<JobSeeker> getJobSeekerById(int id) {
		return jobSeekerRepository.findById(id);
	}

	@Override
	public String showJobSeekerById(int id) {
		Optional<JobSeeker> opt = jobSeekerRepository.findById(id);
		if (opt.isEmpty())
			return "Record not found";
		else
			return "record found" + opt.get();

	}

	@Override
	public JobSeeker fetchJobSeekerById(int id) {
		JobSeeker js = jobSeekerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id"));
		return js;
	}

	@Override
	public JobSeeker findJobSeekerById(int id) {
		return jobSeekerRepository.findById(id).orElse(new JobSeeker("freelancer", "freelancer work", 0.0f, 000000L));

	}

	/*
	 * @Override public String registerOrUpdateJobSeeker(JobSeeker js) {
	 * jsRepo.save(js); return "JobSeeker is  saved or updated"; }
	 */

	@Override
	public String registerOrUpdateJobSeeker(JobSeeker js) {
		Optional<JobSeeker> opt = jobSeekerRepository.findById(js.getJobSeekerID());
		if (opt.isPresent()) {
			int idVal = jobSeekerRepository.save(js).getJobSeekerID();
			return idVal + " JobSeeker is updated";
		} else {
			int idVal = jobSeekerRepository.save(js).getJobSeekerID();
			return idVal + " JobSeeker is inserted";
		}
	}

	@Override
	public String updateJobSeekerInfoById(int id, long newMobileNo) {
		Optional<JobSeeker> opt = jobSeekerRepository.findById(id);
		if (opt.isPresent()) {
			JobSeeker js = opt.get();
			js.setJobSeekerMobileNo(newMobileNo);
			jobSeekerRepository.save(js);
			return id + " JobSeeker is updated";
		}
		return id + " JobSeeker is not found";
	}

	@Override
	public String removeJobSeekerById(int id) {
		Optional<JobSeeker> opt = jobSeekerRepository.findById(id);
		if (opt.isPresent()) {
			jobSeekerRepository.deleteById(id);
			return id + " JobSeeker is deleted";
		}
		return id + " JobSeeker is not found for deletion";
	}

	@Override
	public String removeJobSeeker(JobSeeker js) {
		Optional<JobSeeker> opt = jobSeekerRepository.findById(js.getJobSeekerID());
		if (opt.isPresent()) {
			jobSeekerRepository.delete(js);
			return js.getJobSeekerID() + "  JobSeeker is deleted";
		}
		return js.getJobSeekerID() + " jobseeker is not found";
	}

	@Override
	public String removeAllJobSeekers() {
		long count = jobSeekerRepository.count();
		if (count == 0)
			return "records not found ";
		else {
			jobSeekerRepository.deleteAll();
			return count + " no.of records are deleted";
		}
	}

	@Override
	public String removeJobSeekersByIds(Iterable<Integer> ids) {
		Iterable<JobSeeker> it = jobSeekerRepository.findAllById(ids);
		List<JobSeeker> list = (List<JobSeeker>) it;
		int count = list.size();
		jobSeekerRepository.deleteAllById(ids);

		return count + " no.of records are found and deleted ";
	}

}