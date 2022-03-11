package kodlamaio.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="job_advertisements")
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int advertisementId;
	
	@ManyToOne
	@JoinColumn(name="employer_id")
	private Employer employer;
	
	@NotNull
	@NotBlank
	@ManyToOne
	@JoinColumn(name="job_position_id")
	private  JobPosition jobPosition;

	@NotNull
	@NotBlank
	@Column(name="job_description")
	private String jobDescription;
	
	@NotNull
	@NotBlank
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	@Column(name="min_salary")
	private double minSalary;
	
	@Column(name="max_salary")
	private double maxSalary;
	
	@NotNull
	@NotBlank
	@Column(name="number_of_open_position")
	private int numberOfOpenPosition;
	
	@Temporal(TemporalType.DATE)
	@Column(name="application_dead_line")
	private Date applicationDeadline;
	
	@Temporal(TemporalType.DATE)
	@Column(name="creation_date")
	private Date creationDate;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@OneToOne(mappedBy = "jobAdvertisement",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private ConfirmJobAdvertisement confirmJobAdvertisement; 
	

	
}
