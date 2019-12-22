package com.luv2code.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="instructor_detail_id")
	private int instructorDetailId;
	
	@Column(name="youtube_chanel")
	private String youtubeChanel;
	
	@Column(name="hobby")
	private String hobby;
	
//	@OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL)
	@OneToOne(mappedBy="instructorDetail",
			cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Instructor instructor;

	
	public InstructorDetail() {
		super();
	}

	public InstructorDetail(String youtubeChanel, String hobby) {
		super();
		this.youtubeChanel = youtubeChanel;
		this.hobby = hobby;
	}

	public int getInstructorDetailId() {
		return instructorDetailId;
	}

	public void setInstructorDetailId(int instructorDetailId) {
		this.instructorDetailId = instructorDetailId;
	}

	public String getYoutubeChanel() {
		return youtubeChanel;
	}

	public void setYoutubeChanel(String youtubeChanel) {
		this.youtubeChanel = youtubeChanel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "InstructorDetail [instructorDetailId=" + instructorDetailId + ", youtubeChanel=" + youtubeChanel
				+ ", hobby=" + hobby+"]";
	}
	
	

}
