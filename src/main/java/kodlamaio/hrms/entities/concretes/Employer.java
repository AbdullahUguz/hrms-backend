package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="employers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Employer extends User{
	
	@NotBlank
	@NotNull
	@Column(name="company_name")
	private String companyName;
	
	@NotBlank
	@NotNull
	@Column(name="web_address")
	private String webAddress;
	
	@NotBlank
	@NotNull
	@Column(name="phone_number")
	private String phoneNumber;
	
	@OneToOne(mappedBy = "employer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private ConfirmEmployer confirmEmployer;
	
	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<VerificationCodeEmployer> verificationCodeEmployers;
	
	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<JobAdvertisement>  jobAdvertisements;
	

}
