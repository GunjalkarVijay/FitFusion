package com.CN.FitFusion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto {
	
	private String name;
	private String description;
	private int sets;
	private int reps;
}
