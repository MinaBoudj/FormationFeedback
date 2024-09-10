package fr.cnieg.formation.formationback.Services;

import fr.cnieg.formation.formationback.DTO.QuestionDTO;
import fr.cnieg.formation.formationback.model.Question;
import fr.cnieg.formation.formationback.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<QuestionDTO> recupererToutesQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(this::mapQuestionToQuestionDTO).toList();
    }

    public QuestionDTO mapQuestionToQuestionDTO(Question question) {
        return new QuestionDTO(question.getId(), question.getTexte());
    }

    public Question mapQuestionDTOToQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setTexte(questionDTO.texte());
        return question;
    }

}
