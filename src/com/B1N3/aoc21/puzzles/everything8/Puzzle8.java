package com.B1N3.aoc21.puzzles.everything8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*

--- Day 8: Seven Segment Search ---

You barely reach the safety of the cave when the whale smashes into the cave mouth, collapsing it. Sensors indicate another exit to this cave at a much greater depth, so you have no choice but to press on.

As your submarine slowly makes its way through the cave system, you notice that the four-digit seven-segment displays in your submarine are malfunctioning; they must have been damaged during the escape. You'll be in a lot of trouble without them, so you'd better figure out what's wrong.

Each digit of a seven-segment display is rendered by turning on or off any of seven segments named a through g:

  0:      1:      2:      3:      4:
 aaaa    ....    aaaa    aaaa    ....
b    c  .    c  .    c  .    c  b    c
b    c  .    c  .    c  .    c  b    c
 ....    ....    dddd    dddd    dddd
e    f  .    f  e    .  .    f  .    f
e    f  .    f  e    .  .    f  .    f
 gggg    ....    gggg    gggg    ....

  5:      6:      7:      8:      9:
 aaaa    aaaa    aaaa    aaaa    aaaa
b    .  b    .  .    c  b    c  b    c
b    .  b    .  .    c  b    c  b    c
 dddd    dddd    ....    dddd    dddd
.    f  e    f  .    f  e    f  .    f
.    f  e    f  .    f  e    f  .    f
 gggg    gggg    ....    gggg    gggg
So, to render a 1, only segments c and f would be turned on; the rest would be off. To render a 7, only segments a, c, and f would be turned on.

The problem is that the signals which control the segments have been mixed up on each display. The submarine is still trying to display numbers by producing output on signal wires a through g, but those wires are connected to segments randomly. Worse, the wire/segment connections are mixed up separately for each four-digit display! (All of the digits within a display use the same connections, though.)

So, you might know that only signal wires b and g are turned on, but that doesn't mean segments b and g are turned on: the only digit that uses two segments is 1, so it must mean segments c and f are meant to be on. With just that information, you still can't tell which wire (b/g) goes to which segment (c/f). For that, you'll need to collect more information.

For each display, you watch the changing signals for a while, make a note of all ten unique signal patterns you see, and then write down a single four digit output value (your puzzle input). Using the signal patterns, you should be able to work out which pattern corresponds to which digit.

For example, here is what you might see in a single entry in your notes:

acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
cdfeb fcadb cdfeb cdbaf
(The entry is wrapped here to two lines so it fits; in your notes, it will all be on a single line.)

Each entry consists of ten unique signal patterns, a | delimiter, and finally the four digit output value. Within an entry, the same wire/segment connections are used (but you don't know what the connections actually are). The unique signal patterns correspond to the ten different ways the submarine tries to render a digit using the current wire/segment connections. Because 7 is the only digit that uses three segments, dab in the above example means that to render a 7, signal lines d, a, and b are on. Because 4 is the only digit that uses four segments, eafb means that to render a 4, signal lines e, a, f, and b are on.

Using this information, you should be able to work out which combination of signal wires corresponds to each of the ten digits. Then, you can decode the four digit output value. Unfortunately, in the above example, all of the digits in the output value (cdfeb fcadb cdfeb cdbaf) use five segments and are more difficult to deduce.

For now, focus on the easy digits. Consider this larger example:

be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb |
fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec |
fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef |
cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega |
efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga |
gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf |
gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf |
cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd |
ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg |
gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc |
fgae cfgab fg bagce
Because the digits 1, 4, 7, and 8 each use a unique number of segments, you should be able to tell which combinations of signals correspond to those digits. Counting only digits in the output values (the part after | on each line), in the above example, there are 26 instances of digits that use a unique number of segments (highlighted above).

In the output values, how many times do digits 1, 4, 7, or 8 appear?

Your puzzle answer was 247.

The first half of this puzzle is complete! It provides one gold star: *

--- Part Two ---

Through a little deduction, you should now be able to determine the remaining digits. Consider again the first example above:

acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
cdfeb fcadb cdfeb cdbaf
After some careful analysis, the mapping between signal wires and segments only make sense in the following configuration:

 dddd
e    a
e    a
 ffff
g    b
g    b
 cccc
So, the unique signal patterns would correspond to the following digits:

acedgfb: 8
cdfbe: 5
gcdfa: 2
fbcad: 3
dab: 7
cefabd: 9
cdfgeb: 6
eafb: 4
cagedb: 0
ab: 1
Then, the four digits of the output value can be decoded:

cdfeb: 5
fcadb: 3
cdfeb: 5
cdbaf: 3
Therefore, the output value for this entry is 5353.

Following this same process for each entry in the second, larger example above, the output value of each entry can be determined:

fdgacbe cefdb cefbgd gcbe: 8394
fcgedb cgb dgebacf gc: 9781
cg cg fdcagb cbg: 1197
efabcd cedba gadfec cb: 9361
gecf egdcabf bgf bfgea: 4873
gebdcfa ecba ca fadegcb: 8418
cefg dcbef fcge gbcadfe: 4548
ed bcgafe cdgba cbgef: 1625
gbdfcae bgc cg cgb: 8717
fgae cfgab fg bagce: 4315
Adding all of the output values in this larger example produces 61229.

For each entry, determine all of the wire/segment connections and decode the four-digit output values. What do you get if you add up all of the output values?

*/

public class Puzzle8 {

    class Line {

        ArrayList<String> input = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        Pattern pattern;

        public Line(String line) {
            String[] lineSplit = line.split("\\|");

            // Split and sort
            for (String s : lineSplit[0].strip().split(" "))
                this.input.add(this.sortString(s));
            for (String s : lineSplit[1].strip().split(" "))
                output.add(this.sortString(s));

            pattern = new Pattern(input);
        }

        private String sortString(String s) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            return String.valueOf(charArray);
        }
    }

    /**
     * <pre>
     * Sortiere jede Zeile nach Stringl??nge und leite ein Muster aus den Indizes ab.
     * index	digit	length
     *     0		1		 2
     *     1		7		 3
     *     2		4		 4
     *   3-5	2|3|5 		 5 //Variabel
     *   6-8	0|6|9		 6 //Variabel
     *     9		8		 7
     * </pre>
     */
    class Pattern {

        final HashMap<String, Integer> map = new HashMap<>();
        final ArrayList<String> entries;

        public Pattern(ArrayList<String> s) {
            this.entries = s;

            // Sortiere nach Stringl??nge
            entries.sort(Comparator.comparingInt(String::length));

            map.put(entries.get(0), 1);
            map.put(entries.get(1), 7);
            map.put(entries.get(2), 4);
            map.put(entries.get(9), 8);

            // Index der 6 wird gespeichert, da er f??r die 5 gebraucht wird.
            int sixIndex = 0;

            /**
             * <pre>
             * ??berpr??fe alle Werte mit L??nge 6, ie: 0, 9 und 6
             *
             * 9 besteht aus 1, 7 und 4 			  //Index 0, 1, 2
             * 0 besteht nur aus 1 und 7			  //Index 0, 1
             * 6 besteht weder aus 1, noch 7, noch 4
             * </pre>
             */
            for (int i = 6; i <= 8; i++) {
                String d = entries.get(i);
                if (matches(i, 0) && matches(i, 1) && matches(i, 2))
                    map.put(d, 9);
                else if (matches(i, 0) && matches(i, 1))
                    map.put(d, 0);
                else {
                    map.put(d, 6);
                    sixIndex = i;
                }
            }

            /**
             * ??berpr??fe die restlichen Werte: 3, 2 und 5
             *
             * 3 besteht aus 1 und 7
             *
             * 5 besteht aus 6
             *
             * 2 besteht weder aus 1, noch 7, noch 6
             */
            for (int i = 3; i <= 5; i++) {
                String d = entries.get(i);
                if (matches(i, 0) && matches(i, 1))
                    map.put(d, 3);
                else if (matches(sixIndex, i))
                    map.put(d, 5);
                else
                    map.put(d, 2);
            }
        }

        /** Sind alle chars aus s irgendwo in t? */
        private boolean matches(int i, int j) {
            for (char c : entries.get(j).toCharArray()) {
                if (entries.get(i).indexOf(c) == -1)
                    return false;
            }
            return true;
        }
    }

    public void runPartOne(){
        List<String> outputs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/com/B1N3/aoc21/puzzles/everything8/input.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.replace("|", "-");
                String lineOutputs = line.split(" - ")[1];
                List<String> displayedDigits = Arrays.asList(lineOutputs.split(" "));
                outputs.addAll(displayedDigits);
            }

        }
        catch (Exception e) {
            System.out.println("Could not load file lines to array. Error: " + e.getMessage());
        }

        int uniqueNumbersCount = 0;
        for (String digit : outputs)
            if (digit.length() == 2 || digit.length() == 3 || digit.length() == 4 || digit.length() == 7)
                uniqueNumbersCount++;


        System.out.println("There are " + uniqueNumbersCount + " unique numbers in output.");
    }

    public void runPartTwo() throws IOException {
        ArrayList<Line> LINE = new ArrayList<>();

        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("src/com/B1N3/aoc21/puzzles/everything8/input.txt")));
        for (String l : lines) // Convert Strings to Lines
            LINE.add(new Line(l));

        int sum = 0;
        for (Line line : LINE) {
            int temp = 0;
            for (String s : line.output)
                temp = temp * 10 + line.pattern.map.get(s); // Append newest digit.
            sum += temp;
        }

        System.out.println("The result of part two is " + sum);
    }

}
