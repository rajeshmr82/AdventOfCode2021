package days;

import java.awt.Point;
import java.util.HashSet;

public class Day20 extends Day {

    public Day20(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        char[] ieAlgo = input[0].toCharArray();
        HashSet<Point> image = new HashSet<>();

        for (int i = 2; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                if(input[i].charAt(j)=='#') image.add(new Point(j,i-2));
            }
        }

        int height = input.length-2, width=input[2].length();

        int steps =2;
        HashSet<Point> outputImage = enhance(ieAlgo, image, height, width, steps);

        System.out.printf("Number of lit pixels: %d%n",outputImage.size());
        return String.valueOf(outputImage.size());
    }

    private HashSet<Point> enhance(char[] ieAlgo, HashSet<Point> image, int height, int width,int steps) {
        int offset=0;

        HashSet<Point> outputImage = null;
        for (int step = 1; step <= steps; step++) {
            //to handle the scenario where the algorithm has first character as '#' and last as '.' which essentially means that
            //the pixel gets flipped. Now if we do not restrict the canvas we are looking at, it would expand out creating a lot of
            //extra pixels
            if (step % 2 == 1) offset += 4;
            else offset -= 2;

            outputImage = new HashSet<>();
            for (int row = (-offset); row < (height + offset); row++) {
                for (int col = (-offset); col < (width + offset); col++) {
                    outputImage.addAll(findPixel(row, col, image, ieAlgo));
                }
            }

            image = outputImage;

        }
        printImage(outputImage, height, width,offset);
        return outputImage;
    }

    private HashSet<Point> findPixel(int row, int col, HashSet<Point> image,char[] ieAlgo) {
        HashSet<Point> newPixels = new HashSet<>();
        int[][] deltas = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        StringBuilder bits = new StringBuilder();
        for (int[] d : deltas
        ) {
            bits.append(image.contains(new Point(col + d[1], row + d[0])) ? "1" : "0");
        }
        if(ieAlgo[Integer.parseInt(bits.toString(),2)]=='#'){
            newPixels.add(new Point(col,row));
        }

        return newPixels;
    }


    private void printImage(HashSet<Point> image, int height, int width,int offset) {
        for (int i = -offset; i < height+offset; i++) {
            for (int j = -offset; j < width+offset; j++) {
                if (image.contains(new Point(j, i))) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }

        System.out.println();
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        char[] ieAlgo = input[0].toCharArray();
        HashSet<Point> image = new HashSet<>();

        for (int i = 2; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                if(input[i].charAt(j)=='#') image.add(new Point(j,i-2));
            }
        }

        int height = input.length-2, width=input[2].length();

        int steps =50;
        HashSet<Point> outputImage = enhance(ieAlgo, image, height, width, steps);

        System.out.printf("Number of lit pixels: %d%n",outputImage.size());
        return String.valueOf(outputImage.size());
    }

}