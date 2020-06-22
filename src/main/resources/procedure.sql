use hockeyhero_nearest;
-- sp_NearestHeros - stored procedure to find the nearest user within a max of 
-- 20km. Based off of this procedure to calculate nearest points:
-- 
-- http://www.plumislandmedia.net/mysql/haversine-mysql-nearest-loc/
--
-- this gets us a temporary table of hero_keys id and (calculated) distance
-- only heroes whose hide-me is false are included
--
DROP PROCEDURE IF EXISTS  `sp_Nearest`;
DELIMITER //
CREATE PROCEDURE `sp_Nearest` (
	in fvlatpoint 	float, 
    in fvlongpoint 	float, 
    in fvradius 	int)
BEGIN 
	-- DECLARE distance_unit float default 111.045; -- one degree of latitude 
    
    -- create a temporary table so we can re-use this 
    -- stored procedure in other stored procedures. 
    
    DROP TEMPORARY TABLE IF EXISTS `t_tmpheroes`; 
    CREATE TEMPORARY TABLE `t_tmpheroes` (
		id			int		NOT NULL, 
        distance	float		
    ) ENGINE = MEMORY; 

INSERT INTO `t_tmpheroes`
SELECT id, distance
	FROM (    
		SELECT
			z.id as id,
			p.distance_unit
                 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
                 * COS(RADIANS(z.latitude))
                 * COS(RADIANS(p.longpoint - z.longitude))
                 + SIN(RADIANS(p.latpoint))
                 * SIN(RADIANS(z.latitude)))) AS distance
		FROM `hero_keys` AS z 
		JOIN (   /* these are the query parameters passed in */
			SELECT  fvlatpoint 	AS latpoint,  
					fvlongpoint AS longpoint,
					fvradius 	AS radius,
                    111.045 	AS distance_unit
		) AS p ON 1=1
		WHERE z.latitude
			BETWEEN p.latpoint  - (p.radius / p.distance_unit)
				AND p.latpoint  + (p.radius / p.distance_unit)
		AND z.longitude
			BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
				AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
        AND (z.hide_me = 0)
	) AS d
WHERE distance <= fvradius
ORDER BY distance;
     
END //
DELIMITER ; 

-- NearestHero - stored procedure to find the nearest user 
-- by criteria. We search by position, age range and skill range
--
DROP PROCEDURE IF EXISTS  `sp_NearestHero`;
DELIMITER //
CREATE PROCEDURE `sp_NearestHero` (
	in fvlatpoint 	float, 
    in fvlongpoint 	float, 
    in fvradius 	int, 
    in fvposition   int, 
    in fvlowAge		int, 
    in fvhighAge	int, 
    in fvlowSkill	int,
    in fvhighSkill	int)
BEGIN
SELECT hero_id, distance
	FROM (             
		SELECT
			z.hero_id as hero_id,
			p.distance_unit
                 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
                 * COS(RADIANS(z.latitude))
                 * COS(RADIANS(p.longpoint - z.longitude))
                 + SIN(RADIANS(p.latpoint))
                 * SIN(RADIANS(z.latitude)))) AS distance
		FROM `hero_keys` AS z 
		JOIN (   /* these are the query parameters passed in */
			SELECT  fvlatpoint 	AS latpoint,  
					fvlongpoint AS longpoint,
					fvradius 	AS radius,
                    111.045 	AS distance_unit
		) AS p ON 1=1
		WHERE z.latitude
			BETWEEN p.latpoint  - (p.radius / p.distance_unit)
				AND p.latpoint  + (p.radius / p.distance_unit)
		AND z.longitude
			BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
				AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
		AND (z.age BETWEEN fvlowAge AND fvhighAge) 
		AND (z.skill BETWEEN fvlowSkill AND fvhighSkill)
        AND (z.myposition & fvposition > 0)
		AND (z.hide_me = 0)
	) AS d
WHERE distance <= fvradius
ORDER BY distance;

END //
DELIMITER ; 

-- NextNearestHero - stored procedure to find the next nearest user 
-- by criteria. We search by position, age range and skill range
-- for pagination

DROP PROCEDURE IF EXISTS  `sp_NextNearestHero`;
DELIMITER //
CREATE PROCEDURE `sp_NextNearestHero` (
	in fvlatpoint 	float, 
    in fvlongpoint 	float, 
    in fvradius 	int, 
    in fvposition   int, 
    in fvlowAge		int, 
    in fvhighAge	int, 
    in fvlowSkill	int,
    in fvhighSkill	int,
    in fvLastSeen	int, 
    in fvMaxRows	int)
BEGIN
    /* call sp_Nearest to identify the players near me*/ 
	CALL sp_Nearest(fvlatpoint, fvlongpoint, fvradius);
    
    /* then select the players within that set that meet the criteria */ 
    SELECT 
        x.distance,
  		y.id, 
		y.hero_id,
        y.age,
        y.myposition, 
        y.skill 
		FROM `t_tmpheroes` AS x /* tmpheroes is a subset of hero_keys */
        INNER JOIN `hero_keys` AS y ON x.id = y.id     
		AND (y.age BETWEEN fvlowAge AND fvhighAge) 
		AND (y.skill BETWEEN fvlowSkill AND fvhighSkill)
        AND (y.myposition & fvposition > 0)
        WHERE y.id > fvLastSeen
        ORDER BY y.id
        LIMIT fvMaxRows; 
END //
DELIMITER ; 

-- PrevNearestHero - stored procedure to find the prev nearest user 
-- by criteria. We search by position, age range and skill range
-- for pagination

DROP PROCEDURE IF EXISTS  `sp_PrevNearestHero`;
DELIMITER //
CREATE PROCEDURE `sp_PrevNearestHero` (
	in fvlatpoint 	float, 
    in fvlongpoint 	float, 
    in fvradius 	int, 
    in fvposition   int, 
    in fvlowAge		int, 
    in fvhighAge	int, 
    in fvlowSkill	int,
    in fvhighSkill	int,
    in fvLastSeen	int, 
    in fvMaxRows	int)
BEGIN
    /* call sp_Nearest to identify the players near me*/ 
	CALL sp_Nearest(fvlatpoint, fvlongpoint, fvradius);
    
    /* then select the players within that set that meet the criteria */ 
    SELECT 
        x.distance,
  		y.id, 
		y.hero_id,
        y.age,
        y.myposition, 
        y.skill 
		FROM `t_tmpheroes` AS x /* tmpheroes is a subset of hero_keys */
        INNER JOIN `hero_keys` AS y ON x.id = y.id     
		AND (y.age BETWEEN fvlowAge AND fvhighAge) 
		AND (y.skill BETWEEN fvlowSkill AND fvhighSkill)
        AND (y.myposition & fvposition > 0)
        WHERE y.id < fvLastSeen
        ORDER BY y.id DESC
        LIMIT fvMaxRows; 
END //
DELIMITER ; 
commit;