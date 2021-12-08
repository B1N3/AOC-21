package com.B1N3.aoc21.puzzles.everything5;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


/*

--- Day 5: Hydrothermal Venture ---

You come across a field of hydrothermal vents on the ocean floor! These vents constantly produce large, opaque clouds, so it would be best to avoid them if possible.

They tend to form in lines; the submarine helpfully produces a list of nearby lines of vents (your puzzle input) for you to review. For example:

0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2
Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are the coordinates of one end the line segment and x2,y2 are the coordinates of the other end. These line segments include the points at both ends. In other words:

An entry like 1,1 -> 1,3 covers points 1,1, 1,2, and 1,3.
An entry like 9,7 -> 7,7 covers points 9,7, 8,7, and 7,7.
For now, only consider horizontal and vertical lines: lines where either x1 = x2 or y1 = y2.

So, the horizontal and vertical lines from the above list would produce the following diagram:

.......1..
..1....1..
..1....1..
.......1..
.112111211
..........
..........
..........
..........
222111....
In this diagram, the top left corner is 0,0 and the bottom right corner is 9,9. Each position is shown as the number of lines which cover that point or . if no line covers that point. The top-left pair of 1s, for example, comes from 2,2 -> 2,1; the very bottom row is formed by the overlapping lines 0,9 -> 5,9 and 0,9 -> 2,9.

To avoid the most dangerous areas, you need to determine the number of points where at least two lines overlap. In the above example, this is anywhere in the diagram with a 2 or larger - a total of 5 points.

Consider only horizontal and vertical lines. At how many points do at least two lines overlap?

Your puzzle answer was 5092.

The first half of this puzzle is complete! It provides one gold star: *

--- Part Two ---

Unfortunately, considering only horizontal and vertical lines doesn't give you the full picture; you need to also consider diagonal lines.

Because of the limits of the hydrothermal vent mapping system, the lines in your list will only ever be horizontal, vertical, or a diagonal line at exactly 45 degrees. In other words:

An entry like 1,1 -> 3,3 covers points 1,1, 2,2, and 3,3.
An entry like 9,7 -> 7,9 covers points 9,7, 8,8, and 7,9.
Considering all lines from the above example would now produce the following diagram:

1.1....11.
.111...2..
..2.1.111.
...1.2.2..
.112313211
...1.2....
..1...1...
.1.....1..
1.......1.
222111....
You still need to determine the number of points where at least two lines overlap. In the above example, this is still anywhere in the diagram with a 2 or larger - now a total of 12 points.

Consider all of the lines. At how many points do at least two lines overlap?

*/

public class Puzzle5 {

    class Point{
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Line{
        public Point start;
        public Point end;
        public List<Point> allPoints;

        public Line(Point start, Point end) {
            this.start = start;
            this.end = end;
            this.allPoints = new ArrayList<>();
        }

        public void calculatePoints(String command){

            int angle = (int) Math.toDegrees(Math.atan2(this.start.y - this.end.y, this.start.x - this.end.x));
            int x;
            int y;
            switch (angle) {
                case 0 -> { // pointing to the left (W)
                    this.allPoints.add(this.start);
                    x = this.start.x;
                    while (x > this.end.x) {
                        x--;
                        allPoints.add(new Point(x, this.start.y));
                    }
                }
                case 180 -> { // pointing to the right (E)
                    this.allPoints.add(this.start);
                    x = this.start.x;
                    while (x < this.end.x) {
                        x++;
                        allPoints.add(new Point(x, this.start.y));
                    }
                }
                case 90 -> { // pointing up (N)
                    this.allPoints.add(this.start);
                    y = this.start.y;
                    while (y > this.end.y) {
                        y--;
                        allPoints.add(new Point(this.start.x, y));
                    }
                }
                case -90 -> { // pointing down (S)
                    this.allPoints.add(this.start);
                    y = this.start.y;
                    while (y < this.end.y) {
                        y++;
                        allPoints.add(new Point(this.start.x, y));
                    }
                }
                case 135 -> { // pointing up-right (NE)
                    if(command.equals("onlyHorizontalAndVertical")) break;
                    this.allPoints.add(this.start);
                    x = this.start.x;
                    y = this.start.y;
                    while (y > this.end.y) {
                        y--;
                        x++;
                        allPoints.add(new Point(x, y));
                    }
                }
                case -135 -> { // pointing down-right (SE)
                    if(command.equals("onlyHorizontalAndVertical")) break;
                    this.allPoints.add(this.start);
                    x = this.start.x;
                    y = this.start.y;
                    while (y < this.end.y) {
                        y++;
                        x++;
                        allPoints.add(new Point(x, y));
                    }
                }
                case -45 -> { // pointing down-left (SW)
                    if(command.equals("onlyHorizontalAndVertical")) break;
                    this.allPoints.add(this.start);
                    x = this.start.x;
                    y = this.start.y;
                    while (y < this.end.y) {
                        y++;
                        x--;
                        allPoints.add(new Point(x, y));
                    }
                }
                case 45 -> { // pointing up-left (NW)
                    if(command.equals("onlyHorizontalAndVertical")) break;
                    this.allPoints.add(this.start);
                    x = this.start.x;
                    y = this.start.y;
                    while (y > this.end.y) {
                        y--;
                        x--;
                        allPoints.add(new Point(x, y));
                    }
                }
                default -> System.out.println("This angle is not supported!");
            }

        }
    }

    public void runPartOne(){

        List<Line> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/com/B1N3/aoc21/puzzles/everything5/input.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String startPointString = line.split(" -> ")[0];
                String endPointString = line.split(" -> ")[1];

                Point startPoint = new Point(Integer.parseInt(startPointString.split(",")[0]), Integer.parseInt(startPointString.split(",")[1]));
                Point endPoint = new Point(Integer.parseInt(endPointString.split(",")[0]), Integer.parseInt(endPointString.split(",")[1]));
                lines.add(new Line(startPoint, endPoint));
            }

        }
        catch (Exception e) {
            System.out.println("Could not load file lines to array. Error: " + e.getMessage());
        }


        for (Line line : lines){
            line.calculatePoints("onlyHorizontalAndVertical");
        }

        int maxX = 0;
        int maxY = 0;
        for (Line line : lines){
            if(line.start.x > maxX)
                maxX = line.start.x;
            if(line.start.y > maxY)
                maxY = line.start.y;
            if(line.end.x > maxX)
                maxX = line.end.x;
            if(line.end.y > maxY)
                maxY = line.end.y;
        }

        System.out.println("Grid dimensions: " + maxX + "x" + maxY);
        HashMap<String, Integer> occupiedPointsOnGrid = new HashMap<>();
        for (Line line : lines){
            for (Point point : line.allPoints){
                if(!occupiedPointsOnGrid.containsKey(point.x + ","+point.y)){
                    occupiedPointsOnGrid.put(point.x + ","+point.y, 1);
                }
                else {
                    occupiedPointsOnGrid.put(point.x + ","+point.y, occupiedPointsOnGrid.get(point.x + ","+point.y) + 1);
                }
            }
        }

        int lineIntersections = 0;
        Iterator it = occupiedPointsOnGrid.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if((int) pair.getValue() > 1){
                lineIntersections++;
            }

            it.remove();
        }

        System.out.println("Total line intersections for only horizontal and vertical lines: " + lineIntersections);
    }

    public void runPartTwo(){
        List<Line> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/com/B1N3/aoc21/puzzles/everything5/input.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String startPointString = line.split(" -> ")[0];
                String endPointString = line.split(" -> ")[1];

                Point startPoint = new Point(Integer.parseInt(startPointString.split(",")[0]), Integer.parseInt(startPointString.split(",")[1]));
                Point endPoint = new Point(Integer.parseInt(endPointString.split(",")[0]), Integer.parseInt(endPointString.split(",")[1]));
                lines.add(new Line(startPoint, endPoint));
            }

        }
        catch (Exception e) {
            System.out.println("Could not load file lines to array. Error: " + e.getMessage());
        }


        for (Line line : lines){
            line.calculatePoints("all");
        }

        int maxX = 0;
        int maxY = 0;
        for (Line line : lines){
            if(line.start.x > maxX)
                maxX = line.start.x;
            if(line.start.y > maxY)
                maxY = line.start.y;
            if(line.end.x > maxX)
                maxX = line.end.x;
            if(line.end.y > maxY)
                maxY = line.end.y;
        }

        System.out.println("Grid dimensions: " + maxX + "x" + maxY);
        HashMap<String, Integer> occupiedPointsOnGrid = new HashMap<>();
        for (Line line : lines){
            for (Point point : line.allPoints){
                if(!occupiedPointsOnGrid.containsKey(point.x + ","+point.y)){
                    occupiedPointsOnGrid.put(point.x + ","+point.y, 1);
                }
                else {
                    occupiedPointsOnGrid.put(point.x + ","+point.y, occupiedPointsOnGrid.get(point.x + ","+point.y) + 1);
                }
            }
        }

        int lineIntersections = 0;
        Iterator it = occupiedPointsOnGrid.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if((int) pair.getValue() > 1){
                lineIntersections++;
            }
            it.remove();
        }

        System.out.println("Total line intersections for all lines: " + lineIntersections);
    }
}
