CREATE TABLE `order_location` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `datetime` datetime(6) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `operator_id` bigint NOT NULL,
  `end_order_location_id` bigint DEFAULT NULL,
  `start_order_location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_end_order_location_id` (`end_order_location_id`),
  KEY `FK_start_order_location_id` (`start_order_location_id`),
  CONSTRAINT `FK_end_order_location_id` FOREIGN KEY (`end_order_location_id`) REFERENCES `order_location` (`id`),
  CONSTRAINT `FK_start_order_location_id` FOREIGN KEY (`start_order_location_id`) REFERENCES `order_location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders_assistances` (
  `order_id` bigint NOT NULL,
  `assistance_id` bigint NOT NULL,
  KEY `FK_assistance_id` (`assistance_id`),
  KEY `FK_order_id` (`order_id`),
  CONSTRAINT `FK_assistance_id` FOREIGN KEY (`assistance_id`) REFERENCES `assistances` (`id`),
  CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
