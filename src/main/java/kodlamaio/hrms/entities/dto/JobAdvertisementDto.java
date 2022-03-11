package kodlamaio.hrms.entities.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {
	
	@NotNull
	@NotBlank
	private int jobPositionId;
	
	@NotNull
	@NotBlank
	private String description;
	
	@NotNull
	@NotBlank
	private int employerId;
	
	@NotNull
	@NotBlank
	private int cityId;
	
	private double minSalary;
	
	private double maxSalary;
	
	@NotNull
	@NotBlank
	private int numberOfOpenPosition;
	
	@NotNull
	@NotBlank
	private Date applicationDeadLine;

}
