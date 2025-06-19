CREATE OR REPLACE VIEW place_ranking_view AS
SELECT
    p.id AS place_id,
    p.name AS name,
    i.image_url AS cover_image_url,
    (SELECT COUNT(*) FROM check_ins ci WHERE ci.place_id = p.id) AS check_in_count,
    RANK() OVER (ORDER BY (SELECT COUNT(*) FROM check_ins ci WHERE ci.place_id = p.id) DESC) AS ranking
FROM places p
LEFT JOIN images i ON p.cover_image_id = i.id;


CREATE OR REPLACE VIEW user_ranking_view AS
SELECT
    u.id AS user_id,
    u.first_name AS first_name,
    u.last_name AS last_name,
    i.image_url AS avatar_url,
    u.point AS point,
    RANK() OVER (
        ORDER BY u.point DESC,
                 u.place_check_in_count DESC,
                 u.check_in_count ASC
    ) AS ranking,
    u.check_in_count AS check_in_count,
    u.place_check_in_count AS place_check_in_count
FROM users u
LEFT JOIN images i ON u.avatar_image_id = i.id;
