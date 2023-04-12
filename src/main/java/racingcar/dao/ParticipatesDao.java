package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.ParticipateDto;

@Repository
public class ParticipatesDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParticipatesDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(final ParticipateDto participateDto) {
        final String sql = "INSERT INTO PARTICIPATES(game_id, player_id, position, is_winner) VALUES(?, ?, ?, ?) ";
        jdbcTemplate.update(sql,
                participateDto.getGameId(),
                participateDto.getPlayerId(),
                participateDto.getPosition(),
                participateDto.getIsWinner()
        );
    }
}
