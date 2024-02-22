package com.upchardwar.app.services.impl.lab;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.lab.LabReview;
import com.upchardwar.app.entity.lab.LabReviewRating;
import com.upchardwar.app.entity.lab.LabReviewReply;
import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.payload.LabReviewRatingRequest;
import com.upchardwar.app.entity.payload.LabReviewRatingResponse;
import com.upchardwar.app.entity.payload.LabReviewReplayRequest;
import com.upchardwar.app.entity.payload.LabReviewReplayResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.repository.LabRepository;
import com.upchardwar.app.repository.LabReviewRatingRepo;
import com.upchardwar.app.repository.LabReviewReplyRepository;
import com.upchardwar.app.repository.LabReviewRepository;
import com.upchardwar.app.repository.PatientRepository;
import com.upchardwar.app.services.lab.ILabReviewRating;

@Service
public class LabReviewRatingServiceImpl implements ILabReviewRating {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LabReviewRatingRepo labReviewRatingRepository;

	@Autowired
	private LabReviewRepository reviewRepository;

	@Autowired
	private LabReviewReplyRepository replyRepository;

	@Autowired
	private LabRepository labRepository;

	@Autowired
	private PatientRepository patientRepository;

	public LabReviewRatingResponse labReviewRatingToResponse(LabReviewRating labReviewRating) {
		return this.modelMapper.map(labReviewRating, LabReviewRatingResponse.class);
	}

	public LabReviewRating requestToLabReviewRating(LabReviewRatingRequest request) {
		return this.modelMapper.map(request, LabReviewRating.class);
	}


	
	@Override
	public Map<String, Object> addReview(LabReviewRatingRequest request) {
		Map<String, Object> response = new HashMap<>();

		

		Optional<Lab> lab = labRepository.findById(request.getLabId());
		if (lab.isPresent()) {

			Optional<LabReviewRating> anyMatch = lab.get().getLabReviewRatings().parallelStream()
					.filter(obj -> obj.getPatient().getId() == request.getPatientId()).findFirst();
			if (anyMatch.isEmpty()) {
				LabReviewRating reviewRating = new LabReviewRating();
				reviewRating.setLab(lab.get());
				reviewRating.setRating(request.getRating());
				reviewRating.setPatient(patientRepository.findById(request.getPatientId()).get());
				reviewRating.setDescription(request.getDescription());
				reviewRating.setCreateTime(LocalDateTime.now());

				lab.get().getLabReviewRatings().add(reviewRating);
				labRepository.save(lab.get());
		LabReviewRatingResponse lr=	convertToResponse(	labReviewRatingRepository.save(reviewRating));
				response.put(AppConstant.LAB_REVIEW_RATING, lr);
			} else {
				LabReviewRating rating = anyMatch.get();
				rating.setRating(request.getRating());
				rating.setPatient(patientRepository.findById(request.getPatientId()).get());
				rating.setDescription(request.getDescription());
				rating.setCreateTime(LocalDateTime.now());

				LabReviewRating save = labReviewRatingRepository.save(rating);
				response.put("message", "updated Successfully");
				response.put("data", save);

			}
		} else {
			response.put("message", "Lab  not found.");
		}

		return response;
	}

	
	public LabReviewRatingResponse convertToResponse(LabReviewRating reviewRating) {
		  LabReviewRatingResponse doctorReviewRatingResponse = new LabReviewRatingResponse();
		    doctorReviewRatingResponse.setId(reviewRating.getId());
		    doctorReviewRatingResponse.setPatientId(reviewRating.getPatient().getId());
		    doctorReviewRatingResponse.setLabId(reviewRating.getLab().getId()); // Assuming you have a doctor field in LabReviewRating
		    doctorReviewRatingResponse.setRating(reviewRating.getRating());
		    doctorReviewRatingResponse.setDescription(reviewRating.getDescription()); // Assuming description is the review
		    doctorReviewRatingResponse.setPatientName(reviewRating.getPatient().getPatientName()); // Assuming name is a field in Patient
		    doctorReviewRatingResponse.setImageName(reviewRating.getPatient().getImageName()); 
		    doctorReviewRatingResponse.setReplyResponse( reviewRating.getReplies().parallelStream().map(obj->convertToReplyResponse(obj)).toList());
		    return doctorReviewRatingResponse;
	}
	
	
	public LabReviewReplayResponse convertToReplyResponse(LabReviewReply reviewRating) {
		  LabReviewReplayResponse doctorReviewRatingResponse = new LabReviewReplayResponse();
		   
		    doctorReviewRatingResponse.setPatientId(reviewRating.getPatient().getId());
		     // Assuming you have a doctor field in LabReviewRating
		    doctorReviewRatingResponse.setId(reviewRating.getId());
		    doctorReviewRatingResponse.setDescription(reviewRating.getDescription()); // Assuming description is the review
		    doctorReviewRatingResponse.setPatientName(reviewRating.getPatient().getPatientName()); // Assuming name is a field in Patient
		    doctorReviewRatingResponse.setImageName(reviewRating.getPatient().getImageName()); 
		    doctorReviewRatingResponse.setReviewRatingId(reviewRating.getReviewRating().getId());
		    return doctorReviewRatingResponse;
	}
	
	
	
	public Map<String, Object> addReviewReplly(LabReviewReplayRequest request) {
		Map<String, Object> response = new HashMap<>();

		 Optional<LabReviewRating> optionalLabReviewRating = labReviewRatingRepository.findById(request.getReviewRatingId());

		  System.err.println( optionalLabReviewRating.get().getPatient().getPatientName());
			if (optionalLabReviewRating.isPresent()) {
				//LabReviewRating reviewRating = new LabReviewRating();
				LabReviewReply labReviewReply = new LabReviewReply();
			
			
				labReviewReply.setPatient(patientRepository.findById(request.getPatientId()).get());
				labReviewReply.setDescription(request.getDescription());
				labReviewReply.setCreateTime(LocalDateTime.now());
				labReviewReply.setReviewRating(optionalLabReviewRating.get());
				

				LabReviewReply save = replyRepository.save(labReviewReply);
				optionalLabReviewRating.get().getReplies().add(save);
				labReviewRatingRepository.save(optionalLabReviewRating.get());
				LabReviewReplayResponse lr=	convertToReplyResponse(	save);
				response.put(AppConstant.MESSAGE, lr);
			
		} else {
			response.put(AppConstant.MESSAGE ,AppConstant.LAB_NOT_FOUND);
		}

		return response;
	}
	
	
	
	
	 public ResponseEntity<?> getLabReviewRatingById(Long id) {
	        Optional<LabReviewRating> optionalLabReviewRating = labReviewRatingRepository.findById(id);
	        if (optionalLabReviewRating.get() != null) {
	            return new ResponseEntity<>(convertToResponse(optionalLabReviewRating.get()), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Lab Review Rating not found", HttpStatus.NOT_FOUND);
	        }
	    }
	
	 
	 //get All ReviewRating By labId
	 public ResponseEntity<?> getAllRatingOfLab(long labId){
		 Map<String, Object> response = new HashMap<>();
		 List<LabReviewRating> labReviewRatings = labReviewRatingRepository.findByLabId(labId);
		 if(labReviewRatings.isEmpty()) {
			 response.put(AppConstant.MESSAGE, "this lab has no review yet");
			 return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
		 }
		 else {
		List<LabReviewRatingResponse>lr= labReviewRatings.stream()
         .map(this::convertToResponse)
         .collect(Collectors.toList());
		response.put(AppConstant.RATINGS, lr);
		return new ResponseEntity<>(response,HttpStatus.OK);
	 }
	 }

	 @Override
	 public ResponseEntity<?> deleteReview(Long id, String email) {
	     Map<String, Object> response = new HashMap<>();
	     if (email == null) {
	         response.put(AppConstant.MESSAGE, AppConstant.LAB_REVIEW_STATUS);
	         return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	     }

	     Optional<Patient> patientOptional = patientRepository.findByEmail(email);
	     if (patientOptional.isPresent()) {
	         Patient patient = patientOptional.get();
	         Optional<LabReviewRating> labReviewRatingOptional = labReviewRatingRepository.findByIdAndPatientId(id, patient.getId());
	         
	         if (labReviewRatingOptional.isPresent()) {
	             labReviewRatingRepository.delete(labReviewRatingOptional.get());
	             response.put(AppConstant.MESSAGE, "Lab review deleted successfully");
	             return new ResponseEntity<>(response, HttpStatus.OK);
	         } else {
	             response.put(AppConstant.MESSAGE, AppConstant.LAB_REVIEW_STATUS);
	             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	         }
	     } else {
	         response.put(AppConstant.MESSAGE, AppConstant.PAITENT_NOT_FOUND);
	         return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	     }
	 }

		 
		
	 
}
