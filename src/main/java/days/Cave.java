package days;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Cave {
    private final Map<String, Set<String>> graph = new HashMap<>();

    public void addNode(String node) {
        graph.putIfAbsent(node, new HashSet<>());
    }

    public void addEdge(String source, String destination) {
        graph.get(source).add(destination);
        graph.get(destination).add(source);
    }

    public Set<String> edgesFrom(String node) {
        return graph.get(node);
    }

    public List<List<String>> paths(String start, String destination, int threshold) {
        List<List<String>> paths = new ArrayList<>();
        findPaths(start, destination, paths, threshold);
        return paths;
    }

    private void findPaths(String start, String destination, List<List<String>> paths, int threshold) {
        Queue<LinkedList<String>> queue = new LinkedList<>();

        LinkedList<String> path = new LinkedList<>();
        path.add(start);
        queue.offer(path);

        while (!queue.isEmpty()) {
            path = queue.poll();
            String last = path.getLast();

            if (last.equals(destination)) {
                paths.add(path);
            }

            Set<String> lastNode = edgesFrom(last);

            for (String node : lastNode
            ) {
                if (isNotVisited(node, path, threshold)) {
                    LinkedList<String> newPath = new LinkedList<>(path);
                    newPath.add(node);
                    queue.offer(newPath);
                }
            }
        }
    }

    private boolean isNotVisited(String node, LinkedList<String> path, int threshold) {
        if (Character.isUpperCase(node.charAt(0)) || !path.contains(node)) return true;

        if (path.contains(node) && (node.equals("start") || node.equals("end"))) return false;

        Map<String, Long> counted = path.stream()
                .filter(p -> Character.isLowerCase(p.charAt(0)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return counted.entrySet().stream().noneMatch(c -> c.getValue() >= threshold);

    }

}
