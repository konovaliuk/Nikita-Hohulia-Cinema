-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cinema` ;

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinema` DEFAULT CHARACTER SET utf8mb3 ;
USE `cinema` ;

-- -----------------------------------------------------
-- Table `cinema`.`сinema_hall`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`сinema_hall` ;

CREATE TABLE IF NOT EXISTS `cinema`.`сinema_hall` (
  `hall_id` INT NOT NULL AUTO_INCREMENT,
  `cinema_id` INT NOT NULL DEFAULT '1',
  `seats_rows` INT NOT NULL DEFAULT '6',
  `seats_columns` INT NOT NULL DEFAULT '10',
  PRIMARY KEY (`hall_id`, `cinema_id`),
  UNIQUE INDEX `screen_id_UNIQUE` (`hall_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`movie` ;

CREATE TABLE IF NOT EXISTS `cinema`.`movie` (
  `movie_id` INT NOT NULL AUTO_INCREMENT,
  `duration` TIME NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`movie_id`, `title`),
  UNIQUE INDEX `movie_id_UNIQUE` (`movie_id` ASC) VISIBLE,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`schedule` ;

CREATE TABLE IF NOT EXISTS `cinema`.`schedule` (
  `show_id` BIGINT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `hall_id` INT NOT NULL DEFAULT '1',
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  PRIMARY KEY (`show_id`, `hall_id`, `movie_id`),
  UNIQUE INDEX `show_id_UNIQUE` (`show_id` ASC) VISIBLE,
  INDEX `screen_has_shows_idx` (`hall_id` ASC) VISIBLE,
  INDEX `show_has_movies_idx` (`movie_id` ASC) VISIBLE,
  CONSTRAINT `screen_has_shows`
    FOREIGN KEY (`hall_id`)
    REFERENCES `cinema`.`сinema_hall` (`hall_id`)
    ON DELETE CASCADE,
  CONSTRAINT `show_has_movies`
    FOREIGN KEY (`movie_id`)
    REFERENCES `cinema`.`movie` (`movie_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 61
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`user_list`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`user_list` ;

CREATE TABLE IF NOT EXISTS `cinema`.`user_list` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`booking` ;

CREATE TABLE IF NOT EXISTS `cinema`.`booking` (
  `booking_id` BIGINT NOT NULL AUTO_INCREMENT,
  `datetime` TIMESTAMP NOT NULL,
  `user_id` BIGINT NOT NULL,
  `show_id` BIGINT NOT NULL,
  PRIMARY KEY (`booking_id`, `show_id`, `user_id`),
  UNIQUE INDEX `booking_id_UNIQUE` (`booking_id` ASC) VISIBLE,
  INDEX `user_has_booked_idx` (`user_id` ASC) VISIBLE,
  INDEX `show_has_booking_idx` (`show_id` ASC) VISIBLE,
  CONSTRAINT `show_has_booking`
    FOREIGN KEY (`show_id`)
    REFERENCES `cinema`.`schedule` (`show_id`),
  CONSTRAINT `user_has_booking`
    FOREIGN KEY (`user_id`)
    REFERENCES `cinema`.`user_list` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`payment` ;

CREATE TABLE IF NOT EXISTS `cinema`.`payment` (
  `payment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `booking_id` BIGINT NOT NULL,
  `amount` INT NOT NULL,
  `status` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`payment_id`, `booking_id`),
  UNIQUE INDEX `payment_id_UNIQUE` (`payment_id` ASC) VISIBLE,
  INDEX `booking_has_payment_idx` (`booking_id` ASC) VISIBLE,
  CONSTRAINT `booking_has_payment`
    FOREIGN KEY (`booking_id`)
    REFERENCES `cinema`.`booking` (`booking_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`role` ;

CREATE TABLE IF NOT EXISTS `cinema`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_id_UNIQUE` (`role_id` ASC) VISIBLE,
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`seat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`seat` ;

CREATE TABLE IF NOT EXISTS `cinema`.`seat` (
  `booking_id` BIGINT NOT NULL,
  `seat_price` INT NOT NULL DEFAULT '150',
  `show_id` BIGINT NOT NULL,
  `row` INT NOT NULL,
  `number` INT NOT NULL,
  `seat_id` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`seat_id`),
  UNIQUE INDEX `seat_id` (`seat_id` ASC) VISIBLE,
  INDEX `booking_has_seats_idx` (`booking_id` ASC) VISIBLE,
  INDEX `eachSchow_has_seats_idx` (`show_id` ASC) VISIBLE,
  CONSTRAINT `booking_has_seats`
    FOREIGN KEY (`booking_id`)
    REFERENCES `cinema`.`booking` (`booking_id`)
    ON DELETE CASCADE,
  CONSTRAINT `eachSchow_has_seats`
    FOREIGN KEY (`show_id`)
    REFERENCES `cinema`.`booking` (`show_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`user_role` ;

CREATE TABLE IF NOT EXISTS `cinema`.`user_role` (
  `user_id` BIGINT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_user_list_has_role_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_list_has_role_user_list_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_list_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `cinema`.`role` (`role_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_user_list_has_role_user_list`
    FOREIGN KEY (`user_id`)
    REFERENCES `cinema`.`user_list` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
