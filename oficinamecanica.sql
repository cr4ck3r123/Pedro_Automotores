-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 02, 2023 at 08:47 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oficinamecanica`
--

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
  `idcategoria` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tel_casa` varchar(45) DEFAULT NULL,
  `cel_whatssap` varchar(45) DEFAULT NULL,
  `id_endereco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`idcliente`, `nome`, `cpf`, `email`, `tel_casa`, `cel_whatssap`, `id_endereco`) VALUES
(1, 'FERNANDO XAVIER ', '061.651.719.07', 'suportewicell@gmail.com', '(45) 3025-2633', '(45) 99117-4316', 1),
(2, 'KAUA EDUARDO GOULART DE OLIVEIRA', '991.174.316.16', 'teste@gmail.com', '(45) 3025-2633', '(45) 99117-4316', 2),
(3, 'MAURICIO OLIVEIRA', '123.456.789.00', 'teste@gmail.com', '(45) 3025-2633', '(45) 99999-9999', 3);

-- --------------------------------------------------------

--
-- Table structure for table `endereco`
--

CREATE TABLE `endereco` (
  `idendereco` int(11) NOT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `numero` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `cep` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL,
  `complemento` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `endereco`
--

INSERT INTO `endereco` (`idendereco`, `rua`, `numero`, `bairro`, `cidade`, `cep`, `estado`, `pais`, `complemento`) VALUES
(1, 'RUA BELFORD DUARTE JARDIM S?O MIGUEL', '1287', 'MORUMBI', 'FOZ DO IGUA?U', '85858480', NULL, NULL, 'CASA'),
(2, 'AVENIDA JULES RIMET BEVERLY FALLS PARK', '1287', 'CAMPOS DO IGUA?U', 'FOZ DO IGUA?U', '85858000', NULL, NULL, 'CASA'),
(3, 'AVENIDA REPÚBLICA ARGENTINA VL MARACAN?/BOM JESUS/MATILDE/JD ESMERALDA/TAROBÁ/', '1234', 'CENTRO', 'FOZ DO IGUA?U', '85851200', NULL, NULL, 'CASA');

-- --------------------------------------------------------

--
-- Table structure for table `funcionario`
--

CREATE TABLE `funcionario` (
  `idfuncionario` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `tel_whatssap` varchar(45) DEFAULT NULL,
  `endereco_idendereco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `marca`
--

CREATE TABLE `marca` (
  `idMarca` int(11) NOT NULL,
  `nome_marca` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `marca`
--

INSERT INTO `marca` (`idMarca`, `nome_marca`) VALUES
(1, 'CHEVROLET'),
(2, 'VOLKSWAGEN'),
(3, 'FIAT'),
(4, 'MERCEDES-BENZ'),
(5, 'CITROEN'),
(6, 'CHANA'),
(7, 'HONDA'),
(8, 'SUBARU'),
(10, 'FERRARI'),
(11, 'BUGATTI'),
(12, 'LAMBORGHINI'),
(13, 'FORD'),
(14, 'HYUNDAI'),
(15, 'JAC'),
(16, 'KIA'),
(17, 'GURGEL'),
(18, 'DODGE'),
(19, 'CHRYSLER'),
(20, 'BENTLEY'),
(21, 'SSANGYONG'),
(22, 'PEUGEOT'),
(23, 'TOYOTA'),
(24, 'RENAULT'),
(25, 'ACURA'),
(26, 'ADAMO'),
(27, 'AGRALE'),
(28, 'ALFA ROMEO'),
(29, 'AMERICAR'),
(31, 'ASTON MARTIN'),
(32, 'AUDI'),
(34, 'BEACH'),
(35, 'BIANCO'),
(36, 'BMW'),
(37, 'BORGWARD'),
(38, 'BRILLIANCE'),
(41, 'BUICK'),
(42, 'CBT'),
(43, 'NISSAN'),
(44, 'CHAMONIX'),
(46, 'CHEDA'),
(47, 'CHERY'),
(48, 'CORD'),
(49, 'COYOTE'),
(50, 'CROSS LANDER'),
(51, 'DAEWOO'),
(52, 'DAIHATSU'),
(53, 'VOLVO'),
(54, 'DE SOTO'),
(55, 'DETOMAZO'),
(56, 'DELOREAN'),
(57, 'DKW-VEMAG'),
(59, 'SUZUKI'),
(60, 'EAGLE'),
(61, 'EFFA'),
(63, 'ENGESA'),
(64, 'ENVEMO'),
(65, 'FARUS'),
(66, 'FERCAR'),
(68, 'FNM'),
(69, 'PONTIAC'),
(70, 'PORSCHE'),
(72, 'GEO'),
(74, 'GRANCAR'),
(75, 'GREAT WALL'),
(76, 'HAFEI'),
(78, 'HOFSTETTER'),
(79, 'HUDSON'),
(80, 'HUMMER'),
(82, 'INFINITI'),
(83, 'INTERNATIONAL'),
(86, 'JAGUAR'),
(87, 'JEEP'),
(88, 'JINBEI'),
(89, 'JPX'),
(90, 'KAISER'),
(91, 'KOENIGSEGG'),
(92, 'LAUTOMOBILE'),
(93, 'LAUTOCRAFT'),
(94, 'LADA'),
(95, 'LANCIA'),
(96, 'LAND ROVER'),
(97, 'LEXUS'),
(98, 'LIFAN'),
(99, 'LINCOLN'),
(100, 'LOBINI'),
(101, 'LOTUS'),
(102, 'MAHINDRA'),
(104, 'MASERATI'),
(106, 'MATRA'),
(107, 'MAYBACH'),
(108, 'MAZDA'),
(109, 'MENON'),
(110, 'MERCURY'),
(111, 'MITSUBISHI'),
(112, 'MG'),
(113, 'MINI'),
(114, 'MIURA'),
(115, 'MORRIS'),
(116, 'MP LAFER'),
(117, 'MPLM'),
(118, 'NEWTRACK'),
(119, 'NISSIN'),
(120, 'OLDSMOBILE'),
(121, 'PAG'),
(122, 'PAGANI'),
(123, 'PLYMOUTH'),
(124, 'PUMA'),
(125, 'RENO'),
(126, 'REVA-I'),
(127, 'ROLLS-ROYCE'),
(129, 'ROMI'),
(130, 'SEAT'),
(131, 'UTILITARIOS AGRICOLAS'),
(132, 'SHINERAY'),
(137, 'SAAB'),
(139, 'SHORT'),
(141, 'SIMCA'),
(142, 'SMART'),
(143, 'SPYKER'),
(144, 'STANDARD'),
(145, 'STUDEBAKER'),
(146, 'TAC'),
(147, 'TANGER'),
(148, 'TRIUMPH'),
(149, 'TROLLER'),
(150, 'UNIMOG'),
(154, 'WIESMANN'),
(155, 'CADILLAC'),
(156, 'AM GEN'),
(157, 'BUGGY'),
(158, 'WILLYS OVERLAND'),
(161, 'KASEA'),
(162, 'SATURN'),
(163, 'SWELL MINI'),
(175, 'SKODA'),
(239, 'KARMANN GHIA'),
(254, 'KART'),
(258, 'HANOMAG'),
(261, 'OUTROS'),
(265, 'HILLMAN'),
(288, 'HRG'),
(295, 'GAIOLA'),
(338, 'TATA'),
(341, 'DITALLY'),
(345, 'RELY'),
(346, 'MCLAREN'),
(534, 'GEELY');

-- --------------------------------------------------------

--
-- Table structure for table `modelo`
--

CREATE TABLE `modelo` (
  `idmodelo` int(11) NOT NULL,
  `id_marca` int(11) NOT NULL,
  `nome_modelo` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `modelo`
--

INSERT INTO `modelo` (`idmodelo`, `id_marca`, `nome_modelo`) VALUES
(1, 25, 'INTEGRA'),
(2, 25, 'LEGEND'),
(3, 25, 'NSX'),
(4, 27, 'MARRUA'),
(5, 28, '145'),
(6, 28, '147'),
(7, 28, '155'),
(8, 28, '156'),
(9, 28, '164'),
(10, 28, '166'),
(11, 28, '2300'),
(12, 28, 'SPIDER'),
(13, 156, 'HUMMER'),
(14, 16, 'AM-825'),
(15, 16, 'HI-TOPIC'),
(16, 16, 'ROCSTA'),
(17, 16, 'TOPIC'),
(18, 16, 'TOWNER'),
(19, 32, '100'),
(20, 32, '80'),
(21, 32, 'A1'),
(22, 32, 'A3'),
(23, 32, 'A4 SEDAN'),
(24, 32, 'A5 COUPE'),
(25, 32, 'A6 SEDAN'),
(26, 32, 'A7'),
(27, 32, 'A8'),
(30, 32, 'Q3'),
(31, 32, 'Q5'),
(32, 32, 'Q7'),
(33, 32, 'R8'),
(34, 32, 'RS3'),
(35, 32, 'RS4'),
(36, 32, 'RS5'),
(37, 32, 'RS6'),
(38, 32, 'S3'),
(39, 32, 'S4 SEDAN'),
(40, 32, 'S6 SEDAN'),
(41, 32, 'S8'),
(42, 32, 'TT'),
(43, 32, 'TTS'),
(86, 157, 'BUGGY'),
(87, 155, 'DEVILLE'),
(88, 155, 'ELDORADO'),
(89, 155, 'SEVILLE'),
(90, 42, 'JAVALI'),
(92, 6, 'MINI STAR FAMILY'),
(93, 6, 'MINI STAR UTILITY'),
(95, 47, 'CIELO'),
(96, 47, 'FACE'),
(97, 47, 'QQ'),
(98, 47, 'S-18'),
(99, 47, 'TIGGO'),
(100, 19, '300C'),
(101, 19, 'CARAVAN'),
(102, 19, 'CIRRUS'),
(103, 19, 'GRAN CARAVAN'),
(104, 19, 'LE BARON'),
(105, 19, 'NEON'),
(106, 19, 'PT CRUISER'),
(107, 19, 'SEBRING'),
(108, 19, 'STRATUS'),
(109, 19, 'TOWN E COUNTRY'),
(110, 19, 'VISION'),
(111, 5, 'AIRCROSS'),
(112, 5, 'AX'),
(113, 5, 'BERLINGO'),
(114, 5, 'BX'),
(115, 5, 'C3'),
(116, 5, 'C4'),
(117, 5, 'C5'),
(118, 5, 'C6'),
(119, 5, 'C8'),
(120, 5, 'DS3'),
(121, 5, 'EVASION'),
(122, 5, 'JUMPER'),
(123, 5, 'XANTIA'),
(124, 5, 'XM'),
(125, 5, 'XSARA'),
(126, 5, 'ZX'),
(127, 50, 'CL-244'),
(128, 50, 'CL-330'),
(129, 51, 'ESPERO'),
(130, 51, 'LANOS'),
(131, 51, 'LEGANZA'),
(132, 51, 'NUBIRA'),
(133, 51, 'PRINCE'),
(134, 51, 'RACER'),
(135, 51, 'SUPER SALON'),
(136, 51, 'TICO'),
(137, 52, 'APPLAUSE'),
(138, 52, 'CHARADE'),
(139, 52, 'CUORE'),
(140, 52, 'FEROZA'),
(141, 52, 'GRAN MOVE'),
(142, 52, 'MOVE VAN'),
(143, 52, 'TERIOS'),
(144, 18, 'AVENGER'),
(145, 18, 'DAKOTA'),
(146, 18, 'JOURNEY'),
(147, 18, 'RAM'),
(148, 18, 'STEALTH'),
(151, 61, 'M-100'),
(152, 61, 'PLUTUS'),
(153, 61, 'START'),
(155, 61, 'ULC'),
(158, 63, 'ENGESA'),
(159, 64, 'CAMPER'),
(160, 64, 'MASTER'),
(161, 10, '348'),
(162, 10, '355'),
(163, 10, '360'),
(164, 10, '456'),
(165, 10, '550'),
(166, 10, '575M'),
(167, 10, '612'),
(168, 10, 'CALIFORNIA'),
(169, 10, 'F430'),
(170, 10, 'F599'),
(171, 3, '147'),
(174, 3, '500'),
(175, 3, 'BRAVA'),
(176, 3, 'BRAVO'),
(178, 3, 'COUPE'),
(179, 3, 'DOBLO'),
(180, 3, 'DUCATO CARGO'),
(181, 3, 'DUNA'),
(182, 3, 'ELBA'),
(183, 3, 'FIORINO'),
(184, 3, 'FREEMONT'),
(185, 3, 'GRAND SIENA'),
(186, 3, 'IDEA'),
(187, 3, 'LINEA'),
(188, 3, 'MAREA'),
(189, 3, 'OGGI'),
(190, 3, 'PALIO'),
(191, 3, 'PANORAMA'),
(192, 3, 'PREMIO'),
(193, 3, 'PUNTO'),
(194, 3, 'SIENA'),
(195, 3, 'STILO'),
(196, 3, 'STRADA'),
(197, 3, 'TEMPRA'),
(198, 3, 'TIPO'),
(199, 3, 'UNO'),
(201, 13, 'AEROSTAR'),
(202, 13, 'ASPIRE'),
(203, 13, 'BELINA'),
(204, 13, 'CLUB WAGON'),
(205, 13, 'CONTOUR'),
(206, 13, 'CORCEL II'),
(207, 13, 'COURIER'),
(208, 13, 'CROWN VICTORIA'),
(209, 13, 'DEL REY'),
(210, 13, 'ECOSPORT'),
(211, 13, 'EDGE'),
(212, 13, 'ESCORT'),
(213, 13, 'EXPEDITION'),
(214, 13, 'EXPLORER'),
(215, 13, 'F-100'),
(216, 13, 'F-1000'),
(217, 13, 'F-150'),
(218, 13, 'F-250'),
(219, 13, 'FIESTA'),
(220, 13, 'FOCUS'),
(221, 13, 'FURGLAINE'),
(222, 13, 'FUSION'),
(223, 13, 'IBIZA'),
(224, 13, 'KA'),
(225, 13, 'MONDEO'),
(226, 13, 'MUSTANG'),
(227, 13, 'PAMPA'),
(228, 13, 'PROBE'),
(229, 13, 'RANGER'),
(230, 13, 'VERSAILLES ROYALE'),
(231, 13, 'TAURUS'),
(232, 13, 'THUNDERBIRD'),
(233, 13, 'TRANSIT'),
(234, 13, 'VERONA'),
(235, 13, 'VERSAILLES'),
(236, 13, 'WINDSTAR'),
(238, 1, 'A-10'),
(239, 1, 'A-20'),
(240, 1, 'AGILE'),
(241, 1, 'ASTRA'),
(242, 1, 'BLAZER'),
(243, 1, 'BONANZA'),
(245, 1, 'C-10'),
(246, 1, 'C-20'),
(247, 1, 'CALIBRA'),
(248, 1, 'CAMARO'),
(249, 1, 'CAPRICE'),
(250, 1, 'CAPTIVA'),
(251, 1, 'CARAVAN'),
(252, 1, 'CAVALIER'),
(253, 1, 'CELTA'),
(255, 1, 'CHEVY'),
(256, 1, 'CHEYNNE'),
(258, 1, 'COBALT'),
(259, 1, 'CORSA'),
(262, 1, 'CORVETTE'),
(263, 1, 'CRUZE'),
(264, 1, 'D-10'),
(265, 1, 'D-20'),
(266, 1, 'IPANEMA'),
(267, 1, 'KADETT'),
(268, 1, 'LUMINA'),
(269, 1, 'MALIBU'),
(271, 1, 'MERIVA'),
(272, 1, 'MONTANA'),
(274, 1, 'OMEGA'),
(275, 1, 'OPALA'),
(276, 1, 'PRISMA'),
(277, 1, 'S10'),
(280, 1, 'SILVERADO'),
(281, 1, 'SONIC'),
(282, 1, 'SONOMA'),
(283, 1, 'SPACEVAN'),
(284, 1, 'SS10'),
(285, 1, 'SUBURBAN'),
(287, 1, 'SYCLONE'),
(288, 1, 'TIGRA'),
(289, 1, 'TRACKER'),
(290, 1, 'TRAFIC'),
(291, 1, 'VECTRA'),
(292, 1, 'VERANEIO'),
(293, 1, 'ZAFIRA'),
(294, 75, 'HOVER'),
(295, 17, 'BR-800'),
(296, 17, 'CARAJAS'),
(297, 17, 'TOCANTINS'),
(298, 17, 'XAVANTE'),
(299, 17, 'VIP'),
(300, 76, 'TOWNER'),
(301, 7, 'ACCORD'),
(302, 7, 'CITY'),
(303, 7, 'CIVIC'),
(304, 7, 'CR-V'),
(305, 7, 'FIT'),
(306, 7, 'ODYSSEY'),
(307, 7, 'PASSPORT'),
(308, 7, 'PRELUDE'),
(309, 14, 'ACCENT'),
(310, 14, 'ATOS'),
(311, 14, 'AZERA'),
(312, 14, 'COUPE'),
(314, 14, 'ELANTRA'),
(315, 14, 'EXCEL'),
(316, 14, 'GALLOPER'),
(317, 14, 'GENESIS'),
(318, 14, 'H1'),
(319, 14, 'H100'),
(321, 14, 'I30'),
(323, 14, 'IX35'),
(324, 14, 'MATRIX'),
(325, 14, 'PORTER'),
(326, 14, 'SANTA FE'),
(327, 14, 'SCOUPE'),
(328, 14, 'SONATA'),
(329, 14, 'TERRACAN'),
(330, 14, 'TRAJET'),
(331, 14, 'TUCSON'),
(332, 14, 'VELOSTER'),
(333, 14, 'VERACRUZ'),
(337, 15, 'J3'),
(338, 15, 'J5'),
(339, 15, 'J6'),
(340, 86, 'DAIMLER'),
(341, 86, 'S-TYPE'),
(342, 86, 'X-TYPE'),
(345, 86, 'MODELOS XJ'),
(352, 86, 'MODELOS XK'),
(354, 87, 'CHEROKEE'),
(355, 87, 'COMMANDER'),
(356, 87, 'COMPASS'),
(357, 87, 'GRAND CHEROKEE'),
(358, 87, 'WRANGLER'),
(359, 88, 'TOPIC VAN'),
(360, 89, 'JIPE MONTEZ'),
(361, 89, 'PICAPE MONTEZ'),
(362, 16, 'BESTA'),
(363, 16, 'BONGO'),
(364, 16, 'CADENZA'),
(365, 16, 'CARENS'),
(366, 16, 'CARNIVAL'),
(367, 16, 'CERATO'),
(368, 16, 'CERES'),
(369, 16, 'CLARUS'),
(370, 16, 'MAGENTIS'),
(371, 16, 'MOHAVE'),
(372, 16, 'OPIRUS'),
(373, 16, 'OPTIMA'),
(374, 16, 'PICANTO'),
(375, 16, 'SEPHIA'),
(376, 16, 'SHUMA'),
(377, 16, 'SORENTO'),
(378, 16, 'SOUL'),
(379, 16, 'SPORTAGE'),
(380, 94, 'LAIKA'),
(381, 94, 'NIVA'),
(382, 94, 'SAMARA'),
(383, 12, 'GALLARDO'),
(384, 12, 'MURCIELAGO'),
(385, 96, 'DEFENDER'),
(386, 96, 'DISCOVERY'),
(389, 96, 'FREELANDER'),
(391, 96, 'NEW RANGE'),
(392, 96, 'RANGE ROVER'),
(393, 97, 'ES'),
(396, 97, 'GS'),
(397, 97, 'IS-300'),
(398, 97, 'LS'),
(400, 97, 'RX'),
(402, 97, 'SC'),
(403, 98, '320'),
(404, 98, '620'),
(405, 100, 'H1'),
(406, 101, 'ELAN'),
(407, 101, 'ESPRIT'),
(408, 102, 'SCORPIO'),
(409, 104, '222'),
(410, 104, '228'),
(411, 104, '3200'),
(412, 104, '430'),
(413, 104, 'COUPE'),
(414, 104, 'GHIBLI'),
(415, 104, 'GRANCABRIO'),
(416, 104, 'GRANSPORT'),
(417, 104, 'GRANTURISMO'),
(418, 104, 'QUATTROPORTE'),
(419, 104, 'SHAMAL'),
(420, 104, 'SPIDER'),
(422, 106, 'PICK-UP'),
(423, 108, '323'),
(424, 108, '626'),
(425, 108, '929'),
(426, 108, 'B-2500'),
(427, 108, 'B2200'),
(428, 108, 'MILLENIA'),
(429, 108, 'MPV'),
(430, 108, 'MX-3'),
(431, 108, 'MX-5'),
(432, 108, 'NAVAJO'),
(433, 108, 'PROTEGE'),
(434, 108, 'RX'),
(467, 4, 'CLASSE A'),
(468, 4, 'CLASSE B'),
(469, 4, 'CLASSE R'),
(498, 4, 'CLASSE GLK'),
(531, 4, 'SPRINTER'),
(532, 110, 'MYSTIQUE'),
(533, 110, 'SABLE'),
(534, 112, '550'),
(535, 112, 'MG6'),
(536, 113, 'COOPER'),
(537, 113, 'ONE'),
(538, 111, '3000'),
(539, 111, 'AIRTREK'),
(540, 111, 'ASX'),
(541, 111, 'COLT'),
(542, 111, 'DIAMANT'),
(543, 111, 'ECLIPSE'),
(544, 111, 'EXPO'),
(545, 111, 'GALANT'),
(546, 111, 'GRANDIS'),
(547, 111, 'L200'),
(548, 111, 'L300'),
(549, 111, 'LANCER'),
(550, 111, 'MIRAGE'),
(551, 111, 'MONTERO'),
(552, 111, 'OUTLANDER'),
(553, 111, 'PAJERO'),
(554, 111, 'SPACE WAGON'),
(555, 114, 'BG-TRUCK'),
(556, 43, '350Z'),
(557, 43, 'ALTIMA'),
(558, 43, 'AX'),
(559, 43, 'D-21'),
(560, 43, 'FRONTIER'),
(562, 43, 'KING-CAB'),
(563, 43, 'LIVINA'),
(564, 43, 'MARCH'),
(565, 43, 'MAXIMA'),
(567, 43, 'MURANO'),
(568, 43, 'NX'),
(569, 43, 'PATHFINDER'),
(571, 43, 'PRIMERA'),
(572, 43, 'QUEST'),
(573, 43, 'SENTRA'),
(574, 43, 'STANZA'),
(575, 43, '180SX'),
(576, 43, 'TERRANO'),
(577, 43, 'TIIDA'),
(578, 43, 'VERSA'),
(579, 43, 'X-TRAIL'),
(580, 43, 'XTERRA'),
(581, 43, 'ZX'),
(582, 22, '106'),
(583, 22, '205'),
(584, 22, '206'),
(585, 22, '207'),
(586, 22, '3008'),
(587, 22, '306'),
(588, 22, '307'),
(589, 22, '308'),
(590, 22, '405'),
(591, 22, '406'),
(592, 22, '407'),
(593, 22, '408'),
(594, 22, '504'),
(595, 22, '505'),
(596, 22, '508'),
(597, 22, '605'),
(598, 22, '607'),
(599, 22, '806'),
(600, 22, '807'),
(601, 22, 'BOXER'),
(602, 22, 'HOGGAR'),
(603, 22, 'PARTNER'),
(604, 22, 'RCZ'),
(605, 123, 'GRAN VOYAGER'),
(606, 123, 'SUNDANCE'),
(607, 69, 'TRANS-AM'),
(608, 69, 'TRANS-SPORT'),
(609, 70, '911'),
(612, 70, 'BOXSTER'),
(613, 70, 'CAYENNE'),
(614, 70, 'CAYMAN'),
(615, 70, 'PANAMERA'),
(617, 24, '21 SEDAN'),
(618, 24, 'CLIO'),
(619, 24, 'DUSTER'),
(620, 24, 'EXPRESS'),
(621, 24, 'FLUENCE'),
(622, 24, 'KANGOO'),
(623, 24, 'LAGUNA'),
(624, 24, 'LOGAN'),
(625, 24, 'MASTER'),
(626, 24, 'MEGANE'),
(627, 24, 'SAFRANE'),
(628, 24, 'SANDERO'),
(629, 24, 'SCENIC'),
(630, 24, 'SYMBOL'),
(631, 24, 'TRAFIC'),
(632, 24, 'TWINGO'),
(634, 137, '9000'),
(635, 162, 'SL-2'),
(636, 130, 'CORDOBA'),
(637, 130, 'IBIZA'),
(638, 130, 'INCA'),
(641, 142, 'FORTWO'),
(642, 21, 'ACTYON SPORTS'),
(643, 21, 'CHAIRMAN'),
(644, 21, 'ISTANA'),
(645, 21, 'KORANDO'),
(646, 21, 'KYRON'),
(647, 21, 'MUSSO'),
(648, 21, 'REXTON'),
(649, 8, 'FORESTER'),
(650, 8, 'IMPREZA'),
(651, 8, 'LEGACY'),
(652, 8, 'OUTBACK'),
(653, 8, 'SVX'),
(654, 8, 'TRIBECA'),
(655, 8, 'VIVIO'),
(656, 59, 'BALENO'),
(657, 59, 'GRAND VITARA'),
(658, 59, 'IGNIS'),
(660, 59, 'JIMNY'),
(662, 59, 'SUPER CARRY'),
(663, 59, 'SWIFT'),
(664, 59, 'SX4'),
(665, 59, 'VITARA'),
(666, 59, 'WAGON R'),
(667, 146, 'STARK'),
(668, 23, 'AVALON'),
(669, 23, 'BANDEIRANTE'),
(670, 23, 'CAMRY'),
(671, 23, 'CELICA'),
(672, 23, 'COROLLA'),
(673, 23, 'CORONA'),
(674, 23, 'HILUX'),
(675, 23, 'LAND CRUISER'),
(676, 23, 'MR-2'),
(677, 23, 'PASEO'),
(678, 23, 'PREVIA'),
(679, 23, 'RAV4'),
(680, 23, 'SUPRA'),
(682, 149, 'PANTANAL'),
(684, 149, 'T-4'),
(685, 53, '400 SERIES'),
(687, 53, '850'),
(688, 53, '900 SERIES'),
(700, 2, 'AMAROK'),
(701, 2, 'APOLLO'),
(702, 2, 'BORA'),
(703, 2, 'CARAVELLE'),
(704, 2, 'CORRADO'),
(706, 2, 'EOS'),
(707, 2, 'EUROVAN'),
(708, 2, 'FOX'),
(709, 2, 'FUSCA'),
(710, 2, 'GOL'),
(711, 2, 'GOLF'),
(713, 2, 'JETTA'),
(714, 2, 'KOMBI'),
(715, 2, 'LOGUS'),
(717, 2, 'PARATI'),
(718, 2, 'PASSAT'),
(719, 2, 'POINTER'),
(720, 2, 'POLO'),
(722, 2, 'SANTANA'),
(723, 2, 'SAVEIRO'),
(725, 2, 'SPACEFOX'),
(726, 2, 'TIGUAN'),
(727, 2, 'TOUAREG'),
(729, 2, 'VOYAGE'),
(732, 25, 'ZDX'),
(737, 3, '140'),
(755, 2, 'BRASILIA'),
(756, 13, 'BRASILVAN'),
(775, 13, 'CORCEL'),
(803, 3, 'PALIO WEEKEND'),
(806, 13, 'FOCUS SEDAN'),
(807, 13, 'FIESTA SEDAN'),
(808, 13, 'FIESTA TRAIL'),
(810, 22, '207 SW'),
(811, 13, 'ESCORT SW'),
(812, 22, '307 SEDAN'),
(813, 22, '307 SW'),
(815, 5, 'C4 PALLAS'),
(816, 5, 'C4 PICASSO'),
(817, 5, 'C4 VTR'),
(818, 24, 'CLIO SEDAN'),
(819, 23, 'COROLLA FIELDER'),
(824, 23, 'HILUX SW4'),
(825, 24, 'MEGANE GRAND TOUR'),
(827, 24, 'SANDERO STEPWAY'),
(829, 5, 'XSARA PICASSO'),
(1360, 131, 'COLHEITADEIRA'),
(1361, 158, 'PICKUP F75'),
(1362, 17, 'X12'),
(1365, 1, 'BEL AIR'),
(1366, 36, 'RX'),
(1369, 1, 'C-14'),
(1370, 155, 'SRX4'),
(1372, 1, 'C-15'),
(1373, 1, 'BRASIL'),
(1377, 18, 'POLARA'),
(1380, 3, '600'),
(1382, 13, 'F-01'),
(1383, 13, 'FALCON');

-- --------------------------------------------------------

--
-- Table structure for table `ordem_servico`
--

CREATE TABLE `ordem_servico` (
  `idordem_servico` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `idcliente` int(11) NOT NULL,
  `id_veiculo` int(11) NOT NULL,
  `id_endereco` int(11) NOT NULL,
  `id_servico` int(11) NOT NULL,
  `descricao` varchar(600) DEFAULT NULL,
  `desconto` float DEFAULT NULL,
  `valor_total` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `produtos`
--

CREATE TABLE `produtos` (
  `idprodutos` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `qtde` int(11) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_ordem_servico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `servico`
--

CREATE TABLE `servico` (
  `idmao_de_obra` int(11) NOT NULL,
  `servico` varchar(800) DEFAULT NULL,
  `qtde` int(11) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `idfuncionario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `veiculo`
--

CREATE TABLE `veiculo` (
  `idveiculo` int(11) NOT NULL,
  `cor` varchar(45) DEFAULT NULL,
  `ano` varchar(45) DEFAULT NULL,
  `placa` varchar(45) DEFAULT NULL,
  `km` double DEFAULT NULL,
  `img_veiculo` varchar(45) DEFAULT NULL,
  `descricao` varchar(800) DEFAULT NULL,
  `idmodelo` int(11) NOT NULL,
  `idmarca` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `veiculo`
--

INSERT INTO `veiculo` (`idveiculo`, `cor`, `ano`, `placa`, `km`, `img_veiculo`, `descricao`, `idmodelo`, `idmarca`, `idcliente`) VALUES
(1, 'PRATA', '2010', 'MST1100', 213132, 'amorok.jpg', 'AMORTECERDORES', 700, 2, 1),
(2, 'PRETO', '2009', 'MST2704', 237134, 'vectra.jpg', 'CARRO ESTA COM A SUSPEN??O FAZENDO BARULHO', 291, 1, 1),
(4, 'PRETA', '2010', 'ABC1020', 237123, 'vectra.jpg', 'CARRO ESTA COM VAZAMENTO NA MAGUEIRA DA BOMBA DA AGUA', 291, 1, 2),
(5, 'BRANCO', '2005', 'FIA1020', 234561, 'uno.png', 'CARRO ESTA COM A SUSPEN??O DIANTEIRA FAZENDO BARULHO', 199, 3, 2),
(6, 'PRETA', '2009', 'ABC1234', 213423, 'vectra.jpg', 'JUNTA DE CABE?OTE QUEIMADA', 291, 1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idcategoria`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idcliente`,`id_endereco`),
  ADD KEY `fk_cliente_endereco1_idx` (`id_endereco`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`idendereco`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`idfuncionario`,`endereco_idendereco`),
  ADD KEY `fk_funcionario_endereco1_idx` (`endereco_idendereco`);

--
-- Indexes for table `marca`
--
ALTER TABLE `marca`
  ADD PRIMARY KEY (`idMarca`);

--
-- Indexes for table `modelo`
--
ALTER TABLE `modelo`
  ADD PRIMARY KEY (`idmodelo`,`id_marca`),
  ADD KEY `fk_modelo_Marca_idx` (`id_marca`);

--
-- Indexes for table `ordem_servico`
--
ALTER TABLE `ordem_servico`
  ADD PRIMARY KEY (`idordem_servico`,`idcliente`,`id_veiculo`,`id_endereco`,`id_servico`),
  ADD KEY `fk_ordem_servico_cliente1_idx` (`idcliente`,`id_veiculo`,`id_endereco`),
  ADD KEY `fk_ordem_servico_servico1_idx` (`id_servico`),
  ADD KEY `fk_ordem_servico_cliente1` (`idcliente`,`id_endereco`);

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`idprodutos`,`id_categoria`,`id_ordem_servico`),
  ADD KEY `fk_produtos_categoria1_idx` (`id_categoria`),
  ADD KEY `fk_produtos_ordem_servico1_idx` (`id_ordem_servico`);

--
-- Indexes for table `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`idmao_de_obra`,`idfuncionario`),
  ADD KEY `fk_servico_funcionario1_idx` (`idfuncionario`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`);

--
-- Indexes for table `veiculo`
--
ALTER TABLE `veiculo`
  ADD PRIMARY KEY (`idveiculo`,`idmodelo`,`idmarca`,`idcliente`),
  ADD KEY `fk_veiculo_modelo1_idx` (`idmodelo`,`idmarca`),
  ADD KEY `fk_veiculo_cliente1_idx` (`idcliente`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idcliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `idendereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `idfuncionario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `marca`
--
ALTER TABLE `marca`
  MODIFY `idMarca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=535;

--
-- AUTO_INCREMENT for table `ordem_servico`
--
ALTER TABLE `ordem_servico`
  MODIFY `idordem_servico` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `idprodutos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `servico`
--
ALTER TABLE `servico`
  MODIFY `idmao_de_obra` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `idveiculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_endereco1` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`idendereco`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `fk_funcionario_endereco1` FOREIGN KEY (`endereco_idendereco`) REFERENCES `endereco` (`idendereco`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `modelo`
--
ALTER TABLE `modelo`
  ADD CONSTRAINT `fk_modelo_Marca` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`idMarca`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ordem_servico`
--
ALTER TABLE `ordem_servico`
  ADD CONSTRAINT `fk_ordem_servico_cliente1` FOREIGN KEY (`idcliente`,`id_endereco`) REFERENCES `cliente` (`idcliente`, `id_endereco`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ordem_servico_servico1` FOREIGN KEY (`id_servico`) REFERENCES `servico` (`idmao_de_obra`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `produtos`
--
ALTER TABLE `produtos`
  ADD CONSTRAINT `fk_produtos_categoria1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_produtos_ordem_servico1` FOREIGN KEY (`id_ordem_servico`) REFERENCES `ordem_servico` (`idordem_servico`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `servico`
--
ALTER TABLE `servico`
  ADD CONSTRAINT `fk_servico_funcionario1` FOREIGN KEY (`idfuncionario`) REFERENCES `funcionario` (`idfuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `veiculo`
--
ALTER TABLE `veiculo`
  ADD CONSTRAINT `fk_veiculo_cliente1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_veiculo_modelo1` FOREIGN KEY (`idmodelo`,`idmarca`) REFERENCES `modelo` (`idmodelo`, `id_marca`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
