import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class TestDemoTest {
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void testThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {

		if (!expectException) {

			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);

		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}

	}

	static Stream<Arguments> argumentsForAddPositive() {
		// @formatter:off
		return Stream.of(
				arguments(2, 4, 6, false),
				arguments(1, 5, 6, false),
				arguments(0 , -1, 0, true)
				);
		// @formatter:on
	}

	@Test
	void testRandomNumberSquared() {
		int fiveSquared = createTestDemoMock().getRandomNumberSquared();

		assertThat(fiveSquared).isEqualTo(25);

	}

	private TestDemo createTestDemoMock() {
		TestDemo mockDemo = spy(testDemo);

		doReturn(5).when(mockDemo).getRandomInt();

		return mockDemo;
	}
}