package com.kurvey.u_life_kurly.user.service;

import com.kurvey.u_life_kurly.error.CustomException;
import com.kurvey.u_life_kurly.user.entity.PriorityWeight;
import com.kurvey.u_life_kurly.user.entity.SelectionSet;
import com.kurvey.u_life_kurly.user.entity.Similarity;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.repository.LifeStyleQuestionRepository;
import com.kurvey.u_life_kurly.user.repository.PriorityWeightRepository;
import com.kurvey.u_life_kurly.user.repository.SelectionSetRepository;
import com.kurvey.u_life_kurly.user.repository.SimilarityRepository;
import com.kurvey.u_life_kurly.user.util.SelectionSetGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.kurvey.u_life_kurly.response.StatusCode.PRIORITY_WEIGHTS_NOT_SET;
import static com.kurvey.u_life_kurly.response.StatusCode.REGENERATE_SELECTION_SET;

@RequiredArgsConstructor
@Service
public class SimilarityService {
    private final SelectionSetRepository selectionSetRepository;
    private final PriorityWeightRepository priorityWeightRepository;
    private final SimilarityRepository similarityRepository;
    private final LifeStyleQuestionRepository lifeStyleQuestionRepository;

    @Transactional
    public void deleteSimilarityByUser(User user){
        similarityRepository.deleteByUser(user);
    }

    @Transactional
    public void calculateSimilarities(User user, List<Long> selects, final int SELECTION_COUNT){
        List<SelectionSet> selectionSets = selectionSetRepository.findAll();
        if(selectionSets.isEmpty()) selectionSets = this.generateSelectionSets(SELECTION_COUNT);

        List<PriorityWeight> priorityWeights = priorityWeightRepository.findAll(Sort.by(Sort.Direction.DESC, "priority"));
        if(priorityWeights.isEmpty() || priorityWeights.size() != SELECTION_COUNT)
            throw new CustomException(PRIORITY_WEIGHTS_NOT_SET);

        List<Similarity> similarities = new ArrayList<>();
        for(SelectionSet selectionSet : selectionSets){
            long[] questionIds = SelectionSetGenerator.splitSelectionSetToSelections(selectionSet.getSelection());
            if(questionIds.length != SELECTION_COUNT){
                this.generateSelectionSets(SELECTION_COUNT);
                throw new CustomException(REGENERATE_SELECTION_SET);
            }

            int value = 0;
            for(int i = 0; i < SELECTION_COUNT; i++){
                int priority = selects.indexOf(questionIds[i]);
                value += (priority == -1? 0 : (SELECTION_COUNT - priority)) * priorityWeights.get(i).getWeight();
            }
            similarities.add(Similarity.builder()
                    .user(user)
                    .selectionSet(selectionSet)
                    .value(value)
                    .build());
        }
        similarityRepository.saveAll(similarities);
    }

    public List<SelectionSet> generateSelectionSets(final int SELECTION_COUNT){
        int questionCount = Long.valueOf(lifeStyleQuestionRepository.count()).intValue();
        List<SelectionSet> selectionSets = new SelectionSetGenerator(questionCount, SELECTION_COUNT).generateSelectionSets();
        selectionSetRepository.deleteAll();
        selectionSetRepository.saveAll(selectionSets);
        return selectionSets;
    }
}
