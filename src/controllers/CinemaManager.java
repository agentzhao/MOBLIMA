package controllers;

import entities.Cinema;
import entities.Seat;
import entities.ScreeningTimes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaManager{
    //Admin System(create, update, remove)
    Scanner sc = new Scanner(System.in);

    public void createShowtime(Cinema cinema, int movieID){
        ScreeningTimes screentime = new ScreeningTimes(movieID, null, null);

        //getting the showtime
        System.out.println("Enter show time: ");
        String showtime = sc.next();
        screentime.setScreenTime(showtime);

        //getting the date
        System.out.println("Enter date: ");
        String date = sc.next();
        screentime.setDate(date);

        //adding into the screening time
        cinema.screeningTimes.forEach(screeningTime->{
            if(screeningTime.getDate() == date && screeningTime.getScreenTime() == showtime){
                System.out.println("Showtime is already occupied!");
                return;
            }
        });
        cinema.screeningTimes.add(screentime);
        System.out.println("Showtime created!");
    }

    public void updateShowtime(Cinema cinema, int movieID){
        ScreeningTimes screentime = new ScreeningTimes(movieID, null, null);

        //getting the showtime
        System.out.println("Enter show time: ");
        String showtime = sc.next();
        screentime.setScreenTime(showtime);

        //getting the date
        System.out.println("Enter date: ");
        String date = sc.next();
        screentime.setDate(date);

        //adding into the screening time
        cinema.screeningTimes.forEach(screeningTime->{
            if(screeningTime.getDate() == date && screeningTime.getScreenTime() == showtime){
                screeningTime = screentime;
                System.out.println("Showtime updated!");
                return;
            }
        });
        System.out.println("No such Showtime yet!");
    }

    public void removeShowTime(Cinema cinema, int movieID){
        ScreeningTimes screentime = new ScreeningTimes(movieID, null, null);

        //getting the showtime
        System.out.println("Enter show time: ");
        String showtime = sc.next();
        screentime.setScreenTime(showtime);

        //getting the date
        System.out.println("Enter date: ");
        String date = sc.next();
        screentime.setDate(date);

        if(cinema.screeningTimes.contains(screentime)){
            cinema.screeningTimes.remove(screentime);
            System.out.println("Removed!");
            return;
        }
        System.out.println("No such Showtime yet!");
    }
}
