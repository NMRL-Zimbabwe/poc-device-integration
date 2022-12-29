package zw.org.nmrl.poc.device.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Lawrence Representation of unified Object to create Patient, ,
 *         LaboratoryRequest, Batch and analysis in central repository
 *
 */

public class UnifiedPatientSampleCaseAnalysisDTO {

	private AnalysisRequestAnalysisProfileDTO analysisRequest;

	private BatchDTO batch;

	private PatientDTO patient;

	public AnalysisRequestAnalysisProfileDTO getAnalysisRequest() {
		return analysisRequest;
	}

	public void setAnalysisRequest(AnalysisRequestAnalysisProfileDTO analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	public BatchDTO getBatch() {
		return batch;
	}

	public void setBatch(BatchDTO batch) {
		this.batch = batch;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "UnifiedPatientSampleCaseAnalysisDTO [analysisRequest=" + analysisRequest + ", batch=" + batch
				+ ", patient=" + patient + "]";
	}

}
