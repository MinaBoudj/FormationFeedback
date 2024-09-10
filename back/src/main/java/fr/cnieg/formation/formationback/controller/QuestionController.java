package fr.cnieg.formation.formationback.controller;

import fr.cnieg.formation.formationback.DTO.QuestionDTO;
import fr.cnieg.formation.formationback.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public List<QuestionDTO> recupererToutesQuestions() {
        return questionService.recupererToutesQuestions();
    }
}
