package kodlamaio.hrms.entities.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "candidates")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Candidate extends User {

	@NotBlank
	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@NotBlank
	@NotNull
	@Column(name = "last_name")
	private String lastName;

	@NotBlank
	@NotNull
	@Column(name = "identity_number")
	private String identityNumber;

	@NotBlank
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_of_date")
	private Date birthOfDate;

	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<VerificationCodeCandidate> verificationCodeCandidates;
	
	@OneToOne(mappedBy ="candidate",cascade = CascadeType.ALL,fetch =FetchType.EAGER)
	@JsonIgnore
	private CandidateCv cv;
	
	/*
	 * @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL,fetch =
	 * FetchType.LAZY)
	 * 
	 * @JsonIgnore private List<School> schools;
	 * 
	 * @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL,fetch =
	 * FetchType.LAZY)
	 * 
	 * @JsonIgnore private List<WorkExperience> workExperiences;
	 * 
	 * @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL,fetch =
	 * FetchType.LAZY)
	 * 
	 * @JsonIgnore private List<KnownLanguage> knownLanguages;
	 */
}
