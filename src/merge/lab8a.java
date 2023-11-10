package merge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class lab8a {
    public static void main(String[] args) {
        QReader8a in = new QReader8a();
        int n = in.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(in.nextInt(), in.nextInt());
        }
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.x - o2.x;
            }
        });
        System.out.println(findClosestPair(points));
    }

    public static long findClosestPair(Point[] xPoints) {
        if (xPoints.length <= 3) {
            // Brute force
            long minValue = Long.MAX_VALUE;
            Point[] yPoints = xPoints.clone();
            Arrays.sort(yPoints, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.y - o2.y;
                }
            });

            for (int i = 0; i < xPoints.length; i++) {
                Point cur = xPoints[i];
                for (int j = i + 1; j < xPoints.length; j++) {
                    Point compare = xPoints[j];
                    long dx = cur.x - compare.x;
                    long dy = cur.y - compare.y;
                    long distance = dx * dx + dy * dy;
                    minValue = Math.min(minValue, distance);
                }
            }
            return minValue;
        } else {
            // Divide
            int line = xPoints.length / 2;
            long leftPair = findClosestPair(Arrays.copyOfRange(xPoints, 0, line));
            long rightPair = findClosestPair(Arrays.copyOfRange(xPoints, line, xPoints.length));
            long minValue = Math.min(leftPair, rightPair);

            // Merge sorted arrays by y
            Point[] leftPoints = Arrays.copyOfRange(xPoints, 0, line);
            Point[] rightPoints = Arrays.copyOfRange(xPoints, line, xPoints.length);
            Point[] mergedY = new Point[xPoints.length];
            int i = 0;
            int j = 0;
            int k = 0;
            while (i < leftPoints.length && j < rightPoints.length) {
                if (leftPoints[i].y < rightPoints[j].y) {
                    mergedY[k++] = leftPoints[i++];
                } else {
                    mergedY[k++] = rightPoints[j++];
                }
            }
            while (i < leftPoints.length) {
                mergedY[k++] = leftPoints[i++];
            }
            while (j < rightPoints.length) {
                mergedY[k++] = rightPoints[j++];
            }

            // Scan
            List<Point> strip = new ArrayList<>();
            for (Point point : mergedY) {
                if (Math.abs(point.x - xPoints[line].x) <= minValue) {
                    strip.add(point);
                }
            }
            for (i = 0; i < strip.size(); i++) {
                Point cur = strip.get(i);
                for (j = i + 1; j < strip.size() && j - i <= 6; j++) {
                    Point compare = strip.get(j);
                    long dx = cur.x - compare.x;
                    long dy = cur.y - compare.y;
                    long distance = dx * dx + dy * dy;
                    minValue = Math.min(minValue, distance);
                }
            }
            return minValue;
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    static class QReader8a {
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");

        private String innerNextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String nextLine = innerNextLine();
                if (nextLine == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(nextLine);
            }
            return true;
        }

        public String nextLine() {
            tokenizer = new StringTokenizer("");
            return innerNextLine();
        }

        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}

