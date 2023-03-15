package org.mocktest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class StringServiceTest {
//    @Mock
//    private StringProvider stringProvider;
//    @InjectMocks
//    private StringService stringService;
//    @Captor
//    ArgumentCaptor<String> stringArgumentCaptor;
//
//    @Test
//    void givenWord_WhenAddWordAsUpperCase_thenReturnWordInUpperCase() {
//        var givenWord = "mat";
//        //when
//        stringService.addWordAsUpperCase(givenWord);
//
//        //then
//        then(stringProvider).should().add(stringArgumentCaptor.capture());
//        String word = stringArgumentCaptor.getValue();
//
//        assertThat(word)
//                .isNotNull()
//                .contains(givenWord.toUpperCase());
//    }
//
//    @Test
//    void givenWord_WhenAddWordAsUpperCase_thenReturnWordInUpperCase2() {
//        var givenWord = "mat";
//
////        List<String> givenList = new ArrayList<>(List.of("Moje", "słowo"));
//
//        given(stringProvider.add(anyString())).willAnswer(ans -> {
//            String argument = ans.getArgument(0, String.class);
////            System.out.println("W metodzie bede dodwal: " + argument);
////            givenList.add(argument);
//            assertThat(argument).isUpperCase();
//            return true;
//        });
//
//        //when
//        stringService.addWordAsUpperCase(givenWord);
//
//        //then
////        then(stringProvider).should().add(stringArgumentCaptor.capture());
////        String word = stringArgumentCaptor.getValue();
////
////        assertThat(givenList)
////                .hasSize(3)
////                .contains(givenWord.toUpperCase());
////        System.out.println(givenList);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"jasio", "Marcin", "kolega", "wóz"})
//    void givenParametrizedWord_WhenAddWordAsUpperCase_thenReturnWordInUpperCase(String input) {
//        //when
//        stringService.addWordAsUpperCase(input);
//
//        //then
//        then(stringProvider).should().add(stringArgumentCaptor.capture());
//        String word = stringArgumentCaptor.getValue();
//
//        assertThat(word)
//                .isNotNull()
//                .contains(input.toUpperCase());
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"Hello", "1", "", "Marcin"})
//    void givenWord_whenAddWordAsLowerCase_thenReturnWordInLowerCase(String word) {
//        //when
//        stringService.addWordAsLowerCase(word);
//
//        //then
//        then(stringProvider).should().addWithNoReturn(stringArgumentCaptor.capture());
//        assertThat(stringArgumentCaptor.getValue())
//                .isNotNull()
//                .contains(word.toLowerCase());
//
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideTestData")
//    void givenListOfStrings_whenGetShortestWord_thenReturnShortestWord(List<String> input) {
//
//        given(stringProvider.getAllValues()).willReturn(input);
//
//        //when
//        String shortestWord = stringService.getShortestWord();
//
//        assertThat("AC")
//                .isNotNull()
//                .isEqualTo(shortestWord);
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideTestData2")
//    void givenListOfStrings_whenGetLongestWord_thenReturnLongestWord(List<String> input, String expectedOutput) {
//
//        given(stringProvider.getAllValues()).willReturn(input);
//
//        //when
//        String longestWord = stringService.getLongestWord();
//
//        //then
//        assertThat(longestWord)
//                .isNotNull()
//                .isEqualTo(expectedOutput);
//    }
//
//    @ParameterizedTest
//    @EnumSource(StringService.SortOrder.class)
//    void givenEnum_whenSortValuesAsc_thenReturnNaturalOrderComparator(StringService.SortOrder sortOrder) {
//        //when
//        stringService.sortValues(sortOrder);
//
//        Comparator<String> expectedComparator = sortOrder == StringService.SortOrder.ASC ?
//                Comparator.naturalOrder() : Comparator.reverseOrder();
//        then(stringProvider).should().sort(expectedComparator);
//    }
//
//    @ParameterizedTest
//    @CsvSource(delimiterString = "->", textBlock = """
//            ASC -> Natural
//            DESC -> Reverse
//            """)
//    void givenEnum_whenSortValuesAsc_thenReturnNaturalOrderComparator2(StringService.SortOrder sortOrder, String expected) {
//        //when
//        stringService.sortValues(sortOrder);
//
//        Comparator<String> expectedComparator = switch (expected) {
//            case "Natural" -> Comparator.naturalOrder();
//            case "Reverse" -> Comparator.reverseOrder();
//            default -> null;
//        };
//
//        then(stringProvider).should().sort(expectedComparator);
//    }
//
//    @Test
//    void givenEnum_whenSortValuesDesc_thenReturnReverserOrderComparator() {
//        //when
//        stringService.sortValues(StringService.SortOrder.DESC);
//
//        then(stringProvider).should().sort(Comparator.reverseOrder());
//    }
//
//    @Test
//    void whenSortValuesDesc_thenExpectCorrectEnum() {
//        //when
//        stringService.sortValuesDesc();
//
//        then(stringProvider).should().sort(Comparator.reverseOrder());
//    }
//    @Test
//    void whenSortValuesAsc_thenExpectCorrectEnum() {
//        //when
//        stringService.sortValuesAsc();
//
//        then(stringProvider).should().sort(Comparator.naturalOrder());
//    }
//
//    static Stream<Arguments> provideTestData(){
//        return Stream.of(
//                Arguments.of(List.of("One", "Two", "Three", "Four", "Five", "Marek", "Marcin", "Krzemien", "Olek", "Krzaczun", "Pilka", "Sport", "AC" )));
//    }
//
//    static Stream<Arguments> provideTestData2(){
//        return Stream.of(
//                Arguments.of(List.of("Mark", "Jolanta", "Maciej", "Ul"), "Jolanta"),
//                Arguments.of(List.of("Hello", "World", "Java", "Programming"), "Programming"),
//                Arguments.of(List.of("One", "Two", "Three", "Four", "Five"), "Three"),
//                Arguments.of(List.of("A", "BB", "CCC", "DDDD"), "DDDD"),
//                Arguments.of(List.of("Cat", "Dog", "Elephant"), "Elephant"),
//                Arguments.of(List.of("Zebra", "Lion", "Giraffe"), "Giraffe")
//        );
//    }
}