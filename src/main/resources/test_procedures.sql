-- Test data is organized so that:
-- Victoria Park is centre: 42.987138, -81.248800
-- - 25 entries are 42.989272, -81.260383, ~1km away or < 2km
-- - 25 entries are 42.957720, -81.241387, ~3km away or < 5km
-- - 25 entries are 42.962753, -81.169493, ~7km away or < 8km
-- - 25 entries are 42.963467, -81.140214, ~9km away or < 10km
-- - 25 entries are 42.900998, -81.167931, ~11km away or < 12km
-- - 25 entries are 42.880904, -81.163308, ~13km away or < 15km
use hockeyhero_nearest; 

-- test searches
-- first test that < 2km, < 5km, < 8km, <10km, <12km and <15km produce
-- 25, 50, 75, 100, 125 and 150
call sp_NearestHero(42.987138, -81.248800, 2, 15, 0, 100, 1, 10); 
call sp_NearestHero(42.987138, -81.248800, 5, 15, 0, 100, 1, 10); 
call sp_NearestHero(42.987138, -81.248800, 8, 15, 0, 100, 1, 10); 
call sp_NearestHero(42.987138, -81.248800, 10, 15, 0, 100, 1, 10); 
call sp_NearestHero(42.987138, -81.248800, 12, 15, 0, 100, 1, 10); 
call sp_NearestHero(42.987138, -81.248800, 15, 15, 0, 100, 1, 10); 
