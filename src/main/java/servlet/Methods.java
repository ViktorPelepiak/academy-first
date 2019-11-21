package servlet;

import dto.LessonDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Methods {

    public static long getPeriod() throws ParseException {
        LocalDate date;
        LocalDate now = LocalDate.now();

        if (now.getMonthValue() < 9) {
            date = LocalDate.of(now.getYear() - 1, 9, 1);
        } else {
            date = LocalDate.of(now.getYear(), 9, 1);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = dateFormat.parse("" + date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear());
        Date date2 = dateFormat.parse("" + now.getDayOfMonth() + "." + now.getMonthValue() + "." + now.getYear());
        //todo check next line
        return ((Math.abs(date1.getTime() - date2.getTime()) / 86400000) + date.getDayOfWeek().getValue() - 1) / 7;
    }

    public static List<LessonDto> selectTodayLessons(List<LessonDto> I_week, List<LessonDto> II_week) throws ParseException {
        if (getPeriod() % 2 != 0) {
            return I_week.stream().filter(e -> e.getDay() == LocalDate.now().getDayOfWeek().getValue()).collect(Collectors.toList());
        } else {
            return II_week.stream().filter(e -> e.getDay() == LocalDate.now().getDayOfWeek().getValue()).collect(Collectors.toList());
        }
    }

    public static List<LessonDto> selectTomorrowLessons(List<LessonDto> I_week, List<LessonDto> II_week) throws ParseException {
        if (getPeriod() % 2 != 0) {
            return I_week.stream().filter(e -> e.getDay() == LocalDate.now().getDayOfWeek().getValue() + 1).collect(Collectors.toList());
        } else {
            return II_week.stream().filter(e -> e.getDay() == LocalDate.now().getDayOfWeek().getValue() + 1).collect(Collectors.toList());
        }
    }
}
