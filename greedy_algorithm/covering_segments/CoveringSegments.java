import java.util.*;

public class CoveringSegments {

    private static int findMinRightPoint(Segment[] segments) {
        if (segments == null) return -1;
        int point = segments[0].end;
        for (int i = 0; i < segments.length; i++) {
            int rightPoint = segments[i].end;
            if (rightPoint < point) {
                point = rightPoint;
            }
        }
        return point;
    }

    private static Segment[] removeSegment(Segment[] segments, int pointX) {
        if (segments.length == 0) {
            return null;
        }
        int count = 0, index = 0;
        for (int i = 0; i < segments.length; i++) {
            if (segments[i].end >= pointX && segments[i].start <= pointX) {
                count++;
            }
        }
        if (segments.length - count <= 0) {
            return null;
        } else {
            Segment[] newSegments = new Segment[segments.length - count];
            for (int k = 0; k < segments.length; k++) {
                if (segments[k].start > pointX) {
                    newSegments[index++] = segments[k];
                }
            }
            return newSegments;
        }
    }

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here
        int count = 0, point;
        int[] points = new int[2 * segments.length];
        // Step 1: find minimum right endpoint X, it belongs to our result,
        point = findMinRightPoint(segments);
        points[count++] = point;
        // Step 2: remove all segments that contains X
        while (segments != null) {
            segments = removeSegment(segments, point);
            point = findMinRightPoint(segments);
            points[count++] = point;
        }
        // repeat Step1 until there is no element left in Segment array

        points = trimArray(points) ;
        return points;
    }

    private static int[] trimArray(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != -1) {
                count++;
            } else {
                break;
            }
        }
        int result[] = new int[count];
        for (int k = 0; k < count; k++) {
            result[k] = array[k];
        }
        return result;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
