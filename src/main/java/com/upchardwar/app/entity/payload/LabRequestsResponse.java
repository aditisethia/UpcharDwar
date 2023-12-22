package com.upchardwar.app.entity.payload;

import java.util.List;

import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.lab.LabTest;
import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.status.LabStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabRequestsResponse {

	private Long id;

	private Patient patient;

	private List<LabTest> labTest;

	private List<LabStatus> labStatus;

	private Lab lab;
}
