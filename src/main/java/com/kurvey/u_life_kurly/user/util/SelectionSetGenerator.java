package com.kurvey.u_life_kurly.user.util;

import com.kurvey.u_life_kurly.user.entity.SelectionSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectionSetGenerator {
    private static final String delimiter = ", ";

    private final List<SelectionSet> selectionSets = new ArrayList<>();
    private final int QUESTION_COUNT;
    private final int SELECTION_COUNT;
    private final boolean[] visit;
    private final long[] selections;

    public SelectionSetGenerator(int questionCount, int selectionCount) {
        QUESTION_COUNT = questionCount;
        SELECTION_COUNT = selectionCount;
        visit = new boolean[questionCount];
        selections = new long[selectionCount];
    }

    public List<SelectionSet> generateSelectionSets(){
        permutation(SELECTION_COUNT);
        return selectionSets;
    }

    private void permutation(int cnt){
        if(cnt == 0){
            selectionSets.add(SelectionSet.builder()
                    .selection(makeSelectionsAsString(selections))
                    .build());
            return;
        }

        cnt--;
        for(int i = 0; i < QUESTION_COUNT; i++){
            if(visit[i]) continue;
            visit[i] = true;
            selections[cnt] = i + 1;
            permutation(cnt);
            visit[i] = false;
        }
    }

    public static String makeSelectionsAsString(long[] selections){
        return String.join(delimiter, Arrays.stream(selections).mapToObj(String::valueOf).toArray(String[]::new));
    }

    public static long[] splitSelectionSetToSelections(String selectionSet){
        return Arrays.stream(selectionSet.split(delimiter)).mapToLong(Long::parseLong).toArray();
    }
}
