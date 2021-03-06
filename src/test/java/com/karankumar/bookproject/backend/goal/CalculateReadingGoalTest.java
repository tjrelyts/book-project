package com.karankumar.bookproject.backend.goal;

import com.karankumar.bookproject.backend.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CalculateReadingGoalTest {
    private int booksToRead = 52;
    @Test
    public void progressValueCorrect() {
        int toRead = 25;
        int read = 5;
        Assertions.assertEquals(
                CalculateReadingGoal.calculateProgressTowardsReadingGoal(toRead, read),
                0.2
        );
    }

    @Test
    public void testProgressWhenGoalMet() {
        Assertions.assertEquals(
                CalculateReadingGoal.calculateProgressTowardsReadingGoal(booksToRead, booksToRead),
                1.0
        );
    }

    @Test
    public void testProgressWhenGoalExceeded() {
        Assertions.assertEquals(
                CalculateReadingGoal.calculateProgressTowardsReadingGoal(booksToRead, (booksToRead + 1)),
                1.0
        );
    }

    @Test
    public void testNoProgressMadeTowardsGoal() {
        Assertions.assertEquals(
                CalculateReadingGoal.calculateProgressTowardsReadingGoal(5, 0),
                0);
    }

    @Test
    public void testCalculateProgressTowardsReadingGoalDivideByZero() {
        // ensure 0, and not an arithmetic exception, is returned
        Assertions.assertEquals(
                CalculateReadingGoal.calculateProgressTowardsReadingGoal(5, 0),
                0
        );
    }

    /**
     * Checks whether ahead or behind with reading goal
     */
    @Test
    public void testHowFarAheadOrBehindSchedule(){
        Mockito.mockStatic(DateUtils.class);

        Mockito.when(DateUtils.getCurrentWeekNumberOfYear()).thenReturn(1);
        Assertions.assertEquals(0,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(52,1));
        Assertions.assertEquals(1,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(52,0));
        Assertions.assertEquals(9,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(52,10));
        Assertions.assertEquals(9,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(199,12));
        Assertions.assertEquals(2,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(199,5));

        Mockito.when(DateUtils.getCurrentWeekNumberOfYear()).thenReturn(15);
        Assertions.assertEquals(12,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(52,3));
        Assertions.assertEquals(9,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(52,24));
        Assertions.assertEquals(5,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(52,20));

        Mockito.when(DateUtils.getCurrentWeekNumberOfYear()).thenReturn(10);
        Assertions.assertEquals(20,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(199,50));
        Assertions.assertEquals(22,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(199,8));
        Assertions.assertEquals(70,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(199,100));

        Mockito.when(DateUtils.getCurrentWeekNumberOfYear()).thenReturn(43);
        Assertions.assertEquals(7,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(113,79));
        Assertions.assertEquals(45,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(113,41));
        Assertions.assertEquals(0,
                CalculateReadingGoal.howFarAheadOrBehindSchedule(113,86));
    }
}
