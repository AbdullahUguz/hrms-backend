package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="candidate_cvs")
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int cvId;
	
	@OneToOne
	@JoinColumn(name="candidate_id")
	@JsonIgnore
	private Candidate candidate;
	
	@Column(name="github_link")
	private String github;
	
	@Column(name="linkedin_link")
	private String linkedin;

	
	@OneToMany(mappedBy = "cv",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<School> schools;
	
	@OneToMany(mappedBy = "cv",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<WorkExperience> workExperiences;
	
	@OneToMany(mappedBy = "cv",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<KnownLanguage> knownLanguages;
	
	@OneToOne(mappedBy ="cv",cascade = CascadeType.ALL,fetch =FetchType.EAGER)
	@JsonIgnore
	private Image image;
	
	@OneToMany(mappedBy = "cv",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<KnownTechnology> knownTechnologies;
	
	private String coverLetter;
	
	

}
