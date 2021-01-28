DROP DATABASE  IF EXISTS `idan_doodleclone_demo`;

CREATE DATABASE  IF NOT EXISTS `idan_doodleclone_demo`;
USE `idan_doodleclone_demo`;



DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `event` (title)
VALUES
('party'),
('lach'),
('tv');

DROP TABLE IF EXISTS `eoption`;

CREATE TABLE `eoption` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` varchar(50) NOT NULL,
  `end_time` varchar(50) NOT NULL,
  `event_id` int(11) NOT NULL,
  FOREIGN KEY (`event_id`) 
  REFERENCES `event` (`id`) ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `eoption` (start_time, end_time, event_id)
VALUES
('20:00','23:58', 1), 
('18:00','23:58', 1),
('16:00','19:58', 1);


DROP TABLE IF EXISTS `emember`;

CREATE TABLE `emember` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_name` varchar(50) NOT NULL,
  `event_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`event_id`) 
  REFERENCES `event` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `emember` (member_name,event_id)
VALUES
('Idan', 1), 
('Chen', 1),
('Roi', 1);


SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `member_options`;

CREATE TABLE `member_options` (
  `member_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  PRIMARY KEY (`member_id`,`option_id` ),
  FOREIGN KEY (`member_id`) 
  REFERENCES `emember` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`option_id`) 
  REFERENCES `eoption` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `member_options` (member_id,option_id)
VALUES
(1, 1), 
(1, 2),
(1, 3),
(2, 1), 
(2, 2);

SET FOREIGN_KEY_CHECKS = 1;