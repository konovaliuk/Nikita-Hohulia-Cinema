package com.hohulia.cinema;

import com.hohulia.cinema.connection.DBCPDataSource;
import com.hohulia.cinema.dao.implementation.*;
import com.hohulia.cinema.entities.*;
import com.hohulia.cinema.utilities.TimeConvertor;
import com.hohulia.cinema.utilities.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;
import java.sql.Time;


import java.sql.*;
import java.util.List;

public class runner {
    public static void main(String[] args) throws SQLException {


            try {
                System.out.println("Testing User_list and User_role methods\n");

                UserDaoImp userCon = new UserDaoImp(DBCPDataSource.getConnection());
                UserRoleDaoImp userRoleCon = new UserRoleDaoImp(DBCPDataSource.getConnection());

                // Create user
                //userCon.createUser(new User("delete@gmail.com", "1111"));

                // Find user by email
                System.out.println("\nFind by email");
                User user = userCon.findByEmail("delete@gmail.com");
                long idToDel = user.getUserId();
                System.out.println(user);

                // Add role
                //userRoleCon.addUserRole(new UserRole(idToDel, 2));

                // Find user by user_id
                System.out.println("\nFind by user_id");
                user = userCon.findById(idToDel);
                System.out.println(user);


                // Get userRoles with role_id
                System.out.println("\nGet userRoles with role");
                List<UserRole> userRoles= userRoleCon.findByRoleId(1);
                for (UserRole u: userRoles)
                    System.out.println(u);


                //Update user_role
                userRoleCon.updateByUserId(idToDel, 1);


                // Get userRole with user_id
                System.out.println("\nGet userRole with user_id");
                UserRole userRole = userRoleCon.findRoleByUserId(idToDel);
                System.out.println(userRole);



                // Try delete
/*                try {
                    userRoleCon.deleteById(idToDel);
                    new java.util.Scanner(System.in).nextLine();
                    userCon.deleteById(idToDel);
                } catch (RuntimeException ex){
                    ex.printStackTrace();
                }*/


                // Get all userRoles
                System.out.println("\nGet all userRoles");
                userRoles= userRoleCon.findAll();
                for (UserRole u: userRoles)
                    System.out.println(u);

                // Get all users
                System.out.println("\nGet all users");
                List<User> users= userCon.findAll();
                for (User u: users)
                    System.out.println(u);


                System.out.println("\n\nTesting Role methods");
                RoleDaoImp roleCon = new RoleDaoImp(DBCPDataSource.getConnection());

                System.out.println("\n");
                roleCon.addRole(new Role(3, "manager"));
                Role role = roleCon.findById(3);
                System.out.println(role);
                role = roleCon.findByRoleName("manager");
                System.out.println(role);
                System.out.println("\nPrint all roles");
                List<Role> roles = roleCon.findAll();
                for (Role r: roles)
                    System.out.println(r);


                System.out.println("\n\nTesting Movie methods");
                MovieDaoImp movieCon = new MovieDaoImp(DBCPDataSource.getConnection());

                System.out.println("\n");
                movieCon.addMovie(new Movie(Time.valueOf("01:45:00"), "Fast and Furious 34", "Finally ..."));
                Movie movie = movieCon.findById(1);
                System.out.println(movie);
                movie = movieCon.findByTitle("Fast and Furious 34");
                System.out.println(movie);
                movieCon.deleteByTitle("Fast and Furious 34");
                System.out.println("\nPrint all movies");
                List<Movie> movies = movieCon.findAll();
                for (Movie m: movies)
                    System.out.println(m);


                movie = movies.get(0);
                Time time = movie.getDuration();
                System.out.println(time);//07:52:00

                System.out.println("<><><><><><>");
                LocalDateTime dt = LocalDateTime.now();
                System.out.println(dt);
                Timestamp sqlTime = Timestamp.valueOf(TimeConvertor.toSqlString(dt));
                Timestamp endTime = Timestamp.valueOf("2023-04-01 19:00:00");
                ScheduleDaoImp scheduleDaoImp = new ScheduleDaoImp(DBCPDataSource.getConnection());
                System.out.println(sqlTime + " " + endTime);
                List <Schedule> list = scheduleDaoImp.findByStartTimeBorders(sqlTime, endTime);
                System.out.println(list);
                for (Schedule el: list){
                    System.out.println(el);
                }

            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }

    }
}
