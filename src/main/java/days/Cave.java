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

    public List<List<String>> paths(String src, String dst, boolean revisitAllowed) {
        List<List<String>> paths = new ArrayList<>();
        findPaths(src, dst, paths, revisitAllowed);
        return paths;
    }

    private void findPaths(String src, String dst, List<List<String>> paths, boolean revisitAllowed) {
        Queue<LinkedList<String>> queue = new LinkedList<>();

        LinkedList<String> path = new LinkedList<>();
        path.add(src);
        queue.offer(path);

        while (!queue.isEmpty()) {
            path = queue.poll();
            String last = path.getLast();

            if (last.equals(dst)) {
                paths.add(path);
            }

            Set<String> lastNode = edgesFrom(last);

            for (String node : lastNode
            ) {
                if (isNotVisited(node, path, revisitAllowed)) {
                    LinkedList<String> newPath = new LinkedList<>(path);
                    newPath.add(node);
                    queue.offer(newPath);
                }
            }
        }
    }

    private boolean isNotVisited(String node, LinkedList<String> path, boolean revisitAllowed) {
        if (Character.isUpperCase(node.charAt(0)) || !path.contains(node)) return true;

        if (revisitAllowed && !(node.equals("start") || node.equals("end"))) {

            Map<String, Long> counted = path.stream()
                    .filter(p -> Character.isLowerCase(p.charAt(0)))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            long othersCount = counted.entrySet().stream()
                    .filter(e -> e.getValue() > 1 && !e.getKey().equals(node))
                    .count();

            Long nodeCount = counted.get(node);

            return othersCount == 0 && nodeCount <= 1;
        }

        return !path.contains(node);
    }

}
