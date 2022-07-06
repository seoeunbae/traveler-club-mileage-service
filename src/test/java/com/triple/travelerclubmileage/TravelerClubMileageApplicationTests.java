package com.triple.travelerclubmileage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TravelerClubMileageApplicationTests {

	@Test
	void contextLoads() {
		List mockedList = mock(List.class);
		mockedList.add("one");
		mockedList.clear();

		//확인
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}

}
