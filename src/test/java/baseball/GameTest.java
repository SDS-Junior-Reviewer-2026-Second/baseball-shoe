package baseball;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

	@Test
	public void 입력값이_없을_경우() {
		Game game = new Game("123");

		assertThrows(IllegalArgumentException.class, () -> game.guess(null));
		assertThrows(IllegalArgumentException.class, () -> game.guess(""));
	}

	@Test
	public void 입력값_자리수가_세자리가_아닐_경우() {
		Game game = new Game("123");

		assertThrows(IllegalArgumentException.class, () -> game.guess("12"));
		assertThrows(IllegalArgumentException.class, () -> game.guess("1234"));
	}
	
	@Test
	public void 입력값에_숫자_외의_문자가_입력될_경우() {
		Game game = new Game("123");

		assertThrows(IllegalArgumentException.class, () -> game.guess("A12"));
	}


	@Test
	public void 입력값에_중복된_숫자가_입력될_경우() {
		Game game = new Game("123");

		assertThrows(IllegalArgumentException.class, () -> game.guess("112"));
	}

	@Test
	public void 숫자_세개가_전부_일치_할_경우_3_strike() {
		Game game = new Game("123");

		GuessResult result = game.guess("123");

		assertThat(result).isNotNull();
		assertThat(result.isSolved()).isTrue();
		assertThat(result.getStrikes()).isEqualTo(3);
		assertThat(result.getBalls()).isZero();
	}

	@Test
	public void 숫자_세개가_전부_일치_하지_않을_경우_0_strike_0_ball() {
		Game game = new Game("123");

		GuessResult result = game.guess("456");

		assertThat(result).isNotNull();
		assertThat(result.isSolved()).isFalse();
		assertThat(result.getStrikes()).isZero();
		assertThat(result.getBalls()).isZero();
	}
	@Test
	public void 스트라이크만_있을_경우_2_strike_0_ball() {
		Game game = new Game("123");

		GuessResult result = game.guess("129");

		assertThat(result).isNotNull();
		assertThat(result.isSolved()).isFalse();
		assertThat(result.getStrikes()).isEqualTo(2);
		assertThat(result.getBalls()).isZero();
	}

	@Test
	public void 볼만_있을_경우_0_strike_1_ball() {
		Game game = new Game("123");

		GuessResult result = game.guess("240");

		assertThat(result.isSolved()).isFalse();
		assertThat(result.getStrikes()).isZero();
		assertThat(result.getBalls()).isEqualTo(1);
	}

	@Test
	public void 볼과_스트라이크가_함께_있을_경우_1_strike_1_ball() {
		Game game = new Game("123");

		GuessResult result = game.guess("136");

		assertThat(result.isSolved()).isFalse();
		assertThat(result.getStrikes()).isEqualTo(1);
		assertThat(result.getBalls()).isEqualTo(1);
	}
}
