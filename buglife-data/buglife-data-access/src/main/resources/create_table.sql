USE `webutil` ;

-- -----------------------------------------------------
-- Table `webutil`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webutil`.`group` ;

CREATE TABLE IF NOT EXISTS `webutil`.`group` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `webutil`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webutil`.`member` ;

CREATE TABLE IF NOT EXISTS `webutil`.`member` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT '',
  `group_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;