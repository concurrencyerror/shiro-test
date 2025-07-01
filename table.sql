CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `username` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `salt` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '盐',
                        `encode_time` int DEFAULT NULL COMMENT '加密次数',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE TABLE `role_user_relation` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `role_id` bigint DEFAULT NULL COMMENT '权限id',
                                      `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户id',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限用户关联表'

CREATE TABLE `role` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci



