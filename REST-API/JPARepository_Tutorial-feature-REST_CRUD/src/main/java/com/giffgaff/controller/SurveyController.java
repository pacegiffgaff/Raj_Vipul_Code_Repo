package com.giffgaff.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.giffgaff.entity.Question;
import com.giffgaff.service.SurveyService;

@RestController
public class SurveyController {
	
	
	@Autowired
	private SurveyService surveyService;

	/**
	 * @param surveyId
	 * @return list of questions
	 */
	@GetMapping("/surveys/{surveyId}/questions")
	@ResponseBody
	public List<Question> retrieveQuestions(@PathVariable String surveyId) {
		return surveyService.retrieveQuestions(surveyId);
	}

	/**
	 * @param surveyId
	 * @param questionId
	 * @return the requested question
	 */
	// GET "/surveys/{surveyId}/questions/{questionId}"
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForQuestion(@PathVariable String surveyId,
			@PathVariable String questionId) {
		return surveyService.retrieveQuestion(surveyId, questionId);
	}

	/**
	 * @param surveyId
	 * @param newQuestion
	 * @return
	 */
	// /surveys/{surveyId}/questions
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionToSurvey(
			@PathVariable String surveyId, @RequestBody Question newQuestion) {

		Question question = surveyService.addQuestion(surveyId, newQuestion);

		if (question == null)
			return ResponseEntity.noContent().build();

		// Success - URI of the new resource in Response Header
		// Status - created
		// URI -> /surveys/{surveyId}/questions/{questionId}
		// question.getQuestionId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(question.getId()).toUri();

		// Status
		return ResponseEntity.created(location).build();
	}


	/**
	 * @param questionId
	 * @return
	 */
	  @DeleteMapping("/surveys/delete/{questionId}")
	  @ResponseBody 
	  public String deleteQuestion(@PathVariable String questionId) {
		  
		  surveyService.deleteQuestion(questionId);
		  return"Successfully deleted"; 
	  }

	/**
	 * @param surveyId
	 * @param newQuestion
	 * @return
	 */
	  @PutMapping("/surveys/{surveyId}/put/{questionId}")
	  @ResponseBody 
	  public String putQuestion(@PathVariable String surveyId, @RequestBody Question newQuestion) {
		  Question question = surveyService.retrieveQuestion(surveyId, newQuestion.getId());
		  if(question == null) {
			  return "no question found";
		  }
		  surveyService.updateQuestion(newQuestion);
		  return"Successfully updated"; 
	  }

}
