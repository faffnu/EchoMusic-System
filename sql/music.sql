/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.29 : Database - music
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `like_music` */

CREATE TABLE `like_music` (
  `like_music_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '我喜欢的音乐id',
  `song_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '我的歌曲id',
  `user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `like_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加到喜欢音乐的时间',
  KEY `song_id` (`song_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `like_music_id` (`like_music_id`) USING BTREE,
  CONSTRAINT `like_music_ibfk_1` FOREIGN KEY (`song_id`) REFERENCES `song_music` (`song_music_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `like_music_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `music_user` (`music_user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `like_music` */

insert  into `like_music`(`like_music_id`,`song_id`,`user_id`,`like_time`) values ('64549588d7614508bb454d22d30552a3','c167dc43de854934887e31237679725b','b576ae6fecc84750bd1aaa7f88636573','2025-05-18 17:28:27'),('2e0914eb2d974390ad8380d805f1ab13','05e2c078fddc4c83af9dbec3169aeaf0','b576ae6fecc84750bd1aaa7f88636573','2025-05-18 17:36:08'),('ee8c128af8ef46e1bc245bf1d9c627fd','6ef0cb5768af4743bfd7e5f83842a801','af2460354f224f79ad6dc55de92da6b6','2025-05-20 23:10:26'),('336ffabdc578470eb7b6819f338484bf','d0ae31402c97425cb00735d2b2138d77','af2460354f224f79ad6dc55de92da6b6','2025-09-09 18:41:56'),('7af1bc3237ad4766a486cea45bba6f35','f07b346360cc488db096cb91e36dd88a','96fe09d62e9e4517b9bc521aa5ece1b1','2025-09-14 13:56:27'),('81f8321324d342e1a341f81595ca05ad','f07b346360cc488db096cb91e36dd88a','f0ded9befcf940a898de1e43d37525e1','2025-09-14 18:41:04'),('2cb611876133490b817b4dc85c5da09b','f07b346360cc488db096cb91e36dd88a','af2460354f224f79ad6dc55de92da6b6','2025-09-21 12:54:34');

/*Table structure for table `music_and_singer` */

CREATE TABLE `music_and_singer` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `music_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '音乐id',
  `singer_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌手id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `music_id` (`music_id`) USING BTREE,
  KEY `singer_id` (`singer_id`) USING BTREE,
  CONSTRAINT `music_and_singer_ibfk_1` FOREIGN KEY (`music_id`) REFERENCES `song_music` (`song_music_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `music_and_singer_ibfk_2` FOREIGN KEY (`singer_id`) REFERENCES `singer` (`singer_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `music_and_singer` */

insert  into `music_and_singer`(`id`,`music_id`,`singer_id`) values ('032cb97e4b594777bcd7b008b3ede84a','a53b1e4b1bb64eb5bc73ad8f3280a8c9','bf50623ad71f465dbe90f8c180a67790'),('0cffc9e9606a4494ace8dc31811d114c','de11a654830d4d27b5bfaec142a91835','b9363568406445e39db645aa55f4266b'),('13e4d59decbf46cd93d12329ca36839a','52e1210ac42c4f06a05370d19b12fd56','96cbb04596d9453c92090792ebb85420'),('15f5e1decd7048e2a31e97c20aca65ee','ebffe27265ef4766a28185597d91077a','c49c7318d02c4a39a5b929bb7a3e8028'),('173df9394eb84e4fb8e123494f18a66f','c167dc43de854934887e31237679725b','b0acd4d500fe4216800c2a8642041b5d'),('35a650a6b96f4ed3a3c874ea13b9406b','792071e0caca482da18bb795d5979a7c','a1df2529c27f465a9f9d8eeed45ef033'),('429224c47ec2444ab8f937b9c8151c87','a6e20704d8094c899f40ba1209fe5ad0','0b0ff312b18a4347a37fce521a67dd1c'),('505cc87926954c56923a4ba4cfefc126','8a788e4541544fc19b54883189313117','49b56e2f5d1345199be0e7adfae96402'),('5a42e29be5a84654b12683de4d061646','48c4c11b712c4ae49ea1464b71d47c68','2c4c754b1db6457194281c5000afe79e'),('5fe4ce23232c4983b3b328cf056a4954','c167dc43de854934887e31237679725b','753dee4a584c4d3796e3a1f711b2be64'),('675124358a2b4d7989ae0f05be1fa680','ebffe27265ef4766a28185597d91077a','0c7c6cea71e04d2d9a54d20e900d9e4d'),('6c460399c5c648468751d60a0c8b3680','6ef0cb5768af4743bfd7e5f83842a801','8b9dae378033463995378c3c6dbaae37'),('765ff1810cb34a34897d1e38a7abbd52','f34dbc2dbc994648a02fe31269706cff','3d3152b00f054896ac26cccf4f36c750'),('7cb7a82a97ca4bf7bde9f48046551440','6ef0cb5768af4743bfd7e5f83842a801','4ebd84d9e413457ca4d64707dcf0994c'),('7e8a0832393e402bb41c1d9d9ecff4bd','ce95b5e3fa964b4e95cc477c06d68e32','e8c4187ce13f471aab80ec171971316c'),('9d44bc9402ad4b3eb1f7abfe768b05ed','1f00e6d8d47d4450b3e7ffafd6a36358','582cd22401794fd08de98be20dfd6d3d'),('9d93d48ec3d94650a5ef66d96fbfc6ae','05e2c078fddc4c83af9dbec3169aeaf0','582cd22401794fd08de98be20dfd6d3d'),('b058ab670b69420fba6226b66ca0de87','f07b346360cc488db096cb91e36dd88a','723bf87a92b04d8db54f1005ec40e8d2'),('b328deed20264825a8d29252fa13ae31','5498219a98ad44b0a8992f40e0463a7e','f233ec149225407c9193afe0432574e9'),('b4596e586f9a4806845b81f7ee94d9ad','39328d44f3e8437ea1743217400f1b99','b4596e586f9a4806845b81f7ee94d9ad'),('b6af6ee299ab46be818fa8fd6de9dfe7','f34dbc2dbc994648a02fe31269706cff','8b9dae378033463995378c3c6dbaae37'),('cb6719dd201c4cf0a203e2f8f2675b3a','d0ae31402c97425cb00735d2b2138d77','a10e5b47cc164742bc9e1df32dab89b6'),('db20223abe6d475fbbbf909cb91cd4b3','f07b346360cc488db096cb91e36dd88a','790a17839db44ee5804e90c613314c67'),('ed78917c18e749fe9b896d21e762826a','779e8861ba4b4e96bb62b04da292a10d','b9bba7e1e3b44b409ce36281cd832f0b'),('f16f4606873845fca4c3109de9fc586a','4d6e205a9ff4489a96481b83fd133221','b05da0561e0044ce9742f4813905590a');

/*Table structure for table `music_user` */

CREATE TABLE `music_user` (
  `music_user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password_hash` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码,使用hash值加密',
  `bio` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '这个用户很神秘，还没有留下任何介绍...但相信TA一定是个热爱音乐的人！' COMMENT '用户简介',
  `account_status` enum('active','suspended','banned') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT 'active' COMMENT '正常 active / 冻结 suspended / 封禁 banned',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '/static/avatar/default.png' COMMENT '用户头像',
  `is_vip` int NOT NULL DEFAULT '0' COMMENT '该用户是否是vip,默认为0，不是vip',
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户注册时间',
  `last_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登录时间',
  PRIMARY KEY (`music_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `music_user` */

insert  into `music_user`(`music_user_id`,`user_name`,`password_hash`,`bio`,`account_status`,`avatar`,`is_vip`,`registration_date`,`last_login`) values ('28dce50b4df44ab1a78a5863b6bcf046','MelodySeeker','$2a$10$NJPeoz0M4ehO1ixquoTEgevaftbPnF7b0dY1SsUY2G7.ES8KRL12W','音乐探险家，专注于发现小众独立音乐。每日分享宝藏歌曲，用旋律治愈心灵。','active','/static/avatar/1.jpg',0,'2025-09-14 10:21:49','2025-09-14 10:21:49'),('34b1c93ecd5c459e931b4e24204434fe','RhythmMaster','$2a$10$NJPeoz0M4ehO1ixquoTEgevaftbPnF7b0dY1SsUY2G7.ES8KRL12W','节奏掌控者，热爱电子音乐和嘻哈。beat maker，用鼓点讲述故事。','active','/static/avatar/2.jpg',0,'2025-09-14 10:21:58','2025-09-14 10:21:58'),('384c945a63b3439fa4abb61f7cf69d3c','VocalDreamer','$2a$10$NJPeoz0M4ehO1ixquoTEgevaftbPnF7b0dY1SsUY2G7.ES8KRL12W','声乐爱好者，美声专业在读。分享声乐技巧和经典歌剧片段，梦想站上世界舞台。','active','/static/avatar/default.png',0,'2025-09-14 10:18:51','2025-09-14 10:18:51'),('4cd08967295846e899cefa37ab22ba23','GuitarPoet','$2a$10$NJPeoz0M4ehO1ixquoTEgevaftbPnF7b0dY1SsUY2G7.ES8KRL12W','民谣吉他手，原创音乐人。用六弦琴写诗，在旋律中寻找生活的诗意。','active','/static/avatar/default.png',0,'2025-09-14 10:19:09','2025-09-14 10:19:09'),('52d0bf8e06084e09a3c92e61fae5e7eb','KMusicLover','$2a$10$NJPeoz0M4ehO1ixquoTEgevaftbPnF7b0dY1SsUY2G7.ES8KRL12W','韩流音乐狂热粉，从K-pop到K-indie都有涉猎。一起感受韩国音乐的多样魅力！','active','/static/avatar/default.png',0,'2025-09-14 10:19:31','2025-09-14 10:19:31'),('6e1644fa268242659b5879bf0784f282','PianoWhisper','$2a$10$NJPeoz0M4ehO1ixquoTEgevaftbPnF7b0dY1SsUY2G7.ES8KRL12W','钢琴诗人，擅长古典与现代融合。让琴键诉说那些无法用语言表达的情感。','active','/static/avatar/default.png',0,'2025-09-14 10:19:49','2025-09-14 10:19:49'),('7d9e27309ad2421ea726352f5aca3d98','BassGroover','$2a$10$NJPeoz0M4ehO1ixquoTEgevaftbPnF7b0dY1SsUY2G7.ES8KRL12W','低音炮爱好者，沉迷于funk和soul的律动。好的bassline是音乐的灵魂！','active','/static/avatar/default.png',0,'2025-09-14 12:00:03','2025-09-14 12:00:03'),('96fe09d62e9e4517b9bc521aa5ece1b1','resrgs','$2a$10$dvLwu0/ibBReB6El1fWBFOVGrcqINNDy0wUzv7eoS8b1ce2Z6hoc.','这个用户很神秘，还没有留下任何介绍...但相信TA一定是个热爱音乐的人！','active','/static/avatar/default.png',0,'2025-09-14 13:55:48','2025-09-14 13:55:48'),('af2460354f224f79ad6dc55de92da6b6','admin','$2a$10$345cfCswGqDhLHEZdyhn7ef6QFH1UA8F8FtNCo30FNoZjetBXmxRK','这个用户很神秘，还没有留下任何介绍...但相信TA一定是个热爱音乐的人!','active','/static/avatar/default.png',0,'2025-09-22 20:15:53','2025-09-22 20:15:53'),('b576ae6fecc84750bd1aaa7f88636573','IndieExplorer','$2a$10$NJPeoz0M4ehO1ixquoTEgevaftbPnF7b0dY1SsUY2G7.ES8KRL12W','独立音乐挖掘机，专注发现国内外优秀独立音乐人。支持原创，聆听真实的声音。','active','/static/avatar/default.png',1,'2025-09-14 12:00:03','2025-09-14 12:00:03'),('f0ded9befcf940a898de1e43d37525e1','abcdef','$2a$10$ozITyQzvX/4a7y8DQChFh.FUrvF7t5oE.aLi88XhW0YzxTFDq/HKG','这个用户很神秘，还没有留下任何介绍...但相信TA一定是个热爱音乐的人！','active','/static/avatar/default.png',0,'2025-09-14 18:40:08','2025-09-14 18:40:08'),('f894045993994b21978248994e752f1f','JazzSoulCat','$2a$10$NJPeoz0M4ehO1ixquoTEgevaftbPnF7b0dY1SsUY2G7.ES8KRL12W','爵士老饕，从Billie Holiday到现代爵士都在收藏列表中。让爵士乐滋养你的灵魂。','active','/static/avatar/default.png',0,'2025-09-14 10:21:01','2025-09-14 10:21:01'),('fbfb64b9be454410b0a6811216fd60a1','Rose','$2a$10$TdaefcyCz5MPzI.sQ4s.JeMenbeMFfsPARVY6V7p9bc1I41eiPFNq','这个用户很神秘，还没有留下任何介绍...但相信TA一定是个热爱音乐的人！','active','/static/avatar/default.png',0,'2025-09-14 18:39:43','2025-09-14 18:39:43');

/*Table structure for table `play_history` */

CREATE TABLE `play_history` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `song_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌曲id',
  `user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `play_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加到播放历史的时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `song_id` (`song_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `play_history_ibfk_1` FOREIGN KEY (`song_id`) REFERENCES `song_music` (`song_music_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `play_history_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `music_user` (`music_user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `play_history` */

insert  into `play_history`(`id`,`song_id`,`user_id`,`play_time`) values ('0e4bcde330334188b98d9f8c556cf8ce','779e8861ba4b4e96bb62b04da292a10d','f0ded9befcf940a898de1e43d37525e1','2025-09-14 18:40:43'),('2af594d811b549e9948927c4a8ed7704','05e2c078fddc4c83af9dbec3169aeaf0','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:55:26'),('2b0fb1cbd18145f282509f5c5166cf36','f07b346360cc488db096cb91e36dd88a','af2460354f224f79ad6dc55de92da6b6','2025-09-14 15:44:53'),('2b57bf1a927d47ddb0d41eccf16576b7','ebffe27265ef4766a28185597d91077a','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:52:38'),('310a7392ff144427a7693e112dac2db8','6ef0cb5768af4743bfd7e5f83842a801','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:38:24'),('357d7d29e1ef45b4a9450644466c268d','f34dbc2dbc994648a02fe31269706cff','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:38:27'),('3a4ecc19bdc44f728fd9390b77238b00','de11a654830d4d27b5bfaec142a91835','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:55:27'),('3d32bdbf3e894e3d9207b7e59be1167d','05e2c078fddc4c83af9dbec3169aeaf0','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:55:11'),('536a309e58024a5ca6b4702ddbef6f92','6ef0cb5768af4743bfd7e5f83842a801','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:52:37'),('71ecf80588de4e52a5d4e16f47473b2b','779e8861ba4b4e96bb62b04da292a10d','96fe09d62e9e4517b9bc521aa5ece1b1','2025-09-14 13:56:09'),('7b0f5cc8fa004f87affed4f7fc43e918','779e8861ba4b4e96bb62b04da292a10d','af2460354f224f79ad6dc55de92da6b6','2025-05-27 20:36:10'),('824ee96dc1b14ae7bd1b123304a075e9','52e1210ac42c4f06a05370d19b12fd56','f0ded9befcf940a898de1e43d37525e1','2025-09-14 18:46:16'),('8416cca5af37478a8c3a1c5e61ffe06b','f34dbc2dbc994648a02fe31269706cff','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:52:35'),('84485fcc78c0426eb5d004423ac280c9','05e2c078fddc4c83af9dbec3169aeaf0','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:52:43'),('87525b7fbd83463b8da20440afff4c59','ebffe27265ef4766a28185597d91077a','af2460354f224f79ad6dc55de92da6b6','2025-09-22 20:16:18'),('89aa60ba645c478ab28b58303efa3ba7','ebffe27265ef4766a28185597d91077a','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:52:39'),('8f1d3950595146bd8db32c08eaa9f01e','f34dbc2dbc994648a02fe31269706cff','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:52:39'),('989286b6a2bf42759f17df74d4a986ba','f07b346360cc488db096cb91e36dd88a','96fe09d62e9e4517b9bc521aa5ece1b1','2025-09-14 13:57:17'),('b088858f387a4940a3f5b02311bacc5c','6ef0cb5768af4743bfd7e5f83842a801','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:52:36'),('d337d2b520124b0cac00d407292f7c49','4d6e205a9ff4489a96481b83fd133221','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:52:44'),('dabf061c056843ba8aafbf33532ea893','05e2c078fddc4c83af9dbec3169aeaf0','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:30:21'),('dc62740f4a0b46a8bfce6178e990e32f','6ef0cb5768af4743bfd7e5f83842a801','af2460354f224f79ad6dc55de92da6b6','2025-09-14 15:29:20'),('e198ef4c353c46419e688fd696b9eaa3','de11a654830d4d27b5bfaec142a91835','b576ae6fecc84750bd1aaa7f88636573','2025-05-27 20:55:09'),('ed0d785ce27e42c5b5aaa8d016ea10cd','a53b1e4b1bb64eb5bc73ad8f3280a8c9','f0ded9befcf940a898de1e43d37525e1','2025-09-14 18:40:47'),('f4b804574bee4e049ee24bc43af88f44','52e1210ac42c4f06a05370d19b12fd56','af2460354f224f79ad6dc55de92da6b6','2025-09-21 13:49:31');

/*Table structure for table `play_list` */

CREATE TABLE `play_list` (
  `play_list_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌单id',
  `music_user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '作者id',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌单标题',
  `description` longtext CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌单描述',
  `play_list_cover` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌单图片',
  `play_list_type` enum('HY','MY','LX') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT 'HY' COMMENT '歌单类型,LX：流行，MY：民谣，HY：华语',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `play_count` int NOT NULL DEFAULT '100' COMMENT '播放次数',
  PRIMARY KEY (`play_list_id`) USING BTREE,
  KEY `music_user_id` (`music_user_id`) USING BTREE,
  CONSTRAINT `play_list_ibfk_1` FOREIGN KEY (`music_user_id`) REFERENCES `music_user` (`music_user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `play_list` */

insert  into `play_list`(`play_list_id`,`music_user_id`,`title`,`description`,`play_list_cover`,`play_list_type`,`create_time`,`play_count`) values ('2b148085292844409d5f6544ce7d1839','4cd08967295846e899cefa37ab22ba23','听一首把自己感动的歌','每个人都有一首属于自己的歌，不是因为歌的旋律有多动听或歌词写的多美，而是因为这首歌在唱着你的故事。〈封面来源网络，侵删〉','/static/playlist/2.jpg','HY','2025-09-21 14:00:42',2960),('2bcde96aa229488b8dc19b66f2e88b64','f894045993994b21978248994e752f1f','英皇经典唱片 | 港味浓情 粤式英皇精选',' 英皇娱乐是香港主要的唱片公司之一，是孕育香港乐坛当红艺人的摇篮，旗下歌手在历年颁奖典礼中获奖无数。也为无数观众打造了难忘的港味记忆。','/static/playlist/5.jpg','HY','2025-09-21 14:19:04',1010),('46cf656dc3b14e4cb62bc8044cc43cac','4cd08967295846e899cefa37ab22ba23','如果你想听民谣，可以从这些歌曲开始。','歌单选取了一些比较受关注的独立民谣歌手的歌曲。今天无意中跟女朋友（现已成为老婆）分享了几首民谣，她觉得很好听，有种说不出的复杂情感和共鸣。其实民谣更多的是用平实的语言和朴实的旋律表达内心最原始而又最真挚的情感。选择了一些在常听民谣的朋友们看来“烂大街”的歌曲，然而对于大多数不听民谣的朋友来说，他们的名字都很陌生，希望可以通过我这单能让更多的朋友喜欢上民谣。希望这单能给想听民谣而又无从入耳的朋友们一个索引，更加了解民谣。其实民谣，她很好听。via独行的灵魂#NO.464（建议，随机播放，最后附上一首IU翻唱的斑马，斑马。虽然她不是民谣歌手，但她蹩脚的发音和充沛的感情打动了我。）\r\n部分歌曲因为没有版权，无法下载、收听。','/static/playlist/4.jpg','MY','2025-09-21 14:17:26',5009),('4929615ead544f319d6d5454927cee79','34b1c93ecd5c459e931b4e24204434fe','2020s华语民谣 | 简单质朴 打动人心','心情糟糕的时候 安静地听一首民谣 让音乐驱散情绪','/static/playlist/14.jpg','MY','2025-09-21 11:03:41',1089334),('4d4d504d2c2948ae8224c68c0399a879','b576ae6fecc84750bd1aaa7f88636573','青春经典 | 难以忘怀的青春旋律','年少时的我们 人间琐碎都囿于一方课桌之间 全部的少年心事都源于目光追逐的身影','/static/playlist/10.jpg','LX','2025-09-21 14:18:12',7594157),('5b547b3ca2754c28872f0422f1d403ab','34b1c93ecd5c459e931b4e24204434fe','秋日民谣 | 品味秋的深情旋律','在吉他与人声的交织中 感受那份深藏在心底的宁静','/static/playlist/13.jpg','MY','2025-09-21 11:10:23',671585),('65300a8ce6bd473eac1db32da6d04207','28dce50b4df44ab1a78a5863b6bcf046','傍晚驾车 | 与KPop一起驾车追逐日落',' 水汽冒泡的傍晚 在余晖中轻轻转动你的方向盘','/static/playlist/8.jpg','LX','2025-05-13 15:47:56',133981),('7e6a8bd487f6433b9fb7bf5cd42cc2b8','34b1c93ecd5c459e931b4e24204434fe','学习听民谣 | 埋头学习时听的温柔旋律','温柔旋律与你作伴 书山题海在你身旁 孜孜不倦的用脚步丈量长夜 迎接前路的光','/static/playlist/11.jpg','HY','2025-09-21 11:19:53',1133665),('82f8ba02baa84df4bc577c420d319f58','28dce50b4df44ab1a78a5863b6bcf046','韩国抒情正流行 | 金在中 IU领衔韩系情歌之旅',' 最新K-Pop抒情曲趋势合集 令人沉醉的温柔深情','/static/playlist/7.jpg','LX','2025-05-13 15:48:00',470135),('a022edb67edc4de88d8eb65ec148a52f','6e1644fa268242659b5879bf0784f282','国风R&B大赏 | 律动灵魂中的东方韵味','千禧年间掀起了兼具东方美学和西方韵律的中国风R&B潮流 如今一代又一代的音乐人仍在创新续写 一起来听','/static/playlist/6.jpg','HY','2025-05-13 15:48:02',100),('a45da9a31b3d4e079e80b41d9911347c','4cd08967295846e899cefa37ab22ba23','星空律动 | 聆听乡村夜晚的舒缓旋律',' 聆听乡村夜晚的舒缓旋律','/static/playlist/15.jpg','MY','2025-05-13 15:48:05',46213),('aafe2522b1e64ca1bd92a46068ff17c3','384c945a63b3439fa4abb61f7cf69d3c','不得了！这些英文歌的热评信息量好大……',' 云村故事会系列第二单，英文歌专场，封面Drake(同款神评论日语/电音歌单在我主页可见)——\r\n\r\n自古网易多段子手，相信大家已经领略过了。但是你有没有想过当段子手们开始玩接龙……那可就没这么简单了。通常...','/static/playlist/3.jpg','HY','2025-05-13 15:48:07',53000),('d03764bf095946ab8ae38dc496bb16a4','34b1c93ecd5c459e931b4e24204434fe','民谣摇篮曲 | 跌入夜的温柔梦境','跌入夜的温柔梦境','/static/playlist/12.jpg','MY','2025-05-20 19:42:59',30655),('d7601f04330b4c0a9284c32ed39b7af1','f894045993994b21978248994e752f1f','\r\n重返2013 | 从李白开启2013金曲之旅','2013经典歌曲精选','/static/playlist/9.jpg','LX','2025-05-13 15:48:11',373533),('e32200e9d3b1497e8a4b706dd2a6b42c','7d9e27309ad2421ea726352f5aca3d98','我爱你 第一句是假的 第二句也是假的','周星驰：我养你啊！\r\n苏轼：不思量，自难忘。\r\n黄伟文：余生请你指教。\r\n王家卫：那一刻，我很暖。\r\n张学友：很想带你去吹吹风。\r\n廖凡：今日起，我以你为约束。\r\n范仲淹：酒入愁肠，化作相思泪。\r\n玛格丽特：我在床上，饭在锅里。\r\n李白：郎骑竹马来，绕床弄青梅。\r\n张爱玲：你还不来，我怎敢老去。\r\n钱武肃王：陌上花开，可缓缓归矣。\r\n钱锺书：我只想要个女儿，像你的。','/static/playlist/1.jpg','HY','2025-05-13 15:48:12',2705);

/*Table structure for table `singer` */

CREATE TABLE `singer` (
  `singer_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌手id',
  `singer_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌手姓名',
  PRIMARY KEY (`singer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `singer` */

insert  into `singer`(`singer_id`,`singer_name`) values ('0b0ff312b18a4347a37fce521a67dd1c','赵雷'),('0c7c6cea71e04d2d9a54d20e900d9e4d','摩登兄弟刘宇宁'),('2c4c754b1db6457194281c5000afe79e','水木年华'),('3d3152b00f054896ac26cccf4f36c750','王赫野'),('49b56e2f5d1345199be0e7adfae96402','周深'),('4ebd84d9e413457ca4d64707dcf0994c','黄子弘凡'),('582cd22401794fd08de98be20dfd6d3d','陈奕迅'),('723bf87a92b04d8db54f1005ec40e8d2','黄龄'),('753dee4a584c4d3796e3a1f711b2be64','郭龙'),('790a17839db44ee5804e90c613314c67','梦幻西游'),('8b9dae378033463995378c3c6dbaae37','谭维维'),('96cbb04596d9453c92090792ebb85420','朴树'),('a10e5b47cc164742bc9e1df32dab89b6','张韶涵'),('a1df2529c27f465a9f9d8eeed45ef033','王菲'),('b05da0561e0044ce9742f4813905590a','暗杠'),('b0acd4d500fe4216800c2a8642041b5d','张玮玮'),('b4596e586f9a4806845b81f7ee94d9ad','邵帅'),('b9363568406445e39db645aa55f4266b','谢霆锋'),('b9bba7e1e3b44b409ce36281cd832f0b','张过年'),('bf50623ad71f465dbe90f8c180a67790','宋冬野'),('c49c7318d02c4a39a5b929bb7a3e8028','姚晓棠'),('e8c4187ce13f471aab80ec171971316c','羽泉'),('f233ec149225407c9193afe0432574e9','马頔');

/*Table structure for table `song_and_play_list` */

CREATE TABLE `song_and_play_list` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌单对应歌曲',
  `play_list_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌单id',
  `song_music_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌曲id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `play_list_id` (`play_list_id`) USING BTREE,
  KEY `song_music_id` (`song_music_id`) USING BTREE,
  CONSTRAINT `song_and_play_list_ibfk_1` FOREIGN KEY (`play_list_id`) REFERENCES `play_list` (`play_list_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `song_and_play_list_ibfk_2` FOREIGN KEY (`song_music_id`) REFERENCES `song_music` (`song_music_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `song_and_play_list` */

insert  into `song_and_play_list`(`id`,`play_list_id`,`song_music_id`) values ('14209b976fdb4616a5df5b1bf0997530','7e6a8bd487f6433b9fb7bf5cd42cc2b8','779e8861ba4b4e96bb62b04da292a10d'),('14f0346d15d643f9ba66829586782e2e','2b148085292844409d5f6544ce7d1839','4d6e205a9ff4489a96481b83fd133221'),('158a0936549248198b9494dfe151270b','e32200e9d3b1497e8a4b706dd2a6b42c','ce95b5e3fa964b4e95cc477c06d68e32'),('1ea9bf75a740472999aedd6dbad284b6','2b148085292844409d5f6544ce7d1839','779e8861ba4b4e96bb62b04da292a10d'),('2b169fd18f8a4b13954ff8f50dc7fdbb','aafe2522b1e64ca1bd92a46068ff17c3','d0ae31402c97425cb00735d2b2138d77'),('30c36ec46f4e4254aa10d278518f08aa','d03764bf095946ab8ae38dc496bb16a4','8a788e4541544fc19b54883189313117'),('3adf578e41954b5682b4ab550e7e9a80','5b547b3ca2754c28872f0422f1d403ab','5498219a98ad44b0a8992f40e0463a7e'),('48b3c6dc58e446a4a5d38b777691cc36','46cf656dc3b14e4cb62bc8044cc43cac','a6e20704d8094c899f40ba1209fe5ad0'),('4ad98ea77e174e5ca6931c9a0dad6af6','5b547b3ca2754c28872f0422f1d403ab','f07b346360cc488db096cb91e36dd88a'),('5dd7752f48694026b2d842acd1ecf527','e32200e9d3b1497e8a4b706dd2a6b42c','a53b1e4b1bb64eb5bc73ad8f3280a8c9'),('60372d06721246bc91eb7b0722be9bf3','aafe2522b1e64ca1bd92a46068ff17c3','ce95b5e3fa964b4e95cc477c06d68e32'),('632a4d335fe7414fa819b30d58578e26','d03764bf095946ab8ae38dc496bb16a4','f07b346360cc488db096cb91e36dd88a'),('66ea5971a01c4ce9bedbe6676fc60997','a45da9a31b3d4e079e80b41d9911347c','f07b346360cc488db096cb91e36dd88a'),('6ae4860ccc6643b48a211761cbe67f1e','e32200e9d3b1497e8a4b706dd2a6b42c','d0ae31402c97425cb00735d2b2138d77'),('6b946a9f5196412bb5a86dd93b8087aa','46cf656dc3b14e4cb62bc8044cc43cac','5498219a98ad44b0a8992f40e0463a7e'),('6f06e27a85fe4ce38494c5ed88b95a75','5b547b3ca2754c28872f0422f1d403ab','48c4c11b712c4ae49ea1464b71d47c68'),('70b42799ff81431389a5b82834ab3e53','a022edb67edc4de88d8eb65ec148a52f','de11a654830d4d27b5bfaec142a91835'),('8e8f3f5b58df42258ead8d402237ff33','d7601f04330b4c0a9284c32ed39b7af1','8a788e4541544fc19b54883189313117'),('90c2d564ed594859905d4ad443daad3f','4929615ead544f319d6d5454927cee79','c167dc43de854934887e31237679725b'),('9276efa51a89421fb80fd6de20fd43b5','4929615ead544f319d6d5454927cee79','52e1210ac42c4f06a05370d19b12fd56'),('9b7cbfe4151a4df6b01e9fcbe7821c9e','a45da9a31b3d4e079e80b41d9911347c','a6e20704d8094c899f40ba1209fe5ad0'),('9c3bcee55dcb466c980de243b5e65986','4d4d504d2c2948ae8224c68c0399a879','f07b346360cc488db096cb91e36dd88a'),('9d40a4bbb57748d9a9224c958064f70a','65300a8ce6bd473eac1db32da6d04207','792071e0caca482da18bb795d5979a7c'),('a7810100ba234ef8afaa9d169606fc42','7e6a8bd487f6433b9fb7bf5cd42cc2b8','05e2c078fddc4c83af9dbec3169aeaf0'),('a82a577f75ad42d19cf44a918a60ee42','5b547b3ca2754c28872f0422f1d403ab','a6e20704d8094c899f40ba1209fe5ad0'),('a9c465f564f54b8aa8750b0838dd5ca6','7e6a8bd487f6433b9fb7bf5cd42cc2b8','de11a654830d4d27b5bfaec142a91835'),('a9d774d743704cb3865796eff6aa5511','aafe2522b1e64ca1bd92a46068ff17c3','a53b1e4b1bb64eb5bc73ad8f3280a8c9'),('ad8cfb8e08a24ba3aa8c4a8b0dbfb599','2bcde96aa229488b8dc19b66f2e88b64','d0ae31402c97425cb00735d2b2138d77'),('b52765db3a504586b313c32d67b43715','46cf656dc3b14e4cb62bc8044cc43cac','48c4c11b712c4ae49ea1464b71d47c68'),('be3f6558ad674f06ba7f05942bf60f0e','a45da9a31b3d4e079e80b41d9911347c','39328d44f3e8437ea1743217400f1b99'),('c152ebbf7bf9485da322d343627bf204','82f8ba02baa84df4bc577c420d319f58','f07b346360cc488db096cb91e36dd88a'),('c38b118b49954d939448b2be84b0557d','65300a8ce6bd473eac1db32da6d04207','f07b346360cc488db096cb91e36dd88a'),('c4d52b0f519c425386416eea6883c6c2','82f8ba02baa84df4bc577c420d319f58','8a788e4541544fc19b54883189313117'),('ce47115e38f9446ebdccb0faeb443549','65300a8ce6bd473eac1db32da6d04207','8a788e4541544fc19b54883189313117'),('d23da95728ea43f38a9f5e2a7c561c05','2bcde96aa229488b8dc19b66f2e88b64','05e2c078fddc4c83af9dbec3169aeaf0'),('d8f4346ed0ac457c846a71db0c35c56c','2bcde96aa229488b8dc19b66f2e88b64','de11a654830d4d27b5bfaec142a91835'),('dc9e22462f20458ea7bb939b01148408','2b148085292844409d5f6544ce7d1839','a53b1e4b1bb64eb5bc73ad8f3280a8c9'),('dd4b96795b704ab78cb8f64beaa3e701','5b547b3ca2754c28872f0422f1d403ab','8a788e4541544fc19b54883189313117'),('ddaa2ad941224e4da3dddabd2c241500','d7601f04330b4c0a9284c32ed39b7af1','f07b346360cc488db096cb91e36dd88a'),('dktnd4kai3nlcdi6lsi45hcdsnvr63dj','4d4d504d2c2948ae8224c68c0399a879','6ef0cb5768af4743bfd7e5f83842a801'),('e9f6c4647d3b478c81e051bf4f8ce7a8','a022edb67edc4de88d8eb65ec148a52f','779e8861ba4b4e96bb62b04da292a10d'),('ea4c6c8514184b2f92f890507ff019c4','2bcde96aa229488b8dc19b66f2e88b64','1f00e6d8d47d4450b3e7ffafd6a36358'),('ede050cd8b2049e1a32203adbe55b9b6','4d4d504d2c2948ae8224c68c0399a879','8a788e4541544fc19b54883189313117'),('eds2nd4tdu46nfdyenvls3ofmxy68ksr','4d4d504d2c2948ae8224c68c0399a879','a6e20704d8094c899f40ba1209fe5ad0'),('efd42c76b206445e9ee85d10b6a6f6cf','7e6a8bd487f6433b9fb7bf5cd42cc2b8','4d6e205a9ff4489a96481b83fd133221');

/*Table structure for table `song_music` */

CREATE TABLE `song_music` (
  `song_music_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '歌曲id',
  `song_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌曲名称',
  `cover_image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '播放列表封面的图片路径',
  `song_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '歌曲路径',
  `duration` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '00:00' COMMENT '歌曲时长',
  `is_vip` int NOT NULL DEFAULT '0' COMMENT '该歌曲是否是vip才能播放，1是，0不是',
  `type` enum('ZH','EA','KR') CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT 'ZH' COMMENT '歌曲类型\r\nZH：华语\r\nEA：欧美\r\nKR：韩国',
  `play_count` int NOT NULL DEFAULT '0' COMMENT '歌曲播放次数',
  `is_new_music` int NOT NULL DEFAULT '0' COMMENT '是否是新歌，1是，0不是',
  PRIMARY KEY (`song_music_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `song_music` */

insert  into `song_music`(`song_music_id`,`song_name`,`cover_image`,`song_url`,`duration`,`is_vip`,`type`,`play_count`,`is_new_music`) values ('05e2c078fddc4c83af9dbec3169aeaf0','十年','/static/coverImage/5.jpg','/static/music/5.mp3','03:25',1,'ZH',55,0),('1f00e6d8d47d4450b3e7ffafd6a36358','单车','/static/coverImage/6.jpg','/static/music/6.mp3','03:31',0,'ZH',25,0),('39328d44f3e8437ea1743217400f1b99','你是人间四月天（Live）','/static/coverImage/16.jpg','/static/music/16.mp3','03:52',0,'ZH',30,0),('48c4c11b712c4ae49ea1464b71d47c68','纸船摇','/static/coverImage/9.jpg','/static/music/9.mp3','03:59',1,'ZH',50,1),('4d6e205a9ff4489a96481b83fd133221','酒馆','/static/coverImage/2.jpg','/static/music/2.mp3','02:05',1,'ZH',65,0),('52e1210ac42c4f06a05370d19b12fd56','且听风吟','/static/coverImage/12.jpg','/static/music/12.mp3','03:43',0,'ZH',87,1),('5498219a98ad44b0a8992f40e0463a7e','时间里的','/static/coverImage/10.jpg','/static/music/10.mp3','05:43',0,'ZH',36,0),('6ef0cb5768af4743bfd7e5f83842a801','转身即心痛 (Live版)','/static/coverImage/19.jpg','/static/music/19.mp3','03:38',0,'ZH',197,1),('779e8861ba4b4e96bb62b04da292a10d','深夜的歌','/static/coverImage/1.jpg','/static/music/1.mp3','04:46',0,'ZH',43,0),('792071e0caca482da18bb795d5979a7c','红豆','/static/coverImage/15.jpg','/static/music/15.mp3','04:16',0,'ZH',17,0),('8a788e4541544fc19b54883189313117','卷珠帘','/static/coverImage/13.jpg','/static/music/13.mp3','02:46',0,'ZH',44,0),('a53b1e4b1bb64eb5bc73ad8f3280a8c9','莉莉安','/static/coverImage/3.jpg','/static/music/3.mp3','04:12',0,'ZH',45,0),('a6e20704d8094c899f40ba1209fe5ad0','成都（Live）','/static/coverImage/8.jpg','/static/music/8.mp3','05:29',0,'ZH',27,0),('c167dc43de854934887e31237679725b','米店 (2008北京live)','/static/coverImage/11.jpg','/static/music/11.mp3','05:50',0,'ZH',451,0),('ce95b5e3fa964b4e95cc477c06d68e32','最美','/static/coverImage/17.jpg','/static/music/17.mp3','05:00',0,'ZH',241,1),('d0ae31402c97425cb00735d2b2138d77','淋雨一直走','/static/coverImage/7.jpg','/static/music/7.mp3','03:24',0,'ZH',60,0),('de11a654830d4d27b5bfaec142a91835','香水','/static/coverImage/4.jpg','/static/music/4.mp3','04:29',0,'ZH',32,0),('ebffe27265ef4766a28185597d91077a','座位 (Live版)','/static/coverImage/18.jpg','/static/music/18.mp3','03:38',0,'ZH',96,1),('f07b346360cc488db096cb91e36dd88a','牵丝戏','/static/coverImage/14.jpg','/static/music/14.mp3','04:11',0,'ZH',51,0),('f34dbc2dbc994648a02fe31269706cff','土坡上的狗尾草 (Live版)','/static/coverImage/20.jpg','/static/music/20.mp3','03:26',1,'ZH',36,1);

/*Table structure for table `swiper` */

CREATE TABLE `swiper` (
  `swiper_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图id',
  `swiper_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图地址',
  PRIMARY KEY (`swiper_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `swiper` */

insert  into `swiper`(`swiper_id`,`swiper_url`) values ('37ee3ed64de34139ba20135de1b6f307','/static/swiper/cover3.jpg'),('b34f3e52107c45439b0b2beb502124cd','/static/swiper/cover1.jpg'),('fd92f50813664d098f3840fbbcfd6593','/static/swiper/cover2.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
