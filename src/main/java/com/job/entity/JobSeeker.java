//Entity class
package com.job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="JOB_SEEKER_INFO")
@Data
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class JobSeeker {
	@Id
	@SequenceGenerator(name="gen1",sequenceName = "jsId_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JS_ID")
	private Integer jobSeekerID;

	@Column(name="JS_NAME",length =20)
	@NonNull
	private String jobsSeekerName;

	@Column(name="JS_QLFY",length =20)
	@NonNull
	private String jobSeekerQualification;

	@Column(name="JS_PERCENTAGE")
	@NonNull
	private Float JobSeekerPercentage;

	@Transient
	@Column(name="JS_CONTACT_INFO")
	@NonNull
	private Long jobSeekerMobileNo;

	public JobSeeker() {
		System.out.println("JobSeeker:: 0-param constructor::"+this.hashCode());
	}

}

// TODO row creation date and time